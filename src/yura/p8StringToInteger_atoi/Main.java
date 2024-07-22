package yura.p8StringToInteger_atoi;

class Solution {
    public int myAtoi(String s) {
        int sLength = s.length();
        int carriage = 0;
        int resInt = 0;
        boolean isNegative = false;

        // Searching leading zeroes
        for ( ; carriage < sLength; carriage++) {
            if (s.charAt(carriage) != ' ') {
                break;
            }
        }

        // Checking sign
        if (carriage < sLength) {
            if (s.charAt(carriage) == '-') {
                carriage++;
                isNegative = true;
            } else if (s.charAt(carriage) == '+') {
                carriage++;
            }
        }

        // Processing digits
        for ( ; carriage < sLength; carriage++) {
            if (Character.isDigit(s.charAt(carriage))) {
                if (isNegative) {
                    if ((long)resInt * -10 - s.charAt(carriage) + '0' <= Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    if ((long)resInt * 10 + s.charAt(carriage) - '0' >= Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
                resInt = resInt * 10 + s.charAt(carriage) - '0';
            } else {
                break;
            }
        }
        return isNegative ? -resInt : resInt;
    }
}



public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        //System.out.println(sol.myAtoi(""));
        System.out.println(sol.myAtoi("-21474836482"));
        System.out.println(sol.myAtoi("   3456"));
        System.out.println(sol.myAtoi("2147483649"));
        System.out.println(Integer.MAX_VALUE);
    }
}
