package iterators;

import tree.BinaryTree;
import tree.BinaryNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Tree pre order traversal (current-node =>
 * node-left<recursive> => node-right<recursive>) implementation.
 */
public class PreOrderIter<T extends Comparable<T>>
extends AbstractTreeIterator<T> {

    public PreOrderIter(BinaryTree<T> bt) { super(bt); }

    @Override
    protected void populateQueue(BinaryNode<T> node) {
        if (node == null) return;
        queue.add(node.getCurrent());
        populateQueue(node.getLeftNode());
        populateQueue(node.getRightNode());
    }
}
