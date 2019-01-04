package org.karaffe.compiler.visitor;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseListener;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.report.ReportCode;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.Position;

import java.util.Objects;

public class ClassNameListener extends KaraffeBaseListener {

  private CompilerContext context;

  public ClassNameListener(CompilerContext context) {
    this.context = Objects.requireNonNull(context);
  }

  @Override
  public void exitClassDef(KaraffeParser.ClassDefContext ctx) {
    if (ctx.Identifier() == null) {
      return;
    }
    String className = ctx.Identifier().getText();
    if (Character.isLowerCase(className.charAt(0))) {
      this.context.add(Report.newReport(ReportCode.WARN_CLASSNAME_IS_NOT_PASCALCASE).withVariable(className).with(new Position(ctx.Identifier().getSymbol())).build());
    }
  }
}
