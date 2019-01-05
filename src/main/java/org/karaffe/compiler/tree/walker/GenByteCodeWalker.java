package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.gen.BytecodeSupport;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.objectweb.asm.Opcodes;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenByteCodeWalker extends TreeWalkerAdapter {

  private BytecodeSupport bytecodeSupport = new BytecodeSupport();
  private String currentSourceFile = null;

  @Override
  public void onSourceFile(Tree tree) {
    this.currentSourceFile = tree.getName();
  }

  @Override
  public void onDefClass(Tree tree) {
    bytecodeSupport.newClassDefinition(tree.getName(), this.currentSourceFile);
  }

  @Override
  public void onDefMethod(Tree tree) {
    bytecodeSupport.endMethod();
    Tree modifiers = tree.findFirstFromChildren(NodeType.Modifiers).orElseThrow(IllegalStateException::new);
    int access = Opcodes.ACC_PUBLIC;
    for (Tree modifierTree : modifiers.getChildren()) {
      String name = modifierTree.getName();
    }
    Tree returnTypeName = tree.findFirstFromChildren(NodeType.ReturnType).orElseThrow(IllegalStateException::new).findFirstFromChildren(NodeType.TypeName).orElseThrow(IllegalStateException::new);
    String name = returnTypeName.getName();
    Class<?> returnType;
    try {
      returnType = Class.forName(name);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    Map<String, Class<?>> parameterMap = new LinkedHashMap<>();
    bytecodeSupport.startMethod(access, returnType, tree.getName(), parameterMap);
  }
}
