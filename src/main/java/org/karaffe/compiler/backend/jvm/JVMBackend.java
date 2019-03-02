package org.karaffe.compiler.backend.jvm;

import org.karaffe.compiler.backend.Backend;
import org.karaffe.compiler.util.CompilerContext;

import java.util.Objects;

public class JVMBackend implements Backend {

  private final CompilerContext context;

  public JVMBackend(CompilerContext context) {
    this.context = Objects.requireNonNull(context);
  }

  @Override
  public String getName() {
    return "jvm";
  }

  @Override
  public void execute() {
    
  }
}
