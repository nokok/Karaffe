package karaffe.compiler;

import org.objectweb.asm.tree.AnnotationNode;

class Annotation implements NodeGeneratable<AnnotationNode> {

    private final Identifier identifier;
    private final Position pos;

    Annotation(Identifier id, int idleft, int idright) {
        this.identifier = id;
        this.pos = new Position(idleft, idright);
    }

    @Override
    public AnnotationNode toNode() {
        AnnotationNode node = new AnnotationNode(identifier.id());
        return node;
    }

    @Override
    public String toString() {
        return "(annotation " + identifier.toString() + pos + ")";
    }

}
