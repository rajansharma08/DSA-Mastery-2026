class Solution {
    private long calculateHours(int[] piles, int speed, int h) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + speed - 1) / speed; // ceil division
            if (totalHours > h) return totalHours;    // early stop
        }
        return totalHours;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        int result = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (calculateHours(piles, mid, h) <= h) {
                result = mid;        // possible answer
                right = mid - 1;     // try smaller speed
            } else {
                left = mid + 1;      // need larger speed
            }
        }
        return result;
    }
}
