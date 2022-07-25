package leetcode.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {

    public static List<List<Integer>> threeSum(int[] a, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a);
        for (int i = 0; i < a.length - 2; i++) {
            if (i == 0 || (i > 0 && a[i] != a[i - 1])) {  // 跳过可能重复的答案
                int pl = i + 1;
                int pr = a.length - 1;
                while (pl < pr) {
                    int sum = a[i] + a[pl] + a[pr];
                    if (sum < target) {
                        // 去除重复值
                        while (pl < pr && a[pl] == a[pl + 1]) pl++;
                        pl++;
                    } else if (sum > target) {
                        while (pl < pr && a[pr] == a[pr - 1]) pr--;
                        pr--;
                    } else {
                        result.add(Arrays.asList(a[i], a[pl], a[pr]));
                        while (pl < pr && a[pl] == a[pl + 1]) pl++;
                        while (pl < pr && a[pr] == a[pr - 1]) pr--;
                        pl++;
                        pr--;
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }

    // 最优
    public static List<List<Integer>> threeSumZY(int[] nums, int t) {
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案
                if (i > 0) {
                    System.out.println("shit:nums[i] " + nums[i]);
                    System.out.println("shit:nums[i-1] " + nums[i - 1]);
                }
                int l = i + 1, r = nums.length - 1, sum = t - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) l++;   // 跳过重复值
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                    }
                }
            }
        }
        return ls;
    }

    public static List<List<Integer>> threeSumGf(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] a = {-1,-1,1,2,-1,-4};
//        int[] a = {-1, -1, 2, 2, -4};
        int[] a = {-1, 0, 1, 2, -1, -4};
//        int[] a = {-1, 0, 0, 0, 0};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        int t = 0;
        System.out.println(threeSum(a, t));
    }

}
