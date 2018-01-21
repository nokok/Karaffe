package org.karaffe.compiler.phases;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.Report;
import org.karaffe.compiler.util.ReportFormatter;

public class ReportPhase extends AbstractCompileUnitTransformer {

    private final CompilerConfig context;

    public ReportPhase(final CompilerConfig context) {
        this.context = context;
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        PrintStream outputStream = this.context.getOutput();
        List<Report> reports = input.getReports();
        ReportFormatter formatter = new ReportFormatter();
        String errorMsg = formatter.format(reports);
        System.out.println(errorMsg);
        return Optional.of(input);
    }

}
