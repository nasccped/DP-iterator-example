<div align="center">

DP-iterator-example
===================

[![built in](https://img.shields.io/badge/built_in-kojamp_0.1.1-blue?)](https://github.com/nasccped/kojamp)
[![project kind: java](https://img.shields.io/badge/project_kind-java-orange?)](#)

</div>

This repository is an attempt to implement the iterator pattern code
piece located at _Design Patters: Elements of Reusable Object-Oriented Software_[^design-patterns-book]
_(page 66/358)_.


The implementation is an interface-based contract that offers useful
ways to iterate over Objects that holds a collection of elements
(`List`, `Set`, `Map`, `...`). I chose a binary tree 'cause it
provides three common ways to iterate over it's data _(traversal
ordering)_:

<div align="center" id="image-01">

<img
src="./images/orders-image.jpg"
alt="image 1: orders repr"
height="300px">

_image 1: orders repr_

<table>
  <tr>
    <th>Pre-Order traversal</th>
    <td>when we look to the current node, then, the left and right
    one, respectively</td>
  </tr>
  <tr>
    <th>Post-Order traversal</th>
    <td>when we look to the left node, then, the right and current
    one, respectively</td>
  </tr>
  <tr>
    <th>In-Order traversal</th>
    <td>when we look to the left node, then, the current and right
    one, respectively</td>
  </tr>
</table>

</div>

> [!TIP]
>
> Keep in mind that all ordering traversals works in a recursive way,
> so:
>
> - `Pre-Order` will start the iteration from the current node and
>   then, navigates left to right in our tree _(trees first, leafs
>   last)**(unsorted)**_
> - `Post-Order` will start the iteration from the left node and
>   then, goes to right in our tree, but leaving the current node for
>   last _(leafs first, trees last)**(unsorted)**_
> - `In-Order` will start from the _"most-left"_ subtree and navigate
>   to the _"most-right"_ by passing through the current node _(left
>   leaf - current node - right leaf schema)**(sorted)**_

## Cloning, compiling and running

To clone the project you can:

```sh
# clone using git
git clone https://github.com/nasccped/DP-iterator-example
# remove git folder (works for bash based terminals, only)
rm -rf DP-iterator-example/.git/
# goto dir
cd DP-iterator-example
```

### Compiling

Since this project was built under the kojamp[^kojamp] project
manager, you can use it to handle the project, or:

- compile using a bash terminal (Linux):
```sh
javac $(find src -type f -name **.java) -d out
```
- compile using a powershell terminal (Windows):
```powershell
javac (Get-ChildItem -Recurse -Path src -Filter *.java | ForEach-Object { $_.FullName }) -d out
```

Finally, you can execute the program by running:
```sh
java --class-path out IteratorExample
```

> [!IMPORTANT]
>
> Make sure to uncomment the code that _"runs"_ the stuff:
>
> ```java
> public static void main(String[] args) {
>     // a lot of code...
>     String sourcePath = "src/IteratorExample.java";
>     Printer.echoln(
>         "All the important calls are at `"
>         + yellow(sourcePath) + "`'s main function.\n"
>         + "You should uncomment the code to see the impl working...\n");
>     // uncomment the following code (~line 70)
>     // inOrderExample();
>     // preOrderExample();
>     // postOrderExample();
>   }
> ```

## Why not use the Java's built-in `Iterator` interface?

If you already have experience with Java development, you probably
know that the JDK provides an
[`Iterator`](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)
utility. So, why did I create a new interface instead of using the
built in one?

### The `Iterator` reference

Even though the Java's iterator provide useful features, my
interface is based on this C++ code sample (from the _Design
Patterns_ book):

```cpp
Glyph* g;
Iterator<Glyph*>* i = g->CreateIterator();

for (i->First(); !i->IsDone(); i->Next()) {
    Glyph* child = i->CurrentItem();

    // do something with current child
}
```

Key points (apparently):
- `i->First()` initializes the iterator
- `i->IsDone()` checks if the iterator is done
- `i->Next()` moves to the next item in our iterator
- `i->CurrentItem()` returns the current item in our iterator

So, I've decided to use this features to build my own iterator
interface
([`src/iterators/DPIterator.java`](src/iterators/DPIterator.java)).

> [!NOTE]
>
> I mostly mention the interface, but abstract class concept is also
> used under this project. Consider _"interface"_ not as the Java's
> feature, but the concept itself. (**interface** as Java's both
> `interface` and `abstract class`).

## Iterator implementation

The iterator object works like the
[previous code sample](#the-iterator-reference). By using a queue
data structure (`LinkedList` in this scenario), we can
**initialize** and gradually removes the items to _"go next"_ with
our iterator.

Since the `First`, `IsDone`, `Next` and `CurrentItem` will works all
the same independently of the traversal kind, we can build an
`abstract class`[^abstract-class] to implement all the
_default methods_ and avoid code repetition:

```java
/**
 * Interface file tree:
 * interface-package ┐
 *                   ├ Interface.java
 *                   ├ AbstractClass.java
 *                   └ concrete classes ...
 */

// AbstractClass.java
abstract class Abstract<Generic> implements Interface<Generic> {
    // inner properties...
    // interface default implementations...
}
```

### Properties

Analyzing the code reference, we can define a `BinaryTree` node as
**origin** (init the iterator process from this object), a `Queue`
that holds the elements as **"next"** and a `Generic type` variable
that holds the **current** object:

```java
abstract class Abstract<Generic> implements Interface<Generic> {
    private BinaryNode<Generic> origin;
    protected Queue<Generic> queue;
    private Generic current;
}
```

### Concrete methods

Here, we can implement all the methods that will works the same for
any tree traversal!

#### `First` method

Since our iterator is a queue based object, we can initialize it by
populating our queue and moving to the next item:

```java
@Override
public void first() {
    if (!queue.isEmpty()) queue.clear();
    populateQueue(origin);
    next();
}
```

> [!NOTE]
>
> `Binary Tree` is a recursive data structure, so, we need to
> populate our queue recursively. Keep in mind that the populate
> method must differ from a traversal kind to another (the
> `populateQueue` method should be an
> [abstract method](#abstract-methods)).

#### `Next` method

Points the `current` variable to the next object in our iterator queue:

```java
@Override
public void next() { current = queue.poll(); }
```

I'm using `poll` instead of `remove` 'cause the `current` variable
should be `null` to indicates the iterator ends.

#### `IsDone` method

Returns the iteration is done by checking the queue (no `Object`
remaining) and the `current` variable (`null` obj):

```java
@Override
public boolean isDone() { return queue.isEmpty() && current == null; }
```

#### `CurrentItem` method

Returns the `Object` being held by the `current` variable:

```java
@Override
public Generic getCurrentItem() { return current; }
```

### Abstract methods

Finally, the queue populate method is different for each traversal
kind, so it should be implemented only in the
`concrete class`[^concrete-class].

Let's take the [`In-Order`](#image-01) traversal as example:

1. **base case:** is where our queue pushing stops. We'll never push a
   `null` object to our queue. That's our base case!
2. **node order:** `in-order` traversal works **"most left to most
   right"** (sorted). We should push the left node first, then, the
   current node, and finally, the right one:

```java
public class InOrderIter<T extends Comparable<T>>
extends Abstract<T> {
    // receives a binary tree node (allow recursion)
    @Override
    protected void populateQueue(BinaryNode<T> node) {
        // if non valid object, stops recursion
        if (node == null) return;
        populateQueue(node.getLeftNode());  // go left node recursion
        queue.add(node.getCurrent());       // insert current node value
        populateQueue(node.getRightNode()); // go right node recursion
    }
}
```

The `populateQueue` implement will be different for each child
class[^child-class]. They all works the same but differ in object
pushing order!

[^design-patterns-book]: _Design Patters: Elements of Reusable Object-Oriented Software_
  is a software engineering book that describes software design
  patterns. You can find it at [amazon website](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612).

[^kojamp]: Kojamp is a Java and Kotlin project manager I built. You
  can find more info on it's
  [official repository](https://github.com/nasccped/kojamp)

[^abstract-class]: `abstract class` serves as a blueprint for other
  classes and cannot be instantiated directly, meaning you cannot
  create objects of an abstract class. More info at
  [Oracle website](https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html).

[^concrete-class]: Concrete class it's a class that is fully
  implemented and can be instantiated, unlike an abstract class or
  interface.

[^child-class]: Child class, also known as a subclass or derived
  class, is a new class that inherits properties and methods from an
  existing class, called the parent class (or base class).
