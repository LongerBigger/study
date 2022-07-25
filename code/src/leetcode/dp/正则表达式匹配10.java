package leetcode.dp;

import java.util.Arrays;

// 10
// 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
public class 正则表达式匹配10 {

    public static boolean isMatch(String s, String p) {
        // 定义dp[i,j]为s、p长度为i、j时s、p是否匹配
        // 递推关系式推导：
        // 字符串s[i]与p[j]是否匹配，
        // 先比较s[0]与p[0] p[0]有3种情况，普通字符、'.'、'*'
        // 1.若p[j]=普通字符 则比较两个字符是否相同，得到dp[i][j],i++,j++
        //      若相同、推出：dp[i][j] = dp[i-1][j-1]
        //      若不相同、推出：dp[i][j] = false
        // 2.若p[j]='.'，则字符s[i]与p[j]匹配,i++,j++
        //      推出：dp[i][j] = dp[i-1][j-1]
        // 3.若p[j]='*'，则定义字符pre=p[j-1]，若pre='*'，则有dp[i][j] = dp[i][j-1]
        //  若pre!='*'：
        // （1）比较s[i]与pre是否相等，若相等则有dp[i][j]=dp[i][j-1],令i++,j不变,继续比较s[i]与pre是否相等
        // （2）若不相等，则dp[i][j]=false，将*看成匹配0个，比较s[i]与p[j+1]，比较的操作重复1~3的步骤
        //
        //      a.s[i]与p[j+1]相等dp[i][j+1]=true
        //      b.若不相等dp[i][j+1]=false
        // ，则p[0][0]=true,i++,j++
        // (如果i-1到j匹配并且i<=j)并且p[i]!='.'||p[i]!='*'||s[i]==p[i] 则dp[i,j] =

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 初始化
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1] || dp[0][j - 2];
            } else {
                dp[0][j] = false;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pre = 0;
                if (j >= 2) {
                    pre = p.charAt(j - 2);
                }
                if (p.charAt(j - 1) == '.') {
                    // 只能说明s[i]和p[j]匹配
                    if (pre == '*') {
                        // 匹配0个前面的字符，i=1,j=3,a,.*..
                        // dp[i][j] = dp[i - 1][j - 2]
                        // 匹配1个前面的字符，例如 ab和a*b是匹配的
                        // dp[i][j] = dp[i-1][j-1]
                        // 匹配2个前面的字符，例如当前i为2 ccc和c*是匹配的
                        // dp[i][j] = dp[i-1][j-1]
                        // 匹配多个前面的字符，例如当前i为2 ccc和c*是匹配的
                        // dp[i][j] =  dp[i-1][j-1]
                        dp[i][j] = dp[i - 1][j - 2] || dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else if (p.charAt(j - 1) == '*') {
                    if (pre == '*') {
                        dp[i][j] = dp[i][j - 1];
                    } else if (pre == '.') {
                        dp[i][j] = (j >= 2 && dp[i][j - 2]) //0个
                                || dp[i][j - 1] // 1个
                                || dp[i - 1][j - 1] //2个
                                || dp[i - 1][j];//多个
                    } else {
                        // 匹配0个前面的字符，例如 c和ca*是匹配的 i=1,j=3 dp[i][j] = dp[i][j-2]
                        // 匹配1个前面的字符，例如 cb和c*b是匹配的 dp[i][j] = dp[i][j-1]
                        // 匹配2个前面的字符，例如当前i为2 ccc和c*是匹配的 dp[i][j] = dp[i-1][j-1] && s.charAt(i) == p.charAt(pre)
                        // 匹配多个前面的字符，例如当前i为2 ccc和c*是匹配的 s.charAt(i - 1) == pre &&dp[i][j] = dp[i-1][j]
//                        System.out.println("i:" + i);
//                        System.out.println("j:" + j);
                        dp[i][j] = dp[i][j - 2]
                                || dp[i][j - 1]
                                || s.charAt(i - 1) == pre && dp[i - 1][j - 1]
                                || s.charAt(i - 1) == pre && dp[i - 1][j];
                    }
                    if (i > m) {
                        i = m;
                    }
                } else {
                    if (pre == '*') {
                        // 匹配0个前面的字符，
                        // dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && j <= 3
                        // || s.charAt(i - 1) == p.charAt(j - 1) && j > 3 && dp[i - 1][j - 2]
                        // 匹配1个前面的字符，例如 ab和a*b是匹配的
                        // dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) &&dp[i-1][j-1]
                        // 匹配2个前面的字符，例如当前i为2 ccc和c*是匹配的
                        // dp[i][j] = dp[i-1][j-1] && s.charAt(i - 1) == p.charAt(j - 1)
                        // 匹配多个前面的字符，例如当前i为2 ccc和c*是匹配的
                        // dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && dp[i-1][j-1]
                        dp[i][j] =
//                                (s.charAt(i - 1) == p.charAt(j - 1) && j <= 3 && dp[i - 1][j - 1])
//                                        ||
//                                    s.charAt(i - 1) == p.charAt(j - 1) && j > 3 && dp[i - 1][j - 2]
                                    s.charAt(i - 1) == p.charAt(j - 1)  && dp[i - 1][j - 2]
                                        || s.charAt(i - 1) == p.charAt(j - 1) && dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && dp[i - 1][j - 1];
                    }
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
//        "bbabacccbcbbcaaab"
//        "a*b*a*a*c*aa*c*bc*"
//        "aasdfasdfasdfasdfas"
//        "aasdf.*asdf.*asdf.*asdf.*s"
        String s = "bbabacccbcbbcaaab";
//        String s = "aasdfasdfasdf";
//        String s = "";
        String p = "a*b*a*a*c*aa*c*bc*";
//        String p = ".*";
        System.out.println(isMatch(s, p));
    }
}
