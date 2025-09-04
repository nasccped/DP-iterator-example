package iterators;

import tree.BinaryNode;
import tree.BinaryTree;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Middle ground between the TreeIterator interface and its final
 * implementation. It's a lot usefull since the majority of interface
 * methods are copy + paste in all variants.
 */
abstract class AbstractTreeIterator<T extends Comparable<T>>
implements DPIterator<T> {

    private BinaryNode<T> origin;
    protected Queue<T> queue;
    private T current;

    protected AbstractTreeIterator(BinaryTree<T> bt) {
        this.origin = bt.getRoot();
        this.queue = new LinkedList<T>();
        this.current = null;
    }

    @Override
    public void first() {
        if (!queue.isEmpty()) queue = new LinkedList<T>();
        populateQueue(origin);
        next();
    }

    protected abstract void populateQueue(BinaryNode<T> node);

    @Override
    public void next() { current = queue.poll(); }

    @Override
    public boolean isDone() { return queue.isEmpty() && current == null; }

    @Override
    public T getCurrentItem() { return current; }
}
