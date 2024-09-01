package icu.clemon.jcommon.utils.anytree;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator<T> implements Iterator<Node<T>> {

  private final Stack<Node<T>> stack = new Stack<>();

  public TreeIterator(Node<T> root) {
    if (root != null && root.getChildren() != null && !root.getChildren().isEmpty()) {
      root.getChildren().forEach(stack::push);
    }
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public Node<T> next() {
    var node = stack.pop();
    if (node.getChildren() != null && !node.getChildren().isEmpty()) {
      node.getChildren().forEach(stack::push);
    }
    return node;
  }
}
