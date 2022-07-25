package leetcode.dp;

/**
 * 思路：
 * 1.位置i能接的雨水数等于min(位置i左边最大的值，右边最大值) - 位置i的高度
 * 2.暴力的做法：对于每个位置i，分别遍历其左边（含当前）、右边（含当前）的位置，获得leftMax和rightMax
 * 3.根据1计算出位置i的雨水高度，再求和
 * 4.优化2的暴力做法，用leftMax数组，记录位置i的leftMax，
 * 有：leftMax[0] = height[0]，初始值，
 * 有：rightMax[n-1] = height[n-1]，初始值，
 * 5.leftMax[i] = max(leftMax[i-1],height[i]),n-1>=i>0
 * 6.rightMax[i] = max(rightMax[i+1],height[i]),n-1>=i>0
 * 7.正序、逆序遍历数组height，得到leftMax和rightMax数组，根据2，再遍历一次求和即可得到结果
 */
public class 接雨水42 {

    public int trap(int[] height) {

        int len = height.length;
        // 1.正序遍历得到leftMax数组
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 2.逆序遍历得到rightMax数组
        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 3.遍历height，求和得到结果
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return result;
    }


    public static void main(String[] args) {

    }
}
