package leetcode.dp;

import java.util.Arrays;

public class 解码方法91 {

    public int numDecodings(String s) {

        int n = s.length();
        // dp[i]表示解码到下标i时的方法数
        int dpi = 0;
        int dpi1 = 0;
        int dpi2 = 0;
        // 初始化
        if (s.charAt(0) != '0') {
            dpi2 = 1;
        }

        if (n == 1) {
            return dpi2;
        }

        if (s.charAt(1) != '0') {
            dpi1 += dpi2;
        }

        if (Integer.parseInt(s.substring(0, 2)) <= 26 && s.charAt(0) != '0') {
            dpi1 += 1;
        }
        if (n == 2) {
            return dpi1;
        }
        for (int i = 2; i < n; i++) {
            dpi = 0;
            if (Integer.parseInt(s.substring(i - 1, i + 1)) <= 26 && s.charAt(i - 1) != '0') {
                dpi += dpi2;
                System.out.println("111:" + dpi2);
                System.out.println("shit 1 i: dpi:" + i + " " + dpi);
            }
            if (s.charAt(i) != '0') {
                dpi += dpi1;
                System.out.println("shit 22 i: dpi:" + i + " " + dpi);
            }
            System.out.println("i: dpi:" + i + " " + dpi);
            dpi2 = dpi1;
            dpi1 = dpi;
        }
        return dpi;
    }

    public int numDecodingsDp(String s) {

        int n = s.length();
        // dp[i]表示解码到下标i时的方法数
        // 递推关系式 dp[i] = dp[i-2] ,Integer.parseInt(s.substring(i-2, i)) <= 26 && s.charAt(i - 2) != 0
        //                = dp[i-1] ,s.charAt(i) != 0,下标i为零的话就续不上了
        int[] dp = new int[n];
        // 初始化
        if (s.charAt(0) != '0') {
            dp[0] = 1;
        }

        if (n == 1) {
            return dp[0];
        }

        if (s.charAt(1) != '0') {
            dp[1] += dp[0];
        }

        if (Integer.parseInt(s.substring(0, 2)) <= 26 && s.charAt(0) != '0') {
            dp[1] += 1;
        }

        for (int i = 2; i < n; i++) {
            if (Integer.parseInt(s.substring(i - 1, i + 1)) <= 26 && s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 2];
            }
            if (s.charAt(i) != '0') {
                dp[i] += dp[i - 1];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }

    public static void main(String[] args) {
        String s = "1201234";
        System.out.println(new 解码方法91().numDecodings(s));

    }
}
