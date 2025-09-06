# [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)



## 1. Problem Statement

Given an integer array `nums` of `n` elements and an integer `k`, the task is to find the contiguous subarray of length `k` that has the maximum average value. You need to return this maximum average value.

---
## 2. Intuition

A brute-force solution would be to generate every possible contiguous subarray of length `k`, calculate the sum of each, find their averages, and then return the maximum average. This would typically involve a nested loop structure, leading to an $O(n \cdot k)$ time complexity. For large inputs, this is too slow and would result in a "Time Limit Exceeded" (TLE) error.

The key inefficiency in the brute-force approach is the repeated recalculation of sums for overlapping subarrays. The **Sliding Window** technique is designed to solve exactly this problem.

The core idea is to maintain a "window" of `k` elements. We first calculate the sum of the initial window (the first `k` elements). Then, we slide this window one position at a time to the right. For each slide, we update the sum in constant time ($O(1)$) by simply adding the new element that enters the window and subtracting the old element that leaves it. Throughout this process, we keep track of the maximum sum we've encountered. After the window has passed over the entire array, we can derive the maximum average from the maximum sum.

---
## 3. Algorithm

1.  Calculate the sum of the initial window (the first `k` elements of the array). Let's call this `windowSum`.
2.  Initialize a variable `maxSum` and set it equal to this initial `windowSum`.
3.  Iterate through the array, starting from the `k`-th element (`i = k`) to the end.
4.  In each iteration, "slide" the window by updating the `windowSum`:
    a.  Add the new element entering the window from the right: `windowSum += nums[i]`.
    b.  Subtract the old element leaving the window from the left: `windowSum -= nums[i - k]`.
5.  After each update, compare the `windowSum` with `maxSum`. If `windowSum` is greater, update `maxSum = windowSum`.
6.  Once the loop is finished, `maxSum` will hold the maximum sum of any contiguous subarray of size `k`.
7.  The final result is `maxSum / k`.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(n)$
    * We calculate the sum of the first `k` elements, which takes $O(k)$ time. Then, we iterate through the rest of the array (`n-k` elements) once. The total time complexity is $O(k + n - k)$, which simplifies to $O(n)$.

* **Space Complexity:** $O(1)$
    * We only use a few variables to store the sums and pointers, which requires constant extra space regardless of the input size.