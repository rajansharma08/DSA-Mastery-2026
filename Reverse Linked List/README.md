# [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)



## 1. Problem Statement

Given the `head` of a singly linked list, reverse the list, and return *the new head of the reversed list*.

---
## 2. Intuition

The goal is to change the direction of the `.next` pointers for every node in the list, so that a list like `A -> B -> C -> null` becomes `null <- A <- B <- C`. The node `C` will be the new head.

The main challenge in reversing a linked list is that as soon as you change a node's `.next` pointer (e.g., making `B.next` point to `A`), you lose the link to the rest of the original list (node `C`).

To solve this, we can iterate through the list and carefully manage our pointers at each step. The standard iterative approach uses three pointers to keep track of our state:
1.  `prev`: The node that came *before* the current node. This starts as `null` because the original head will become the new tail, pointing to `null`.
2.  `curr`: The node we are currently visiting. This starts at `head`.
3.  `nextNode`: A temporary variable to store the link to the next node in the original list before we break it.

The process is a "pointer dance" that we repeat for every node: first, we save our next step, then we reverse the current link, and finally, we move our `prev` and `curr` pointers one step down the list.

---
## 3. Algorithm (Iterative)

1.  Initialize two pointers: `prev = null` and `curr = head`.
2.  Start a `while` loop that continues as long as `curr` is not `null`.
3.  **Inside the loop (The Pointer Dance):**
    a. **Save the next node:** Before changing any links, store the next node in a temporary variable: `nextNode = curr.next`.
    b. **Reverse the link:** Point the current node's `next` pointer backward to the `prev` node: `curr.next = prev`.
    c. **Move pointers forward:** Advance `prev` to `curr` and `curr` to the saved `nextNode`: `prev = curr;` and `curr = nextNode;`.
4.  When the loop terminates, `curr` will be `null` and `prev` will be at the last node of the original list, which is the new `head` of the reversed list.
5.  Return `prev`.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(n)$
    * We traverse the linked list of `n` nodes exactly once.

* **Space Complexity:** $O(1)$
    * The reversal is performed in-place. We only use a few extra pointers to keep track of nodes, which is constant extra space.