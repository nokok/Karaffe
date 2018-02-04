package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.types.InferResult;

public class Assign extends AbstractNode {

    public Assign(final Node target, final Node expr) {
        super(NodeType.ASSIGN, target, expr);
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Node findExpr() {
        return this.getChildren().get(1);
    }

    public Name findTarget() {
        return (Name) this.getChildren().get(0);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final NodeList normalizedExpr = this.findExpr().normalize(context);
        nodes.addAll(normalizedExpr.flatten());
        nodes.add(new Assign(this.findTarget(), normalizedExpr.lastAssignName()));
        return new NodeList(nodes);

    }

    @Override
    public String vSource() {
        return String.format("%s = %s;", this.findTarget().vSource(), this.findExpr().vSource());
    }

    @Override
    public Optional<InferResult> tryTypeInference(TypeInferenceContext context) {
        Name name = this.findTarget();
        if (!context.hasAlreadyDefinedName(name)) {
            // Report symbol notfound
            System.err.println("Symbol Not Found. : " + name.getText());
            throw new IllegalStateException(name.getText());
        }

        Optional<InferResult> targetNameTypeInferResult = context.getInferredType(name);
        Optional<InferResult> result = targetNameTypeInferResult.<Optional<InferResult>>flatMap(targetType -> {
            return Optional.empty();
        }).orElseGet(() -> {
            Optional<InferResult> inferOpt = findExpr().tryTypeInference(context);
            InferResult inferResult = inferOpt.map(i -> i).orElse(InferResult.noHint());
            context.updateType(name, inferResult);
            return inferOpt;
        });
        return result;
    }
}
