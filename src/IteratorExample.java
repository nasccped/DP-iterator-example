/**
 * This file was generated using kojamp CLI-app
 * Take a look at the official repository (https://github.com/nasccped/kojamp)
 */

import tree.BinaryTree;
import iterators.DPIterator;
import iterators.PreOrderIter;
import iterators.PostOrderIter;
import iterators.InOrderIter;

/**
 * App's main function
 */
public class IteratorExample {

    /** Integer binary tree (in-order traversal example) */
    private static BinaryTree<Integer> bint = new BinaryTree<>();
    /** String binary tree (pre-order traversal example) */
    private static BinaryTree<String> bstr = new BinaryTree<>();
    /** Double binary tree (pre-order traversal example) */
    private static BinaryTree<Double> bdou = new BinaryTree<>();
    private static DPIterator<?> iter;
    private static int nth;

    /** Populate all the trees with this static block */
    static {
        // integer
        bint.add(2);
        bint.add(13);
        bint.add(11);
        bint.add(17);
        bint.add(5);
        bint.add(3);
        bint.add(7);

        // string
        bstr.add("James Gosling");
        bstr.add("Linus Torvalds");
        bstr.add("Bjarne Stroustrup");
        bstr.add("Alan Turing");
        bstr.add("Dennis Ritchie");
        bstr.add("Guido van Rossum");
        bstr.add("Tim Berners-Lee");

        // double
        bdou.add(1.61803);
        bdou.add(0.57721);
        bdou.add(0.00000);
        bdou.add(1.41421);
        bdou.add(3.14159);
        bdou.add(0.99999);
        bdou.add(2.71828);
    }

    public static void main(String[] args) {
        clearTerminal();

        Printer.echoln(green("Iterator Example"));
        Printer.echoln(white("================"));
        Printer.echoln();

        String sourcePath = "src/IteratorExample.java";
        Printer.echoln(
            "All the important calls are at `"
            + yellow(sourcePath) + "`'s main function.\n"
            + "You should uncomment the code to see the impl working...\n");
        // uncomment the following code
        // inOrderExample();
        // preOrderExample();
        // postOrderExample();
    }

    private static void inOrderExample() {
        nth = 1;
        iter = new InOrderIter<Integer>(bint);

        printExampleTitle("in-order iteration");

        for (iter.first(); !iter.isDone(); iter.next()) {
            printObjectWithPosition(nth, iter.getCurrentItem());
            nth++;
        }
        Printer.echoln();
    }

    private static void preOrderExample() {
        nth = 1;
        iter = new PreOrderIter<String>(bstr);

        printExampleTitle("pre-order iteration");

        for (iter.first(); !iter.isDone(); iter.next()) {
            printObjectWithPosition(nth, iter.getCurrentItem());
            nth++;
        }
        Printer.echoln();
    }

    private static void postOrderExample() {
        nth = 1;
        iter = new PostOrderIter<Double>(bdou);

        printExampleTitle("post-order iteration");

        for (iter.first(); !iter.isDone(); iter.next()) {
            printObjectWithPosition(nth, iter.getCurrentItem());
            nth++;
        }
        Printer.echoln();
    }

    /** Util for element fast printing */
    private static void printObjectWithPosition(int position, Object o) {
        Printer.echo(yellow(String.format("%d. nth", position)));
            Printer.echo(" element: ");
            Printer.echoln(iter.getCurrentItem());
    }

    /** Visual util */
    private static void clearTerminal() {
        Printer.echo("\u001b[2J\u001b[H");
    }

    private static void printExampleTitle(String title) {
        Printer.echoln(yellow(title));
        Printer.echoln();
    }

    // turn strings into colored ones -----------------------------------------
    private static String green(String content) {
        return String.format("\u001b[92m%s\u001b[0m", content);
    }

    private static String white(String content) {
        return String.format("\u001b[97m%s\u001b[0m", content);
    }

    private static String yellow(String content) {
        return String.format("\u001b[93m%s\u001b[0m", content);
    }
}
