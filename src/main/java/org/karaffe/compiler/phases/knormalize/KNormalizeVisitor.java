package org.karaffe.compiler.phases.knormalize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Assign;
import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.If;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitorAdapter;

public class KNormalizeVisitor extends KaraffeTreeVisitorAdapter {

    private final KNormalRule k = new KNormalRule();
    private final Node target;
    private final List<Node> nodeList = new ArrayList<>();

    public KNormalizeVisitor(Node target) {
        this.target = Objects.requireNonNull(target);
    }

    public Node normalize() {
        this.target.accept(this);
        if (this.nodeList.isEmpty()) {
            return this.target;
        }
        if (this.nodeList.size() == 1) {
            return this.nodeList.get(0);
        }
        return new NodeList(new NodeList(this.nodeList).flatten());
    }

    @Override
    public void visit(Apply node) {
        this.nodeList.addAll(this.k.normalize(node));
    }

    @Override
    public void visit(ValDef node) {
        this.nodeList.add(node);
    }

    @Override
    public void visit(VarDef node) {
        this.nodeList.add(node);
    }

    @Override
    public void visit(If node) {
        Node condExpr = this.k.normalize(node.findCondExpr());
        Node thenBlock = this.k.normalize(node.findThenBlock());
        Optional<Node> elseBlockOpt = node.findElseBlock().map(this.k::normalize);
        If ifBlock = elseBlockOpt
                .map(elseBlock -> new If(condExpr, thenBlock, elseBlock))
                .orElseGet(() -> new If(condExpr, thenBlock));
        this.nodeList.add(ifBlock);
    }

    @Override
    public void visit(MethodDef node) {
        Node modifiers = node.findModifierNode();
        Node name = node.findNameNode();
        Node parameters = node.findParameterNode();
        Node returnType = node.findReturnTypeNode();
        Node block = this.k.normalize(node.findMethodBodyNode());
        this.nodeList.add(new MethodDef(modifiers, name, parameters, returnType, block));
    }

    @Override
    public void visit(Assign node) {
        Node assignTarget = node.findTarget();
        NodeList initializer = this.k.normalize(node.findExpr());
        this.nodeList.addAll(initializer.flatten());
        this.nodeList.add(new Assign(assignTarget, initializer.lastAssignName()));
    }

    @Override
    public void visit(Block node) {
        this.nodeList.add(this.k.normalize(node));
    }

    @Override
    public void visit(CompileUnit node) {
        Node packageDef = node.findPackageDef();
        List<Node> types = new ArrayList<>();
        for (Node typeDef : node.findTypeDefs()) {
            types.addAll(this.k.normalize(typeDef).flatten());
        }
        this.nodeList.add(new CompileUnit(packageDef, types));
    }

}