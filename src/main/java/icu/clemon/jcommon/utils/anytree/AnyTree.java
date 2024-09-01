package icu.clemon.jcommon.utils.anytree;

import jakarta.validation.constraints.NotNull;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class AnyTree<T> implements Iterable<Node<T>> {

  private final Node<T> root;

  public AnyTree(Node<T> root) {
    this.root = root;
  }

  @Override
  @NotNull
  public Iterator<Node<T>> iterator() {
    return new TreeIterator<>(root);
  }

  @Override
  public void forEach(Consumer<? super Node<T>> action) {
    Objects.requireNonNull(action);
    for (Node<T> t : this) {
      action.accept(t);
    }
  }
}
