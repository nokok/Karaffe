package org.karaffe.compiler;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseListener;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;

import java.util.Objects;

public class WarningListener extends KaraffeBaseListener {

    private CompilerContext context;

    public WarningListener(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public void exitClassDef(KaraffeParser.ClassDefContext ctx) {
        String className = ctx.Identifier().getText();
        if (Character.isLowerCase(className.charAt(0))) {
            this.context.addOutputText("[warning] class name must be PascalCase : " + className);
        }
    }
}
