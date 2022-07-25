package leetcode.排序;

public class BinarySearch {

    public static String YES = "YES";
    public static String NO = "NO";

    public static String binarySearch(int[] a, int t) {
        if (a.length == 0) return "NO";

        return binarySearch(a, 0, a.length - 1, t) != -1 ? YES : NO;
    }

    public static int binarySearch(int[] a, int left, int right, int t) {
        if (left == right && a[left] != t) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (a[middle] == t) {
            return middle;
        } else if (a[middle] < t) {
            return binarySearch(a, middle + 1, right, t);
        } else {
            return binarySearch(a, left, middle - 1, t);
        }

    }

    public static void main(String[] args) {
        int[] a = {22, 33, 44, 55, 7, 8, 9};
        System.out.println(binarySearch(a, 33));
    }
}
