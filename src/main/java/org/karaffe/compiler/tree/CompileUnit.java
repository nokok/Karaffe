package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.OutputSet;
import org.karaffe.compiler.context.ReportContainer;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.util.Report;

public class CompileUnit extends AbstractNode implements OutputSet, ReportContainer {

    private static List<Node> mkList(final Node packageDecl, final List<Node> classes) {
        final List<Node> nodes = new ArrayList<>();
        nodes.add(packageDecl);
        nodes.addAll(classes);
        return nodes;
    }

    private final List<Report> reports = new ArrayList<>();

    public CompileUnit(final Node packageDecl, final List<Node> classes) {
        super(NodeType.COMPILEUNIT, mkList(packageDecl, classes));
    }

    @Override
    public void addReport(final Report report) {
        this.reports.add(report);
    }

    public Node findPackageDef() {
        return this.getChildren().get(0);
    }

    public List<Node> findTypeDefs() {
        return this.getChildren().stream().skip(1).collect(Collectors.toList());
    }

    @Override
    public List<Report> getReports() {
        return new ArrayList<>(this.reports);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final Node packageDef = this.findPackageDef();
        final List<Node> types = new ArrayList<>();
        for (final Node typeDef : this.findTypeDefs()) {
            types.addAll(typeDef.normalize(context).flatten());
        }
        return new NodeList(new CompileUnit(packageDef, types));
    }

    @Override
    public String vSource() {
        return String.format("%s%s", this.findPackageDef().vSource(), String.join("", this.findTypeDefs().stream().map(Node::vSource).collect(Collectors.toList())));
    }
}
