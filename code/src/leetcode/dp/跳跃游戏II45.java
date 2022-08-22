package leetcode.dp;

import java.util.Arrays;

public class 跳跃游戏II45 {


    public int jumpmY(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int lastIndex = nums.length - 1;
        // 思路：从终点往回找，记录能跳到位置i的最左边的点
        // 定义dp[i] 为，能跳到位置i的最左边的点，包括当前自己这个点
        // 记录下来之后，再从终点往回遍历，找dp[lastIndex]走到dp[0]花了几步即可
        // 初始化
        int[] dp = new int[lastIndex + 1];
        for (int i = lastIndex; i > 0; i--) {
            // 找到下标为i的最左边可达的下标
            int leftest = i;
            for (int j = i; j >= 0; j--) {
                // 下标j加上可跳的长度nums[j]，>=当前位置下标i，即j可达i
                if (j + nums[j] >= i) {
                    leftest = j;
                }
            }
            dp[i] = leftest;
        }
        System.out.println(Arrays.toString(dp));
        int result = 0;
        for (int i = lastIndex; i > 0; i = dp[i]) {
            result++;
        }
        return result;
    }

    public int jumpMy(int[] nums) {
        int target = nums.length - 1;
        // next表示下一步跳到的下标
        int next = 0;
        int r = 0;
        for (int i = 0; i < target; i = next) {

            next = i;
            // 找到下一步能跳最远的点，包含当前这一步
            for (int j = 1; j <= nums[i]; j++) {
                // 数组下标一定要看看会不会越界
                if (i + j >= target) {
                    r++;
                    return r;
                }
                // 如果这个条件不进来，说明无法往前跳了，不能到达终点，与题设不符
                if (i + j + nums[i + j] > next + nums[next]) {
                    next = i + j;
                }
            }
            r++;
            if (next >= target) {
                // 到终点了
                break;
            }
        }

        return r;
    }

    public int jump(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 0;
        }
        int result = 0;
        // 思路：根据当前位置的数字可以知道下一次跳跃起点的范围，
        // 保持当前跳跃距离+下一次能跳的距离之和为最长即可
        // 需要先判断直接从当前距离跳跃之后能否到达终点，不能到达才进行下一次跳跃

        // 定义dp为从当前位置跳跃到下一个位置，两次跳跃距离的最大值
        int dp = 0;
        int s = nums.length;

        for (int i = 0; i < s; i += dp) {
            if (dp + nums[i] >= s) {
                System.out.println("result:" + result);
                result++;
                break;
            } else {
                for (int d = i + 1; d <= i + nums[i]; d++) {
                    // 第一次跳跃距离 d-i
                    // 第二次跳跃距离 nums[d]
                    int sum = d - i + nums[d];
                    dp = Math.max(sum, dp);
                    System.out.println("dp:" + dp);
                }
                result++;
            }
            result++;
            System.out.println("result:" + result);
        }
        System.out.println("result:" + result);
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {1,2,3,4,5};
        System.out.println(new 跳跃游戏II45().jumpMy(nums));
    }
}
