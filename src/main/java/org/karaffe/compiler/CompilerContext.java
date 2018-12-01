package org.karaffe.compiler;

import org.karaffe.compiler.util.KaraffeSource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class CompilerContext {
    private String[] rawArgs = new String[0];
    private Set<String> flags = new HashSet<>();
    private List<KaraffeSource> sources = new ArrayList<>();
    private List<String> outputs = new ArrayList<>();
    private Map<Path, byte[]> outputFiles = new HashMap<>();

    public void parseRawArgs(String[] rawArgs) {
        this.rawArgs = Objects.requireNonNull(rawArgs);
        Stack<String> argStack = new Stack<>();
        argStack.addAll(Arrays.asList(rawArgs));
        while (!argStack.empty()) {
            String arg = argStack.pop();
            if (arg.endsWith(".krf")) {
                try {
                    this.sources.add(KaraffeSource.fromPath(Paths.get(arg)));
                } catch (IOException e) {
                    throw new UncheckedIOException(Paths.get(arg).toAbsolutePath().toString(), e);
                }
                continue;
            }

            boolean added = false;
            switch (arg) {
            case "--dry-run":
                added |= this.flags.add(arg);
                added |= this.flags.add(arg);
                break;
            default:
                this.addOutputText("Unrecognized option : " + arg);
                added = true;
            }
            if (!added) {
                this.addOutputText("Duplicated flag : " + arg);
            }
        }
    }

    public void addOutputText(String line) {
        this.outputs.add(Objects.requireNonNull(line));
    }

    public String getOutputText() {
        return String.join("\n", outputs);
    }

    public boolean hasOutputText() {
        return !hasNoOutputText();
    }

    public boolean hasNoOutputText() {
        return this.outputs.isEmpty();
    }

    public void addSource(KaraffeSource source) {
        this.sources.add(Objects.requireNonNull(source));
    }

    public List<KaraffeSource> getSources() {
        return this.sources;
    }

    public void addOutputFile(Path target, byte[] content) {
        this.outputFiles.put(Objects.requireNonNull(target), Objects.requireNonNull(content));
    }

    public Map<Path, byte[]> getOutputFiles() {
        return outputFiles;
    }

    public boolean requireShowUsage() {
        return this.rawArgs.length == 0 && this.getSources().isEmpty();
    }

    public boolean hasFlag(String flagName) {
        return this.flags.contains(flagName);
    }
}
