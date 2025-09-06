# [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

## 1. Problem Statement

You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `i-th` line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water. Return the maximum amount of water a container can store.

![alt text](image.png)

---

## 2. Intuition

A brute-force approach would be to check every possible pair of lines, calculate the area for each, and keep track of the maximum. This involves two nested loops and results in an $O(n^2)$ time complexity, which is too slow for large inputs.

A more optimal, $O(n)$ solution can be achieved using the **Two Pointers** technique. The core idea is to start with the widest possible container and then intelligently shrink the search space.

We initialize a `left` pointer at the beginning of the array (`0`) and a `right` pointer at the end (`n-1`). This configuration gives us the maximum possible width. We calculate the area. Now, to find a potentially larger area, we must move one of the pointers inward. But which one?

The area is determined by `width * min(height[left], height[right])`. When we move a pointer, the `width` is guaranteed to decrease. Therefore, the only way we can possibly find a larger area is if the height of our container increases.

- If we move the pointer of the **taller** line, the new container's height will still be limited by the original **shorter** line. Since the width has decreased, the new area is guaranteed to be smaller.
- If we move the pointer of the **shorter** line, we lose a limiting factor. We now have a chance of finding a new, taller line that could overcome the decrease in width and result in a larger area.

This greedy approach of always moving the shorter line's pointer ensures that we never discard a potentially optimal container.

---

## 3. Algorithm

1.  Initialize three variables: `left = 0`, `right = height.length - 1`, and `maxArea = 0`.
2.  Start a `while` loop that continues as long as `left < right`.
3.  Inside the loop:
    a. Calculate the current area: `currentArea = (right - left) * Math.min(height[left], height[right])`.
    b. Update the maximum area found so far: `maxArea = Math.max(maxArea, currentArea)`.
4.  **Move the pointers:**
    - If `height[left]` is less than `height[right]`, increment `left`.
    - Otherwise, decrement `right`.
5.  After the loop finishes, return `maxArea`.

---

## 4. Complexity Analysis

- **Time Complexity:** $O(n)$
  - The `left` and `right` pointers start at opposite ends and move towards each other. They will traverse the array at most once, resulting in a linear time complexity.

- **Space Complexity:** $O(1)$
  - We only use a few variables to store our pointers and the `maxArea`. The space required is constant and does not depend on the size of the input array.
