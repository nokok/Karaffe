package org.karaffe.compiler.phase.frontend.karaffe;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.karaffe.compiler.frontend.karaffe.DefaultErrorListener;
import org.karaffe.compiler.frontend.karaffe.KaraffeParseErrorStrategy;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KaraffeParsePhase implements Phase {
  @Override
  public String getName() {
    return "frontend-karaffe-parser";
  }

  @Override
  public void execute(CompilerContext context) {
    Map<String, KaraffeParser.SourceFileContext> map = new HashMap<>();
    for (KaraffeSource source : context.getSourceFiles()) {
      Optional<KaraffeParser.SourceFileContext> result = parse(source, context);
      result.ifPresent(r -> map.put(source.getSourceName(), r));
    }
    context.set("parse.result", map);
  }

  private Optional<KaraffeParser.SourceFileContext> parse(KaraffeSource source, CompilerContext context) {
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
      KaraffeParser.SourceFileContext sourceFileContext = parser.sourceFile();
      source.setConcreteSyntaxTree(sourceFileContext.toStringTree(parser));
      if (errorHandler.hasSyntaxError()) {
        return Optional.empty();
      } else {
        return Optional.of(sourceFileContext);
      }
    } catch (RecognitionException e) {
      return Optional.empty();
    }
  }

}
