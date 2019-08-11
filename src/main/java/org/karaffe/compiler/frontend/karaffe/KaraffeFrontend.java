package org.karaffe.compiler.frontend.karaffe;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.karaffe.compiler.frontend.Frontend;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.visitor.KaraffeASTCreateVisitor;
import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker;
import org.karaffe.compiler.frontend.karaffe.walker.MakeTACWalker;
import org.karaffe.compiler.frontend.karaffe.walker.NameValidator;
import org.karaffe.compiler.frontend.karaffe.walker.ScopeWalker;
import org.karaffe.compiler.frontend.karaffe.walker.TreeSchemaValidator;
import org.karaffe.compiler.frontend.karaffe.walker.TypeNameCheck;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TreeWalker;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.karaffe.compiler.util.Lambda.uncheck;

public class KaraffeFrontend implements Frontend {
  private final CompilerContext context;
  private final List<TreeWalker> untypedTreeWalkers = new ArrayList<>();

  public KaraffeFrontend(CompilerContext context) {
    this.context = context;
    this.untypedTreeWalkers.add(new TreeSchemaValidator());
    this.untypedTreeWalkers.add(new FlatApplyWalker());
    this.untypedTreeWalkers.add(new NameValidator(context));
    this.untypedTreeWalkers.add(new ScopeWalker(context));
    this.untypedTreeWalkers.add(new TypeNameCheck(context));
    this.untypedTreeWalkers.add(new MakeTACWalker());
  }

  @Override
  public String getName() {
    return "karaffe";
  }

  @Override
  public void execute() {
    List<KaraffeSource> sources = context.getSourceFiles();
    KaraffeASTCreateVisitor astCreateVisitor = new KaraffeASTCreateVisitor(context);
    Stream<Optional<KaraffeParser.SourceFileContext>> antlrContextStream = sources.stream()
      .map(this::parse);
    Stream<KaraffeParser.SourceFileContext> concreteSyntaxTreeStream = antlrContextStream
      .filter(Optional::isPresent).map(Optional::get).filter(ctx -> Objects.nonNull(ctx.EOF()));
    concreteSyntaxTreeStream
      .forEach(c -> uncheck(() -> c.accept(astCreateVisitor)));
    Tree untypedAST = astCreateVisitor.getCompilationUnit();
    this.context.setUntypedTree(untypedAST);
    this.untypedTreeWalkers.forEach(w -> w.walk(untypedAST));
  }

  private Optional<KaraffeParser.SourceFileContext> parse(KaraffeSource source) {
    try {
      KaraffeLexer lexer = new KaraffeLexer(source.asCharStream());
      lexer.removeErrorListeners();
      DefaultErrorListener errorHandler = new DefaultErrorListener(context);
      lexer.addErrorListener(errorHandler);
      CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
      KaraffeParser parser = new KaraffeParser(commonTokenStream);
      parser.setErrorHandler(new KaraffeParseErrorStrategy());
      parser.removeErrorListeners();
      parser.addErrorListener(errorHandler);
      KaraffeParser.SourceFileContext context = parser.sourceFile();
      source.setConcreteSyntaxTree(context.toStringTree(parser));
      if (errorHandler.hasSyntaxError()) {
        return Optional.empty();
      } else {
        return Optional.of(context);
      }
    } catch (RecognitionException e) {
      return Optional.empty();
    }
  }

}
