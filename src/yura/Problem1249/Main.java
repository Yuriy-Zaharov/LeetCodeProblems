package yura.Problem1249;

public class Main {
    public static void main(String[] args) {

        String testString = "())()(((";

        Solution sola = new Solution();
        System.out.println(sola.minRemoveToMakeValid(testString));

    }
}

class Solution {
    public String minRemoveToMakeValid(String s) {
        s = minRemoveForward(s);
        s = minRemoveBackward(s);
        return s;
    }

    private String minRemoveForward(String s) {
        int differ = 0;
        for (int lIndex = 0; lIndex < s.length(); lIndex++){
            if (s.charAt(lIndex) == '(') {
                differ++;
            } else if (s.charAt(lIndex) == ')') {
                if (differ < 1) {
                    s = deleteSymbol(s, lIndex);
                    lIndex --;
                } else {
                    differ--;
                }
            }
        }
        return s;
    }

    private String minRemoveBackward(String s) {
        int differ = 0;
        for (int lIndex = s.length() - 1; lIndex >= 0; lIndex--){
            if (s.charAt(lIndex) == ')') {
                differ++;
            } else if (s.charAt(lIndex) == '(') {
                if (differ < 1) {
                    s = deleteSymbol(s, lIndex);
                } else {
                    differ--;
                }
            }
        }
        return s;
    }

    private String deleteSymbol(String s, int lIndex) {
        return s.substring(0, lIndex) + s.substring(lIndex + 1);
    }
}