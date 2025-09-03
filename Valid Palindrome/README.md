# [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)



## 1. Problem Statement

Given a string `s`, the task is to determine if it is a palindrome. The check must be performed **after** two preprocessing steps:
1.  Converting all uppercase letters into lowercase letters.
2.  Removing all non-alphanumeric characters (i.e., anything that is not a letter or a number).

An empty string is considered a valid palindrome.

## 2. Intuition

A palindrome is a sequence that reads the same forwards and backward. The core of this problem is to compare characters from the beginning of the string with characters from the end of the string.

A less optimal approach would be to first build a new, cleaned string by iterating through the input `s`, filtering out invalid characters, and then checking if that new string is a palindrome. This approach, however, requires $O(n)$ extra space to store the new string.

A more memory-efficient solution is to use the **Two Pointers** technique directly on the original string. We can place a `left` pointer at the start and a `right` pointer at the end.

The main challenge is the "dirty" data (uppercase letters and non-alphanumeric symbols). We can handle this on the fly. As our pointers move towards the center, we'll "skip" any character that isn't a letter or a number.

So, the logic is: move the `left` pointer forward until it finds an alphanumeric character. Move the `right` pointer backward until it finds an alphanumeric character. Then, compare the two characters (in lowercase). If they don't match, we know it's not a palindrome. If they do match, we continue the process until the pointers cross.

## 3. Algorithm

1.  Initialize two pointers: `left = 0` and `right = s.length() - 1`.
2.  Start a `while` loop that runs as long as `left < right`.
3.  **Clean from the left:** Inside the loop, use a nested `while` loop to skip non-alphanumeric characters from the start. Keep incrementing `left` as long as `left < right` and `s.charAt(left)` is not a letter or digit.
4.  **Clean from the right:** Do the same for the right pointer. Keep decrementing `right` as long as `left < right` and `s.charAt(right)` is not a letter or digit.
5.  **Compare:** Once both pointers are on valid characters, compare them. If `Character.toLowerCase(s.charAt(left))` is not equal to `Character.toLowerCase(s.charAt(right))`, the string is not a palindrome, so we can return `false`.
6.  **Advance Pointers:** If the characters match, we've confirmed that part of the string is palindromic. Move the pointers closer to the center by incrementing `left` and decrementing `right`.
7.  If the main `while` loop completes, it means all character pairs matched. The string is a palindrome, so return `true`.

## 4. Complexity Analysis

* **Time Complexity:** $O(n)$
    * In the worst-case scenario, each pointer will traverse the entire string once to find all the alphanumeric characters. This makes the runtime linearly proportional to the length of the string, `n`.

* **Space Complexity:** $O(1)$
    * This approach operates directly on the input string. We only use a few variables to store the pointers, which is constant extra space.