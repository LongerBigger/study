package leetcode.分治;

import leetcode.排序.BinarySearch;
import leetcode.排序.MergeSort;

import java.util.Arrays;
import java.util.HashMap;


public class 两数之和 {

    static int[] twoSum(int[] s, int x) {
        MergeSort.mergeSortByLoop(s);
        int[] result = new int[2];
        result[1] = -1;
        for (int i = 0; i < s.length; i++) {
            result[0] = i;
            int br = BinarySearch.binarySearch(s, 0, s.length - 1, x - s[i]);
            if (br != -1) {
                result[1] = br;
                break;
            }
        }

        return result;
    }


    static int[] twoSumHm(int[] a, int t) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            if (hm.containsKey(t - a[i])) {
                return new int[]{hm.get(t - a[i]), i};
            } else {
                hm.put(a[i], i);
            }
        }
        return new int[]{};
    }


    public static void main(String[] args) {
        int[] a = {22, 33, 44, 55, 7, 8, 9};
        int x = 1;
        System.out.println(Arrays.toString(twoSumHm(a, x)));
    }
}
