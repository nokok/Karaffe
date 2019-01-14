package org.karaffe.compiler.frontend.karaffe.util;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

import java.util.Optional;
import java.util.stream.Stream;

public class TypeTreeBuilder {
  public Tree buildTreeFromClass(Class<?> clazz) {
    String simpleClassName = clazz.getSimpleName();
    Package pkg = clazz.getPackage();
    Tree packageTree;
    if (pkg == null) {
      // unnamed package
    } else {
      packageTree = structuredPackage(pkg);
    }
    return new Tree(NodeType.Error);
  }

  private Tree structuredPackage(Package pkg) {
    String packageName = pkg.getName();
    if (packageName.isEmpty() || !packageName.contains(".")) {
      // unnamed package
      return new Tree(NodeType.PackageName, packageName);
    }
    String[] pkgs = packageName.split("\\.");
    Optional<Tree> reduce = Stream.of(pkgs).map(p -> new Tree(NodeType.PackageName, p)).reduce((parent, child) -> {
      parent.addChild(child);
      return parent;
    });
    return reduce.orElseThrow(IllegalArgumentException::new);
  }
}
