package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

public class Parameters extends AbstractNodes {

    public Parameters() {
        this(new ArrayList<>(0));
    }

    public Parameters(final Node valDef) {
        this(new ArrayList<>(Arrays.asList(valDef)));
    }

    public Parameters(final List<Node> valDefs) {
        super(NodeType.S_PARAM, valDefs);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public List<Node> findParameters() {
        return getChildren().stream().collect(Collectors.toList());
    }

    @Override
    public String vSource() {
        return String.format("(%s)", String.join(",", getChildren().stream().map(Node::vSource).collect(Collectors.toList())));
    }

	@Override
	public NodeList normalize(NormalizeContext context) {
		return this.toNodeList();
	}
}
