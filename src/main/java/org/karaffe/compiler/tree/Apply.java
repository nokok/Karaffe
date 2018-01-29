package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

public class Apply extends AbstractNode {

    public Apply(final Node target) {
        this(target, new ArrayList<>());
    }

    public Apply(final Node target, final Node... args) {
        this(target, Arrays.asList(args));
    }

    public Apply(final Node target, final List<Node> args) {
        super(NodeType.APPLY, target);
        args.stream().forEach(this::addChild);
    }

    public Node findTarget() {
        return getChildren().get(0);
    }

    public Optional<List<? extends Node>> findArguments() {
        List<? extends Node> children = getChildren();
        int childrenSize = children.size();
        if (childrenSize == 1) {
            return Optional.empty();
        }
        return Optional.ofNullable(children.subList(1, childrenSize));
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String vSource() {
        return String.format("%s(%s)", findTarget().vSource(), findArguments().map(args -> String.join(",", args.stream().map(Node::vSource).collect(Collectors.toList()))).orElse(""));
    }

	@Override
	public NodeList normalize(NormalizeContext context) {
		List<Node> nodes = new ArrayList<>();
		Node originalTarget = this.findTarget();
		Node newTarget;
		if(originalTarget.isName()) {
			newTarget = originalTarget;
		} else {
			NodeList normalizedName = originalTarget.normalize(context);
		    nodes.addAll(normalizedName.flatten());
			newTarget = normalizedName.lastAssignName();
		}
		Optional<List<? extends Node>> argumentsOpt = this.findArguments();
        if (argumentsOpt.isPresent()) {
            List<Node> newArgs = new ArrayList<>();
            List<? extends Node> args = argumentsOpt.get();
            for (Node arg : args) {
                if (arg.isName()) {
                    newArgs.add(arg);
                } else {
                    NodeList normalizedArg = arg.normalize(context);
                    nodes.addAll(normalizedArg.flatten());
                    newArgs.add(normalizedArg.lastAssignName());
                }
            }
            Apply newApply = new Apply(newTarget, newArgs);
            Name res = context.nextName(nodes);
            Assign assign = new Assign(res, newApply);
            nodes.add(assign);
            return new NodeList(nodes);
        }
        Apply newApply = new Apply(newTarget);
        Name res = context.nextName(nodes);
        Assign assign = new Assign(res, newApply);
        nodes.add(assign);
        
		return new NodeList(nodes);
	}
}
