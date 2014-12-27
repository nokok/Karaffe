package net.nokok.karaffe.parser.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.ASTInterfaces;
import net.nokok.karaffe.parser.ASTSuperType;
import net.nokok.karaffe.parser.ASTTypeDeclaration;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.Token;
import net.nokok.karaffe.parser.excptn.InternalCompilerException;
import net.nokok.karaffe.parser.excptn.KaraffeCompilerException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.util.CurrentState;
import net.nokok.karaffe.parser.util.ErrorType;
import net.nokok.karaffe.parser.util.Modifiers;

public class MakeClassVisitor extends KaraffeParserDefaultVisitor {

    private String id;
    private String superType;
    private final List<String> interfaces = new ArrayList<>();

    private Optional<Modifiers> modifier = Optional.empty();

    public MakeClassVisitor(CurrentState state) {

    }

    @Override
    public CtClass visit(ASTTypeDeclaration node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        ClassPool pool = ClassPool.getDefault();
        CtClass classObj = pool.makeClass(id);
        modifier.ifPresent(m -> {
            if (m.isAbstract()) {
                classObj.setModifiers(Modifier.ABSTRACT);
            }
            if (m.isSealed()) {
                classObj.setModifiers(Modifier.FINAL + classObj.getModifiers());
            }
            if (m.isPrivate()) {
                classObj.setModifiers(Modifier.PRIVATE + classObj.getModifiers());
            }
        });
        try {
            if (id.equals("Any")) {
                return classObj;
            }
            if (superType == null) {
                classObj.setSuperclass(pool.getCtClass("Any"));
            } else {
                classObj.setSuperclass(pool.getCtClass(superType));
            }
            if (!interfaces.isEmpty()) {
                for (String interfaceName : interfaces) {
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
    public Object visit(ASTIdentifier node, Object data) throws KaraffeParserException {
        if (id == null) {
            id = node.jjtGetValue().toString();
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
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            interfaces.add(((Token) ((SimpleNode) node.jjtGetChild(i)).jjtGetValue()).image);
        }
        return null;
    }
}
