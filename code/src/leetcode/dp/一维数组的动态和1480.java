package leetcode.dp;

import java.util.Arrays;

public class 一维数组的动态和1480 {
    public static int[] runningSum(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(runningSum(nums)));
    }
}
