package leetcode.排序;

import java.util.Arrays;

public class BubbleSort {



    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Util.swap(arr, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {111,23,45,777,88,999,66,342,113};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
}
