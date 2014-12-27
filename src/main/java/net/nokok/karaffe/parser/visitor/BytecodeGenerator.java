/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.visitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTImportStatement;
import net.nokok.karaffe.parser.ASTMethodInvocation;
import net.nokok.karaffe.parser.ASTTypeDeclaration;
import net.nokok.karaffe.parser.ASTVariableOrFunctionDeclaration;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.util.CurrentState;

public class BytecodeGenerator extends KaraffeParserDefaultVisitor {

    private final Set<CtClass> classes = new HashSet<>();
    private final CurrentState currentState = new CurrentState();
    private final ClassPool pool = ClassPool.getDefault();

    @Override
    public Set<CtClass> visit(ASTCompileUnit node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return Collections.unmodifiableSet(classes);
    }

    @Override
    public CtMethod visit(ASTMethodInvocation node, Object data) throws KaraffeParserException {
        return (CtMethod) node.jjtAccept(new MethodInvocationVisitor(), null);
    }

    @Override
    public CtClass visit(ASTTypeDeclaration node, Object data) throws KaraffeParserException {
        CtClass ctClass = (CtClass) node.jjtAccept(new MakeClassVisitor(currentState), null);
        if (classes.contains(ctClass)) {
        } else {
            classes.add(ctClass);
        }
        return ctClass;
    }

    @Override
    public Object visit(ASTImportStatement node, Object data) throws KaraffeParserException {
        String importPackage = ((SimpleNode) node.jjtGetChild(0)).jjtGetValue().toString();
        pool.importPackage(importPackage);
        return null;
    }

    @Override
    public Object visit(ASTVariableOrFunctionDeclaration node, Object data) throws KaraffeParserException {
        return node.jjtAccept(new VarOrFuncDeclVisitor(currentState), data);
    }
}
