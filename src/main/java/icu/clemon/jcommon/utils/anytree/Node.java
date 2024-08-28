package icu.clemon.jcommon.utils.anytree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node<T> {

    private String name;
    private T data;

    private Node<T> parent = null;
    private List<Node<T>> children;


    public Node(String name, Node<T> parent, T data) {
        this.name = name;
        this.data = data;
        if (parent != null) {
            this.parent = parent;
            parent.addChild(this);
        }
    }

    public Node(String name, Node<T> parent) {
        this(name, parent, null);
    }

    public Node(String name, T data) {
        this(name, null, data);
    }

    public Node(String name) {
        this(name, null, null);
    }

    public void addChild(Node<T> child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public boolean isRoot() {
        return parent == null;
    }

}
