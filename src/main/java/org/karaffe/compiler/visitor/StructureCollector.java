package org.karaffe.compiler.visitor;

import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeBaseVisitor;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.util.CompilerContext;

public class StructureCollector extends KaraffeBaseVisitor<CompilerContext> {

  @Override
  public CompilerContext visitClassDef(KaraffeParser.ClassDefContext ctx) {
    return super.visitClassDef(ctx);
  }

  @Override
  public CompilerContext visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx) {
    return super.visitEntryPointBlock(ctx);
  }

  @Override
  public CompilerContext visitVarDef(KaraffeParser.VarDefContext ctx) {
    return super.visitVarDef(ctx);
  }
}
