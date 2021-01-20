package math;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC224 {
    public static void main(String[] args) {
        LC224 lc224 = new LC224();
        String s = "- (3 + (4 + 5))";
        int res = lc224.calculate(s);
        System.out.println(res);
    }

    /**
     * We have several different kinds of characters.
     * 1. ' ' space
     * We want to skip each of the spaces since it is useless when computing the result
     * 2. + - * /
     * If we have these operators, we can evaluate the digit left to it and save the sign
     * for next round of evaluation.
     * When evaluating the digit left to it, we will need the operand of the last round.
     * If it is a '+', we can just add to it. If it is a '-', we can add its opposite number.
     * If it is a '*' or '/', pop the top of slack, do the operation and push it back
     * 3. '(', ')'
     * We can use recursion to address this problem. Calculating the starting point and the
     * end point of parentheses, move the pointer to the new start after calculation.
     * 4. Digits
     * Add it until we meet an operator
     */
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        char sign = '+';
        int num = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            /**
             * If ' ' is the last character, we still need to add all the remaining numbers
             * and push to the stack.
             */
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (c == '(') {
                    int end = findEnd(s, i);
                    num = calculate(s.substring(i + 1, end));
                    i = end;
                    continue;
                }
                if (sign == '+') {
                    stack.offerFirst(num);
                } else if (sign == '-') {
                    stack.offerFirst(-num);
                } else if (sign == '*') {
                    int peek = stack.pollFirst();
                    stack.offerFirst(peek * num);
                } else if (sign == '/') {
                    int peek = stack.pollFirst();
                    stack.offerFirst(peek / num);
                }
                num = 0;
                sign = c != '(' ? c : sign;
            }
            i++;
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }

    private int findEnd(String s, int index) {
        int count = 1;
        int i = index + 1;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
            if (count == 0) {
                return i;
            }
            i++;
        }
        return 0;
    }
}
