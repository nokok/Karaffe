package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TreeWalker;
import org.karaffe.compiler.util.NumGen;

import java.util.ArrayList;
import java.util.List;

import static org.karaffe.compiler.tree.NodeType.Apply;
import static org.karaffe.compiler.tree.NodeType.Argument;
import static org.karaffe.compiler.tree.NodeType.Arguments;
import static org.karaffe.compiler.tree.NodeType.DefVar;
import static org.karaffe.compiler.tree.NodeType.Empty;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.TypeName;
import static org.karaffe.compiler.tree.NodeType.VarName;

public class MakeTACWalker extends TreeWalker {
  private final NumGen numGen = new NumGen();

  @Override
  public void onApply(Tree tree) {
    Tree originalTarget = unwrap(tree.getChildren().get(0));
    Tree originalOperator = tree.getChildren().get(1);
    Tree originalArgs = tree.getChildren().get(2);
    if (originalTarget.getNodeType() == Empty) {
      return;
    }
    String generatedName = "$" + numGen.next();
    Tree defVar = DefVar.create().in(
      Identifier.create(generatedName),
      TypeName.create("__ANY__"),
      originalTarget
    );
    tree.insertBefore(defVar);
    List<Tree> newArgs = new ArrayList<>();
    for (Tree arg : originalArgs.getChildren()) {
      String g = "$" + numGen.next();
      Tree aDefVar = DefVar.create().in(
        Identifier.create(g),
        TypeName.create("__ANY__"),
        unwrap(arg)
      );
      tree.insertBefore(aDefVar);
      newArgs.add(Argument.create().in(VarName.create(g)));
    }
    Tree args = Arguments.create();
    newArgs.forEach(args::addChild);
    tree.replaceThis(Apply.create().in(
      VarName.create(generatedName),
      originalOperator,
      args
    ));
  }

  private Tree unwrap(Tree tree) {
    switch (tree.getNodeType()) {
    case Argument:
      return tree.getChildren().get(0);
    default:
      return tree;
    }
  }

}
