class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, currSum = 0;

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);

        for(int num : nums){
            currSum += num;
            int oldSum = currSum - k;
            
            if(map.containsKey(oldSum)) count += map.get(oldSum);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }
}