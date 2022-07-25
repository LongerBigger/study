package leetcode.排序;

import java.util.Arrays;

public class MergeSort {

    static void mergeSort(int[] a, int left, int right) {
        if (right <= left) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(a, left, middle);
        mergeSort(a, middle + 1, right);
        // 归并
        merge(a, left, middle, right);
    }

    static int log2(int x) {
        int exp = 0;
        int sum = 1;
        while (sum < x) {
            sum *= 2;
            exp++;
        }
        return exp;
    }


    public static void mergeSortByLoop(int[] a) {
        for (int exp = 0; exp <= log2(a.length) - 1; exp++) {
            int step = (int) Math.pow(2.0, exp);
            for (int l = 0; l < a.length; l += 2 * step) {
                merge(a, l, Math.min(a.length - 1, (l + l + 2 * step - 1) / 2),
                        Math.min(a.length - 1, l + 2 * step - 1));
            }
        }
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int len = right - left + 1;
        int[] tmp = new int[len];
        int i = 0;
        int j = left;
        int k = middle + 1;
        while (i < tmp.length) {
            if (k > right) {
                while (j <= middle) {
                    tmp[i++] = a[j++];
                }
            } else if (j > middle) {
                while (k <= right) {
                    tmp[i++] = a[k++];
                }
            } else if (a[j] <= a[k]) {
                tmp[i++] = a[j++];
            } else {
                tmp[i++] = a[k++];
            }
        }
        System.arraycopy(tmp, 0, a, left, len);
    }

    public static void main(String[] args) {
        int[] a = {111, 3,23, 4533, 777, 9988, 999, 88, 999, 66, 342, 113,-9,0};
//        merge(a, 2, 2, 3);
        mergeSortByLoop(a);
        System.out.println(Arrays.toString(a));
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

}
