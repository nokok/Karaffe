package net.nokok.karaffe.parser.asm.nodes.collectors;

import java.util.List;
import net.nokok.karaffe.parser.ASTAlgebraicDataTypeDecl;
import net.nokok.karaffe.parser.ASTClassDecl;
import net.nokok.karaffe.parser.ASTEnumDecl;
import net.nokok.karaffe.parser.ASTInterfaceDecl;
import net.nokok.karaffe.parser.ASTTypeAliasDecl;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import net.nokok.karaffe.parser.excptn.ParserException;
import org.objectweb.asm.tree.ClassNode;

public class ClassCollector {

    private final ParserVisitor visitor = new ParserDefaultVisitor() {

        @Override
        public void visit(ASTClassDecl node, Object data) throws ParserException {
        }

        @Override
        public void visit(ASTTypeAliasDecl node, Object data) throws ParserException {
        }

        @Override
        public void visit(ASTEnumDecl node, Object data) throws ParserException {
        }

        @Override
        public void visit(ASTInterfaceDecl node, Object data) throws ParserException {
        }

        @Override
        public void visit(ASTAlgebraicDataTypeDecl node, Object data) throws ParserException {
        }

    };

    public ClassCollector(ClassResolver resolver) {

    }

    public List<ClassNode> getClassNodes() {
        return null;
    }
}
