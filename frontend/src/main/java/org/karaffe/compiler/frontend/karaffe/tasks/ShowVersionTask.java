package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShowVersionTask implements Task {

    @Override
    public String name() {
        return "show version";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Result run(CompilerContext context) {
        try {
            String version = readVersionResource(ClassLoader.getSystemResourceAsStream("VERSION"));
            String branch = readVersionResource(ClassLoader.getSystemResourceAsStream("BRANCH"));
            String tag = readVersionResource(ClassLoader.getSystemResourceAsStream("TAG"));
            String hash = readVersionResource(ClassLoader.getSystemResourceAsStream("HASH"));
            Platform.stdOut("Karaffe Compiler" + version + branch + tag + hash);
            return Result.SUCCESS;
        } catch (IOException e) {
            return Result.NON_RECOVERABLE;
        }
    }

    @Override
    public boolean isRunnable(CompilerContext context) {
        return context.cmdLineOptions.showVersion;
    }

    @Override
    public boolean changed() {
        return false;
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
}
