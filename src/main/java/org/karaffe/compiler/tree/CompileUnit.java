package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.context.OutputSet;
import org.karaffe.compiler.context.ReportContainer;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.util.Report;

public class CompileUnit extends AbstractNode implements OutputSet, ReportContainer {

    private final List<Report> reports = new ArrayList<>();

    public CompileUnit(final PackageDef packageDecl, final TypeDefs classes) {
        super(NodeType.COMPILEUNIT, new ArrayList<>(Arrays.asList(packageDecl, classes)));
    }

    @Override
    public void addReport(Report report) {
        reports.add(report);
    }

    @Override
    public List<Report> getReports() {
        return new ArrayList<>(this.reports);
    }
}
