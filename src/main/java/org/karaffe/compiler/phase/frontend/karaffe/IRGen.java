package org.karaffe.compiler.phase.frontend.karaffe;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.ir.ClassIR;
import org.karaffe.compiler.ir.IR;

public class IRGen extends KaraffeBaseVisitor<IR> {
  @Override
  public IR visitClassDef(KaraffeParser.ClassDefContext ctx) {
    return new ClassIR();
  }
}
