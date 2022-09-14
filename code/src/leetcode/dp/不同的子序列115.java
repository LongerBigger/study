package leetcode.dp;

import java.util.Arrays;

public class 不同的子序列115 {

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }

        // 思路：
        // 定义dp[i][j]为s前i个字符子串中包含t的前j个字符子串的子序列数目
        // 递推关系：
        // dp[i][j] = dp[i-1][j-1]+dp[i-1][j],s[i-1] == t[j-1]
        // dp[i][j] = dp[i-1][j],s[i-1] != t[j-1]

        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化边界值
        // 第一行，空串不可能是有长度串的父序列，初始化为0即可
        // 第一列
        for (int i = 0; i <= m; i++) {
            // 空串是任何串的子序列
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m][n];
    }

    public int numDistinctGdsz(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }

        // 思路：
        // 定义dp[i][j]为s前i个字符子串中包含t的前j个字符子串的子序列数目
        // 递推关系：
        // dp[i][j] = dp[i-1][j-1]+dp[i-1][j],s[i-1] == t[j-1]
        // dp[i][j] = dp[i-1][j],s[i-1] != t[j-1]

        int m = s.length();
        int n = t.length();
        int[][] dp = new int[2][n + 1];
        // 初始化边界值
        // 第一行，空串不可能是有长度串的父序列，初始化为0即可
        // 初始化dp[0],空串是空串的子序列
        dp[0][0] = 1;
        dp[1][0] = 1;

        for (int i = 1; i <= m; i++) {
            // 左上角的值在数组滚动的时候会被覆盖，要用一个变量先记下来
            for (int j = 1; j <= n; j++) {
                dp[1][j] = dp[0][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[1][j] += dp[0][j - 1];
                }
            }
            System.arraycopy(dp[1], 0, dp[0], 0, n + 1);
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
//        System.out.println(new 不同的子序列115().numDistinct(s, t));
        System.out.println(new 不同的子序列115().numDistinctGdsz(s, t));
    }
}
