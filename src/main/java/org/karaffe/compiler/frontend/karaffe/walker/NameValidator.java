package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TreeWalker;
import org.karaffe.compiler.util.ClassNameValidator;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.NameValidationResult;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;

import java.util.Objects;

public class NameValidator extends TreeWalker {

  private final ClassNameValidator classNameValidator = new ClassNameValidator();
  private CompilerContext context;

  public NameValidator(CompilerContext context) {
    this.context = Objects.requireNonNull(context);
  }

  @Override
  public void onDefClass(Tree tree) {
    String name = tree.dig(NodeType.Identifier).map(Tree::getName).orElseThrow();
    NameValidationResult result = classNameValidator.validate(name);
    if (result.isError()) {
//      this.context.add(Report.newReport(ReportCode.ERR_NAME_VALIDATION_FAILED).withVariable(result).with(tree.getPosition()).build());
    } else if (result.isWarn()) {
//      this.context.add(Report.newReport(ReportCode.WARN_NAME_VALIDATION).withVariable(result).with(tree.getPosition()).build());
    }
  }
}
