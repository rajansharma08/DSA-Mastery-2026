# [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)

## 1. Problem Statement

You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. This operation can be performed at most `k` times.

Return _the length of the longest substring containing the same letter you can get after performing the above operations._

---

## 2. Intuition

This problem asks for the **longest substring** that satisfies a specific condition, which is a classic indicator for the **Sliding Window** pattern. We need to expand a window from the right and, when the condition is violated, shrink it from the left.

The core of the problem is defining the "validity" of a window. A window is considered valid if the number of characters that need to be replaced to make all characters the same is less than or equal to `k`.

The number of characters that need to be replaced can be calculated with a simple formula:
`replacementsNeeded = windowLength - maxFrequency`

Where `windowLength` is the total size of our current window (`right - left + 1`), and `maxFrequency` is the count of the single most frequent character within that window.

So, our window is valid as long as **`(right - left + 1) - maxFrequency <= k`**.

Our strategy will be to expand the window by moving the `right` pointer. With each expansion, we update our character frequency map and the `maxFrequency`. Then, we check if our validity condition has been broken. If it has (`windowLength - maxFrequency > k`), we shrink the window from the left by moving the `left` pointer until the window is valid again.

For efficiency, since the input is limited to 26 uppercase English letters, we can use a simple integer array of size 26 as our frequency map instead of a `HashMap`.

---

## 3. Algorithm

1.  Initialize an integer array `counts` of size 26 to store the frequency of each character.
2.  Initialize pointers and tracking variables: `left = 0`, `maxFreq = 0`, and `maxLength = 0`.
3.  Iterate through the string with a `right` pointer from `0` to the end.
4.  **Expand the Window:**
    a. Increment the count for the character at the `right` pointer.
    b. Update `maxFreq` by comparing it with the new count of the current character.
5.  **Check Validity & Shrink Window:**
    a. Use a `while` loop to check if the window is invalid: `(right - left + 1) - maxFreq > k`.
    b. If the window is invalid, decrement the count of the character at the `left` pointer and increment `left`.
6.  **Update Result:**
    a. After the potential shrinking, the window is guaranteed to be valid. We update our result: `maxLength = Math.max(maxLength, right - left + 1)`.
7.  After the main loop finishes, return `maxLength`.

---

## 4. Complexity Analysis

- **Time Complexity:** $O(n)$
  - Both the `right` and `left` pointers traverse the string at most once. Each character is processed a constant number of times, leading to a linear time complexity proportional to the length of the string, `n`.

- **Space Complexity:** $O(1)$
  - We use an integer array of size 26 to store character frequencies. The space required is constant as it does not depend on the length of the input string `s`.
