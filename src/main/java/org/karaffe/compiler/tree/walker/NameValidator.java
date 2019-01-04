package org.karaffe.compiler.tree.walker;

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
    if (result != NameValidationResult.OK) {
      
    }
  }
}
