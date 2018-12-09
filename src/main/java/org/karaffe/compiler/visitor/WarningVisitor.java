package org.karaffe.compiler.visitor;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.Objects;

public class WarningVisitor extends KaraffeBaseVisitor<Void> {

    private CompilerContext context;

    public WarningVisitor(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public Void visitClassDef(KaraffeParser.ClassDefContext ctx) {
        String className = ctx.Identifier().getText();
        if (Character.isLowerCase(className.charAt(0))) {
            this.context.add(Report.newWarningReport("Class name must be PascalCase : " + className).with(new Position(ctx.Identifier().getSymbol())).build());
        }
        return null;
    }
}
