# [15. 3Sum](https://leetcode.com/problems/3sum/)

## 1. Problem Statement

Given an integer array `nums`, the task is to find all the **unique** triplets `[nums[i], nums[j], nums[k]]` (where `i`, `j`, and `k` are distinct indices) such that their sum is equal to `0`. The solution set must not contain duplicate triplets.

---

## 2. Intuition

The brute-force approach of using three nested loops to check every combination of three numbers would result in an $O(n^3)$ time complexity, which is too slow for typical constraints.

The key to a more optimal solution is to first **sort the array**. Sorting allows us to use more efficient searching patterns and provides a structured way to handle duplicates.

Once the array is sorted, we can reduce the problem from `3Sum` to `2Sum`. We iterate through the array with a single pointer `i`, fixing the first number of our potential triplet, `a = nums[i]`. For each `a`, we then need to find two other numbers, `b` and `c`, in the remainder of the array (from index `i+1` to the end) such that `b + c = -a`. This is a classic `2Sum` problem on a sorted array, which is perfectly solved using the **Two Pointers** technique.

The second major challenge is avoiding duplicate triplets in the result. Sorting is essential for this. We can avoid duplicates by ensuring we never start a search with the same element twice.

1.  **Skipping duplicates for the first element (`a`):** As we iterate with `i`, if `nums[i]` is the same as `nums[i-1]`, we can skip it, as it would only generate triplets we've already found.
2.  **Skipping duplicates for the other two elements (`b`, `c`):** After finding a valid triplet, we must advance our `left` and `right` pointers. It's possible the next element(s) are identical (e.g., `[..., 2, 2, 3, ...]`). We must skip past all these duplicates to avoid adding the same triplet to our results multiple times.

---

## 3. Algorithm

1.  Initialize an empty list to store the `results`.
2.  **Sort** the input array `nums`. This is a critical first step.
3.  Iterate through the array with a pointer `i` from `0` to `nums.length - 2`.
4.  **Skip duplicate for the first element:** If `i > 0` and `nums[i] == nums[i-1]`, then `continue` to the next iteration to avoid duplicate triplets.
5.  Initialize the two pointers for the `2Sum` subproblem: `left = i + 1` and `right = nums.length - 1`.
6.  Start a `while` loop that continues as long as `left < right`.
7.  Calculate the three-number `sum = nums[i] + nums[left] + nums[right]`.
8.  **Decision logic:**
    - If `sum == 0`: A valid triplet is found. Add `[nums[i], nums[left], nums[right]]` to the `results`. Then, to avoid duplicates, increment `left` and decrement `right`. Additionally, use nested `while` loops to advance `left` and `right` past any subsequent duplicate elements.
    - If `sum < 0`: The sum is too small. We need a larger number, so increment `left`.
    - If `sum > 0`: The sum is too large. We need a smaller number, so decrement `right`.
9.  After the main loop finishes, return the `results` list.

---

## 4. Complexity Analysis

- **Time Complexity:** $O(n^2)$
  - The initial sorting of the array takes $O(n \log n)$.
  - The main part of the algorithm is the nested loop structure. The outer `for` loop runs up to `n` times, and the inner `while` loop (with the two pointers) also runs up to `n` times in total for each outer loop iteration. This results in an $O(n^2)$ complexity.
  - The overall complexity is $O(n \log n + n^2)$, which simplifies to $O(n^2)$.

- **Space Complexity:** $O(1)$ or $O(n)$
  - This depends on the implementation of the sorting algorithm used. If the sort is done in-place (like Heapsort), the space complexity of our algorithm is $O(1)$ (excluding the storage for the output list).
  - Standard library sorts (like Java's `Arrays.sort` or Python's Timsort) can use up to $O(n)$ space in the worst case. For interview purposes, the space complexity of the algorithm itself is often considered $O(1)$.
