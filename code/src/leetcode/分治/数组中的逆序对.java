package leetcode.分治;

// 51
public class 数组中的逆序对 {

    /*
     * 思路：
     * 1.修改归并排序，左右的逆序对 + 右边的逆序对 + 左边对右边的逆序对 = 总的逆序对
     * 2.左边或右边的逆序对算出来之后 可以对其进行排序，来计算左边对右边的逆序对，
     *   利用大于的传递性（例，左右两边都从大到小排序，若左边第一个大于右边第一个，则此处的逆序对个数为右边的个数），减少比较次数
     * 重点：一个数有多少逆序对，与他后面数的顺序无关
     */
    static int calcReversePair(int a[]) {
        return calcReversePair(a, 0, a.length - 1);
    }

    static int calcReversePair(int[] a, int left, int right) {
        if (right <= left) {
            return 0;
        }

        int middle = (left + right) / 2;
        int leftCount = calcReversePair(a, left, middle);
        int rightCount = calcReversePair(a, middle + 1, right);
        // 归并
        int left2rightCount = merge(a, left, middle, right);
        return leftCount + rightCount + left2rightCount;
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

    private static int merge(int[] a, int left, int middle, int right) {
        int reversePair = 0;
        int len = right - left + 1;
        int[] tmp = new int[len];
        int i = 0;
        int iLeft = left;
        int iRight = middle + 1;
        while (i < tmp.length) {
            if (iRight > right) {
                while (iLeft <= middle) {
                    tmp[i++] = a[iLeft++];
                }
            } else if (iLeft > middle) {
                while (iRight <= right) {
                    tmp[i++] = a[iRight++];
                }
            } else if (a[iLeft] > a[iRight]) { //从大到小排序
                // 左边大于右边的，那么这个数的逆序对就等于iRight到右边的个数
                reversePair += right - iRight + 1;
                tmp[i++] = a[iLeft++];
            } else {
                tmp[i++] = a[iRight++];
            }
        }
        System.arraycopy(tmp, 0, a, left, len);
        return reversePair;
    }

    public static void main(String[] args) {
//        int[] a = {1,0,-1,0,-2,2};
        int[] a = {7,5,6,4};
        System.out.println(calcReversePair(a));
    }
}


