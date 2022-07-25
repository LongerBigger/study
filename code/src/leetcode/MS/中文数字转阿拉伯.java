package leetcode.MS;

import java.math.BigInteger;
import java.util.*;

public class 中文数字转阿拉伯 {

    static class Pair {
        Character c;
        Integer v;

        Pair(Character c, Integer v) {
            this.c = c;
            this.v = v;
        }
    }


    public static BigInteger trans(String s) {
        // 0.处理非法输入
        // 1.定义单位以及单位对应的权重数字int值
        // 2.定义中文数字零~九，及数字对应的int值
        // 3.分词，将字符串分割成单个的数字和单位组合，
        // 4.计算，计算每个单位下的数值，再求和，用BigInteger表示，以免溢出。

        List<Pair> dw = new ArrayList<>(Arrays.asList(new Pair('个', 1),
                new Pair('十', 1),
                new Pair('个', 1),
                new Pair('个', 1),
                new Pair('个', 1),
                new Pair('个', 1)));


//    Set<String> dwMap = new HashSet<>();
//        dwMap.addAll(Arrays.asList(dw));
//
//    Character[] sz = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};
//    Set<String> szMap = new HashSet<>();
//        szMap.addAll(Arrays.asList(sz));
return null;

}


    public static void main(String[] args) {

    }
}
