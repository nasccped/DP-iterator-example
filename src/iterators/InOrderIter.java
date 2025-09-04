package iterators;

import tree.BinaryTree;
import tree.BinaryNode;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Binary Tree in order traversal (node-left<recursive> =>
 * current-node => node-right<recursive>) implementation.
 */
public class InOrderIter<T extends Comparable<T>>
extends AbstractTreeIterator<T> {

    public InOrderIter(BinaryTree<T> bt) { super(bt); }

    @Override
    protected void populateQueue(BinaryNode<T> node) {
        if (node == null) return;
        populateQueue(node.getLeftNode());
        queue.add(node.getCurrent());
        populateQueue(node.getRightNode());
    }
}
