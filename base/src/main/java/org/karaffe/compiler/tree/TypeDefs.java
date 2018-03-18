package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;

public class TypeDefs extends AbstractNodes {
    public TypeDefs() {
        this(new ArrayList<>());
    }

    public TypeDefs(final List<Node> defs) {
        super(NodeType.S_TYPEDEF, defs);
    }

    public TypeDefs(final TypeDef def) {
        this(new ArrayList<>(Arrays.asList(def)));
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.normalize(context);
    }

    @Override
    public String vSource() {
        return String.join("", this.getChildren().stream().map(Node::vSource).collect(Collectors.toList()));
    }
}
