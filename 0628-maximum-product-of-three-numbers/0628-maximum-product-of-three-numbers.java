class Solution {
    public int maximumProduct(int[] nums) {
        int first_max=-2000,second_max=-2000,third_max=-2000;
        int min1=2000,min2=2000;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>=first_max){
              third_max=second_max;
              second_max=first_max;
              first_max=nums[i];
            }
            else if(nums[i]>second_max){
                third_max=second_max;
                second_max=nums[i];

            }
            else if(nums[i]>third_max){
                third_max=nums[i];
            }
            if(nums[i]<=min1){
                min2=min1;
                min1=nums[i];
            }
            else if(nums[i]<=min2) min2=nums[i];
        }
        return Math.max(first_max*second_max*third_max,
                         min1*min2*first_max);
            }
}