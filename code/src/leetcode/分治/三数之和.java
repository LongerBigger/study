package leetcode.分治;

import java.util.*;

public class 三数之和 {
    static List<List<Integer>> threeSum(int[] a, int target) {
        Set<List<Integer>> result = new HashSet<>();

        for (int i = 0; i < a.length - 2; i++) {
            // 在a[i]之后的数中，找两数之和为target-a[i]的两个数
            List<List<Integer>> two = twoSumHm(a, i + 1, a.length - 1, target - a[i]);
            if (two.size() != 0) {
                for (List<Integer> list :two){
                    List<Integer> l = new ArrayList<>();
                    l.add(a[i]);
                    l.add(a[list.get(0)]);
                    l.add(a[list.get(1)]);
                    Collections.sort(l);
                    result.add(l);
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }

    static List<List<Integer>> twoSumHm(int[] a, int left, int right, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (hm.containsKey(target - a[i])) {
                List<Integer> l = new ArrayList<>();
                l.add(hm.get(target - a[i]));
                l.add(i);
                result.add(l);
//                return new int[]{hm.get(target - a[i]), i};
            } else {
                hm.put(a[i], i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        int[] a = {22, 33, 44, 55, 7, 8, 9,21,9,-2,1,1,-2,1,1};
        int[] a = {-1,0,1,2,-1,-4};
        int x = 17;
        System.out.println(twoSumHm(a, 0, a.length - 1, x).toString());
        System.out.println(threeSum(a, 0).toString());
    }
}
