# [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)



## 1. Problem Statement

Given an array of integers `nums` and an integer `k`, the task is to return the total number of continuous subarrays whose sum equals `k`.

---
## 2. Intuition

A standard sliding window approach fails for this problem because the array can contain **negative numbers**. In a typical sliding window, we shrink the window from the left when the sum is too large, assuming this will decrease the sum. However, if the number leaving the window is negative, shrinking the window would actually *increase* the sum, breaking the core logic of the pattern.

The optimal solution uses a **HashMap** combined with the powerful concept of **Prefix Sums**.

The core insight is based on a simple mathematical relationship. If the running sum from the start of the array up to index `j` is `sum_j`, and the running sum up to an earlier index `i` is `sum_i`, then the sum of the subarray *between* `i` and `j` is `sum_j - sum_i`.

We are looking for subarrays that sum to `k`. So, we are looking for points where `sum_j - sum_i = k`.

By rearranging this equation, we get **`sum_i = sum_j - k`**.

This brilliantly reframes the problem. As we iterate through the array calculating our `currentSum` (our `sum_j`), the question is no longer "does a subarray sum to k?". Instead, it becomes: **"How many times in the past have we seen a prefix sum (`sum_i`) that equals our `currentSum - k`?"** A `HashMap` is the perfect tool to store the frequencies of the prefix sums we've seen, allowing us to ask this question in $O(1)$ time.

---
## 3. Algorithm

1.  Initialize `count = 0` and `currentSum = 0`.
2.  Create a `HashMap` named `prefixSumMap` to store the frequency of each prefix sum (`<Prefix Sum, Frequency>`).
3.  **Crucial Base Case:** Add `(0, 1)` to the `prefixSumMap`. This is to account for subarrays that start from the very beginning of the array.
4.  Iterate through the `nums` array. For each number:
    a. Add the number to `currentSum`.
    b. Calculate the required "old sum" we need to find in our history: `oldSum = currentSum - k`.
    c. Check if `prefixSumMap` contains `oldSum`. If it does, we have found a valid subarray (or multiple subarrays, if the frequency is > 1). Add the frequency of `oldSum` to our `count`.
    d. Update the `prefixSumMap` with the `currentSum`. If `currentSum` is already in the map, increment its frequency. Otherwise, add it with a frequency of 1.
5.  After the loop finishes, `count` will hold the total number of valid subarrays. Return `count`.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(n)$
    * We iterate through the array of `n` elements exactly once. The `HashMap` insertion and lookup operations take, on average, constant time ($O(1)$).

* **Space Complexity:** $O(n)$
    * In the worst-case scenario where all prefix sums are unique, the `HashMap` will store up to `n` key-value pairs.