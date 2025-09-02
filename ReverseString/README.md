# [344. Reverse String](https://leetcode.com/problems/reverse-string/)

## 1. Problem Statement

The task is to write a function that reverses a string, which is provided as an array of characters `char[] s`. The key constraint is that the reversal must be done **in-place**, without allocating extra space for a new array. This means we must solve it using only $O(1)$ extra memory.

## 2. Intuition

A simple approach to reversing a string would be to create a new array and copy the characters from the original array into it, starting from the end. However, the problem's strict $O(1)$ space complexity constraint forbids this.

This constraint forces us to modify the input array directly. The most efficient and elegant way to achieve this is with the **Two Pointers** technique.

We can place one pointer, `left`, at the beginning of the array and a second pointer, `right`, at the very end. We then swap the characters at these two positions. After swapping, we move the `left` pointer one position to the right and the `right` pointer one position to the left, bringing them closer to the center. We repeat this process of swapping and moving until the `left` and `right` pointers meet or cross each other, at which point the entire array will have been reversed.

## 3. Algorithm

1.  Initialize two integer pointers: `left` at the start of the array (`0`) and `right` at the end of the array (`s.length - 1`).
2.  Use a `while` loop that continues as long as `left` is less than `right`.
3.  Inside the loop:
    a. Store the character `s[left]` in a temporary variable `temp`.
    b. Assign the character `s[right]` to `s[left]`.
    c. Assign the character from the `temp` variable to `s[right]`.
4.  After the swap, move the pointers one step closer to the center by incrementing `left` and decrementing `right`.
5.  The loop will automatically terminate when the pointers meet or cross, and the array will be fully reversed.

## 4. Complexity Analysis

- **Time Complexity:** $O(n)$
  - We iterate through the array until the two pointers meet, which means we perform approximately $n/2$ swaps. The number of operations is directly proportional to the number of elements, `n`.

- **Space Complexity:** $O(1)$
  - The reversal is performed in-place. The only extra memory used is for a single temporary variable to facilitate the swap, which is constant space. This satisfies the problem's memory constraint.
