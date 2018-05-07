package org.karaffe.compiler.launcher;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.DiagnosticInfo;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.ast.CompilationUnit;
import org.karaffe.compiler.frontend.karaffe.tasks.CheckCompilerPrecondition;
import org.karaffe.compiler.frontend.karaffe.tasks.ConfigureLogLevelTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ParseCommandLineOptionsTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowUsageTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ShowVersionTask;
import org.karaffe.compiler.frontend.karaffe.tasks.Task;
import org.karaffe.compiler.frontend.karaffe.tasks.TaskRunner;
import org.karaffe.compiler.frontend.karaffe.transformer.AbstractTransformer;
import org.karaffe.compiler.frontend.karaffe.transformer.TransformerBuilder;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;

public class KaraffeCompilerLauncher {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KaraffeCompilerLauncher.class);

    public KaraffeCompilerLauncher(InputStream stdIn, PrintStream stdOut, PrintStream stdErr) {
        Platform.setStdIn(stdIn);
        Platform.setStdOut(stdOut);
        Platform.setStdErr(stdErr);
    }

    public KaraffeCompilerLauncher() {

    }

    public static void main(String[] args) throws Exception {
        KaraffeCompilerLauncher launcher = new KaraffeCompilerLauncher();
        launcher.run(args);
    }

    public synchronized void run(String[] args) throws Exception {
        TaskRunner taskRunner = TaskRunner.defaultTaskRunner();
        ServiceLoader<Task> taskServiceLoader = ServiceLoader.load(Task.class, Thread.currentThread().getContextClassLoader());
        taskServiceLoader.forEach(taskRunner::standby);

        CompilerContext context = CompilerContext.getCurrent();
        context.reset();
        context.setArgs(args);

        taskRunner.exec(ParseCommandLineOptionsTask::new);
        taskRunner.exec(ConfigureLogLevelTask::new);

        LOGGER.debug("Karaffe Compiler is running on up to {} thread(s)", Runtime.getRuntime().availableProcessors());

        taskRunner.standBy(ShowUsageTask::new);
        taskRunner.standBy(ShowVersionTask::new);

        taskRunner.runAll();

        if (true) {
            return;
        }

        CheckCompilerPrecondition preConditionChecker = new CheckCompilerPrecondition();


        Options options = new Options();
        ParserProperties properties = ParserProperties.defaults().withUsageWidth(999);
        CmdLineParser cmdLineParser = new CmdLineParser(options, properties);
        if (args.length == 0) {
            usage(cmdLineParser);
        }

        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            Platform.stdOut("Error: " + e.getLocalizedMessage());
            usage(cmdLineParser);
        }

        if (options.showHelp) {
            usage(cmdLineParser);
            return;
        }

        List<String> unrecognizedArguments = options.arguments
                .stream()
                .filter(arg -> !Files.exists(Paths.get(arg)))
                .collect(Collectors.toList());

        if (!unrecognizedArguments.isEmpty()) {
            Platform.stdOut("Source file " + unrecognizedArguments + " could not be found");
            usage(cmdLineParser);
            return;
        }

        final Level logLevel;
        if (options.isTraceLog) {
            logLevel = Level.TRACE;
        } else if (options.isDebugLog) {
            logLevel = Level.DEBUG;
        } else if (options.isInfoLog) {
            logLevel = Level.INFO;
        } else {
            logLevel = Level.OFF;
        }

        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(logLevel);

        LOGGER.info("Logger is activated : {}", logLevel);

        if (options.showVersion) {
            String version = readVersionResource(ClassLoader.getSystemResourceAsStream("VERSION"));
            String branch = readVersionResource(ClassLoader.getSystemResourceAsStream("BRANCH"));
            String tag = readVersionResource(ClassLoader.getSystemResourceAsStream("TAG"));
            String hash = readVersionResource(ClassLoader.getSystemResourceAsStream("HASH"));
            Platform.stdOut("Karaffe Compiler" + version + branch + tag + hash);
            return;
        }

        if (options.showDiag) {
            Platform.stdOut(DiagnosticInfo.INSTANCE.toString());
        }

        boolean hasStopPhaseOption = options.stopPhaseName != null;
        Set<AbstractTransformer> transformers;
        boolean showTreeOnExit = options.showLastTree;
        if (hasStopPhaseOption) {
            transformers = new TransformerBuilder().buildTransformers(options.stopPhaseName);
            showTreeOnExit = true;
        } else {
            transformers = new TransformerBuilder().getTransformers();
        }

        LOGGER.debug("Executable Transformers: {}", transformers);

        final boolean isShowPhases = options.showPhases;
        if (isShowPhases) {
            transformers.stream().map(AbstractTransformer::getTransformerName).forEach(Platform::stdOut);
        }

        List<File> files = options.arguments.stream().map(File::new).filter(File::exists).collect(Collectors.toList());

        LOGGER.debug("Process files: {}", files);

        CompilationUnit compilationUnit = new CompilationUnit();

        final boolean isPrintTreeEveryPhase = options.printTree;

        if (isPrintTreeEveryPhase) {
            Platform.stdOut("===");
            Platform.stdOut(compilationUnit);
        }

        CompilationUnit cu = compilationUnit;
        String lastString = cu.toString();
        for (AbstractTransformer transformer : transformers) {
            cu = transformer.transform(cu);
            if (isPrintTreeEveryPhase) {
                Platform.stdOut("=== After : " + transformer.getTransformerName() + " ===");
                if (lastString.equals(cu.toString())) {
                    Platform.stdOut("No change.");
                } else {
                    Platform.stdOut(cu);
                }
            }
            lastString = cu.toString();
        }
        if (showTreeOnExit) {
            Platform.stdOut(cu);
        }
        LOGGER.info("Shutting down");
    }

    private String readVersionResource(InputStream stream) throws IOException {
        String r;
        if (stream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                r = " " + reader.readLine();
            } finally {
                stream.close();
            }
        } else {
            r = "";
        }
        return r;
    }

    private void usage(CmdLineParser parser) {
        Platform.stdOut("Usage:");
        Platform.stdOut(" krfc <options> <sources>");
        Platform.stdOut();
        Platform.stdOut("Available options are:");
        parser.printUsage(Platform.getStdOut());
    }
}
