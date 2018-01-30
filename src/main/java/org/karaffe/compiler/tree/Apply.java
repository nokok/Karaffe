package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Apply extends AbstractNode {

    public Apply(final Node target) {
        this(target, new ArrayList<>());
    }

    public Apply(final Node target, final List<Node> args) {
        super(NodeType.APPLY, target);
        args.stream().forEach(this::addChild);
    }

    public Apply(final Node target, final Node... args) {
        this(target, Arrays.asList(args));
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Optional<List<? extends Node>> findArguments() {
        final List<? extends Node> children = this.getChildren();
        final int childrenSize = children.size();
        if (childrenSize == 1) {
            return Optional.empty();
        }
        return Optional.ofNullable(children.subList(1, childrenSize));
    }

    public Node findTarget() {
        return this.getChildren().get(0);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final Node originalTarget = this.findTarget();
        Node newTarget;
        if (originalTarget.isName()) {
            newTarget = originalTarget;
        } else {
            final NodeList normalizedName = originalTarget.normalize(context);
            nodes.addAll(normalizedName.flatten());
            newTarget = normalizedName.lastAssignName();
        }
        final Optional<List<? extends Node>> argumentsOpt = this.findArguments();
        if (argumentsOpt.isPresent()) {
            final List<Node> newArgs = new ArrayList<>();
            final List<? extends Node> args = argumentsOpt.get();
            for (final Node arg : args) {
                if (arg.isName()) {
                    newArgs.add(arg);
                } else {
                    final NodeList normalizedArg = arg.normalize(context);
                    nodes.addAll(normalizedArg.flatten());
                    newArgs.add(normalizedArg.lastAssignName());
                }
            }
            final Apply newApply = new Apply(newTarget, newArgs);
            final Name res = context.nextName(nodes);
            final Assign assign = new Assign(res, newApply);
            nodes.add(assign);
            return new NodeList(nodes);
        }
        final Apply newApply = new Apply(newTarget);
        final Name res = context.nextName(nodes);
        final Assign assign = new Assign(res, newApply);
        nodes.add(assign);

        return new NodeList(nodes);
    }

    @Override
    public String vSource() {
        return String.format("%s(%s)", this.findTarget().vSource(), this.findArguments().map(args -> String.join(",", args.stream().map(Node::vSource).collect(Collectors.toList()))).orElse(""));
    }
}
