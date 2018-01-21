package org.karaffe.compiler.phases;

import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.Report;
import org.karaffe.compiler.util.ReportFormatter;
import org.karaffe.compiler.util.Traceable;

public class ReportPhase extends AbstractCompileUnitTransformer implements Traceable {

    private final CompilerConfig context;

    public ReportPhase(final CompilerConfig context) {
        this.context = context;
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        List<Report> reports = input.getReports();
        if (reports.isEmpty()) {
            return Optional.of(input);
        }
        info(reports.toString());
        ReportFormatter formatter = new ReportFormatter();
        String errorMsg = formatter.format(reports);
        System.out.println(errorMsg);
        return Optional.of(input);
    }

}
