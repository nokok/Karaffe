package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class FlatApplyWalker extends TreeWalkerAdapter {

  private final int HIGHEST_PRECEDENCE = 10;
  private final int LOWEST_PRECEDENCE = 1000;
  private Map<String, Integer> operatorPrecedences = new HashMap<>();

  public FlatApplyWalker() {
    operatorPrecedences.put("*", 100);
    operatorPrecedences.put("/", 100);
    operatorPrecedences.put("%", 100);
    operatorPrecedences.put("+", 120);
    operatorPrecedences.put("-", 120);
    operatorPrecedences.put("!", 130);
    operatorPrecedences.put(">", 140);
    operatorPrecedences.put("<", 140);
    operatorPrecedences.put("&", 150);
    operatorPrecedences.put("^", 160);
    operatorPrecedences.put("|", 170);
  }

  @Override
  public void onFlatApply(Tree tree) {
    List<Tree> exprs = tree.getChildren();
    ArrayDeque<Tree> q = new ArrayDeque<>();
    Stack<Tree> stack = new Stack<>();
    for (Tree node : exprs) {
      switch (node.getNodeType()) {
      case IntLiteral:
      case This:
      case StringLiteral:
      case Apply:
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
        if (isHigherOrEqualsPriority(o2.getName(), node.getName())) {
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
        Tree apply = new Tree(NodeType.Apply, "()", t.getPosition());
        Tree select = new Tree(NodeType.Select, t.getPosition());
        select.addChild(t);
        select.addChild(left);
        Tree arguments = new Tree(NodeType.Arguments, right.getPosition());
        Tree argument = new Tree(NodeType.Argument, right.getPosition());
        argument.addChild(right);
        arguments.addChild(argument);
        apply.addChild(select);
        apply.addChild(arguments);
        vm.push(apply);
      } else {
        vm.push(t);
      }
    }
    tree.replaceThis(vm.pop());
  }

  private boolean isHigherOrEqualsPriority(String peek, String expr) {
    Integer i1 = operatorPrecedences.get(peek.substring(0, 1));
    Integer i2 = operatorPrecedences.get(expr.substring(0, 1));
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
