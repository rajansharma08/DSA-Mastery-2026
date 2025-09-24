# [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

## 1. Problem Statement

Given a string `s` containing just the characters `(`, `)`, `{`, `}`, `[` and `]`, determine if the input string is valid.

An input string is valid if:

1.  Open brackets must be closed by the same type of brackets.
2.  Open brackets must be closed in the correct order.
3.  Every close bracket has a corresponding open bracket of the same type.

---

## 2. Intuition

This problem involves correctly matching and nesting pairs of brackets. The key insight is that when we encounter a closing bracket, it must correspond to the most recently opened, still-unclosed bracket. For example, in the string `({[]})`, the `]` must match the `[`, then the `}` must match the `{`, and so on.

This behavior is **Last-In, First-Out (LIFO)**. The last opening bracket we see is the first one that needs to be closed. This LIFO pattern is the exact problem that the **Stack** data structure is designed to solve.

Our strategy is to use a stack to keep track of the opening brackets. We will iterate through the input string:

- If we see an **opening** bracket (`(`, `{`, `[`), we **push** it onto the stack.
- If we see a **closing** bracket (`)`, `}`, `]`), we check the top of the stack. If the stack is empty or the item at the top is not the matching opening bracket, we know the string is invalid. If it _is_ a match, we **pop** the opening bracket from the stack, which signifies a successful pairing.

After iterating through the entire string, if the stack is empty, it means every opening bracket was successfully matched and closed. If the stack is not empty, it means there are unclosed opening brackets, and the string is invalid.

---

## 3. Algorithm

1.  Initialize an empty `Stack` of characters.
2.  Iterate through each character `c` of the input string `s`.
3.  If `c` is an opening bracket (`(`, `{`, or `[`), push it onto the stack.
4.  If `c` is a closing bracket:
    a. First, check if the stack is empty. If it is, we have a closing bracket with no corresponding opener, so the string is invalid. Return `false`.
    b. Pop the top element from the stack. Let's call it `top`.
    c. Check if `c` and `top` are a matching pair. If `c` is `)` but `top` is not `(`, or `c` is `}` but `top` is not `{`, etc., then the string is invalid. Return `false`.
5.  After the loop, if the stack is empty, all brackets have been matched. Return `true`. Otherwise, there are unmatched opening brackets, so return `false`.

---

## 4. Complexity Analysis

- **Time Complexity:** $O(n)$
  - We iterate through the string of length `n` exactly once. Each stack operation (`push` and `pop`) takes constant time, $O(1)$.

- **Space Complexity:** $O(n)$
  - In the worst-case scenario, the string might consist of only opening brackets (e.g., `((((...`)), which would mean we push all `n` characters onto the stack.
