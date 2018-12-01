package org.karaffe.compiler;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;

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
            this.context.addOutputText("[warning] class name must be PascalCase : " + className);
        }
        return null;
    }
}
