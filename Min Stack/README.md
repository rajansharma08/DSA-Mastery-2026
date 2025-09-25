# [155. Min Stack](https://leetcode.com/problems/min-stack/)



## 1. Problem Statement

Design a stack that supports `push`, `pop`, `top`, and retrieving the minimum element in **constant time**.

Implement the `MinStack` class:
* `MinStack()` initializes the stack object.
* `void push(int val)` pushes the element `val` onto the stack.
* `void pop()` removes the element on the top of the stack.
* `int top()` gets the top element of the stack.
* `int getMin()` retrieves the minimum element in the stack.

Each function should run in $O(1)$ time.

---
## 2. Intuition

A standard stack gives us $O(1)$ time for `push`, `pop`, and `top`. The main challenge is the `getMin()` operation, which must also be $O(1)$. A naive approach of iterating through the stack to find the minimum would take $O(n)$ time, failing the problem's requirement.

The key to an $O(1)$ solution is to store additional information with each element as we push it onto the stack. Instead of just storing the value, we can store a **pair of integers**:
1.  The `value` itself.
2.  The **minimum element in the stack at the moment that value was pushed**.

Let's say we want to `push(val)`. We look at the minimum of the element currently at the top of the stack (`currentMin`). The new minimum to be stored is `Math.min(val, currentMin)`. We then push the pair `(val, newMin)` onto the stack.

This way, the `second` element of the pair at the top of our stack always holds the current minimum for the entire stack.
* `getMin()` is now simple: just peek at the top pair and return its second value.
* `pop()` is also simple: when we pop a pair, the previous minimum value is automatically restored at the new top of the stack.

---
## 3. Algorithm

1.  The underlying data structure will be a `Stack` that stores a `Pair` of integers, where `Pair` is a simple class or tuple containing `<value, currentMinimum>`.
2.  **`push(val)`:**
    a. Check if the stack is empty. If so, the `newMin` is `val` itself.
    b. If the stack is not empty, get the `currentMin` from the pair at the top (`stack.peek().second`).
    c. Calculate the `newMin` for the current state: `newMin = Math.min(val, currentMin)`.
    d. Push a `new Pair(val, newMin)` onto the stack.
3.  **`pop()`:**
    a. Pop the top `Pair` from the stack.
4.  **`top()`:**
    a. Return the `value` from the top `Pair`: `stack.peek().first`.
5.  **`getMin()`:**
    a. Return the `minimum` from the top `Pair`: `stack.peek().second`.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(1)$ for all operations (`push`, `pop`, `top`, and `getMin`).
    * Each operation is a simple action on the top of the underlying stack, which takes constant time.

* **Space Complexity:** $O(n)$
    * For `n` elements pushed onto the stack, we store `n` pairs, each containing two integers. The space usage is linear with the number of elements.