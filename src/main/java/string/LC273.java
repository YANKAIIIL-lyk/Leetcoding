package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC273 {
    /**
     * Create three methods or arrays to denote all the characters. Adding an emply string in thousands
     * for the numbers < 1000
     */
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};

    private String lessThan20(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    private String tenth(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }

    /**
     * In the main method, return 0 if the input is 0.
     * In the while loop, we can break the input into 3 digits in a group and parse each of the group.
     * 1. If the parsed number is larger than 0, than we can add it to our result.
     * 2. In the result list, we can combine all the segments and add space between them.
     * 3. Return the result and call trim to remove all the leading and following spaces.
     */
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        List<String> list = new ArrayList<>();
        int i = 0;
        while (num != 0) {
            int parse = num % 1000;
            num = num / 1000;
            String temp = parseThree(parse);
            if (parse != 0) {
                temp = temp + " " + thousands[i];
            }
            i++;
            if (temp.length() > 0) {
                list.add(temp);
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int j = list.size() - 1; j >= 0; j--) {
            sb.append(list.get(j) + " ");
        }
        return sb.toString().trim();
    }

    /**
     * This is the key part of this question.
     * If num is zero, return nothing.
     * <20, all the nums are mapping to a word
     * <100, we need to notice that it might be the multiply of ten. No space is added
     * <= 100, similar to the above case, we need to consider the multiply of 100
     */
    private String parseThree(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 20) {
            return lessThan20(num);
        } else if (num < 100) {
            StringBuilder sb = new StringBuilder();
            sb.append(tenth(num / 10));
            num = num % 10;
            if (num != 0) {
                sb.append(" " + lessThan20(num));
            }
            return sb.toString().trim();
        }
        if (num % 100 == 0) {
            return lessThan20(num / 100) + " Hundred";
        }
        return lessThan20(num / 100) + " Hundred " + parseThree(num % 100);
    }
}
