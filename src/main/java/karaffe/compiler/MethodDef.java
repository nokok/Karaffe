package karaffe.compiler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import static java.util.stream.Collectors.toList;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

class MethodDef implements Statement, NodeGeneratable<MethodNode> {

    private final List<Annotation> annotations;
    private final List<Modifier> modifiers;
    private final Identifier id;
    private final TypeElement retType;
    private final List<Parameter> param;
    //List<Statement or Expr>
    private List<NodeGeneratable<?>> b;
    private ClassDef parent;
    private String path;

    MethodDef(List<Annotation> a, List<Modifier> m, Identifier id, List<Parameter> p, TypeElement retType, List<NodeGeneratable<?>> b) {
        this.annotations = a;
        this.modifiers = m;
        this.id = id;
        this.param = p;
        this.retType = retType;
        this.b = b;
        Context.INSTANCE.add(this);
    }

    @Override
    public Class<?> inferredType() {
        return Void.class;
    }

    public void setParent(ClassDef classDef) {
        if ( this.parent != null ) {
            return;
        }
        this.parent = classDef;
    }

    public void setPath(String path) {
        StringBuilder methodPath = new StringBuilder(Objects.requireNonNull(path));
        methodPath.append((path.isEmpty() ? "" : ".")).append(id.id()).append(":");
        for ( Parameter parameter : param ) {
            TypeElement type = parameter.type();
            methodPath.append(type.id());
            methodPath.append(type.args().stream().map(id -> id.id()).collect(toList()));
        }
        methodPath.append("->").append(retType.id()).append(retType.args().stream().map(id -> id.id()).collect(toList()));
        this.path = methodPath.toString();
    }

    @Override
    public MethodNode toNode() {
        MethodNode methodNode = new MethodNode();
        methodNode.name = id.id();

        Type returnType;
        if ( Identifier.isNone(retType.id()) ) {
            returnType = Type.VOID_TYPE;
        } else {
            returnType = Context.INSTANCE.resolveTypeByIdent(retType.id()).orElse(Type.getType(Object.class));
        }

        methodNode.access = modifiers.stream().map(c -> c.flag()).reduce((i1, i2) -> i1 + i2).orElse(Opcodes.ACC_PUBLIC);

        List<Type> argumentTypes = new ArrayList<>();
        for ( Parameter parameter : param ) {
            TypeElement type = parameter.type();
            String typeName = type.id();
            List<Identifier> typeArgs = type.args();
            if ( typeName.equals("Array") ) {
                if ( typeArgs.size() != 1 ) {
                    throw new UnsupportedOperationException();
                }
                argumentTypes.add(Type.getType(Array.newInstance(Context.INSTANCE.resolveClassByIdent(typeArgs.get(0).id()).orElse(Object.class), 0).getClass()));
            } else {
                argumentTypes.add(Type.getType(Context.INSTANCE.resolveClassByIdent(typeName).orElse(Object.class)));
            }
        }
        methodNode.attrs = new ArrayList<>();
        methodNode.desc = Type.getMethodDescriptor(returnType, argumentTypes.toArray(new Type[]{}));
        methodNode.instructions = new InsnList();
        List<LocalVarDef> localVarDefs = b.stream().filter(e -> e instanceof LocalVarDef).map(LocalVarDef.class::cast).collect(toList());
        methodNode.localVariables = new ArrayList<>();
        for ( LocalVarDef def : localVarDefs ) {
            Context.INSTANCE.add(def);
            //methodNode.localVariables.add(def.toNode());
        }
        b.stream()
            .filter(e -> e instanceof LocalVarDef)
            .map(LocalVarDef.class::cast)
            .map(LocalVarDef::getExprInsnList)
            .forEach(methodNode.instructions::add);
        b.stream()
            .map(elem -> elem.toNode())
            .filter(node -> !(node instanceof LocalVariableNode))
            .forEach(n -> {
                if ( n instanceof JumpInsnNode ) {
                    System.out.println("jump");
                    methodNode.instructions.add((AbstractInsnNode) n);
                } else if ( n instanceof AbstractInsnNode ) {
                    System.out.println("abst");
                    methodNode.instructions.add((AbstractInsnNode) n);
                } else if ( n instanceof InsnList ) {
                    System.out.println("insn");
                    methodNode.instructions.add((InsnList) n);
                } else {
                    throw new RuntimeException();
                }
            });
        int lastOpcode;
        if ( methodNode.instructions.getLast() == null ) {
            lastOpcode = -1;
        } else {
            lastOpcode = methodNode.instructions.getLast().getOpcode();
        }
        if ( lastOpcode != Opcodes.RETURN || lastOpcode != Opcodes.ARETURN ) {
            Type methodReturnType = Type.getReturnType(methodNode.desc);
            if ( methodReturnType.equals(Type.VOID_TYPE) ) {
                methodNode.instructions.add(new InsnNode(Opcodes.RETURN));
            } else {
                methodNode.instructions.add(new InsnNode(Opcodes.ARETURN));
            }
        }
        methodNode.exceptions = Collections.emptyList();
        methodNode.visitMaxs(0, 0);

        return methodNode;
    }

    @Override
    public String toString() {
        return "(method-def " + String.join(" ", "parent:" + (parent == null ? "none" : parent.name()), path, "(param " + param.toString() + ")", retType.toString(), b.toString()) + ")";
    }

    public String name() {
        return id.id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, retType, param, b, parent);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }

    ClassDef getParent() {
        return parent;
    }

    String getPath() {
        return this.path;
    }

}
