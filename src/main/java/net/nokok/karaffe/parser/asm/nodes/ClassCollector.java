package net.nokok.karaffe.parser.asm.nodes;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;

import net.nokok.karaffe.parser.ASTAlgebraicDataTypeDecl;
import net.nokok.karaffe.parser.ASTClassDecl;
import net.nokok.karaffe.parser.ASTEnumDecl;
import net.nokok.karaffe.parser.ASTInterfaceDecl;
import net.nokok.karaffe.parser.ASTTypeAliasDecl;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import net.nokok.karaffe.parser.excptn.ParserException;

public class ClassCollector {
    private final ParserVisitor visitor = new ParserDefaultVisitor() {

        @Override
        public Object visit(ASTClassDecl node, Object data) throws ParserException {
            return null;
        }

        @Override
        public Object visit(ASTTypeAliasDecl node, Object data) throws ParserException {
            return null;
        }

        @Override
        public Object visit(ASTEnumDecl node, Object data) throws ParserException {
            return null;
        }

        @Override
        public Object visit(ASTInterfaceDecl node, Object data) throws ParserException {
            return null;
        }

        @Override
        public Object visit(ASTAlgebraicDataTypeDecl node, Object data) throws ParserException {
            return null;
        }

    };
    
    public ClassCollector(ClassResolver resolver) {
        
    }
    
    public List<ClassNode> getClassNodes(){
        return null;
    }
}
