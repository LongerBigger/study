package leetcode.dp;

public class 最大矩形85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        // 思路：
        // 1.先计算出每个点左边的连续1的个数，记在数组left中
        //   left[i][j]表示矩阵matrix[i][j]左边（包含当前点matrix[i][j]）的连续1的个数
        // 2.若matrix[i][j]=='0'则left[i][j]=0;
        // 3.若matrix[i][j]=='1',
        // 则 left[i][j]=matrix[i][j-1]+1  ,j>0;
        //    left[i][j]=1  ,j==0;
        // 4.计算出left数组后，遍历矩阵，计算以matrix[i][j]为右下角的最大矩阵面积
        // 5.遍历所有满足条件的矩形，得到最大面积，设0=<k<i
        // 则满足条件的矩形高度为: high = i-k+1，宽度width初始化为left[i][j]
        // 满足条件的矩形宽度为:  width = Math.min(width,left[i][k])
        // 6.最大面积为： area初始化为left[i][j]
        // area = Math.max(left[i][j],high * width)

        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = j == 0 ? 1 : left[i][j - 1] + 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int area = left[i][j];
                    int width = left[i][j];
                    for (int k = i - 1; k >= 0; k--) {
                        width = Math.min(width, left[k][j]);
                        int height = i - k + 1;
                        area = Math.max(area, width * height);
                    }
                    result = Math.max(result, area);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

//        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        char[][] matrix ={{'0','0'}};
                System.out.println(new 最大矩形85().maximalRectangle(matrix));
    }
}
