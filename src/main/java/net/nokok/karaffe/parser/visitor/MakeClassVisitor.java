package net.nokok.karaffe.parser.visitor;

import java.util.ArrayList;
import java.util.List;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import net.nokok.karaffe.parser.ASTInterfaces;
import net.nokok.karaffe.parser.ASTKaraffeIdentifier;
import net.nokok.karaffe.parser.ASTPrivateModifier;
import net.nokok.karaffe.parser.ASTSealedModifier;
import net.nokok.karaffe.parser.ASTSuperType;
import net.nokok.karaffe.parser.ASTTypeDeclaration;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.Token;
import net.nokok.karaffe.parser.excptn.InternalCompilerException;
import net.nokok.karaffe.parser.excptn.KaraffeCompilerException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.util.ErrorType;

public class MakeClassVisitor extends KaraffeParserDefaultVisitor {

    private String id;
    private String superType;
    private final List<String> interfaces = new ArrayList<>();

    private boolean isSealedModifier;
    private boolean isPrivateModifier;

    @Override
    public CtClass visit(ASTTypeDeclaration node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        ClassPool pool = ClassPool.getDefault();
        CtClass classObj = pool.makeClass(id);
        if ( isPrivateModifier ) {
            classObj.setModifiers(Modifier.PRIVATE);
        }
        if ( isSealedModifier ) {
            classObj.setModifiers(Modifier.FINAL + classObj.getModifiers());
        }

        try {
            if ( id.equals("Any") ) {
                return classObj;
            }
            if ( superType == null ) {
                classObj.setSuperclass(pool.getCtClass("Any"));
            } else {
                classObj.setSuperclass(pool.getCtClass(superType));
            }
            if ( !interfaces.isEmpty() ) {
                for ( String interfaceName : interfaces ) {
                    classObj.addInterface(pool.getCtClass(interfaceName));
                }
            }

        } catch (CannotCompileException e) {
            throw new InternalCompilerException(node, e.getReason());
        } catch (NotFoundException e) {
            throw new KaraffeCompilerException(ErrorType.TYPE_NOT_FOUND + ":" + e.getMessage());
        }
        return classObj;
    }

    @Override
    public Object visit(ASTSealedModifier node, Object data) throws KaraffeParserException {
        if ( isSealedModifier ) {
            throw new KaraffeCompilerException(ErrorType.DUP_MODIFIER);
        }
        isSealedModifier = true;
        return null;
    }

    @Override
    public Object visit(ASTPrivateModifier node, Object data) throws KaraffeParserException {
        if ( isPrivateModifier ) {
            throw new KaraffeCompilerException(ErrorType.DUP_MODIFIER);
        }
        isPrivateModifier = true;
        return null;
    }

    @Override
    public Object visit(ASTKaraffeIdentifier node, Object data) throws KaraffeParserException {
        if ( id == null ) {
            Token t = (Token) node.jjtGetValue();
            id = t.image;
        }
        return null;
    }

    @Override
    public Object visit(ASTSuperType node, Object data) throws KaraffeParserException {
        superType = ((Token) ((SimpleNode) node.jjtGetChild(0)).jjtGetValue()).image;
        return null;
    }

    @Override
    public Object visit(ASTInterfaces node, Object data) throws KaraffeParserException {
        for ( int i = 0; i < node.jjtGetNumChildren(); i++ ) {
            interfaces.add(((Token) ((SimpleNode) node.jjtGetChild(i)).jjtGetValue()).image);
        }
        return null;
    }
}
