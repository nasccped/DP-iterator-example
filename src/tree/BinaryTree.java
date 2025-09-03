package tree;

/**
 * Small representation of a BinaryTree implementation.
 *
 * This class only provides tree's fast-access methods, such as the
 * public constructor, the add (append new items) and the getRoot
 * methods. All the "hard stuff" is done be the "BinaryNode" member.
 */
public class BinaryTree<T extends Comparable<T>> {

    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void add(T value) {
        if (root == null) root = new BinaryNode<>(value);
        else root.add(value);
    }
}
