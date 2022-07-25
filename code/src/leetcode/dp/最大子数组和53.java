package leetcode.dp;

public class 最大子数组和53 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        //思路,定义dp以为位置i结尾的最大子数组和
        int dp = nums[0];
        int result = dp;
        for (int i = 1; i < nums.length; i++) {
            dp = dp < 0 ? nums[i] : nums[i] + dp;
            result = Math.max(result, dp);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new 最大子数组和53().maxSubArray(nums));
    }
}
