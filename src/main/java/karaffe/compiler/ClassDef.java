package karaffe.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import static java.util.stream.Collectors.toList;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class ClassDef implements Statement, NodeGeneratable<ClassNode> {

    private final List<Annotation> annotationList;
    private final List<Modifier> classModifierList;
    private final List<Statement> c;
    private final Identifier id;
    private String path;

    ClassDef(List<Annotation> a, List<Modifier> m, Identifier id, List<Statement> c) {
        this.annotationList = a;
        this.classModifierList = m;
        this.id = id;
        this.c = c;
        Context.INSTANCE.add(this);
    }

    @Override
    public Class<?> inferredType() {
        return Void.class;
    }

    public void setPath(String path) {
        this.path = Objects.requireNonNull(path) + (path.isEmpty() ? "" : ".") + id.id();
    }

    public List<MethodDef> methodDefs() {
        return c.stream().filter(stmt -> stmt instanceof MethodDef).map(MethodDef.class::cast).collect(toList());
    }

    @Override
    public String toString() {
        return "(class-def:" + String.join(" ", annotationList.toString(), classModifierList.toString(), c.toString(), path) + ")";
    }

    @Override
    public ClassNode toNode() {
        ClassNode classNode = new ClassNode();
        classNode.visibleAnnotations = annotationList.stream().map(a -> a.toNode()).collect(toList());
        classNode.access = classModifierList.stream().map(c -> c.flag()).reduce((i1, i2) -> i1 + i2).orElse(Opcodes.ACC_PUBLIC);
        classNode.version = Opcodes.V1_8;
        classNode.name = id.id();
        classNode.fields = c.stream().filter(c -> c instanceof FieldDef)
            .map(FieldDef.class::cast)
            .map(f -> f.toNode())
            .collect(toList());
        classNode.fields.addAll(c.stream().filter(c -> c instanceof FieldDefBlock)
            .map(FieldDefBlock.class::cast)
            .map(f -> f.toNodes())
            .reduce((list1, list2) -> {
                List<FieldNode> ret = new ArrayList<>(list1.size() + list2.size());
                ret.addAll(list1);
                ret.addAll(list2);
                return ret;
            }).orElseGet(ArrayList::new));
        classNode.methods = c.stream().filter(c -> c instanceof MethodDef)
            .map(MethodDef.class::cast)
            .map(m -> m.toNode())
            .collect(toList());
        classNode.superName = Type.getInternalName(Object.class);
        classNode.innerClasses = Collections.emptyList();
        return classNode;
    }

    public String name() {
        return id.id();
    }

    public String getPath() {
        return path;
    }
}
