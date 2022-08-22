package leetcode.dp;

public class 跳跃游戏55 {

    public boolean canJump(int[] nums) {
        int end = nums.length - 1;
        int pre = end;
        for (int i = pre - 1; i >= 0; i--) {
            if (i + nums[i] >= pre) {
                pre = i;
            }
        }
        return pre == 0;
    }

    // 如果一直往最前面跳的话会出现跳到0过不去的情况，这里还是要从后往前看
    public boolean canJumpMyError(int[] nums) {
        int target = nums.length - 1;
        int next = 0;
        for (int i = 0; i < target; i = next) {
            for (int j = 0; j <= nums[i]; j++) {
                // 数组下标一定要看看会不会越界[2,0]这里就越界了
                next = Math.max(next, i + j >= target ? i + j : i + j + nums[i + j]);
            }
            if (i == next) {
                // 跳不动了
                break;
            }
        }

        return next >= target;
    }

    public boolean canJump1(int[] nums) {
        int n = nums.length;
        //temp保存可以跳到最后一个可达目标的元素
        int temp = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int nextIndex = i + nums[i];
            if (nextIndex >= temp) {
                temp = i;
            }
        }
        return temp == 0 ? true : false;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 1, 4};
//        int[] nums = {3, 2, 1, 0, 4};
        int[] nums = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
//        int[] nums = {2, 0};
        System.out.println(new 跳跃游戏55().canJump(nums));
    }
}
