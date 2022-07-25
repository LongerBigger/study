package leetcode.dp;

// 62
public class 不同路径62 {
    public static int uniquePaths(int m, int n) {

        if (n <= 0 || m <= 0) {
            return 0;
        }

        // 定义dp[i][j]为到i,j位置的不同路径条数
        int[][] dp = new int[m][n];
        // 因为只能向右和向下走，那么有dp[i][j] = dp[i-1][j] + dp[i][j-1];
        // 由上式知i、j必须大于0否则就数组越界了，那么i、j分别等于1时dp[0][j]和dp[i][0]的值就是初始值
        // dp[0][j]，表示一直往下走，只有一条路径
        for (int k = 0; k < n; k++) {
            //初始化第一行
            dp[0][k] = 1;
        }
        // dp[i][0]，表示一直往右走，也只有一条路径
        for (int k = 0; k < m; k++) {
            //初始化第一列
            dp[k][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }
}
