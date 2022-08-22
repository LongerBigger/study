package leetcode.dp;

import java.util.Arrays;

public class 不同路径63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;

        if (rows <= 0) {
            return 0;
        }
        int cols = obstacleGrid[0].length;
        //1.定义dp[i][j]为 走到obstacleGrid[i][j]的不同路径数
        int[][] dp = new int[rows][cols];
        //2.初始化第一行、第一列
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < cols; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        //3.遍历矩阵计算所有dp
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        for (int[] a : dp) {
            System.out.println(Arrays.toString(a));
        }
        return dp[rows - 1][cols - 1];

    }

    // 滚动数组优化空间复杂度
    public int uniquePathsWithObstaclesGdszs(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;

        if (rows <= 0) {
            return 0;
        }
        int cols = obstacleGrid[0].length;
        //1.定义dp[j]为 走到obstacleGrid[i][j]的不同路径数，i从0开始滚动
        int[] dp = new int[cols];
        //2.初始化第一行、第一列
        for (int i = 0; i < cols; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[i] = 0;
            } else {
                dp[i] = i == 0 ? 1 : dp[i - 1];
            }
        }

        //3.遍历矩阵计算所有dp
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j != 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[cols - 1];

    }

    public static void main(String[] args) {

        int[][] arr = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(new 不同路径63().uniquePathsWithObstaclesGdszs(arr));
    }
}
