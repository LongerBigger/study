package search;

import java.util.Arrays;

public class BinarySearch {
    public static int lengthOfLIS(int[] nums) {
        System.out.println(Arrays.toString(nums));
        int head = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[head]) {
                nums[++head] = nums[i];
            } else if (nums[i] < nums[head]) {
                int firstGreaterIndex = binarySearchFirstGreater(nums, 0, head, nums[i]);
                nums[firstGreaterIndex] = nums[i];
                System.out.println(" nums[head]:" + nums[head] + " nums[i]:" + nums[i] + " firstGreaterIndex:" + firstGreaterIndex);
            }
            System.out.println("i:" + i + " head:" + head);
            System.out.println(Arrays.toString(nums));
        }
        return head + 1;
    }

    public static int binarySearchFirstGreater(int[] nums, int low, int high, int target) {
        int l = low;
        int h = high;
        int mid = l + (h - l) / 2;//这样写不会溢出
        while (l <= h) {
            mid = l + (h - l) / 2;//这样写不会溢出
//            System.out.println("l:" + l + " h:" + h + " mid:" + mid + " nums[mid]:" + nums[mid] + " target:" + target);
//            System.out.println("nums[mid]:" + nums[mid] + " h:" + h + " mid:" + mid);
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                h = mid - 1;
            } else {
                return mid;
            }
        }
        return nums[mid] < target ? mid + 1 : mid;
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,3,4,  5, 7, 9,11};
//        System.out.println(Arrays.toString(nums));
//        System.out.println(binarySearchFirstGreater(nums, 0, nums.length - 1, 6));
//        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = {10, 9, 2, 2,2,2,6, 5, 3,3,3, 4, 5};
        System.out.println(lengthOfLIS(nums));
    }

}
