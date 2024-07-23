package yura.p9PalindromeNumber;

class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int sLength = s.length();
        for (int left = 0, right = sLength - 1; left < sLength / 2; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isPalindrome(-2147483648));
        System.out.println(sol.isPalindrome(12321));
        System.out.println(sol.isPalindrome(11));
        System.out.println(sol.isPalindrome(10));
        System.out.println(sol.isPalindrome(Integer.MAX_VALUE));
    }
}
