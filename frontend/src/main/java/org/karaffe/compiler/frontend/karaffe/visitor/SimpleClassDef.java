package org.karaffe.compiler.frontend.karaffe.visitor;

import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;
import org.karaffe.compiler.frontend.karaffe.ast.statements.ClassDef;

public class SimpleClassDef extends KaraffeBaseVisitor<ClassDef> implements PositionContext {

    @Override
    public ClassDef visitSimpleClassDef(KaraffeParser.SimpleClassDefContext ctx) {
        ClassDef classDef = new ClassDef();
        classDef.resetPosition(getPosition(ctx));
        classDef.setName(new SimpleName(getPosition(ctx.identifier()), ctx.identifier().getText()));
        classDef.setSuperClassName(new TypeName("Any"));


        KaraffeParser.ClassDefBodyContext classDefBodyContext = ctx.classDefBody();
        return classDef;
    }
}
