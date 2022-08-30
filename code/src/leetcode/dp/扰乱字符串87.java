package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class 扰乱字符串87 {


    public boolean isScramble(String s1, String s2) {

        // 思路:
        // 1.统计词频，s1和s2词频不同返回false
        // 2.枚举所有切分点i,若s1长度为n，则将s1切分为s1(0,i)、s1(i+1,n)
        // 3.根据题设需要判断 s1(0,i)和s2(0,i)并且s1(i+1,n)和s2(i+1,n)
        //   或s1(0,i)和s2(n-i,n)并且s1(i+1,n)和s2(0,i)，若上述关系为true则s1和s2满足题设
        // 4.由此原问题划分为若干子问题，子问题的解合起来就是原问题的解，可以用动态规划求解
        // 5.因为需要遍历切分点1<i<n-1，子串切分的时候会有重复计算，因此可以将计算结果保存起来减少重复计算
        // 6.定义一个状态，将s1、s2、及其切分子串是否满足题设统一起来，
        //   定义f(i,j,n),i为s1的起始下标、j为s2的起始下标,n为子串长度，表示两个子串s1(i,i+n)、s2(j,j+n)是否满足题设
        // 7.则原问题为：f(0,0,n) n = s1.length
        // 8.递归计算原问题，递归结束条件为 n==1;或词频不同，直接返回。
        // 9.递归的过程做三件事
        //   a.看是否到达结束条件
        //   b.遍历切分点
        //   c.计算每一个切分点
        //   d.将每一个切分点的结果组合成原问题的解
        boolean[][][] dp = new boolean[s1.length()][s1.length()][s1.length() + 1];
        boolean[][][] freqDp = new boolean[s1.length()][s1.length()][s1.length() + 1];
        return f(s1, s2, 0, 0, s1.length(), dp, freqDp);
    }

    boolean f(String s1, String s2, int i, int j, int n, boolean[][][] dp, boolean[][][] freqDp) {
//        System.out.println("i:" + i + " j:" + j + " n:" + n);
        if (dp[i][j][n]) {
            return true;
        }
        if (n == 1) {
            // 只有一个字母的时候，判断是否相等即可
//            System.out.println("s1.charAt(i) :" + s1.charAt(i) + " s2.charAt(i):" + s2.charAt(i));
            return s1.charAt(i) == s2.charAt(j);
        }
//        System.out.println("ss1:" + ss1 + " ss2:" + ss2);
        // 统计词频
        int[] charCount = new int[26];
        for (char c1 : s1.toCharArray()) {
            charCount[c1 - 'a']++;
        }
        for (char c2 : s2.toCharArray()) {
            charCount[c2 - 'a']--;
        }
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
//        if (!charFrqeuenceEqual(s1, s2, i, j, n, freqDp)) {
//            return false;
//        }

        boolean r = false;
        // 遍历切分点
        for (int k = 1; k <= n - 1; k++) {
            // 比较两种情况
//            System.out.println("k:" + k);
            r |= f(s1, s2, i, j, k, dp, freqDp) && f(s1, s2, i + k, j + k, n - k, dp, freqDp) ||
                    f(s1, s2, i, j + n - k, k, dp, freqDp) && f(s1, s2, i + k, j, n - k, dp, freqDp);
        }
        dp[i][j][n] = r;
        return r;
    }


    private boolean charFrqeuenceEqual(String s1, String s2, int i, int j, int n, boolean[][][] freqDp) {
        if (freqDp[i][j][n]) {
            return true;
        }
        String ss1 = s1.substring(i, i + n);
        String ss2 = s2.substring(j, j + n);
        //比较两个字符串词频是否相等
        HashMap<Character, Integer> ss1Freq = new HashMap<>();
        HashMap<Character, Integer> ss2Freq = new HashMap<>();
        for (int k = 0; k < ss1.length(); k++) {
            char c1 = ss1.charAt(k);
            char c2 = ss2.charAt(k);
            ss1Freq.put(c1, ss1Freq.getOrDefault(c1, 0) + 1);
            ss2Freq.put(c2, ss2Freq.getOrDefault(c2, 0) + 1);
        }
        for (Map.Entry<Character, Integer> e : ss1Freq.entrySet()) {
            if (e.getValue() != ss2Freq.get(e.getKey())) {
                return false;
            }
        }
        freqDp[i][j][n] = true;
        return true;
    }

    public boolean isScrambleDp(String s1, String s2) {

        // 定义 dp[i][j][n]表示s1、s2分别以下标i、下标j开始，长度为n的字符串是否为扰乱字符串
        // 则递推关系有
        // dp[i][j][n] = 所有的dp[i][j][k]&&dp[i+k][j+k][n-k]||dp[i][j+n-k][k]&&dp[i+n-k][j][k](1<=k<=n-1)
        int n = s1.length();
        boolean[][][] dp = new boolean[s1.length()][s1.length()][s1.length() + 1];
        // 初始化先算出k=0的所有dp值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        // 遍历算出所有的dp值
        // 注意遍历顺序，应该是以从k=2遍历到k=n
        for (int k = 2; k <= n; k++) {
            for (int i = 0; i <= n - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    for (int h = 1; h < k; h++) {
                        dp[i][j][k] |= dp[i][j][h] && dp[i + h][j + h][k - h]
                                || dp[i][j + k - h][h] && dp[i + h][j][k - h];
                    }
                }
            }
        }

        return dp[0][0][n];
    }

    public static void main(String[] args) {
//        String s1 = "eebaacbcbcadaaedceaaacadccd";
//        String s1 = "great";
//        String s2 = "eadcaacabaddaceacbceaabeccd";
//        String s2 = "rgeat";
//        String s1 = "AC";
//        String s2 = "CA";
        String s1 = "abcdbdacABCDE";
        String s2 = "bdacabcdABCDC";
        long s = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());
//        System.out.println(new 扰乱字符串87().isScramble(s1, s2));
        System.out.println(new 扰乱字符串87().isScrambleDp(s1, s2));
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() - s);
//    boolean a = false;
//    boolean b = false;
//        System.out.println(a|b|false|false|true);
    }
}
