# [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)



## 1. Problem Statement

You are given an integer array `nums` sorted in ascending order (with distinct values), which has been rotated at some unknown pivot index `k`. For example, `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`.

Given the array `nums` after the rotation and an integer `target`, return *the index of `target` if it is in `nums`, or `-1` if it is not in `nums`*. You must write an algorithm with $O(\log n)$ runtime complexity.

---
## 2. Intuition

The $O(\log n)$ time complexity requirement is a huge clue that we must use a **Binary Search**. However, a standard binary search won't work because the array is not fully sorted.

The key insight is that even though the entire array is rotated, when we pick a `mid` element, **at least one of the two halves of the array—from `left` to `mid` OR from `mid` to `right`—must be sorted.**



Our strategy is to modify the standard binary search to first identify which half is sorted. We can do this by comparing `nums[left]` with `nums[mid]`.
* If `nums[left] <= nums[mid]`, we know the left half is sorted.
* Otherwise, the right half must be sorted.

Once we've identified the sorted half, we can check if our `target` lies within the range of that sorted portion.
* If it does, we can confidently search within that half by moving our pointers.
* If it doesn't, we know the `target` must be in the other, "weird" half, so we search there instead.

This process allows us to discard half of the search space in every iteration, preserving the $O(\log n)$ efficiency.

---
## 3. Algorithm

1.  Initialize two pointers: `left = 0` and `right = nums.length - 1`.
2.  Start a `while` loop that continues as long as `left <= right`.
3.  Calculate the `mid` index.
4.  If `nums[mid] == target`, we've found the answer, so return `mid`.
5.  **Check which half is sorted.** Compare `nums[left]` and `nums[mid]`.
    * **Case 1: The left half is sorted (`nums[left] <= nums[mid]`)**
        * Check if the `target` is within the range of the sorted left half (`target >= nums[left]` and `target < nums[mid]`).
        * If yes, search the left half: `right = mid - 1`.
        * If no, the target must be in the right half: `left = mid + 1`.
    * **Case 2: The right half is sorted**
        * Check if the `target` is within the range of the sorted right half (`target > nums[mid]` and `target <= nums[right]`).
        * If yes, search the right half: `left = mid + 1`.
        * If no, the target must be in the left half: `right = mid - 1`.
6.  If the loop finishes, the `target` was not found. Return `-1`.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(\log n)$
    * This is a binary search algorithm. At each step, we eliminate half of the remaining search space, which results in logarithmic time complexity.

* **Space Complexity:** $O(1)$
    * The algorithm uses a fixed number of variables (`left`, `right`, `mid`). The space required is constant and does not depend on the size of the input array.