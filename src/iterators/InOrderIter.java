package iterators;

import tree.BinaryTree;
import tree.BinaryNode;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Binary Tree in order traversal (node-left<recursive> =>
 * current-node => node-right<recursive>) implementation.
 */
public class InOrderIter<T extends Comparable<T>> implements DPIterator<T> {

    private BinaryNode<T> origin;
    private Queue<T> queue;
    private T current;

    public InOrderIter(BinaryTree<T> bt) {
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
        recursiveQueuePush(queue, node.getLeftNode());
        queue.add(node.getCurrent());
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
