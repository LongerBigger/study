package leetcode.dp;

/*
头	s1	1	1	0	0	1	1	0	0	1	1	0	0	1	1	0	0
        s2	0	1	1	0	0	1	1	0	0	1	1	0	0	1	1	0
        尾	s1	1	1	1	1	1	1	1	1	0	0	0	0	0	0	0	0
        s2	0	0	0	0	1	1	1	1	1	1	1	1	0	0	0	0
        "s1去头去尾
        s3去头去尾"	"两种：
        1.s1去头，s1去尾、s3去头去尾
        2.s2去头，s1去尾、s3去头去尾"	"s2去头
        s1去尾
        s3去头去尾"	s1、s3去尾	"两种：
        1.s1去头，s1去尾、s3去头去尾
        2.s1去头，s2去尾、s3去头去尾"	"四种：
        1.s1去头，s1去尾、s3去头去尾
        2.s1去头，s2去尾、s3去头去尾
        3.s2去头，s2去尾、s3去头去尾
        4.s2去头，s1去尾、s3去头去尾"	"两种：
        1.s2去头，s1去尾、s3去头去尾
        2.s2去头，s2去尾、s3去头去尾"	"两种：
        1.s1去尾、s3去尾
        2.s2去尾、s3去尾"	"s1去头
        s2去尾
        s3去头去尾"	"两种：
        1.s1去头，s2去尾、s3去头去尾
        2.s2去头，s2去尾、s3去头去尾"	"s2去头去尾
        s3去头去尾"	s2、s3去尾	s1、s3去头	"两种：
        1.s1去头、s3去头
        2.s2去头、s3去头"	s2、s3去头	return false
*/

public class 交错字符串97 {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // 思路：
        // 1.如果s3为s1、s3交错字符串，那么s3开头和结尾的字符一定是s1、s2开头和结尾字符中的某一个
        // 2.由1可以确定递归的方向，比对s1、s2的开头和结尾，从而缩短字符串，缩短后的字符串有同样的性质，直到字符串缩为空时结束
        // 3.由2可知，当前问题变为一个子问题，求所有子问题的解即可得原问题的解


        return f(s1, s2, s3);
    }

    private boolean f(String s1, String s2, String s3) {
        if ("".equals(s1) && "".equals(s2) && "".equals(s3)) {
            return true;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean head13 = s1.length() == 0 || s1.charAt(0) == s3.charAt(0);
        boolean tail13 = s1.length() == 0 || s1.charAt(s1.length() - 1) == s3.charAt(s3.length() - 1);
        boolean head23 = s2.length() == 0 || s2.charAt(0) == s3.charAt(0);
        boolean tail23 = s2.length() == 0 || s2.charAt(s2.length() - 1) == s3.charAt(s3.length() - 1);
        // 16种情况，s1、s2的头尾和s3的头尾是否相等决定递归的方向

        if (head13 && !head23 && tail13 && !tail23) {
            // 1010 s1去头去尾 s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3)));
        } else if (head13 && head23 && tail13 && !tail23) {
            // 1110
//            1.s1去头，s1去尾、s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3))) ||
//            2.s2去头，s1去尾、s3去头去尾
                    f(cutTail(s1), cutHead(s2), cutHead(cutTail(s3)));
        } else if (!head13 && head23 && tail13 && !tail23) {
            // 0110
//            3.s1去尾、s3去尾
            return f(cutTail(s1), s2, cutTail(s3));
        } else if (!head13 && !head23 && tail13 && !tail23) {
            // 0010
//           4.
//           1.s1去尾、s3去尾
            return f(cutTail(s1), s2, cutTail(s3));
        } else if (head13 && !head23 && tail13 && tail23) {
            // 1011
//           5.
//           1.s1去头，s1去尾、s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3))) ||
//            2.s1去头，s2去尾、s3去头去尾
                    f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3)));
        } else if (head13 && head23 && tail13 && tail23) {
            // 1111
//           6.四种：
//           1.s1去头，s1去尾、s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3))) ||
//           2.s1去头，s2去尾、s3去头去尾
                    f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3))) ||
//           3.s2去头，s2去尾、s3去头去尾
                    f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3))) ||
