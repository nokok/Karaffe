package org.karaffe.compiler.frontend.karaffe.visitor;

import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlrautogenerated.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.ast.Parameter;
import org.karaffe.compiler.frontend.karaffe.ast.api.Statement;
import org.karaffe.compiler.frontend.karaffe.ast.modifiers.Public;
import org.karaffe.compiler.frontend.karaffe.ast.modifiers.Static;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;
import org.karaffe.compiler.frontend.karaffe.ast.statements.MethodDef;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainMethodDefVisitor extends KaraffeBaseVisitor<MethodDef> implements PositionContext {

    @Override
    public MethodDef visitMainMethodDef(KaraffeParser.MainMethodDefContext ctx) {
        MethodDef mainMethod = new MethodDef(
                getPosition(ctx),
                Arrays.asList(new Public(), new Static()),
                TypeName.voidType(),
                new SimpleName(getPosition(ctx.MAIN()), "main"),
                Collections.singletonList(new Parameter(new SimpleName("args"), new TypeName("String", true)))
        );

        List<KaraffeParser.StatementContext> statements = ctx.statement();
        StatementVisitor statementVisitor = new StatementVisitor();
        List<Statement> body = statements.stream().map(s -> s.accept(statementVisitor)).collect(Collectors.toList());
        body.forEach(mainMethod::addMethodBody);
        return mainMethod;
    }
}
