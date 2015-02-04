/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.nokok.karaffe.parser.ASTAmbiguousName;
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

    private final ASTFuncDecl node;
    private final NodeUtil nodeUtil;
    private final ClassResolver resolver;

    public MethodVisitor(ASTFuncDecl node, ClassResolver resolver) {
        this.node = node;
        this.nodeUtil = new NodeUtil(node);
        this.resolver = resolver;
    }

    public MethodNode getMethodNode() {
        ASTIdentifier identifier = nodeUtil.findFirstNode(ASTIdentifier.class).orElseThrow(IllegalArgumentException::new);
        methodNode.access = new ModifierNode(node).getModifier().orElse(0);
        methodNode.name = identifier.jjtGetValue().toString();
        ASTParenFormalParams parenformalParams = nodeUtil.forceGetFindFirstNode(ASTParenFormalParams.class);
        ASTReturnType returnNode = nodeUtil.forceGetFindFirstNode(ASTReturnType.class);
        methodNode.desc = genMethodDesc(parenformalParams, returnNode);
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
        class TypeBox {

            Type type;
        }

        final TypeBox typeBox = new TypeBox();
        final List<Type> types = new ArrayList<>();
        final Function<SimpleNode, Type> nodeToType = node -> {
            NodeUtil paramNodeUtil = new NodeUtil(node);
            ASTAmbiguousName ambiguousNameNode = paramNodeUtil.forceGetFindFirstNode(ASTAmbiguousName.class);
            AmbiguousName ambiguousName = new AmbiguousName(ambiguousNameNode);
            return resolver.resolveType(ambiguousName.getPath()).orElseThrow(UnsupportedOperationException::new);
        };
        final ParserVisitor argVisitor = new ParserDefaultVisitor() {

            @Override
            public Object visit(ASTFormalParameter node, Object data) throws ParserException {
                types.add(nodeToType.apply(node));
                return null;
            }

            @Override
            public Object visit(ASTLastFormalParamter node, Object data) throws ParserException {
                types.add(nodeToType.apply(node));
                return null;
            }
        };

        final ParserVisitor returnVisitor = new ParserDefaultVisitor() {
            @Override
            public Object visit(ASTReturnType node, Object data) throws ParserException {
                typeBox.type = nodeToType.apply(node);
                return null;
            }
        };
        try {
            params.jjtAccept(argVisitor, null);
            returnType.jjtAccept(returnVisitor, null);
            return Type.getMethodDescriptor(typeBox.type, types.toArray(new Type[]{}));
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }
}
