package leetcode.dp;

// 64
public class 最小路径和64 {

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;


        if (m == 0) {
            return 0;
        }
        if (n == 0) {
            return 0;
        }
        // 定义dp[i][j]为走到i,j位置的最小路径和
//        int[][] dp = new int[m][n];
        int[] dp = new int[n];
        // 那么由于只能向下和向右走有，dp[i][j] = min(dp[i-1][j],dp[i][j-1])+grid[i][j]
        // i、j不能为0，所以初始值为dp[0][j]、dp[i][0]
        // 只能往右走，初始化第一行，注意初始化时，dp[0][k] = dp[0][k-1]+grid[0][k]
        dp[0] = grid[0][0];
        for (int k = 1; k < n; k++) {
            dp[k] = dp[k - 1] + grid[0][k];
        }
        // 只能往下走，初始化第一列，注意，这里从dp[1][0]开始
//        for (int k = 1; k < m; k++) {
//            dp[k][0] = dp[k - 1][0] + grid[k][0];
//        }

        // 计算dp数组
        for (int i = 1; i < m; i++) {
            dp[0] = grid[i][0] + dp[0];
            for (int j = 1; j < n; j++) {
//                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
//        int[][] grid = {{1}, {2}};
//        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(grid));

//        int[][] g = new int[2][2];
//        System.out.println(g[0][1]);
    }
}
