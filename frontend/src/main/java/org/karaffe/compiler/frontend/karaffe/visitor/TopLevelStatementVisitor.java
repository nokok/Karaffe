package org.karaffe.compiler.frontend.karaffe.visitor;

import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.ast.api.Tree;

public class TopLevelStatementVisitor extends KaraffeBaseVisitor<Tree> {

    @Override
    public Tree visitTopLevelStatement(KaraffeParser.TopLevelStatementContext ctx) {
        if (ctx.classDef() != null) {
            ClassDefVisitor classDefVisitor = new ClassDefVisitor();
            return ctx.classDef().accept(classDefVisitor);
        } else {
            throw new IllegalStateException("Unexpected ctx.classDef == null");
        }
    }
}
