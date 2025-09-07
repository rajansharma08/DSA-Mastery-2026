# [704. Binary Search](https://leetcode.com/problems/binary-search/)

## 1. Problem Statement

Given a **sorted** (in ascending order) integer array `nums` of `n` elements and a `target` value, write a function to search for the `target` in `nums`. If the target exists, then return its index. Otherwise, return `-1`.

---

## 2. Intuition

A simple approach would be a linear scan, checking every element one by one from the beginning. This would have a time complexity of $O(n)$. However, the problem provides a crucial piece of information: the array is **sorted**. This is a clear signal that we can use a much more efficient algorithm.

**Binary Search** is a classic "divide and conquer" algorithm. Instead of checking elements sequentially, it repeatedly divides the search space in half. The logic is similar to searching for a word in a dictionary: you open to a page in the middle and decide if your word is in the first half or the second half, immediately eliminating 50% of the pages.

We use two pointers, `left` and `right`, to represent the boundaries of our current search space. We calculate the `mid` point and compare the element at that position with our `target`.

- If `nums[mid]` is the `target`, we've found our element.
- If `nums[mid]` is less than the `target`, we know the target, if it exists, must be in the right half of our search space. We can discard the left half by moving our `left` pointer to `mid + 1`.
- If `nums[mid]` is greater than the `target`, the target must be in the left half. We discard the right half by moving our `right` pointer to `mid - 1`.

This process continues until the element is found or the search space becomes empty (`left` crosses `right`), which means the target is not in the array.

---

## 3. Algorithm

1.  Initialize two pointers: `left = 0` and `right = nums.length - 1`.
2.  Start a `while` loop that continues as long as `left <= right`.
3.  Calculate the midpoint: `mid = left + (right - left) / 2`. This method is preferred over `(left + right) / 2` to prevent potential integer overflow on very large inputs.
4.  Compare the element at the midpoint with the target:
    - If `nums[mid] == target`, the element is found. Return `mid`.
    - If `nums[mid] < target`, the target must be in the right half. Update `left = mid + 1`.
    - If `nums[mid] > target`, the target must be in the left half. Update `right = mid - 1`.
5.  If the loop terminates because `left > right`, the target was not found in the array. Return `-1`.

---

## 4. Complexity Analysis

- **Time Complexity:** $O(\log n)$
  - With each comparison, we eliminate half of the remaining search space. For an array of size `n`, the number of times you can divide the space in half is represented by a logarithm. This is extremely fast, even for very large arrays.

- **Space Complexity:** $O(1)$
  - This iterative approach uses a fixed number of variables (`left`, `right`, `mid`) to perform the search. The space required is constant and does not grow with the size of the input array.
