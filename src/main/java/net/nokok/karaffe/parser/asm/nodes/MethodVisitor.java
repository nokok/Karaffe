/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.nokok.karaffe.parser.ASTAmbiguousName;
import net.nokok.karaffe.parser.ASTClassCtorDecl;
import net.nokok.karaffe.parser.ASTFormalParameter;
import net.nokok.karaffe.parser.ASTFuncBody;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.ASTLastFormalParamter;
import net.nokok.karaffe.parser.ASTParenFormalParams;
import net.nokok.karaffe.parser.ASTReturnType;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.AmbiguousName;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodVisitor {

    private final MethodNode methodNode = new MethodNode();

    private final NodeUtil nodeUtil;
    private final ClassResolver resolver;

    public MethodVisitor(ASTFuncDecl node, ClassResolver resolver) {
        this.nodeUtil = new NodeUtil(node);
        this.resolver = resolver;
        ASTIdentifier identifier = nodeUtil.findFirstNode(ASTIdentifier.class).orElseThrow(IllegalArgumentException::new);
        methodNode.access = new ModifierNode(node).getModifier().orElse(0);
        methodNode.name = identifier.jjtGetValue().toString();
        ASTParenFormalParams parenformalParams = nodeUtil.forceGetFindFirstNode(ASTParenFormalParams.class);
        Optional<ASTReturnType> returnNode = nodeUtil.findFirstNode(ASTReturnType.class);
        methodNode.desc = genMethodDesc(parenformalParams, returnNode); //TODO
        Optional<ASTFuncBody> funcBody = nodeUtil.findFirstNode(ASTFuncBody.class);
        funcBody.ifPresent(body -> {
            InsnVisitor insnVisitor = new InsnVisitor(body);
            methodNode.instructions = insnVisitor.getInsnList();
            if (methodNode.instructions.size() == 0) { //isEmptyが無い
                //Bodyが空
                methodNode.instructions.add(new InsnNode(Opcodes.RETURN));
            } else {
                methodNode.instructions.add(new InsnNode(Opcodes.ARETURN));
            }
        });
        methodNode.exceptions = new ArrayList<>();
    }

    public MethodVisitor(ASTClassCtorDecl node, ClassResolver resolver) {
        this.nodeUtil = new NodeUtil(node);
        this.resolver = resolver;
        methodNode.access = new ModifierNode(node).getModifier().orElse(0);
        methodNode.name = "<init>";
        methodNode.desc = genMethodDesc(nodeUtil.forceGetFindFirstNode(ASTParenFormalParams.class), Optional.empty());
    }

    public MethodNode getMethodNode() {
        return methodNode;
    }

    private String genMethodDesc(ASTParenFormalParams params, Optional<ASTReturnType> returnType) {
        class TypeBox {

            Type type;
        }

        final TypeBox returnTypeBox = new TypeBox();
        final List<Type> types = new ArrayList<>();
        final Function<SimpleNode, Type> nodeToType = node -> {
            NodeUtil paramNodeUtil = new NodeUtil(node);
            ASTAmbiguousName ambiguousNameNode = paramNodeUtil.forceGetFindFirstNode(ASTAmbiguousName.class);
            AmbiguousName ambiguousName = new AmbiguousName(ambiguousNameNode);
            return resolver.resolveType(ambiguousName.getPath()).orElseThrow(UnsupportedOperationException::new);
        };
        final ParserVisitor argVisitor = new ParserDefaultVisitor() {

            @Override
            public void visit(ASTFormalParameter node, Object data) throws ParserException {
                types.add(nodeToType.apply(node));
            }

            @Override
            public void visit(ASTLastFormalParamter node, Object data) throws ParserException {
                types.add(nodeToType.apply(node));
            }
        };

        final ParserVisitor returnVisitor = new ParserDefaultVisitor() {
            @Override
            public void visit(ASTReturnType node, Object data) throws ParserException {
                returnTypeBox.type = nodeToType.apply(node);
            }
        };
        try {
            params.jjtAccept(argVisitor, null);
            if (returnType.isPresent()) {
                returnType.get().jjtAccept(returnVisitor, null);
            } else {
                returnTypeBox.type = Type.VOID_TYPE;
            }
            return Type.getMethodDescriptor(returnTypeBox.type, types.toArray(new Type[]{}));
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }
}
