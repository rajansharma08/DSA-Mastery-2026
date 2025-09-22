# [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)

## 1. Problem Statement

Given the `head` of a linked list, return _the node where the cycle begins_. If there is no cycle, return `null`.

---

## 2. Intuition

This problem is a follow-up to "Linked List Cycle I." We don't just need to detect _if_ a cycle exists, but pinpoint the exact node where it starts. The optimal solution is a two-phase extension of the Fast & Slow Pointer method, known as **Floyd's Cycle-Finding Algorithm**.

**Phase 1: Detect the Cycle**
First, we use the standard "Tortoise and Hare" algorithm. We have a `slow` pointer that moves one step at a time and a `fast` pointer that moves two steps. If they ever meet, we know a cycle exists, and we have found a `meetingPoint` somewhere inside that cycle. If the `fast` pointer reaches `null`, there is no cycle.

**Phase 2: Find the Cycle's Entrance**
This is the brilliant part of the algorithm. It is a mathematical proof that the distance from the `head` of the list to the start of the cycle is the same as the distance from the `meetingPoint` (found in Phase 1) to the start of the cycle.

Therefore, once we find the `meetingPoint`, we can find the cycle's start by:

1.  Initializing one pointer (`ptr1`) at the `head`.
2.  Keeping another pointer (`ptr2`) at the `meetingPoint`.
3.  Moving **both** pointers one step at a time.

The node where `ptr1` and `ptr2` inevitably meet is the exact start of the cycle.

---

## 3. Algorithm

1.  Initialize `slow` and `fast` pointers at the `head`.
2.  **Phase 1: Find the Meeting Point**
    a. Use a `while` loop that runs as long as `fast` and `fast.next` are not `null`.
    b. Advance `slow` by one step and `fast` by two.
    c. If `slow == fast`, a meeting point has been found. Break out of this loop and proceed to Phase 2.
3.  If the loop finishes without the pointers meeting, it means `fast` reached `null`. There is no cycle, so return `null`.
4.  **Phase 2: Find the Start of the Cycle**
    a. Initialize a new pointer, `ptr1`, at the `head`.
    b. The other pointer, `ptr2`, is the `slow` (or `fast`) pointer, which is currently at the meeting point.
    c. Start a new `while` loop that runs as long as `ptr1 != ptr2`.
    d. Inside this loop, advance both pointers one step at a time: `ptr1 = ptr1.next` and `ptr2 = ptr2.next`.
5.  When this second loop terminates, both pointers are at the start of the cycle. Return either pointer.

---

## 4. Complexity Analysis

- **Time Complexity:** $O(n)$
  - The algorithm traverses the list's linear and cyclic parts a constant number of times, making the time complexity proportional to the total number of nodes, `n`.

- **Space Complexity:** $O(1)$
  - We only use a few pointers (`slow`, `fast`, `ptr1`, `ptr2`), which requires constant extra space.
