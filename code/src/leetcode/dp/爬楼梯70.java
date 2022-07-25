package leetcode.dp;

public class 爬楼梯70 {
    public int climbStairs(int n) {
        //思路
        // 定义dp[0]跳上n-2阶的跳法、dp[1]为跳上n-1阶的跳法
        // 则跳上n阶的跳法 total =dp[0] +dp[1]
        // 初始化dp[0]=1
        // dp[1] =1;
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[2];
        dp[0] = 1;//跳上一级台阶有一种跳法
        dp[1] = 2;//跳上两级台阶有两种跳法
        int total = 2;//初始化为跳上两级台阶的跳法
        for (int i = 3; i <= n; i++) {
            total = dp[0] + dp[1];//n= n-1 + n-2
            dp[0] = dp[1];
            dp[1] = total;
        }
        return total;
    }


    public static void main(String[] args) {
        int n = 5;
        System.out.println(new 爬楼梯70().climbStairs(n));

    }
}
