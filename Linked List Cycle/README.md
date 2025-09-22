# [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)



## 1. Problem Statement

Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

A cycle exists if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that the tail's `next` pointer is connected to. Note that `pos` is not passed as a parameter.

Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

---
## 2. Intuition

A common approach is to use a `HashSet` to store each node we visit. As we traverse the list, if we encounter a node that's already in our set, we've found a cycle. This works, but it requires $O(n)$ extra space.

A more optimal and elegant solution is the **Fast & Slow Pointer** technique, famously known as **Floyd's Tortoise and Hare algorithm**. This approach uses two pointers that traverse the list at different speeds.

**The "Racetrack" Analogy:**
Imagine two runners on a path: a `slow` runner moving one step at a time and a `fast` runner moving two steps at a time.
* If the path is a straight line, the `fast` runner will simply reach the end and finish the race.
* If the path contains a circular loop (like a racetrack), the `fast` runner will enter the loop and eventually lap the `slow` runner, and they will inevitably meet at the same point.

This logic applies directly to linked lists. If the `fast` pointer ever reaches `null`, the list is linear. If the `fast` pointer ever equals the `slow` pointer, a cycle is guaranteed to exist.

---
## 3. Algorithm

1.  Handle the edge case where the list is empty or has only one node (`head == null` or `head.next == null`), as a cycle is impossible. Return `false`.
2.  Initialize two pointers, `slow` and `fast`, both starting at `head`.
3.  Start a `while` loop that continues as long as `fast` and `fast.next` are not `null`. This condition ensures that the `fast` pointer can always safely jump two steps (`fast.next.next`).
4.  Inside the loop:
    a. Advance the `slow` pointer by one step: `slow = slow.next`.
    b. Advance the `fast` pointer by two steps: `fast = fast.next.next`.
5.  After moving, check if the pointers have met: `if (slow == fast)`. If they have, a cycle has been detected, and we can return `true`.
6.  If the loop completes, it means the `fast` pointer has reached the end of the list (`null`). No cycle was found, so return `false`.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(n)$
    * In a list with `n` nodes, the `slow` pointer will travel at most `n` steps. The `fast` pointer will travel at most `2n` steps. The overall complexity is linear.

* **Space Complexity:** $O(1)$
    * We only use two pointers (`slow` and `fast`). The space required is constant and does not depend on the size of the linked list.