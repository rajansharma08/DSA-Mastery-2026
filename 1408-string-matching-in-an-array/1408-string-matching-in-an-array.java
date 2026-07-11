class Solution {
    public List<String> stringMatching(String[] words) {
        // Step 1: Sort words by increasing length
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        
        // Step 2: Concatenate all words into one big string with separator
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(w).append("#");  // '#' ensures no overlap confusion
        }
        String big = sb.toString();
        
        // Step 3: Check if each word appears more than once
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            int first = big.indexOf(w);
            int second = big.indexOf(w, first + 1);
            if (second != -1) {
                ans.add(w);
            }
        }
        return ans;
    }
}