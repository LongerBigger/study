package leetcode.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 四数之和 {

    public static List<List<Integer>> fourSum(int[] a, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a);

        for (int i = 0; i < a.length - 3; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                for (int j = i + 1; j < a.length - 2; j++) {
                    int sum = target - a[i] - a[j];
                    int l = j + 1;
                    int r = a.length - 1;

                    while (l < r) {
                        if (a[j] + a[l] + a[r] == target - a[i]) {
                            result.add(Arrays.asList(a[i], a[j], a[l], a[r]));
                            while (j < a.length - 2 && a[j] == a[j + 1]) j++;
                            while (l < r && a[l] == a[l + 1]) l++;
                            while (l < r && a[r] == a[r - 1]) r--;
                            l++;
                            r--;
                        } else if (a[j] + a[l] + a[r] < target - a[i]) {
                            while (j < a.length - 2 && a[j] == a[j + 1]) j++;
                            while (l < r && a[l] == a[l + 1]) l++;
                            l++;
                        } else {
                            while (j < a.length - 2 && a[j] == a[j + 1]) j++;
                            while (l < r && a[r] == a[r - 1]) r--;
                            r--;
                        }
                    }

                }
            }
        }
        return result;
    }

    public static List<List<Integer>> fourSumGf(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    public static void main(String[] args) {
        int[] a = {1,0,-1,-1,-1,-1,-1,0,-2,2};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        int t = 0;
        System.out.println(fourSum(a, t));
    }
}
