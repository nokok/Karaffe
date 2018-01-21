package org.karaffe.compiler.phases.knormalize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Assign;
import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.If;
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.New;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.Return;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.util.NameGen;

public class KNormalRule {
    private final NameGen generator = new NameGen("kn_");

    public List<Node> normalize(Apply node) {
        List<Node> nodes = new ArrayList<>();
        Node originalTarget = node.findTarget();
        Node newTarget;
        if (originalTarget.isName()) {
            newTarget = originalTarget;
        } else {
            NodeList normalized = this.normalize(originalTarget);
            nodes.add(normalized);
            newTarget = normalized.lastAssignName();
        }

        Optional<List<? extends Node>> argumentsOpt = node.findArguments();
        if (argumentsOpt.isPresent()) {
            List<Node> newArgs = new ArrayList<>();
            List<? extends Node> args = argumentsOpt.get();
            for (Node arg : args) {
                if (arg.isName()) {
                    newArgs.add(arg);
                } else {
                    NodeList normalizedArg = this.normalize(arg);
                    nodes.add(normalizedArg);
                    newArgs.add(normalizedArg.lastAssignName());
                }
            }
            Apply newApply = new Apply(newTarget, newArgs);
            Name res = this.generator.genName();
            Assign assign = new Assign(res, newApply);
            nodes.add(assign);
            return nodes;
        }
        Apply newApply = new Apply(newTarget);
        Name res = this.generator.genName();
        Assign assign = new Assign(res, newApply);
        nodes.add(assign);
        return nodes;
    }

    public List<Node> normalize(Literal.IntLiteral node) {
        Name name = this.generator.genName();
        return new ArrayList<>(Arrays.asList(new Assign(name, node)));
    }

    public List<Node> normalize(Select node) {
        List<Node> nodes = new ArrayList<>();
        List<Node> names = new ArrayList<>();
        for (Node n : node.getChildren()) {
            if (n.isName()) {
                names.add(n);
            } else {
                NodeList normalized = this.normalize(n);
                nodes.add(normalized);
                names.add(normalized.lastAssignName());
            }
        }
        Name name = this.generator.genName();
        Assign ref = new Assign(name, new Select(names));
        nodes.add(ref);
        return nodes;
    }

    public List<Node> normalize(Literal.ThisLiteral node) {
        Name name = this.generator.genName();
        return new ArrayList<>(Arrays.asList(new Assign(name, node)));
    }

    public List<Node> normalize(Assign node) {
        List<Node> nodes = new ArrayList<>();
        NodeList normalizedExpr = this.normalize(node.findExpr());
        nodes.add(normalizedExpr);
        nodes.add(new Assign(node.findTarget(), normalizedExpr.lastAssignName()));
        return nodes;
    }

    public Block normalize(Block node) {
        List<Node> nodes = new ArrayList<>();
        for (Node n : node.getChildren()) {
            nodes.add(this.normalize(n));
        }
        return new Block(nodes);
    }

    public VarDef normalize(VarDef node) {
        Node modifiers = node.findModifierNode();
        Node name = node.findNameNode();
        Node type = node.findTypeNameNode();
        Optional<Node> initializerExprOpt = node.findInitializerExprNode().map(expr -> this.normalize(expr));
        VarDef def = initializerExprOpt.map(initializerExpr -> new VarDef(modifiers, name, type, initializerExpr)).orElseGet(() -> new VarDef(modifiers, name, type));
        return def;
    }

    public ValDef normalize(ValDef node) {
        Node modifiers = node.findModifierNode();
        Node name = node.findNameNode();
        Node type = node.findTypeNameNode();
        Optional<Node> initializerExprOpt = node.findInitializerExprNode().map(expr -> this.normalize(expr));
        ValDef def = initializerExprOpt.map(initializerExpr -> new ValDef(modifiers, name, type, initializerExpr)).orElseGet(() -> new ValDef(modifiers, name, type));
        return def;
    }

    public List<Node> normalize(If node) {
        List<Node> nodes = new ArrayList<>();
        NodeList normalizedCond = this.normalize(node.findCondExpr());
        NodeList normalizedThen = this.normalize(node.findThenBlock());
        Optional<NodeList> normalizedElseOpt = node.findElseBlock().map(elseBlock -> this.normalize(elseBlock));
        nodes.add(normalizedCond);
        Node condNode = normalizedCond.lastAssignName();
        If ifNode = normalizedElseOpt.map(elseBlock -> new If(condNode, normalizedThen, elseBlock)).orElseGet(() -> new If(condNode, normalizedThen));
        nodes.add(ifNode);
        return nodes;
    }

    public List<Node> normalize(Return node) {
        List<Node> nodes = new ArrayList<>();
        NodeList normalizedExpr = this.normalize(node.findExpr());
        nodes.add(normalizedExpr);
        nodes.add(new Return(normalizedExpr.lastAssignName()));
        return nodes;
    }

    public List<Node> normalize(New node) {
        Name name = this.generator.genName();
        return new ArrayList<>(Arrays.asList(new Assign(name, node)));
    }

    public ClassDef normalize(ClassDef node) {
        Node nameNode = node.findNameNode();
        Node superClassNode = node.findSuperClassNameNode();
        Node normalizedBlock = this.normalize(node.findClassBodyNode());
        return new ClassDef(nameNode, superClassNode, normalizedBlock);
    }

    public MethodDef normalize(MethodDef node) {
        Node modifiers = node.findModifierNode();
        Node name = node.findNameNode();
        Node parameters = node.findParameterNode();
        Node returnType = node.findReturnTypeNode();
        Node block = this.normalize(node.findMethodBodyNode());
        return new MethodDef(modifiers, name, parameters, returnType, block);
    }

    public NodeList normalize(Node node) {
        List<Node> nodes = new ArrayList<>();
        if (node instanceof Apply) {
            nodes.addAll(this.normalize((Apply) node));
        } else if (node instanceof Select) {
            nodes.addAll(this.normalize((Select) node));
        } else if (node instanceof Literal.IntLiteral) {
            nodes.addAll(this.normalize((Literal.IntLiteral) node));
        } else if (node instanceof Literal.ThisLiteral) {
            nodes.addAll(this.normalize((Literal.ThisLiteral) node));
        } else if (node instanceof Block) {
            nodes.add(this.normalize((Block) node));
        } else if (node instanceof Assign) {
            nodes.addAll(this.normalize((Assign) node));
        } else if (node instanceof VarDef) {
            nodes.add(this.normalize((VarDef) node));
        } else if (node instanceof If) {
            nodes.addAll(this.normalize((If) node));
        } else if (node instanceof Return) {
            nodes.addAll(this.normalize((Return) node));
        } else if (node instanceof New) {
            nodes.addAll(this.normalize((New) node));
        } else if (node instanceof ClassDef) {
            nodes.add(this.normalize((ClassDef) node));
        } else if (node instanceof MethodDef) {
            nodes.add(this.normalize((MethodDef) node));
        } else {
            throw new UnsupportedOperationException(node.getClass().getName());
        }
        return new NodeList(new NodeList(nodes).flatten());
    }
}
