package org.karaffe.compiler.phase.frontend.karaffe;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.ir.IR;
import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.util.CompilerContext;

import java.util.Map;

public class IRPhase implements Phase {
  @Override
  public String getName() {
    return "ir";
  }

  @Override
  public void execute(CompilerContext context) {
    Map<String, KaraffeParser.SourceFileContext> map = context.get("parse.result");
    for (Map.Entry<String, KaraffeParser.SourceFileContext> entry : map.entrySet()) {
      KaraffeParser.SourceFileContext ctx = entry.getValue();
      IRGen irGen = new IRGen();
      IR ir = ctx.accept(irGen);
    }
  }
}
