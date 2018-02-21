package org.karaffe.compiler.tree.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.api.TypeDefMember;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.statements.ClassDef;
import org.karaffe.compiler.tree.v2.statements.InterfaceDef;
import org.karaffe.compiler.tree.v2.statements.LetDef;
import org.karaffe.compiler.tree.v2.statements.LetFieldDef;
import org.karaffe.compiler.tree.v2.statements.LocalLetDef;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

public class KNormalizer {

    public CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        CompilationUnit compilationUnit = new CompilationUnit();
        oldCompilationUnit.getAttributes().forEach(compilationUnit::addAttribute);
        oldCompilationUnit.getPackages().forEach(pkg -> {
            PackageDef packageDef = transform(pkg);
            compilationUnit.addPackageDef(packageDef);
        });
        return compilationUnit;
    }

    public PackageDef transform(PackageDef oldPackageDef) {
        PackageDef packageDef = new PackageDef(oldPackageDef.getPackageName());
        oldPackageDef.getAttributes().forEach(packageDef::addAttribute);
        for (TypeDefStatement typeDefStatement : packageDef.getTypeDefStatements()) {
            packageDef.addTypeDefStatement(transform(typeDefStatement));
        }
        return packageDef;
    }

    public TypeDefStatement transform(TypeDefStatement typeDefStatement) {
        if (typeDefStatement.isInterfaceDecl()) {
            return new InterfaceDef((InterfaceDef) typeDefStatement);
        } else if (typeDefStatement.isClassDecl()) {
            ClassDef classDef = new ClassDef(typeDefStatement.getPosition(), typeDefStatement.getName(), typeDefStatement.getSuperClassName(), typeDefStatement.getInterfaceNames());
            typeDefStatement.getAttributes().forEach(classDef::addAttribute);
            for (TypeDefMember member : typeDefStatement.getBody()) {
                classDef.addMember(transform(member));
            }
            return typeDefStatement;
        }
        throw new IllegalStateException();
    }

    public TypeDefMember transform(TypeDefMember otherMember) {
        switch (otherMember.getStatementType()) {
        case LET_FIELD_DEF:
            LetDef letDef = (LetDef) otherMember;
            Expression initializer = letDef.getInitializer().map(this::transform).orElse(null);
            SimpleName typeName = letDef.getTypeName().orElse(null);
            return new LetFieldDef(letDef.getPosition(), letDef.getName(), typeName, initializer);
        case METHOD_DEF:
            MethodDef methodDef = (MethodDef) otherMember;
            List<Statement> body = new ArrayList<>();
            for (Statement s : methodDef.getBody()) {
                body.add(transform(s));
            }
            return new MethodDef(
                    methodDef.getPosition(),
                    methodDef.getModifiers(),
                    methodDef.getReturnTypeName(),
                    methodDef.getName(),
                    methodDef.getParameters(),
                    body);
        default:
            throw new IllegalStateException();
        }
    }

    public Statement transform(Statement statement) {
        switch (statement.getStatementType()) {
        case EXPRESSION:
            return transform((Expression) statement);
        case CLASS_DEF:
            return transform((ClassDef) statement);
        case INTERFACE_DEF:
            return transform((InterfaceDef) statement);
        case LET_FIELD_DEF:
            return transform((LetFieldDef) statement);
        case LOCAL_LET_DEF:
            return transform((LocalLetDef) statement);
        case METHOD_DEF:
            return transform((MethodDef) statement);
        case SIMPLE_IMPORT_DEF:
            return transform((SimpleImport) statement);
        }
        throw new IllegalStateException();
    }

    public LocalLetDef transform(LocalLetDef otherLocalLetDef) {
        return otherLocalLetDef;
    }

    public SimpleImport transform(SimpleImport simpleImport) {
        return simpleImport;
    }

    public Expression transform(Expression expression) {
        switch (expression.getExpressionType()) {
        case APPLY: {
            Apply apply = (Apply) expression;
            Expression expr = transform(apply.getExpression());
            SimpleName methodName = apply.getMethodName();
            List<? extends Expression> args = apply.getArgs().stream().map(this::transform).collect(Collectors.toList());
            Apply ret = new Apply(apply.getPosition(), expr, methodName, args);
            apply.getAttributes().forEach(ret::addAttribute);
            return ret;
        }
        case BLOCK: {
            Block block = (Block) expression;
            List<Statement> statements = new ArrayList<>();
            for (Statement t : block.getBody()) {
                transform(t);
            }
            Block ret = new Block(block.getPosition());
            block.getAttributes().forEach(ret::addAttribute);
            statements.forEach(ret::add);
            return ret;
        }
        default:
            return expression;
        }
    }

}
