package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Select extends AbstractNode {

    public Select(final List<Node> names) {
        super(NodeType.SELECT, names);
    }

    public Select(final Node name) {
        this(new ArrayList<>(Arrays.asList(name)));
    }

    public Select(final Node... names) {
        this(new ArrayList<>(Arrays.asList(names)));
    }

    public Select(final Node target, final Node selector) {
        super(NodeType.SELECT, new ArrayList<>(Arrays.asList(target, selector)));
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public List<Node> getNames() {
        return (List<Node>) this.getChildren();
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final List<Node> names = new ArrayList<>();
        for (final Node n : this.getChildren()) {
            if (n.isName()) {
                names.add(n);
            } else {
                final NodeList normalized = n.normalize(context);
                nodes.addAll(normalized.flatten());
                names.add(normalized.lastAssignName());
            }
        }
        final Name name = context.nextName(nodes);
        final Assign ref = new Assign(name, new Select(names));
        nodes.add(ref);
        return new NodeList(nodes);
    }

    @Override
    public String toString() {
        return String.format("(Select %s)", this.toString(" "));
    }

    public String toString(final String delimiter) {
        return String.join(delimiter, this.getNames().stream().map(t -> {
            if (t instanceof Name) {
                final Name name = (Name) t;
                return name.getName();
            }
            return t.toString();
        }).collect(Collectors.toList()));
    }

    @Override
    public String vSource() {
        return this.getNames().stream().map(Node::vSource).reduce((l, r) -> l + "." + r).orElse("?");

    }
}
