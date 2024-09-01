package icu.clemon.jcommon.utils.anytree;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node<T> {

  private T data;

  private List<Node<T>> children;

  public void addChild(Node<T> child) {
    if (children == null) {
      children = new ArrayList<>();
    }
    children.add(child);
  }
}
