package net.nokok.karaffe.parser.asm.nodes;

import net.nokok.karaffe.parser.ASTClassCtorDecl;
import net.nokok.karaffe.parser.ASTClassDecl;
import net.nokok.karaffe.parser.ASTFieldDecl;
import net.nokok.karaffe.parser.ASTFuncAlias;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTImportStmt;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import net.nokok.karaffe.parser.excptn.ParserException;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassVisitor {

    private final org.objectweb.asm.tree.ClassNode classNode;

    private final ParserVisitor parserVisitor = new ParserDefaultVisitor() {
        @Override
        public void visit(ASTClassCtorDecl node, Object data) throws ParserException {
            MethodVisitor methodVisitor = new MethodVisitor(node, resolver);
            MethodNode methodNode = methodVisitor.getMethodNode();
            classNode.methods.add(methodNode);
        }

        @Override
        public void visit(ASTFuncDecl node, Object data) throws ParserException {
            MethodVisitor methodVisitor = new MethodVisitor(node, resolver);
            MethodNode methodNode = methodVisitor.getMethodNode();
            classNode.methods.add(methodNode);
        }

        @Override
        public void visit(ASTFieldDecl node, Object data) throws ParserException {
        }

        public void visit(ASTImportStmt node, Object data) throws ParserException {
        }

        @Override
        public void visit(ASTFuncAlias node, Object data) throws ParserException {
        }

    };

    private final ClassResolver resolver;

    public ClassVisitor(ASTClassDecl node, ClassResolver resolver) {
        this.resolver = resolver;
        this.classNode = new ClassNode();
        classNode.access = new ModifierNode(node).getModifier().orElse(0);
    }

    public ClassNode getClassNode() {
        return classNode;
    }
}
