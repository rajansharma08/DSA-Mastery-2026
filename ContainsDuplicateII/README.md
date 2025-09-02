# [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/)

## 1. Problem Statement

Given an integer array `nums` and an integer `k`, the task is to determine if there exist two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and the absolute difference between `i` and `j` is at most `k`.

## 2. Intuition

The brute-force approach involves a nested loop, comparing every pair of elements to check both conditions (equal value and index distance). This would result in a time complexity of $O(n^2)$, which is too slow for large inputs.

The main bottleneck is the search for a duplicate. To optimize this, we need a data structure that can provide a fast lookup (ideally $O(1)$) to check if we've seen an element recently. A **`HashSet`** is the perfect tool for this.

However, we can't just store all the elements we've seen. The problem has a distance constraint of `k`. This strongly suggests a **sliding window** approach. We can maintain a `HashSet` that only contains the elements within the current window of size `k`. As we iterate through the array, our window slides forward: we add the new element entering the window and remove the old element that's leaving it. If we ever try to add an element that's already in our window-set, we have found a valid duplicate.

## 3. Algorithm

1.  Initialize an empty `HashSet` to serve as our `window`.
2.  Iterate through the `nums` array with an index `i` from `0` to `nums.length - 1`.
3.  **Maintain the window size:** Before checking the current element, check if the window has become too large. If `i > k`, the element at `nums[i - k - 1]` is no longer within the `k`-distance limit. Remove it from the `window` set.
4.  **Check for duplicates:** Attempt to add the current element `nums[i]` to the `window` set.
5.  The `add` method of a `HashSet` returns `false` if the element is already present. If this happens, it means we have found a duplicate within the valid window. Return `true` immediately.
6.  If the loop completes without finding any such duplicates, return `false`.

## 4. Complexity Analysis

- **Time Complexity:** $O(n)$
  - We iterate through the array of `n` elements exactly once. All operations inside the loop (`add`, `remove`, and `contains` for a `HashSet`) take constant time on average, $O(1)$.

- **Space Complexity:** $O(k)$
  - In the worst-case scenario (where all elements in the window are unique), the `HashSet` will store up to `k+1` elements. Therefore, the space used is proportional to the size of the window, `k`.
