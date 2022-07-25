package leetcode.dp;

import java.util.Arrays;
import java.util.Stack;

/*
一定要注意边界条件，<、>、<=,>=的判定
dp要注意，dp[i]对应字符的位置为s[i-1]，字符串下标可以为0,


 */
public class 最长有效括号32 {


    public static int longestValidParenthesesC(String s) {
        // 1.定义dp[i]为，以字符s[i-1]结尾的连续有效括号的长度
        // 2.则有，若s[i-1]=='('，则dp[i]=0。
        //      若s[i-1]==')'，则有三种情况：
        //  情况1：字符s[i-2]为'('，它与s[i-1]匹配，dp[i]=dp[i-2]+2
        // 情况2：字符s[i-2]为')'，s[i-1]与s[i-1-dp[i-1]-1]匹配(s[i-1-dp[i-1]-1]为左括号)，
        //      dp[i]=dp[i-1]+2+dp[i-1-dp[i-1]]  (dp[i-1]!=0)
        //      dp[i]=0  (dp[i-1]==0)
        // 情况3：字符s[i-2]为')'，s[i-1]与s[i-1-dp[i-1]-1]匹配(s[i-1-dp[i-1]-1]为右括号)，
        //      dp[i]=0
        // 3.初始化全部为0
        int[] dp = new int[s.length() + 1];
        int ans = 0;
        for (int i = 2; i <= s.length(); i++) {
            System.out.println("i:"+i);
            if (s.charAt(i-1) == ')') {
                if (s.charAt(i - 2) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (dp[i - 1] != 0
                        && i - 2 - dp[i - 1] >= 0
                        && s.charAt(i - 2 - dp[i - 1]) == '(') {
                    dp[i] = dp[i - 1] + 2 + dp[i - 2 - dp[i - 1]];
                }
            }

            if (dp[i] > ans) {
                ans = dp[i];
            }
        }
        System.out.println(Arrays.toString(dp));
        return ans;
    }

    public static int longestValidParentheses(String s) {

        if (s.length() < 2) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        // 1.定义dp[i]为字符串s[0~i]的最长有效括号长度
        // 2.可知有：
        //    a. dp[i] = dp[i-1]  当前括号为左括号'('
        //    b. dp[i] = MAX(dp[i-1],s[i-dp[i-1]-1~i]是有效括号吗？dp[i-1]+2:dp[i-1]) 当前括号为右括号')'
        // 3.初始化
        dp[0] = 0;
        dp[1] = 0;

        // 遍历字符串，计算出DP
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) == '(') {
                dp[i] = dp[i - 1];
            } else if (s.charAt(i - 1) == ')') {
//                boolean r1 = isValidBracket(s, i - dp[i - 1] - 2, i - 1);
//                int i1 = r1 ? dp[i - 1] + 2 : dp[i - 1];
//
//                boolean r2 = isValidBracket(s, i - dp[i - 1] - dp[i - 2] - 2, i - 1);
//                int i2 = r2 ? dp[i - 1] + dp[i - 2] + 2 : dp[i - 1];
                boolean r1 = isValidBracket(s, 0, i - 1);

                System.out.println("i:" + i + "  sum:" + sum);
                int i1 = sum;
                dp[i] = Math.max(i1, i - i1 - 1 < 0 ? 0 : dp[i - i1 - 1]);
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }

    static int sum = 0;

    public static boolean isValidBracket(String s, int begin, int end) {
        if (begin < 0) {
            return false;
        }
        String ss = s.substring(begin, end + 1);


        // 用栈存储所有字符，从字符串尾到头依次弹出，记录有括号的数量，遇到左括号就把计算器-1遇到有括号+1
        // 若计数器小于0则返回false，若到最后计数器等于0，返回true
        Stack<Character> stack = new Stack<>();
        for (char c : ss.toCharArray()) {
            stack.push(c);
        }
        int count = 0;
        sum = 0;
        while (!stack.empty()) {
            Character cc = stack.pop();
            if (cc == '(') {
                count--;
            } else {
                count++;
            }
            if (count < 0) {
                return false;
            }
            sum++;
        }
        if (count != 0) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
//        String s = "(())(((())))(((()()()()()";
        String s = "(()())";
//        String s = ")()())";
//        String s = "()(())";
//        String s = ")(((((()())()()))()(())";
//        String s = "()())()()())))(((((((((())))))))))";
        System.out.println(longestValidParenthesesC(s));
    }

}
