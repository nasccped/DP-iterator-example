/**
 * A cool way to print things without having to "System.out.println" the
 * entire program.
 */
class Printer {

    protected static void echoln(Object o) {
        System.out.println(o);
    }

    protected static void echoln() {
        System.out.println();
    }

    protected static void echo(Object o) {
        System.out.print(o);
    }
}
