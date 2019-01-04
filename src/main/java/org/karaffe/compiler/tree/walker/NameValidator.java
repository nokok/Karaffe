package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.report.Report;
import org.karaffe.compiler.report.ReportCode;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.ClassNameValidator;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.NameValidationResult;

import java.util.Objects;

public class NameValidator extends TreeWalkerAdapter {

  private final ClassNameValidator classNameValidator = new ClassNameValidator();
  private CompilerContext context;

  public NameValidator(CompilerContext context) {
    this.context = Objects.requireNonNull(context);
  }

  @Override
  public void onDefClass(Tree tree) {
    String name = tree.getName();
    NameValidationResult result = classNameValidator.validate(name);
    if (result.isError()) {
      this.context.add(Report.newReport(ReportCode.ERR_NAME_VALIDATION_FAILED).withVariable(result).with(tree.getPosition()).build());
    } else if (result.isWarn()) {
      this.context.add(Report.newReport(ReportCode.WARN_NAME_VALIDATION).withVariable(result).with(tree.getPosition()).build());
    }
  }
}
