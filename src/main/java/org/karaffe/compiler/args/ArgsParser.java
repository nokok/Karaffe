package org.karaffe.compiler.args;

import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.util.KaraffeSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ArgsParser {

    private final List<Report> reports = new ArrayList<>();

    private final Map<String, Flag> supportedFlags = new HashMap<>();

    public ArgsParser() {
        for (Flag flag : Flag.values()) {
            flag.getShortName().ifPresent(n -> supportedFlags.put(n, flag));
            flag.getFullName().ifPresent(n -> supportedFlags.put(n, flag));
        }
    }

    public Optional<Options> parse(String[] options) {
        Objects.requireNonNull(options);
        if (options.length == 0) {
            return Optional.of(new Options());
        }
        Set<Flag> recognizedFlags = new HashSet<>();
        Set<KaraffeSource> sourceSet = new HashSet<>();
        for (int i = 0; i < options.length; i++) {
            String c = options[i];
            if (c.startsWith("-")) {
                boolean containsKey = supportedFlags.containsKey(c);
                if (containsKey) {
                    if (!recognizedFlags.add(supportedFlags.get(c))) {
                        this.reports.add(Report.newErrorReport("Duplicated flag : " + c).build());
                    }
                } else {
                    this.reports.add(Report.newErrorReport("Unrecognized option : " + c).build());
                }
            } else {
                Path filePath = Paths.get(c);
                if (Files.exists(filePath)) {
                    try {
                        sourceSet.add(KaraffeSource.fromPath(filePath));
                    } catch (IOException e) {
                        this.reports.add(Report.newErrorReport(e.getMessage()).build());
                    }
                } else {
                    this.reports.add(Report.newErrorReport("File not found : " + c).build());
                }
            }
        }
        if (this.reports.isEmpty()) {
            return Optional.of(new Options(recognizedFlags, sourceSet));
        } else {
            return Optional.empty();
        }

    }

    public List<Report> getReports() {
        return reports;
    }
}
