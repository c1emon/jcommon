package icu.clemon.jcommon.utils.anytree;

import java.util.function.Function;

public class NodeMapper<T,S> {

  private final Function<T,S> mapper;

  public NodeMapper(Function<T,S> mapper) {
    this.mapper = mapper;
  }

  private Node<S> mapChild(Node<T> root) {
    var newRoot = new Node<S>();
    newRoot.setData(mapper.apply(root.getData()));

    if (root.getChildren() != null) {
      for (var child : root.getChildren()) {
        newRoot.addChild(mapChild(child));
      }
    }

    return newRoot;
  }

  public Node<S> map(Node<T> root) {
    var newRoot = new Node<S>();
    newRoot.setData(mapper.apply(root.getData()));

    if (root.getChildren() != null) {
      for (var child : root.getChildren()) {
        newRoot.addChild(mapChild(child));
      }
    }

    return newRoot;
  }
}
