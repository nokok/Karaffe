package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.types.InferResult;

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

    public Select(String... names) {
        this(Stream.of(names).map(Name::new).collect(Collectors.toList()));
        if (names.length == 0) {
            throw new IllegalArgumentException("empty names");
        }
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public List<Node> getNames() {
        return (List<Node>) this.getChildren();
    }

    public Select.Category getCategory(TypeInferenceContext context) {
        return null;
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

    @Override
    public Optional<InferResult> tryTypeInference(TypeInferenceContext context) {
        if (this.getChildren().isEmpty()) {
            return Optional.empty();
        }
        if (this.getChildren().size() == 1) {
            // Name
            Node node = this.getChildren().get(0);
            if (node.isName()) {
                Name name = (Name) node;
                return Optional.of(InferResult.anyTarget(name));
            }
        }
        if (this.getChildren().size() == 2) {
            // Ref
            Node ownerNode = this.getChildren().get(0);
            Node memberNode = this.getChildren().get(1);
            if (ownerNode.isName() && memberNode.isName()) {
                Name owner = (Name) ownerNode;
                Name member = (Name) memberNode;
                Optional<InferResult> ownerTypeOpt = context.getInferredType(owner);
                Optional<InferResult> memberTypeOpt = context.getInferredType(member);
                Optional<InferResult> composedType = ownerTypeOpt.flatMap(ownerType -> memberTypeOpt.flatMap(memberType -> ownerType.compose(memberType, context)));
                return composedType;
            }
            if (ownerNode.isName()) {
                return Optional.empty();
            }
            if (memberNode.isName()) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public enum Category {
        UNKNOWN,
        PACKAGEREF,
        CLASSREF,
        METHODREF
    }
}
