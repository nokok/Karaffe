package karaffe.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

class FieldDefBlock implements Statement {

    private final List<Annotation> annotations;
    private final List<Modifier> modifiers;
    private final List<FieldDef> fieldDefs;
    private String path;

    FieldDefBlock(List<Annotation> a, List<Modifier> m, List<FieldDef> l) {
        this.modifiers = m;
        this.fieldDefs = l;
        this.annotations = a;
    }

    public void setPath(String path) {
        if ( this.path != null ) {
            throw new IllegalStateException();
        }
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Class<?> inferredType() {
        return Object.class;
    }

    public List<FieldNode> toNodes() {
        int flag = modifiers
            .stream()
            .map(m -> m.flag())
            .reduce((l, r) -> l + r)
            .orElse(Opcodes.ACC_PUBLIC);

        List<FieldNode> nodes = new ArrayList<>();
        for ( FieldDef def : fieldDefs ) {
            FieldNode node = def.toNode();
            node.access = flag;
            nodes.add(node);
        }
        return Collections.unmodifiableList(nodes);
    }

    @Override
    public String toString() {
        return "(field-def-block: " + String.join(" ", modifiers.toString(), fieldDefs.toString()) + ")";
    }

}
