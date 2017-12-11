package org.karaffe.compiler.phases;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.Report;

public class ReportPhase extends AbstractTransformer<CompileUnit, CompileUnit> {

    private final CompilerConfig context;

    public ReportPhase(final CompilerConfig context) {
        super(CompileUnit.class, CompileUnit.class);
        this.context = context;
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        PrintStream outputStream = this.context.getOutput();
        List<Report> reports = input.getReports();
        int errorNumCount = String.valueOf(reports.size()).length();
        int maxLineLength = 3;

        StringBuilder output = new StringBuilder();
        String headerPrefixBar = String.format("%0" + maxLineLength + "d", 0).replace("0", "-");
        String headerFormat = headerPrefixBar + " #%" + errorNumCount + "d [%5s] %s in %s";

        int i = 1;
        for (Report report : reports) {
            String headerString = String.format(headerFormat, i, report.getType().name(), report.message(), report.position());
            i++;

        }

        // --+ #1 [ERROR] Type mismatch found in src/HelloWorld.krf
        // 3 | println(1 + "2");
        // __| ^^^^^^
        // __| type mismatch
        // __| found : String
        // __| required: Int
        // __|

        return Optional.of(input);
    }

}
