# [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)



## 1. Problem Statement

Koko has `n` piles of bananas, with `piles[i]` bananas in the `i-th` pile. She has `h` hours to eat them all. Koko can choose an eating speed of `k` bananas per hour. For each hour, she eats from a single pile. If a pile has fewer than `k` bananas, she eats them all and waits for the hour to end.

The goal is to find the **minimum integer speed `k`** such that she can eat all the bananas within `h` hours.

---
## 2. Intuition

The problem asks for the **minimum possible value for the speed `k`** that satisfies a condition (finishing within `h` hours). This structure is a classic signal for a powerful pattern: **Binary Search on the Answer**.

Instead of searching through the `piles` array itself, we can search through the range of all possible answers for the speed `k`. This range is a sorted space, which makes it perfect for binary search.

1.  **Define the Search Space:**
    * The **minimum possible speed** is `1` banana per hour. This is our `left` boundary.
    * The **maximum necessary speed** is the size of the largest pile in the array. Any speed higher than this is redundant, as it would still take one hour to finish that largest pile. This is our `right` boundary.

2.  **Binary Search Logic:**
    * We pick a `mid` speed from our search space (`left` to `right`).
    * We then write a helper function to check if this `mid` speed is "feasible"—that is, can Koko finish all the bananas in `h` hours at this speed?
    * **If the speed is feasible (`calculatedHours <= h`):** This is a potential answer! But since we want the *minimum* speed, we store this answer and try to find an even slower speed. We do this by discarding the right half of our search space: `right = mid - 1`.
    * **If the speed is not feasible (`calculatedHours > h`):** The speed is too slow. We must try a faster speed. We discard the left half of our search space: `left = mid + 1`.

This process efficiently narrows down the range of possible speeds until we converge on the minimum one that works.

---
## 3. Algorithm

1.  Define the search space for the speed `k`. Set `left = 1` and `right = max(piles)`.
2.  Initialize a variable `result` to store the minimum valid speed found so far (a safe initial value is `right`).
3.  Start a `while` loop that continues as long as `left <= right`.
4.  Calculate the `mid` speed: `mid = left + (right - left) / 2`.
5.  **Check Feasibility:** Use a helper function to calculate the total hours required to eat all bananas at the `mid` speed.
    * For each pile, the hours needed are `ceil(pile / mid)`.
    * This can be calculated efficiently with integer math: `(pile + speed - 1) / speed`.
    * The total hours variable must be a `long` to prevent potential integer overflow.
6.  Compare the `calculatedHours` with the deadline `h`:
    * If `calculatedHours <= h`, the speed is valid. We update `result = mid` and search for a slower speed by setting `right = mid - 1`.
    * If `calculatedHours > h`, the speed is too slow. We must search for a faster speed by setting `left = mid + 1`.
7.  After the loop finishes, `result` will hold the minimum speed. Return `result`.

---
## 4. Complexity Analysis

* **Time Complexity:** $O(n \log m)$
    * Where `n` is the number of piles and `m` is the range of possible speeds (from 1 to `max(piles)`).
    * The binary search performs $\log m$ iterations. In each iteration, we check if the speed is feasible, which requires iterating through all `n` piles.

* **Space Complexity:** $O(1)$
    * The algorithm uses a fixed number of variables. The space required is constant and does not depend on the input size.