//           4.s2去头，s1去尾、s3去头去尾
                    f(cutTail(s1), cutHead(s2), cutHead(cutTail(s3)));
        } else if (!head13 && head23 && tail13 && tail23) {
            // 0111
//           7.两种：
//           1.s2去头，s1去尾、s3去头去尾
            return f(cutTail(s1), cutHead(s2), cutHead(cutTail(s3))) ||
//           2.s2去头，s2去尾、s3去头去尾
                    f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3)));
        } else if (!head13 && !head23 && tail13 && tail23) {
            // 0011
//           8.两种：
//           1.s1去尾、s3去尾
            return f(cutTail(s1), s2, cutTail(s3)) ||
//           2.s2去尾、s3去尾
                    f(s1, cutTail(s2), cutTail(s3));
        } else if (head13 && !head23 && !tail13 && tail23) {
            // 1001
//           9.
//           1.s1去头
//             s2去尾
//             s3去头去尾
            return f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3)));
        } else if (head13 && head23 && !tail13 && tail23) {
            // 1101
//           10.两种：
//           1.s1去头，s2去尾、s3去头去尾
            return f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3))) ||
//           2.s2去头，s2去尾、s3去头去尾
                    f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3)));
        } else if (!head13 && head23 && !tail13 && tail23) {
            // 0101
//           11.
//            s2去头去尾
//            s3去头去尾
            return f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3)));
        } else if (!head13 && !head23 && !tail13 && tail23) {
            // 0001
//           12.
//           1.s2、s3去尾
            return f(s1, cutTail(s2), cutTail(s3));
        } else if (head13 && !head23 && !tail13 && !tail23) {
            // 1000
//           13.
//           1.s1、s3去头
            return f(cutHead(s1), s2, cutHead(s3));
        } else if (head13 && head23 && !tail13 && !tail23) {
            // 1100
//           14.两种：
//           1.s1去头、s3去头
            return f(cutHead(s1), s2, cutHead(s3)) ||
//           2.s2去头、s3去头
                    f(s1, cutHead(s2), cutHead(s3));
        } else if (!head13 && head23 && !tail13 && !tail23) {
            // 0100
//           15.
//           1.s2、s3去头
            return f(s1, cutHead(s2), cutHead(s3));
        } else if (!head13 && !head23 && !tail13 && !tail23) {
            return false;
        }

        return false;
    }

    public static String cutHead(String s) {
        if (s.length() == 0) {
            return "";
        }
        return s.substring(1);
    }

    public static String cutTail(String s) {
        if (s.length() == 0) {
            return "";
        }
        return s.substring(0, s.length() - 1);
    }


    private boolean fRememberSearch(String s1, String s2, String s3) {
        if ("".equals(s1) && "".equals(s2) && "".equals(s3)) {
            return true;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean head13 = s1.length() == 0 || s1.charAt(0) == s3.charAt(0);
        boolean tail13 = s1.length() == 0 || s1.charAt(s1.length() - 1) == s3.charAt(s3.length() - 1);
        boolean head23 = s2.length() == 0 || s2.charAt(0) == s3.charAt(0);
        boolean tail23 = s2.length() == 0 || s2.charAt(s2.length() - 1) == s3.charAt(s3.length() - 1);
        // 16种情况，s1、s2的头尾和s3的头尾是否相等决定递归的方向

        if (head13 && !head23 && tail13 && !tail23) {
            // 1010 s1去头去尾 s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3)));
        } else if (head13 && head23 && tail13 && !tail23) {
            // 1110
//            1.s1去头，s1去尾、s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3))) ||
//            2.s2去头，s1去尾、s3去头去尾
                    f(cutTail(s1), cutHead(s2), cutHead(cutTail(s3)));
        } else if (!head13 && head23 && tail13 && !tail23) {
            // 0110
//            3.s1去尾、s3去尾
            return f(cutTail(s1), s2, cutTail(s3));
        } else if (!head13 && !head23 && tail13 && !tail23) {
            // 0010
//           4.
//           1.s1去尾、s3去尾
            return f(cutTail(s1), s2, cutTail(s3));
        } else if (head13 && !head23 && tail13 && tail23) {
            // 1011
//           5.
//           1.s1去头，s1去尾、s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3))) ||
//            2.s1去头，s2去尾、s3去头去尾
                    f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3)));
        } else if (head13 && head23 && tail13 && tail23) {
            // 1111
//           6.四种：
//           1.s1去头，s1去尾、s3去头去尾
            return f(cutHead(cutTail(s1)), s2, cutHead(cutTail(s3))) ||
//           2.s1去头，s2去尾、s3去头去尾
                    f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3))) ||
