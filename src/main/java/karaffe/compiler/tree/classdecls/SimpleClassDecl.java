/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.classdecls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import karaffe.compiler.KCompiler;
import karaffe.compiler.phase.ToDo;
import karaffe.compiler.tree.ASMConvertible;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.tree.Identifier;
import karaffe.compiler.visitor.Visitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class SimpleClassDecl extends AbstractNode implements ASMConvertible<ClassNode> {

    private final Optional<AST> annotationList;
    private final Optional<AST> modifierList;
    private final Identifier identifier;
    private final Optional<AST> autoDeclList;
    private final Optional<AST> body;

    public SimpleClassDecl(Object a, Object m, Object id, Object b, Object ex, Object bd) {
        this.annotationList = Optional.ofNullable((AST) a);
        this.modifierList = Optional.ofNullable((AST) m);
        this.identifier = (Identifier) id;
        this.autoDeclList = Optional.ofNullable((AST) b);
        this.body = Optional.ofNullable((AST) bd);
        addChildren(annotationList);
        addChildren(modifierList);
        addChildren(identifier);
        addChildren(autoDeclList);
        addChildren(body);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.simpleClassDecl(this);
    }

    @Override
    public String toString() {
        return "(SimpleClassDecl:" + String.join(",", annotationList.toString(), modifierList.toString(), identifier.toString(), autoDeclList.toString(), body.toString()) + ")";
    }

    public int access() {
        return Opcodes.ACC_PUBLIC;
    }

    public String name() {
        return identifier.get();
    }

    public String signature() {
        return null;
    }

    public String superName() {
        return Type.getInternalName(Object.class);
    }

    public String[] interfaces() {
        return null;
    }

    public List<String> interfacesList() {
        return new ArrayList<>();
    }

    @Override
    public ClassNode toNode() {
        ClassNode classNode = new ClassNode();
        classNode.access = access();
        classNode.name = name();
        classNode.signature = signature();
        classNode.superName = superName();
        classNode.interfaces = interfacesList();
        classNode.version = Opcodes.V1_8;
        List<FieldNode> fields = new ArrayList<>();
        List<MethodNode> methods = new ArrayList<>();
        autoDeclList.ifPresent(d -> fields.addAll(AutoDeclList.class.cast(d).get()));
        fields.stream()
                .map(f -> makeProperty(classNode, f))
                .forEach(methods::add);
        if (!fields.isEmpty()) {
            makeCtor(classNode, methods, fields);
        }
        body.ifPresent(b -> fields.addAll(ClassBody.class.cast(b).get()));
        if (fields.size()
                != fields.stream()
                .map(f -> f.name)
                .distinct()
                .toArray().length) {
            //フィールド名が重複している
            KCompiler.todoList.add(new ToDo(ToDo.Type.ERROR, "フィールド名が重複しています"));
        }
        if (methods.size()
                != methods.stream()
                .map(m -> m.name)
                .distinct()
                .toArray().length) {
            KCompiler.todoList.add(new ToDo(ToDo.Type.ERROR, "メソッド名が重複しています"));
        }
        classNode.fields = fields;
        classNode.methods = methods;
        return classNode;
    }

    private void makeCtor(ClassNode classNode, List<MethodNode> methods, List<FieldNode> fields) {
        List<Type> desclist = fields.stream()
                .map(f -> f.desc)
                .map(Type::getType)
                .collect(toList());
        MethodNode ctor = new MethodNode();
        ctor.access = Opcodes.ACC_PUBLIC;
        ctor.name = "<init>";
        ctor.desc = Type.getMethodDescriptor(Type.VOID_TYPE, desclist.toArray(new Type[]{}));
        ctor.exceptions = Collections.emptyList();
        InsnList insnList = new InsnList();
        insnList.add(new IntInsnNode(Opcodes.ALOAD, 0));
        insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false));
        for (int index = 0; index < desclist.size(); index++) {
            insnList.add(new IntInsnNode(Opcodes.ALOAD, 0));
            insnList.add(new IntInsnNode(Opcodes.ALOAD, index + 1));
            insnList.add(new FieldInsnNode(Opcodes.PUTFIELD, classNode.name, fields.get(index).name, fields.get(index).desc));
        }
        insnList.add(new InsnNode(Opcodes.RETURN));
        ctor.instructions = insnList;
        methods.add(ctor);
    }

    public MethodNode makeProperty(ClassNode classNode, FieldNode f) {
        MethodNode methodNode = new MethodNode();
        methodNode.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        methodNode.name = f.name;
        methodNode.desc = Type.getMethodDescriptor(Type.getType(f.desc));
        methodNode.exceptions = Collections.emptyList();
        InsnList insnList = new InsnList();
        insnList.add(new IntInsnNode(Opcodes.ALOAD, 0));
        insnList.add(new FieldInsnNode(Opcodes.GETFIELD, classNode.name, f.name, f.desc));
        insnList.add(new InsnNode(Opcodes.ARETURN));
        methodNode.instructions = insnList;
        return methodNode;
    }
}
