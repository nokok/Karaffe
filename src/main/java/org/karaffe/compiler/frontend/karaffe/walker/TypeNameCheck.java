package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TreeWalkerAdapter;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.report.Report;
import org.karaffe.compiler.util.report.ReportCode;

import java.lang.reflect.Modifier;
import java.util.Optional;

public class TypeNameCheck extends TreeWalkerAdapter {
  private final CompilerContext context;

  public TypeNameCheck(CompilerContext context) {
    this.context = context;
  }

  @Override
  public void onSuperClass(Tree tree) {
    tree.dig(NodeType.ArrayTypeName, NodeType.Identifier).ifPresent(c -> {
      this.context.add(Report.newReport(ReportCode.ERR_INVALID_INHERITANCE_ARRAY_TYPE).with(tree.getPosition()).build());
    });
    Optional<Tree> typeName = tree.dig(NodeType.TypeName, NodeType.Identifier);
    typeName.map(Tree::getName).ifPresent(className -> {
      try {
        Class<?> clazz = Class.forName(className);
        Optional<Tree> superClass = tree.climb(NodeType.SuperClass);
        superClass.ifPresent(s -> {
          if (clazz.isInterface()) {
            this.context.add(Report.newReport(ReportCode.ERR_INVALID_INHERITANCE_UNEXPECTED_INTERFACE).with(tree.getPosition()).build());
          }
          int modifiers = clazz.getModifiers();
          if (Modifier.isFinal(modifiers)) {
            this.context.add(Report.newReport(ReportCode.ERR_INVALID_INHERITANCE_FINAL_CLASS).with(tree.getPosition()).build());
          }
        });
      } catch (ClassNotFoundException e) {
        this.context.add(Report.newReport(ReportCode.ERR_CLASS_NOT_FOUND).withVariable(className).build());
      }
    });
  }
}
