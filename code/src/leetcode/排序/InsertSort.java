package leetcode.排序;

import java.util.Arrays;

public class InsertSort {

    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            //开始插入
            for (int j = i; j > 0 &&a[j]<a[j-1]; j--){
                Util.swap(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {111,23,45,777,88,999,66,342,113};
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }

}


