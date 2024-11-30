package icu.clemon.jcommon;

import icu.clemon.jcommon.utils.anytree.Node;
import icu.clemon.jcommon.utils.anytree.NodeMapper;
import org.junit.jupiter.api.Test;

public class AnyTreeTest {

  @Test
  void map() {
    var root = new Node<String>();
    root.setData("root");

    var node1 = new Node<String>();
    node1.setData("node1");
    var node2 = new Node<String>();
    node2.setData("node2");
    var node3 = new Node<String>();
    node3.setData("node3");

    root.addChild(node1);
    root.addChild(node2);
    root.addChild(node3);

    var node21 = new Node<String>();
    node21.setData("node21");
    var node22 = new Node<String>();
    node22.setData("node22");

    node2.addChild(node21);
    node2.addChild(node22);

    var mapper = new NodeMapper<String, String>((val) -> "m:"+val);

    System.out.println(mapper.map(root));
  }

}
