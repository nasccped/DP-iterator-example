<div align="center">

DP-iterator-example
===================

[![built in](https://img.shields.io/badge/built_in-kojamp_0.1.1-blue?)](https://github.com/nasccped/kojamp)
[![project kind: java](https://img.shields.io/badge/project_kind-java-orange?)](#)

</div>

This repository is an attempt to implement the iterator pattern code
piece located at _Design Patters: Elements of Reusable Object-Oriented Software_[^design-patterns-book]
_(page 66/358)_.

The implementation is an interface-based contract that offers usefull
ways to iterate over Objects that holds a collection of elements
(`List`, `Set`, `Map`, `...`). I chose a binary tree 'cause it
provides three common ways to iterate over it's data:

1. **Pre-Order:** when we look to the current node, then, the left
   and right one, respectively.
2. **Post-Order:** when we look to the left node, then, the right and
   current one, respectively.
3. **In-Order:** when we look to the left node, then, the current and
   right one, respectively.

[^design-patterns-book]: _Design Patters: Elements of Reusable Object-Oriented Software_
  is a software engineering book that describes software design
  patterns. You can find it at [amazon website](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612).
