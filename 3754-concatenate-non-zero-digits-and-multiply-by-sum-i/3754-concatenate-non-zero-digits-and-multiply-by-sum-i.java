class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;

        int sum = 0;
        long num = 0;
        long place = 1;

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                sum += digit;
                num += (long) digit * place;
                place *= 10;
            }

            n /= 10;
        }

        return (long) sum * num;
    }
}