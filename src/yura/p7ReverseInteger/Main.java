package yura.p7ReverseInteger;

// https://leetcode.com/problems/reverse-integer/

class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 ||
                            (rev == Integer.MAX_VALUE / 10 && pop > 7)
            ) return 0;
            if (rev < Integer.MIN_VALUE / 10 ||
                            (rev == Integer.MIN_VALUE / 10 && pop < -8)
            ) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(sol.reverse(-123));
        System.out.println(sol.reverse(-45645));
        System.out.println(sol.reverse(2147483647));
    }
}
