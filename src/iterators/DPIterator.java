package iterators;

/**
 * The "Design Patterns" book inspired iterator interface.
 *
 * To avoid info overload, you can read the docs at the project's
 * `README`, or take a look at the official repository
 * (https://github.com/nasccped/DP-iterator-example)
 */
public interface DPIterator<T> {

    /**
     * Called first, but just initializes the iterator queue/stack
     * instead.
     */
    public void first();

    /** Moves to the next item in the iteration. */
    public void next();

    /** Checks if the end of the iteration has been reached. */
    public boolean isDone();

    /** Returns the current item of the iteration. */
    public T getCurrentItem();
}
