package iterators;

import tree.BinaryTree;
import tree.BinaryNode;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Binary Tree post order traversal (node-left<recursive> =>
 * node-right<recursive> => current-node) implementation.
 */
public class PostOrderIter<T extends Comparable<T>>
extends AbstractTreeIterator<T> {

    public PostOrderIter(BinaryTree<T> bt) { super(bt); }

    @Override
    protected void populateQueue(BinaryNode<T> node) {
        if (node == null) return;
        populateQueue(node.getLeftNode());
        populateQueue(node.getRightNode());
        queue.add(node.getCurrent());
    }
}
