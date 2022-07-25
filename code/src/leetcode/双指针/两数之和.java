package leetcode.双指针;

import java.util.Arrays;

public class 两数之和 {

    public static int[] twoSum(int[] a, int t) {

        Arrays.sort(a);
        int[] result = new int[2];
        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            System.out.println("a[l]:"+a[l]+" a[r]:"+a[r]);
            System.out.println("l:" + l + " r:" + r);
            if (a[l] + a[r] < t) {
                System.out.println("l:" + l + " r:" + r);
                System.out.println("小于");
                while (l < r && a[l] == a[l + 1]) l++;
                l++;
            } else if (a[l] + a[r] > t) {
                System.out.println("l:" + l + " r:" + r);
                System.out.println("大于");
                while (l < r && a[r] == a[r - 1]) r--;
                r--;
            } else {
                System.out.println("l:" + l + " r:" + r);
                System.out.println("等于");
                result[0] = l;
                result[1] = r;
                while (l < r && a[l] == a[l + 1]) l++;
                while (l < r && a[r] == a[r - 1]) r--;
                l++;
                r--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {3,2,4};
        int x = 6;
        System.out.println(Arrays.toString(twoSum(a, x)));
    }
}
