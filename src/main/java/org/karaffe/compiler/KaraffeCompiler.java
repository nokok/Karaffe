package org.karaffe.compiler;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.karaffe.compiler.frontend.Frontend;
import org.karaffe.compiler.frontend.karaffe.DefaultErrorListener;
import org.karaffe.compiler.frontend.karaffe.KaraffeParseErrorStrategy;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer;
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeParser;
import org.karaffe.compiler.frontend.karaffe.visitor.KaraffeASTCreateVisitor;
import org.karaffe.compiler.frontend.karaffe.walker.FlatApplyWalker;
import org.karaffe.compiler.frontend.karaffe.walker.NameValidator;
import org.karaffe.compiler.frontend.karaffe.walker.TreeSchemaValidator;
import org.karaffe.compiler.tree.formatter.FormatType;
import org.karaffe.compiler.tree.formatter.TreeFormatter;
import org.karaffe.compiler.tree.walker.TreeWalker;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;
import org.karaffe.compiler.util.args.Flag;
import org.karaffe.compiler.util.args.ParameterName;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.karaffe.compiler.util.Lambda.uncheck;

public class KaraffeCompiler implements Runnable {
  private final CompilerContext context;

  public KaraffeCompiler(CompilerContext context) {
    this.context = context;
  }

  @Override
  public void run() {
    CompilerContext currentContext = this.context;
    List<KaraffeSource> sources = context.getSources();
    Frontend frontend = Frontend.getFrontend(currentContext);
    currentContext = frontend.execute();
    if (currentContext.hasError()) {
      return;
    }

    KaraffeASTCreateVisitor createASTVisitor = new KaraffeASTCreateVisitor(context);
    sources.stream()
      .map(this::parse)
      .filter(Optional::isPresent).map(Optional::get).filter(ctx -> Objects.nonNull(ctx.EOF()))
      .forEach(c -> uncheck(() -> c.accept(createASTVisitor)));
    this.context.setUntypedTree(createASTVisitor.getCompilationUnit());

    TreeWalker validator = new TreeSchemaValidator();
    validator.walk(this.context.getUntypedTree());
    TreeWalker exprWalker = new FlatApplyWalker();
    exprWalker.walk(this.context.getUntypedTree());
    TreeWalker nameValidator = new NameValidator(context);
    nameValidator.walk(this.context.getUntypedTree());

    context.getParameter(ParameterName.EMIT).map(String::toLowerCase).ifPresent(param -> {
      FormatType type;
      if (param.equals("ast")) {
        type = FormatType.SIMPLE;
      } else if (param.equals("source")) {
        type = FormatType.SOURCE;
      } else {
        this.context.add(Report.newReport(ReportCode.ERR_UNRECOGNIZED_ARGUMENT).withVariable(param).build());
        return;
      }

      TreeFormatter formatter = TreeFormatter.fromType(type);

      this.context.add(Report.newReport(ReportCode.INFO_AST).withBody(formatter.format(this.context.getUntypedTree())).build());
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
