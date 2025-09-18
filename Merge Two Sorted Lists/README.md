# [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

## 1. Problem Statement

You are given the heads of two sorted linked lists, `list1` and `list2`.

Merge the two lists into one **sorted** list. The list should be made by splicing together the nodes of the first two lists.

Return _the head of the merged linked list_.

---

## 2. Intuition

The goal is to combine two sorted lists into a new single sorted list by carefully choosing the smaller of the two nodes at each step.

A common challenge in problems where you build a new list from scratch is handling the head of the list. The first node added needs special treatment. A much cleaner and more robust way to handle this is the **Dummy Head Node** technique.

We start by creating a placeholder `dummy` node which will sit just before the actual head of our new list. We also use a `current` pointer, initialized to this `dummy` node, to act as the "tail" of our new list, to which we will append smaller nodes.

We iterate while both `list1` and `list2` have nodes, comparing their values. We append the smaller of the two nodes to `current.next` and advance the pointer of the list from which we took the node. We also advance our `current` pointer.

After the loop, one of the lists might still have nodes left. Since they are already sorted, we can simply append the entire remaining list to the end.

The final head of our merged list will be `dummy.next`, neatly bypassing the need for any special initial conditions.

---

## 3. Algorithm (Iterative)

1.  Create a `dummy` node to serve as a placeholder for the head of the merged list.
2.  Create a `current` pointer and initialize it to the `dummy` node.
3.  Start a `while` loop that continues as long as both `list1` and `list2` are not `null`.
4.  Inside the loop, compare the values at the current heads of `list1` and `list2`:
    - If `list1.val <= list2.val`:
      - Set `current.next` to `list1`.
      - Advance `list1` to its next node.
    - Else:
      - Set `current.next` to `list2`.
      - Advance `list2` to its next node.
5.  After appending the smaller node, advance the `current` pointer: `current = current.next`.
6.  Once the `while` loop finishes, at least one list is fully traversed. Append the remaining non-null list to the end of the merged list: `current.next = (list1 != null) ? list1 : list2;`.
7.  Return `dummy.next`, which is the true head of the new, sorted list.

---

## 4. Complexity Analysis

- **Time Complexity:** $O(m + n)$
  - We iterate through both lists once. `m` and `n` are the number of nodes in `list1` and `list2`, respectively.

- **Space Complexity:** $O(1)$
  - This iterative approach rearranges the existing nodes in place. We only use a few extra pointers (`dummy`, `current`), which requires constant extra space.
