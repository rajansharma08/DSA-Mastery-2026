class Solution {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int maxProfit = 0;

        for(int sell = 1; sell < prices.length; sell++){
            
            if(buy > prices[sell]){
                buy = prices[sell];
            }
            else if(prices[sell] - buy > maxProfit){
                maxProfit = prices[sell] - buy;
            }
            
        }
        return maxProfit;
    }
}
       