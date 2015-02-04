/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Optional;
import net.nokok.karaffe.parser.ASTFuncBody;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.ASTParenFormalParams;
import net.nokok.karaffe.parser.ASTReturnType;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodVisitor {

    private final MethodNode methodNode = new MethodNode();

    private final ASTFuncDecl node;
    private final NodeUtil nodeUtil;
    private final Optional<ClassResolver> optResolver;

    public MethodVisitor(ASTFuncDecl node) {
        this(node, null);
    }

    public MethodVisitor(ASTFuncDecl node, ClassResolver resolver) {
        this.node = node;
        this.nodeUtil = new NodeUtil(node);
        this.optResolver = Optional.ofNullable(resolver);
    }

    public MethodNode getMethodNode() {
        ASTIdentifier identifier = nodeUtil.findFirstNode(ASTIdentifier.class).orElseThrow(IllegalArgumentException::new);
        methodNode.access = new ModifierNode(node).getModifier().orElse(0);
        methodNode.name = identifier.jjtGetValue().toString();
        ASTParenFormalParams parenformalParams = nodeUtil.forceGetFindFirstNode(ASTParenFormalParams.class);
        ASTReturnType returnType = nodeUtil.forceGetFindFirstNode(ASTReturnType.class);
        methodNode.desc = genMethodDesc(parenformalParams, returnType);
        Optional<ASTFuncBody> funcBody = nodeUtil.findFirstNode(ASTFuncBody.class);
        funcBody.ifPresent(body -> {
            InsnVisitor insnVisitor = new InsnVisitor(body);
            methodNode.instructions = insnVisitor.getInsnList();
            methodNode.instructions.add(new InsnNode(Opcodes.RETURN));
        });
        methodNode.exceptions = new ArrayList<>();
        return methodNode;
    }

    private String genMethodDesc(ASTParenFormalParams params, ASTReturnType returnType) {
        System.out.println("params--");
        params.dump("");
        System.out.println("return--");
        returnType.dump("");
        final ParserVisitor argVisitor = new ParserDefaultVisitor() {

        };
        return "";
    }
}
