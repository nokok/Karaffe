package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.OutputSet;
import org.karaffe.compiler.context.ReportContainer;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.Report;

public class CompileUnit extends AbstractNode implements OutputSet, ReportContainer {

    private final List<Report> reports = new ArrayList<>();

    public CompileUnit(final Node packageDecl, final List<Node> classes) {
        super(NodeType.COMPILEUNIT, mkList(packageDecl, classes));
    }

    private static List<Node> mkList(final Node packageDecl, final List<Node> classes) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(packageDecl);
        nodes.addAll(classes);
        return nodes;
    }

    @Override
    public void addReport(Report report) {
        reports.add(report);
    }

    @Override
    public List<Report> getReports() {
        return new ArrayList<>(this.reports);
    }

    public Node findPackageDef() {
        return this.getChildren().get(0);
    }

    public List<Node> findTypeDefs() {
        return this.getChildren().stream().skip(1).collect(Collectors.toList());
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
