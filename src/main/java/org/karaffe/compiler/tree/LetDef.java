package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class LetDef extends AbstractNode implements NamedDef {

    public LetDef(final Node modifiers, final Node name, final Node type) {
        super(NodeType.DEFVAL, new ArrayList<>(Arrays.asList(modifiers, name, type)));
    }

    public LetDef(final Node modifiers, final Node name, final Node type, final Node initializer) {
        super(NodeType.DEFVAL, new ArrayList<>(Arrays.asList(modifiers, name, type, initializer)));
    }

    public Optional<Node> findInitializerExprNode() {
        if (this.getChildren().size() == 3) {
            return Optional.empty();
        }
        return Optional.of(this.getChildren().get(3));
    }

    public Node findModifierNode() {
        return this.getChildren().get(0);
    }

    public String getName() {
        return ((Name) this.getChildren().get(1)).getText();
    }

    public boolean has(final Class<? extends ModifierToken> modifier) {
        return ((Modifiers) this.getChildren().get(0)).stream().filter(t -> t.getClass().equals(modifier)).count() != 0;
    }

    @Override
    public NodeList normalize(NormalizeContext context) {
        List<Node> nodes = new ArrayList<>();
        NodeList normalizedInitialzer = this.findInitializer().map(initializer -> initializer.normalize(context)).orElseGet(NodeList::new);
        nodes.addAll(normalizedInitialzer.flatten());
        if (normalizedInitialzer.isEmpty()) {
            nodes.add(new LetDef(this.findModifierNode(), this.findNameNode(), this.findDefinitionTypeName().orElseGet(Empty::new)));
        } else {
            nodes.add(new LetDef(this.findModifierNode(), this.findNameNode(), this.findDefinitionTypeName().orElseGet(Empty::new), normalizedInitialzer.lastAssignName()));
        }
        return new NodeList(nodes);
    }

    @Override
    public String vSource() {
        return String.format("final %s %s%s;", this.findDefinitionTypeName(), this.findNameNode().vSource(), this.findInitializerExprNode().map(Node::vSource).orElse(""));
    }

}
