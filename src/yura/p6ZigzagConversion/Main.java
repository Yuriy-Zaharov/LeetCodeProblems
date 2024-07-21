package yura.p6ZigzagConversion;

class Solution {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }

        int sLength = s.length();
        char[] resultCArray = new char[sLength];
        int resultIndex = 0;
        for (int sIndex = 0; sIndex < sLength; sIndex += 2 * numRows - 2, resultIndex++) {
            // Fill upper nodes - roots
            resultCArray[resultIndex] = s.charAt(sIndex);
        }
        int numCols = resultIndex;
        // Fill columns under roots
        for (int rowNum = 1; rowNum < numRows; rowNum ++) { // for every row
            for (int colNum = 0; colNum < numCols; colNum++) { // for every column
                // Fill left sub column
                int sIndex = colNum * (2 * numRows - 2) - rowNum;
                if (sIndex > -1 && rowNum < numRows - 1) {
                    resultCArray[resultIndex] = s.charAt(sIndex);
                    resultIndex++;
                }

                // Fill right sub column
                sIndex = colNum * (2 * numRows - 2) + rowNum;
                if (sIndex < sLength) {
                    resultCArray[resultIndex] = s.charAt(sIndex);
                    resultIndex++;
                }

                // Fill last sub column if it doesn't have own column
                if (colNum == numCols - 1 && rowNum < numRows - 1) {
                    sIndex = (colNum + 1) * (2 * numRows - 2) - rowNum;
                    if (sIndex < sLength) {
                        resultCArray[resultIndex] = s.charAt(sIndex);
                        resultIndex++;
                    }
                }
            }
        }
        return new String(resultCArray);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.convert("КартошечкаСПюрешкойИСалатик", 5));
        //System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("ABCD", 3));
        System.out.println(solution.convert("КартошечкаСПюрешкойИСалатикБаЛу", 5));
    }
}
