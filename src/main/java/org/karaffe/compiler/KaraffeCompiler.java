package org.karaffe.compiler;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.karaffe.compiler.args.Flag;
import org.karaffe.compiler.args.ParameterName;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.tree.formatter.FormatType;
import org.karaffe.compiler.tree.formatter.TreeFormatter;
import org.karaffe.compiler.tree.walker.FlatApplyWalker;
import org.karaffe.compiler.tree.walker.TreeSchemaValidator;
import org.karaffe.compiler.tree.walker.TreeWalker;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;
import org.karaffe.compiler.visitor.ClassNameListener;
import org.karaffe.compiler.visitor.KaraffeASTCreateVisitor;
import org.karaffe.compiler.visitor.KaraffeParserVisitor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.karaffe.compiler.util.Lambda.uncheck;

public class KaraffeCompiler {
  private final CompilerContext context;

  public KaraffeCompiler(CompilerContext context) {
    this.context = context;
  }

  public void run() {
    List<KaraffeSource> sources = context.getSources();
    KaraffeParserVisitor parserVisitor = new KaraffeParserVisitor(context);
    KaraffeASTCreateVisitor createASTVisitor = new KaraffeASTCreateVisitor(context);
    sources.stream()
    .map(this::parse)
    .filter(Optional::isPresent).map(Optional::get).filter(ctx -> Objects.nonNull(ctx.EOF()))
    .forEach(c -> uncheck(() -> {
      c.accept(createASTVisitor);
      c.accept(parserVisitor);
    }
    ));
    this.context.setAST(createASTVisitor.getCompilationUnit());

    TreeWalker validator = new TreeSchemaValidator();
    validator.walk(this.context.getCurrentAST());
    TreeWalker exprWalker = new FlatApplyWalker();
    exprWalker.walk(this.context.getCurrentAST());

    context.getParameter(ParameterName.EMIT).map(String::toLowerCase).ifPresent(param -> {
      FormatType type;
      if (param.equals("ast")) {
        type = FormatType.SIMPLE;
      } else if (param.equals("source")) {
        type = FormatType.SOURCE;
      } else {
        this.context.add(Report.newErrorReport("Unrecognized argument(s) : " + param).build());
        return;
      }

      TreeFormatter formatter = TreeFormatter.fromType(type);

      this.context.add(Report.newInfoReport("AST Info").withBody(formatter.format(this.context.getCurrentAST())).build());
    });

    if (context.hasError()) {
      return;
    }

    if (this.context.hasFlag(Flag.DRY_RUN)) {
      return;
    }
    for (Map.Entry<Path, byte[]> entry : context.getOutputFiles().entrySet()) {
      try {
        Files.write(entry.getKey(), entry.getValue());
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }
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
      parser.addParseListener(new ClassNameListener(context));
      parser.addErrorListener(errorHandler);
      if (errorHandler.hasSyntaxError()) {
        return Optional.empty();
      } else {
        return Optional.of(parser.sourceFile());
      }
    } catch (RecognitionException e) {
      return Optional.empty();
    }
  }

}
