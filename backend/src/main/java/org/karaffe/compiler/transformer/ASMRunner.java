package org.karaffe.compiler.transformer;

import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import net.nokok.azm.tree.AbstractInsnNode;
import net.nokok.azm.tree.ClassNode;
import net.nokok.azm.tree.InsnNode;
import net.nokok.azm.tree.IntInsnNode;
import net.nokok.azm.tree.ModuleNode;
import org.karaffe.compiler.ast.PackageDef;
import org.karaffe.compiler.ast.expressions.IntLiteral;
import org.karaffe.compiler.ast.statements.ClassDef;
import org.karaffe.compiler.transformer.util.TransformerContext;

import java.util.ArrayList;
import java.util.List;

public class ASMRunner extends AbstractTransformer {

    private final List<AbstractInsnNode> instructions = new ArrayList<>();
    private final List<ClassNode> classNodes = new ArrayList<>();

    private ModuleNode moduleNode = null;
    private String currentPackageName = "";
    private ClassNode classNode = null;

    public ASMRunner() {
        super("asm");
    }

    public List<ClassNode> getClassNodes() {
        return classNodes;
    }

    @Override
    public void onPackageDefBefore(PackageDef packageDef) {
        this.currentPackageName = packageDef.getPackageName().toString();
    }

    @Override
    public void onClassDefAfter(ClassDef classDef) {
        this.classNode = new ClassNode();
        this.classNode.version = Opcodes.V1_8;
        this.classNode.access = Opcodes.ACC_PUBLIC;
        String className = this.currentPackageName.replaceAll("\\.", "/") + "/" + classDef.getName().toString();
        this.classNode.name = className.startsWith("\\") ? className.replace("\\", "") : className;
        this.classNode.superName = Type.getInternalName(Object.class);
        this.classNode.sourceFile = TransformerContext.CONTEXT.getSourceFile(classDef);
        this.classNodes.add(this.classNode);
    }

    @Override
    public void onIntLiteralAfter(IntLiteral intLiteral) {
        switch (intLiteral.getRawValue()) {
        case 0:
            this.instructions.add(new InsnNode(Opcodes.ICONST_0));
            break;
        case 1:
            this.instructions.add(new InsnNode(Opcodes.ICONST_1));
            break;
        case 2:
            this.instructions.add(new InsnNode(Opcodes.ICONST_2));
            break;
        case 3:
            this.instructions.add(new InsnNode(Opcodes.ICONST_3));
            break;
        case 4:
            this.instructions.add(new InsnNode(Opcodes.ICONST_4));
            break;
        case 5:
            this.instructions.add(new InsnNode(Opcodes.ICONST_5));
            break;
        default:
            int value = intLiteral.getRawValue();
            if (Byte.MIN_VALUE < value && value < Byte.MAX_VALUE) {
                this.instructions.add(new IntInsnNode(Opcodes.BIPUSH, value));
            } else if (Short.MIN_VALUE < value && value < Short.MAX_VALUE) {
                this.instructions.add(new IntInsnNode(Opcodes.SIPUSH, value));
            } else {
                this.instructions.add(new IntInsnNode(Opcodes.LDC, 1));
            }
        }
    }
}
