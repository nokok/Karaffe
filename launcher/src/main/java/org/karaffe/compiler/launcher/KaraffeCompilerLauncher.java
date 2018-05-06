package org.karaffe.compiler.launcher;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.karaffe.compiler.base.util.DiagnosticInfo;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.ast.CompilationUnit;
import org.karaffe.compiler.frontend.karaffe.transformer.AbstractTransformer;
import org.karaffe.compiler.frontend.karaffe.transformer.PhaseException;
import org.karaffe.compiler.frontend.karaffe.phase.Phases;
import org.karaffe.compiler.frontend.karaffe.transformer.TransformerBuilder;
import org.karaffe.compiler.frontend.karaffe.transformer.util.PhaseResult;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.ir.Tree;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void run(String[] args) throws Exception {
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

        Phases phases = new Phases(options);

        Map<String, Tree> mirMap = new HashMap<>();

        for (File file : files) {
            LOGGER.trace("File : {}", file);
            try {
                PhaseResult<Tree> mirResult = phases.run(file);
                mirResult.ifPresent(mir -> mirMap.put(file.getAbsolutePath(), mir));
            } catch (PhaseException e) {
                e.printStackTrace(Platform.getStdErr());
                Platform.stdErr("PhaseException");
                continue;
            }
        }

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
