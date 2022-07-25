package leetcode.dp;

//
public class 编辑距离72 {

    public static int minDistance(String word1, String word2) {
        // 采用二维数组DP
        // 一、定义dp[i][j]为，当word1长度为i、word2长度为j时，word1转换为word2的最少操作数
        int rowLen = word1.length() + 1;
        int colLen = word2.length() + 1;

//        int[][] dp = new int[rowLen][colLen];

        // 二、关系式推导，word1[i] 表示word1的第i个字符
        // dp[i-1][j]表示word1去掉最后一个字符转换成word2的最小操作数
        // 三种操作对应的关系式（注意、操作都是对word1进行的   ）
        // 删除：1.dp[i][j] = dp[i-1][j] +1  只需在word1末尾删除字符word1[i],就变成word[i-1]了，在将替换成word2[j]了
        // 插入：2.dp[i][j] = dp[i][j-1] +1  word1[i]替换成word2[j-1]后再插入一个字符即可变成word2[j]
        // 替换：3.dp[i][j] = dp[i-1][j-1] +1  字符串word[i-1]替换成word[j-1]后，若字符word[i]!=word[j]只需要一个替换操作就可以了
        //                                    若等于的话，不需要操作，则有dp[i][j] = dp[i-1][j-1]
        // 那么有 dp[i][j] = min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]) +1
        // 另，若word1的第i个字符（下标i-1）与word2的第j个字符（下标j-1）相等，那么dp[i][j] = dp[i-1][j-1]
        // 知，完整关系式为：
        // dp[i][j] = dp[i-1][j-1]   （word1的第i个字符（下标i-1）与word2的第j个字符（下标j-1）相等）
        // dp[i][j] = min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]) +1 （word1的第i个字符（下标i-1）与word2的第j个字符（下标j-1）不相等）

        // 三、初始化，
        // 1.因为i、j不能等于0，否则就越界了，初始值为dp[0][j]和dp[i][0]
        // 可知：
        // 2.dp[0][j] = word2.length() ，一直插入
        // 3.dp[i][0] = word1.length() ，一直删除
//        for (int k = 0; k < rowLen; k++) {
//             初始化第一列，dp[i][0]
//            dp[k][0] = k;
//        }

        int[] dp_1 = new int[colLen];
        for (int k = 0; k < colLen; k++) {
            // 初始化第一行，dp[0][j]
//            dp[0][k] = k;
            dp_1[k] = k;
        }

        // 计算二维度数组的所有值，最后得到dp[i][j]
        for (int i = 1; i < rowLen; i++) {
            int tmp = dp_1[0];
            dp_1[0] = i;
            for (int j = 1; j < colLen; j++) {
                int pre = tmp;
                tmp = dp_1[j];
//                System.out.println("word1:"+word1.charAt(i-1)+"  "+"word2:"+word2.charAt(j-1));
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp_1[j] = pre;
                } else {
                    dp_1[j] = Math.min(dp_1[j - 1], Math.min(dp_1[j], pre)) + 1;
                }
//                System.out.println("j:"+j+" dp[i]:"+dp_1[j]);
            }
        }
//        System.out.println(Arrays.toString(dp_1));
        return dp_1[colLen - 1];
    }


    public static int zh_yh_minDistance(String word1, String word2) {

        int n1 = word1.length();
        int n2 = word2.length();
        int[] dp = new int[n2 + 1];
        // dp[0...n2]的初始值
        for (int j = 0; j <= n2; j++) dp[j] = j; // dp[j] = min(dp[j-1], pre, dp[j]) + 1
        for (int i = 1; i <= n1; i++) {
            int temp = dp[0]; // 相当于初始化
            dp[0] = i;
            for (int j = 1; j <= n2; j++) {
                // pre 相当于之前的 dp[i-1][j-1]
                int pre = temp;
                temp = dp[j]; // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(Math.min(dp[j - 1], pre), dp[j]) + 1;
                }
                // 保存要被抛弃的值 } }
            }
        }
        return dp[n2];
    }
    public static int shit_minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[] dp = new int[n2 + 1];
        // dp[0...n2]的初始值
        for (int j = 0; j <= n2; j++)
            dp[j] = j;
        // dp[j] = min(dp[j-1], pre, dp[j]) + 1
        for (int i = 1; i <= n1; i++) {
            int temp = dp[0];
            // 相当于初始化
            dp[0] = i;
            for (int j = 1; j <= n2; j++) {
                // pre 相当于之前的 dp[i-1][j-1]
                int pre = temp;
                temp = dp[j];
                // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[j] = pre;
                }else {
                    dp[j] = Math.min(Math.min(dp[j - 1], pre), dp[j]) + 1;
                }
                // 保存要被抛弃的值
            }
        }
        return dp[n2];
    }
     /*   public static int zh_minDistance(String word1, String word2){
            int n1 = word1.length();
            int n2 = word2.length();
            int[][] dp = new int[n1 + 1][n2 + 1];
            // dp[0][0...n2]的初始值
            for (int j = 1; j <= n2; j++)
                dp[0][j] = dp[0][j - 1] + 1;
            // dp[0...n1][0] 的初始值
            for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;
            // 通过公式推出 dp[n1][n2]
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    }
                }
            }
            return dp[n1][n2];
        }*/

        public static void main (String[]args){
            System.out.println(shit_minDistance("sea", "eat"));
            System.out.println(minDistance("sea", "eat"));
        }
}
