package org.karaffe.compiler.frontend.karaffe.util;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.TreeFactory;

import java.util.Objects;

public class TypeTreeConverter {
  public Tree convert(Class<?> clazz) {
    Objects.requireNonNull(clazz);
    String simpleClassName = clazz.getSimpleName();
    Package pkg = clazz.getPackage();
    Tree packageTree;
    if (pkg == null) {
      packageTree = TreeFactory.newTree(NodeType.PackageName);
    } else {
      packageTree = TreeFactory.newTree(NodeType.PackageName, pkg.getName());
    }
    packageTree.addChild(TreeFactory.newTree(NodeType.ClassName, simpleClassName));
    return packageTree;
  }
}
