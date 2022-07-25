package leetcode.dp;

public class 通配符匹配44 {

    public boolean isMatchTJ(String s, String p) {
        // 思路：定义dp[i][j]为以s[i]结尾的字符串与p[j]结尾的模式串是否匹配
        // dp[i][j] == dp[i][j-1],p[j]=='*'，*匹配空字符串
        // dp[i][j] == dp[i-1][j],p[j]=='*'，*匹配1个字符的字符串并且保留*，由于for循环的原因，匹配一个保留*和匹配多个字符是一样的效果
        // dp[i][j] = dp[i-1][j-1],s[i]==p[j]||p[j]=='?'
        // 初始化：要看第0列和第0行
        int m = s.length() + 1;
        int n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for (int j = 1; j < n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = true;
            }
            else {
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && dp[i - 1][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public boolean isMatch(String s, String p) {
        // 思路：定义dp[i][j]为以s[i]结尾的字符串与p[j]结尾的模式串是否匹配
        // 则有：
        // 1.dp[0][0] = true
        // 2.dp[i][j] = dp[i-1][j-1] ,p[j]=='?'||s[i] == s[j]
        // 3.dp[i~n][j] = dp[i-1][j-1] ,p[j]=='*'，注意*可以匹配空字符串
        int n = s.length() + 1;
        int m = p.length() + 1;
        boolean[][] dp = new boolean[n][m];
        // 1.初始化第0行
        dp[0][0] = true;
        for (int j = 1; j < m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j]) {
                    continue;
                }
                if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // *匹配非空字符串
                    for (int k = i; k < n; k++) {
                        dp[k][j] = dp[i - 1][j - 1];
                    }
                    // 匹配空字符串注意要有：dp[i][j] =dp[i][j-1]，dp[i][j+1]=dp[i-1][j-1];
                    if (!dp[i][j]) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    if (j + 1 < m && (p.charAt(j) == '?' || s.charAt(i - 1) == p.charAt(j))) {
                        dp[i][j + 1] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    public static void main(String[] args) {
        String s = "bb";
        String p = "*****";
        System.out.println(new 通配符匹配44().isMatchTJ(s, p));
    }
}
