package org.karaffe.compiler.tree.walker;

public class PrintTreeWalker extends TreeWalkerAdapter {

  @Override
  public void onNullTree() {
    System.out.println("<null>");
  }
}
