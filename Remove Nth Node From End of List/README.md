# [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)



## 1. Problem Statement

Given the `head` of a linked list, remove the `n-th` node from the end of the list and return its head.

---
## 2. Intuition

A straightforward approach is to make two passes. In the first pass, we traverse the list to find its total length, `L`. The n-th node from the end is the `(L - n)`-th node from the beginning. In the second pass, we traverse to the node just before it (`L - n - 1`) and perform the deletion. However, the follow-up question in an interview is almost always to solve this in a single pass.

**Optimal One-Pass Approach (Two Pointers with a Gap):**
The optimal solution uses two pointers, a `fast` pointer and a `slow` pointer. The core idea is to create a fixed "gap" of `n` nodes between them.

First, we let the `fast` pointer advance `n` steps into the list. After this, we move both the `fast` and `slow` pointers forward one step at a time. Because they are separated by a fixed gap, when the `fast` pointer reaches the end of the list, the `slow` pointer will be positioned exactly at the node *just before* the one we need to remove.

**Handling the Head Edge Case:**
This logic runs into an edge case if we need to remove the head of the list (e.g., a 5-node list, remove the 5th from the end). To handle this gracefully, we use a **dummy node**. We create a `dummy` node that points to the original `head` and start our pointers from there. This ensures that `slow` always has a preceding node, even if the target is the original head.

---
## 3. Algorithm

1.  Create a `dummy` node and set its `next` pointer to the `head` of the list.
2.  Initialize two pointers, `slow` and `fast`, both starting at the `dummy` node.
3.  **Create the gap:** Move the `fast` pointer forward `n + 1` steps.
4.  **Move in tandem:** Start a `while` loop that continues as long as `fast` is not `null`. Inside the loop, advance both pointers one step: `slow = slow.next` and `fast = fast.next`.
5.  When the loop terminates, `fast` is at the end, and `slow` is at the node just before the target node that needs to be removed.
6.  **Perform the deletion:** Bypass the target node by setting `slow.next = slow.next.next`.
7.  Return `dummy.next`, which is the head of the modified list.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(L)$
    * Where `L` is the number of nodes in the list. We traverse the list with our pointers only once.

* **Space Complexity:** $O(1)$
    * We only use a constant amount of extra space for the `dummy` node and the two pointers.