package leetcode.dp;

public class 不同的二叉搜索树96 {
    public int numTrees(int n) {
        // dp[n]表示长度为n的序列所构成的不同二叉搜索树的数目
        int[] dp = new int[n + 1];
        // dp[n]等于选取以i(1<=i<=n)为根节点的不同二叉搜索树的数目之和
        // 以i为根节点的话，这棵树的数目=左边的数目乘以右边的数目
        // 左边的数目为dp[i-1]，右边的数目为dp[n-i]
        // 可知dp[n]=∑dp[i-1]*dp[n-1] ,1<=i<=n
        // 初始化
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // 计算dp[i]
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new 不同的二叉搜索树96().numTrees(3));
    }
}