//           3.s2去头，s2去尾、s3去头去尾
                    f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3))) ||
//           4.s2去头，s1去尾、s3去头去尾
                    f(cutTail(s1), cutHead(s2), cutHead(cutTail(s3)));
        } else if (!head13 && head23 && tail13 && tail23) {
            // 0111
//           7.两种：
//           1.s2去头，s1去尾、s3去头去尾
            return f(cutTail(s1), cutHead(s2), cutHead(cutTail(s3))) ||
//           2.s2去头，s2去尾、s3去头去尾
                    f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3)));
        } else if (!head13 && !head23 && tail13 && tail23) {
            // 0011
//           8.两种：
//           1.s1去尾、s3去尾
            return f(cutTail(s1), s2, cutTail(s3)) ||
//           2.s2去尾、s3去尾
                    f(s1, cutTail(s2), cutTail(s3));
        } else if (head13 && !head23 && !tail13 && tail23) {
            // 1001
//           9.
//           1.s1去头
//             s2去尾
//             s3去头去尾
            return f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3)));
        } else if (head13 && head23 && !tail13 && tail23) {
            // 1101
//           10.两种：
//           1.s1去头，s2去尾、s3去头去尾
            return f(cutHead(s1), cutTail(s2), cutHead(cutTail(s3))) ||
//           2.s2去头，s2去尾、s3去头去尾
                    f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3)));
        } else if (!head13 && head23 && !tail13 && tail23) {
            // 0101
//           11.
//            s2去头去尾
//            s3去头去尾
            return f(s1, cutHead(cutTail(s2)), cutHead(cutTail(s3)));
        } else if (!head13 && !head23 && !tail13 && tail23) {
            // 0001
//           12.
//           1.s2、s3去尾
            return f(s1, cutTail(s2), cutTail(s3));
        } else if (head13 && !head23 && !tail13 && !tail23) {
            // 1000
//           13.
//           1.s1、s3去头
            return f(cutHead(s1), s2, cutHead(s3));
        } else if (head13 && head23 && !tail13 && !tail23) {
            // 1100
//           14.两种：
//           1.s1去头、s3去头
            return f(cutHead(s1), s2, cutHead(s3)) ||
//           2.s2去头、s3去头
                    f(s1, cutHead(s2), cutHead(s3));
        } else if (!head13 && head23 && !tail13 && !tail23) {
            // 0100
//           15.
//           1.s2、s3去头
            return f(s1, cutHead(s2), cutHead(s3));
        } else if (!head13 && !head23 && !tail13 && !tail23) {
            return false;
        }

        return false;
    }

    public boolean isInterleaveNormal(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int i = 0, j = 0, k = 0;
        while (k < s3.length()) {
            // 思路，从头开始对比看谁的相同子串最长
            int m = 0;
            int n = 0;
            int h = 0;
            int l = 0;
            while (i + m < s1.length() && s1.charAt(i + m) == s3.charAt(k + h)) {
//                System.out.println("s1:" + s1.charAt(i + m) + " s3:" + s3.charAt(k + h) + " m:" + m);
                m++;
                h++;
            }
            while (j + n < s2.length() && s2.charAt(j + n) == s3.charAt(k + l)) {
//                System.out.println("s2:" + s2.charAt(j + n) + " s3:" + s3.charAt(k + l) + " n:" + n);
                n++;
                l++;
            }
//            System.out.println("m:" + m + " n:" + n + " h:" + h);
            if (m == 0 && n == 0) {
                break;
            }
            if (m >= n) {
                i += m;
                k += h;
            } else {
                j += n;
                k += l;
            }
//            System.out.println("i:" + i + " j:" + j + " k:" + k);
        }
        int ii = s1.length() - 1, jj = s2.length() - 1, kk = s3.length() - 1;
        while (k >= 0) {
            // 思路，从头开始对比看谁的相同子串最长
            int m = 0;
            int n = 0;
            int h = 0;
            int l = 0;
            while (ii - m >= 0 && s1.charAt(ii - m) == s3.charAt(kk - h)) {
//                System.out.println("s1:" + s1.charAt(i + m) + " s3:" + s3.charAt(k + h) + " m:" + m);
                m++;
                h++;
            }
            while (jj - n >= 0 && s2.charAt(jj - n) == s3.charAt(kk - l)) {
                System.out.println("s2:" + s2.charAt(jj - n) + " s3:" + s3.charAt(kk - l) + " n:" + n);
                n++;
                l++;
            }
//            System.out.println("m:" + m + " n:" + n + " h:" + h);
            if (m == 0 && n == 0) {
                break;
            }
            if (m >= n) {
                ii -= m;
                kk -= h;
            } else {
                jj -= n;
                kk -= l;
            }
//            System.out.println("i:" + i + " j:" + j + " k:" + k);
        }
        return kk < 0 || k == s3.length();
    }

    public boolean isInterleaveDp(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // 思路：
        // 定义dp[i][j]表示s1、s2的前i、j个字符能否交错成s3的前i+j个字符
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 看递推条件，s1[i]==s3[i]、s2[j]==s3[j]
        // 找递推关系 dp[i][j] = dp[i-1][j],s1[i-1]==s3[i+j-1]
        //          dp[i][j] = dp[i][j-1],s2[j-1]==s3[i+j-1]
        //          dp[i][j] = false,s1[i-1]==s3[i+j-1] && s2[j-1]!=s3[i+j-1]
        // 注意这里i、j表示长度，则对应字符下标为i-1、j-1
        // 一定要注意多个条件之间的关系

        // 初始化:
        // 空串可以交错成空串
        dp[0][0] = true;
        // 第一行
        for (int j = 1; j <= n; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = dp[0][j - 1];
            }
        }
        // 第一列
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = dp[i - 1][0];
            }
        }

        // 求dp[m][n]
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    // 两个条件关系是或
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public boolean isInterleaveDpGdsz(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // 思路：
        // 定义dp[i][j]表示s1、s2的前i、j个字符能否交错成s3的前i+j个字符
        int m = s1.length();
        int n = s2.length();
        // 看递推条件，s1[i]==s3[i]、s2[j]==s3[j]
        // 找递推关系 dp[i][j] = dp[i-1][j],s1[i-1]==s3[i+j-1]
        //          dp[i][j] = dp[i][j-1],s2[j-1]==s3[i+j-1]
        //          dp[i][j] = false,s1[i-1]==s3[i+j-1] && s2[j-1]!=s3[i+j-1]
        // 注意这里i、j表示长度，则对应字符下标为i-1、j-1
        // 一定要注意多个条件之间的关系

        // 2.滚动数组降维
        // 由递推式可知，当前状态只和前面一行的状态、以及左边的一个状态有关，
        // 因此定义一行即可
        boolean[] dp = new boolean[n + 1];

        // 初始化:
        // 空串可以交错成空串
        dp[0] = true;
        // 第一行
        for (int j = 1; j <= n; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[j] = dp[j - 1];
            }
        }
        // 求dp[m][n]
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i - 1) != s3.charAt(i - 1)) {
                dp[0] = false;
            }
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) != s3.charAt(i + j - 1)) {
                    dp[j] = false;
                }

                if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    // 两个条件关系是或
                    dp[j] |= dp[j - 1];
                }

            }

        }


        return dp[n];
    }

    public static void main(String[] args) {
//        String s1 = "aabcc";
//        String s2 = "dbbca";
//        String s3 = "aadbbcbcac";
        String s1 = "ab";
        String s2 = "bc";
        String s3 = "bbac";
//        String s1 = "ab";
//        String s2 = "aa";
//        String s3 = "aaba";
//        String s1 = "aacaac";
//        String s2 = "aacaaeaac";
//        String s3 = "aacaaeaaeaacaac";
        long a = System.currentTimeMillis();
        System.out.println(a);
//        String s1 = "abbbbbbcabbacaacccababaabcccabcacbcaabbbacccaaaaaababbbacbb";
//        String s2 = "ccaacabbacaccacababbbbabbcacccacccccaabaababacbbacabbbbabc";
//        String s3 = "cacbabbacbbbabcbaacbbaccacaacaacccabababbbababcccbabcabbaccabcccacccaabbcbcaccccaaaaabaaaaababbbbacbbabacbbacabbbbabc";
        System.out.println(new 交错字符串97().isInterleaveDpGdsz(s1, s2, s3));
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() - a);
//        String ss = "lA";
//        System.out.println(cutHead(cutTail(cutHead(ss))));
//        System.out.println(cutTail(ss));
    }
}
