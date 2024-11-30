package icu.clemon.jcommon.utils.anytree;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

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
