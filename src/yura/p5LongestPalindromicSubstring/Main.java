package yura.p5LongestPalindromicSubstring;

class SubStringBorders {
    private int left;
    private int right;
    private int length;
    public SubStringBorders(int left, int right) {
        this.left = left;
        this.right = right;
        this.length = right - left + 1;
    }
    public SubStringBorders(SubStringBorders pal) {
        this.left = pal.left;
        this.right = pal.right;
        this.length = pal.length;
    }
    public void setLeft(int left) {
        this.left = left;
        this.length = this.right - this.left + 1;
    }
    public void setRight(int right) {
        this.right = right;
        this.length = this.right - this.left + 1;
    }

    public void moveLeft() {
        this.setLeft(left - 1);
    }

    public void moveRight() {
        this.setRight(right + 1);
    }

    public void moveBothWays() {
        this.setLeft(left - 1);
        this.setRight(right + 1);
    }
    
    public boolean isLeftEqualsRight(String s) {
        return s.charAt(this.left) == s.charAt(this.right);
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getLength() {
        return length;
    }

    public SubStringBorders maxSubStringBorders (SubStringBorders sSB) {
        if (this.length > sSB.length) {
            return this;
        } else {
            return sSB;
        }
    }
}

class Solution {
    public String longestPalindrome(String s) {
        int sLength = s.length();
        int startCenter = sLength / 2;
        SubStringBorders maxPalindrome = new SubStringBorders(0, 0);
        SubStringBorders palindrome;

        // 1) Start from center (sLength / 2), then check left pair and right pair (center - 1, center) (center, center + 1)
        // 2) One move to left from center, then check from center and (center - 1, center)
        // 3) One move to right from center, then check from center and (center, center + 1)
        // 4) Repeat (2-3) while reach border

        // (1)
        palindrome = LongestPalindromeFromCenter(s,startCenter,startCenter,sLength);
        maxPalindrome = maxPalindrome.maxSubStringBorders(palindrome);

        if (startCenter > 0) {
            palindrome = LongestPalindromeFromCenter(s,startCenter - 1,startCenter,sLength);
            maxPalindrome = maxPalindrome.maxSubStringBorders(palindrome);
        }
        if (startCenter + 1 < sLength) {
            palindrome = LongestPalindromeFromCenter(s,startCenter,startCenter + 1,sLength);
            maxPalindrome = maxPalindrome.maxSubStringBorders(palindrome);
        }

        for(int step = 1; step < sLength / 2; step++) {
            // Check if length of maxPalindrome bigger than remaining space
            if (maxPalindrome.getLength() > (startCenter - step) * 2) {
                break;
            }

            // (2)
            palindrome = LongestPalindromeFromCenter(s,startCenter - step,startCenter - step, sLength);
            maxPalindrome = maxPalindrome.maxSubStringBorders(palindrome);

            palindrome = LongestPalindromeFromCenter(s,startCenter - step - 1,startCenter - step, sLength);
            maxPalindrome = maxPalindrome.maxSubStringBorders(palindrome);

            // (3)
            palindrome = LongestPalindromeFromCenter(s,startCenter + step,startCenter + step, sLength);
            maxPalindrome = maxPalindrome.maxSubStringBorders(palindrome);

            if (startCenter + step + 1 < sLength) {
                palindrome = LongestPalindromeFromCenter(s,startCenter + step,startCenter + step + 1, sLength);
                maxPalindrome = maxPalindrome.maxSubStringBorders(palindrome);
            }
        }

        return s.substring(maxPalindrome.getLeft(), maxPalindrome.getRight() + 1);
    }

    private SubStringBorders LongestPalindromeFromCenter(String s, int left, int right, int sLength) {
        SubStringBorders maxPalindrome = new SubStringBorders(0, 0);

        SubStringBorders subStringBorders = new SubStringBorders(left, right);

        if (subStringBorders.isLeftEqualsRight(s)) {
            while (isBiggerWordPalindrome(s, subStringBorders, sLength)) {
                subStringBorders.moveBothWays();
            }
            if (subStringBorders.getLength() > maxPalindrome.getLength()) {
                maxPalindrome = new SubStringBorders(subStringBorders);
            }
        }

        return maxPalindrome;
    }

    private boolean isBiggerWordPalindrome(String s, SubStringBorders pal, int sLength) {
        if (pal.getLeft() > 0 && pal.getRight() < sLength - 1)  {  //check border
            return s.charAt(pal.getLeft() - 1) == s.charAt(pal.getRight() + 1);  //return true if there is bigger palindrome
        }
        else {
            return false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(sol.longestPalindrome("xabaxyy"));
//        System.out.println(sol.longestPalindrome("babad"));
//        System.out.println(sol.longestPalindrome("cbbd"));
//        System.out.println(sol.longestPalindrome("abcdcbalopiuy"));
        System.out.println(sol.longestPalindrome("adam"));
    }
}
