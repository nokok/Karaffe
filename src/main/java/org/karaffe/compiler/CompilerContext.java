package org.karaffe.compiler;

import org.karaffe.compiler.util.KaraffeSource;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CompilerContext {
    private String[] rawArgs = new String[0];
    private Set<String> flags = new HashSet<>();
    private List<KaraffeSource> sources = new ArrayList<>();
    private List<String> outputs = new ArrayList<>();
    private Map<Path, byte[]> outputFiles = new HashMap<>();

    public String[] getRawArgs() {
        return rawArgs;
    }

    public void parseRawArgs(String[] rawArgs) {
        this.rawArgs = Objects.requireNonNull(rawArgs);
        for (String arg : this.rawArgs) {
            if (arg.equals("--dry-run")) {
                if (!this.flags.add("dry-run")) {
                    this.outputs.add("Duplicate flag: dry-run");
                }
            }
        }
    }

    public void addOutputText(String line) {
        this.outputs.add(Objects.requireNonNull(line));
    }

    public String getOutputText() {
        return String.join("\n", outputs);
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
