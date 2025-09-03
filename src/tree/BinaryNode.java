package tree;

/**
 * And BinaryTree member and core functionalities owner (such as
 * value getters + recursive insertion).
 */
public class BinaryNode<T extends Comparable<T>> {

    private T current;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    protected BinaryNode(T value) {
        this.current = value;
        this.left = null;
        this.right = null;
    }

    public void add(T value) {
        if (value.compareTo(current) < 0) {
            if (left == null) left = new BinaryNode<>(value);
            else left.add(value);
        } else {
            if (right == null) right = new BinaryNode<>(value);
            else right.add(value);
        }
    }

    public T getCurrent() { return current; }

    public BinaryNode<T> getLeftNode() { return left; }

    public BinaryNode<T> getRightNode() { return right; }
}
