package iterators;

import tree.BinaryTree;
import tree.BinaryNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Tree pre order traversal (current-node =>
 * node-left<recursive> => node-right<recursive>) implementation.
 */
public class PreOrderIter<T extends Comparable<T>> implements DPIterator<T> {

    private BinaryNode<T> origin;
    private Queue<T> queue;
    private T current;

    public PreOrderIter(BinaryTree<T> bt) {
        this.origin = bt.getRoot();
        this.queue = new LinkedList<T>();
        this.current = null;
    }

    @Override
    public void first() {
        if (!queue.isEmpty()) queue = new LinkedList<T>();
        recursiveQueuePush(queue, origin);
        next();
    }

    private static <U extends Comparable<U>>
    void recursiveQueuePush(Queue<U> queue, BinaryNode<U> node) {
        if (node == null) return;
        queue.add(node.getCurrent());
        recursiveQueuePush(queue, node.getLeftNode());
        recursiveQueuePush(queue, node.getRightNode());
    }

    @Override
    public void next() {
        current = queue.poll();
    }

    @Override
    public boolean isDone() { return queue.isEmpty() && current == null; }

    @Override
    public T getCurrentItem() { return current; }
}
