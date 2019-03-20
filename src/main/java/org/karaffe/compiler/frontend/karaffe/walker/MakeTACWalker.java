package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TreeWalker;
import org.karaffe.compiler.util.NumGen;

import java.util.ArrayList;
import java.util.List;

import static org.karaffe.compiler.tree.NodeType.Apply;
import static org.karaffe.compiler.tree.NodeType.Argument;
import static org.karaffe.compiler.tree.NodeType.Arguments;
import static org.karaffe.compiler.tree.NodeType.DefVar;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.TypeName;

public class MakeTACWalker extends TreeWalker {
  private final NumGen numGen = new NumGen();

  @Override
  public void onApply(Tree tree) {
    List<Tree> originalArgs = new ArrayList<>(tree.dig(NodeType.Arguments).filter(a -> a.hasChildren(NodeType.Argument)).map(a -> a.findAllFromChildren(NodeType.Argument)).orElseGet(ArrayList::new));
    if (originalArgs.isEmpty()) {
      return;
    }
    List<Tree> names = new ArrayList<>();
    List<Tree> generated = new ArrayList<>();
    for (Tree originalArg : originalArgs) {
      Tree generatedName = Identifier.create("$" + numGen.next());
      Tree generatedDefVar =
        DefVar.create().in(
          generatedName,
          TypeName.create("__ANY__"),
          originalArg.getChildren().get(0)
        );
      generated.add(generatedDefVar);
      names.add(Argument.create().in(generatedName));
    }
    Tree newArgs = Arguments.create().in(names.toArray(new Tree[]{}));
    tree.replaceThis(Apply.create().in(
      tree.getChildren().get(0),
      newArgs
    ));
    generated.forEach(tree::insertBefore);
  }
}
