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

        return f(s1, s2, 0, 0, s1.length());
    }

    boolean f(String s1, String s2, int i, int j, int n) {
        if (n == 1) {
            // 只有一个字母的时候，判断是否相等即可
            return s1.charAt(i) == s2.charAt(j);
        }
        String ss1 = s1.substring(i, i + n);
        String ss2 = s2.substring(j, j + n);
        // 统计词频
        if (!charFrqeuenceEqual(ss1, ss2)) {
            return false;
        }

        boolean r = false;
        // 遍历切分点
        for (int k = 1; k < n - 1; k++) {
            // 比较两种情况
            r |= f(ss1, ss2, 0, 0, k) && f(ss1, ss2, k, k, n - k) ||
                    f(ss1, ss2, 0, n - k, k) && f(ss1, ss2, k, 0, n - k);
        }
        return r;
    }


    private boolean charFrqeuenceEqual(String ss1, String ss2) {
        //比较两个字符串词频是否相等
        HashMap<Character, Integer> ss1Freq = new HashMap<>();
        HashMap<Character, Integer> ss2Freq = new HashMap<>();
        for (int i = 0; i < ss1.length(); i++) {
            char c1 = ss1.charAt(i);
            char c2 = ss2.charAt(i);
            ss1Freq.put(c1, ss1Freq.getOrDefault(c1, 0) + 1);
            ss2Freq.put(c2, ss2Freq.getOrDefault(c2, 0) + 1);
        }
        for (Map.Entry<Character, Integer> e : ss1Freq.entrySet()) {
            if (e.getValue() != ss2Freq.get(e.getKey())) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";
        System.out.println(new 扰乱字符串87().isScramble(s1, s2));
//    boolean a = false;
//    boolean b = false;
//        System.out.println(a|b|false|false|true);
    }
}
