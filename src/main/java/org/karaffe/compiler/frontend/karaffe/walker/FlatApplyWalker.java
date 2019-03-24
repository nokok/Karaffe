package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.TreeFactory;
import org.karaffe.compiler.tree.walker.TreeWalker;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class FlatApplyWalker extends TreeWalker {

  private final int HIGHEST_PRECEDENCE = 0;
  private final int LOWEST_PRECEDENCE = Integer.MAX_VALUE;
  private Map<String, Integer> builtInOperators = new HashMap<>();
  private Set<String> rightAssoc = new HashSet<>();
  private Set<String> noAssoc = new HashSet<>();
  private Map<String, Integer> userDefinedOperators = new HashMap<>();

  public FlatApplyWalker() {
    builtInOperators.put(".", 50);
    builtInOperators.put("<<", 100);
    noAssoc.add("<<");
    builtInOperators.put(">>", 100);
    noAssoc.add(">>");

    builtInOperators.put("*", 200);
    builtInOperators.put("/", 200);
    builtInOperators.put("%", 200);
    builtInOperators.put("&", 200);

    builtInOperators.put("+", 300);
    builtInOperators.put("-", 300);
    builtInOperators.put("|", 300);
    builtInOperators.put("^", 300);

    builtInOperators.put("..<", 400);
    noAssoc.add("..<");
    builtInOperators.put("...", 400);
    noAssoc.add("...");

    builtInOperators.put("is", 500);
    builtInOperators.put("as", 500);
    builtInOperators.put("as?", 500);
    builtInOperators.put("as!", 500);

    builtInOperators.put("<", 600);
    builtInOperators.put("<=", 600);
    builtInOperators.put(">", 600);
    builtInOperators.put(">=", 600);
    builtInOperators.put("==", 600);
    builtInOperators.put("!=", 600);
    builtInOperators.put("===", 600);
    builtInOperators.put("!==", 600);

    builtInOperators.put("&&", 700);

    builtInOperators.put("||", 800);

    builtInOperators.put("@", 850);
    builtInOperators.put(":", 850);
    builtInOperators.put("#", 850);

    builtInOperators.put("=", 900);
    rightAssoc.add("=");
    builtInOperators.put("*=", 900);
    rightAssoc.add("*=");
    builtInOperators.put("/=", 900);
    rightAssoc.add("/=");
    builtInOperators.put("%=", 900);
    rightAssoc.add("%=");
    builtInOperators.put("+=", 900);
    rightAssoc.add("+=");
    builtInOperators.put("-=", 900);
    rightAssoc.add("-=");
    builtInOperators.put("<<=", 900);
    rightAssoc.add("<<=");
    builtInOperators.put(">>=", 900);
    rightAssoc.add(">>=");
    builtInOperators.put("&=", 900);
    rightAssoc.add("&=");
    builtInOperators.put("|=", 900);
    rightAssoc.add("|=");
    builtInOperators.put("^=", 900);
    rightAssoc.add("^=");
  }

  @Override
  public void onFlatApply(Tree tree) {
    List<Tree> exprs = tree.getChildren();
    ArrayDeque<Tree> q = new ArrayDeque<>();
    Stack<Tree> stack = new Stack<>();
    for (Tree node : exprs) {
      switch (node.getNodeType()) {
      case Block:
      case IntLiteral:
      case This:
      case StringLiteral:
      case Apply:
      case VarName:
      case Select:
        q.add(node);
        break;
      case BinOp:
        if (stack.empty()) {
          stack.push(node);
          continue;
        }
        if (stack.empty()) {
          break;
        }
        Tree o2 = stack.peek();
        if (isHigherOrEqualsPriority(o2.getName(), node.getName()) && isLeftAssoc(node.getName())) {
          stack.push(node);
        } else if (isNoAssoc(node.getName())) {
          stack.push(node);
        } else if (isRightAssoc(node.getName())) {
          stack.push(node);
        } else {
          while (!stack.empty()) {
            q.add(stack.pop()); //o2
          }
          stack.push(node);
        }
        break;
      default:
        throw new IllegalStateException(node.getNodeType().name());
      }
    }
    while (!stack.empty()) {
      q.add(stack.pop());
    }

    Stack<Tree> vm = new Stack<>();
    while (!q.isEmpty()) {
      Tree t = q.getFirst();
      q.removeFirst();
      if (t.getNodeType() == NodeType.BinOp) {
        Tree right = vm.pop();
        Tree left = vm.pop();
        Tree apply = TreeFactory.newTree(NodeType.Apply, t.getPosition());
        Tree arguments = TreeFactory.newTree(NodeType.Arguments, right.getPosition());
        Tree argument = TreeFactory.newTree(NodeType.Argument, right.getPosition());
        argument.addChild(right);
        arguments.addChild(argument);
        apply.addChild(left);
        apply.addChild(t);
        apply.addChild(arguments);
        vm.push(apply);
      } else {
        vm.push(t);
      }
    }
    tree.replaceThis(vm.pop());
  }

  private boolean isLeftAssoc(String op) {
    if (isRightAssoc(op)) {
      return false;
    }
    return !isNoAssoc(op);
  }

  private boolean isRightAssoc(String op) {
    return rightAssoc.contains(op);
  }

  private boolean isNoAssoc(String op) {
    return noAssoc.contains(op);
  }

  private boolean isHigherOrEqualsPriority(String peek, String expr) {
    Integer i1 = builtInOperators.get(peek);
    Integer i2 = builtInOperators.get(expr);
    if (i1 == null && Character.isAlphabetic(peek.charAt(0))) {
      i1 = LOWEST_PRECEDENCE;
    } else if (i1 == null) {
      i1 = HIGHEST_PRECEDENCE;
    }
    if (i2 == null && Character.isAlphabetic(expr.charAt(0))) {
      i2 = LOWEST_PRECEDENCE;
    } else if (i2 == null) {
      i2 = HIGHEST_PRECEDENCE;
    }
    return i1 > i2;
  }
}
