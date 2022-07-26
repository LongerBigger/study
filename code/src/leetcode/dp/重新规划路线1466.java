package leetcode.dp;

public class 重新规划路线1466 {
    public int minReorder(int n, int[][] connections) {
        // 思路：
        // 1.以0作为根多条路线形成一颗多叉树
        // 2.因为只有n-1条线路，因此不会形成环，当前节点的最少次数等于以当前节点为根形成多叉树，各个树叉的最少变更次数加起来
        // 3.在一条树叉上，
        //   a.若当前节点指向前一个节点，则当前节点的最少变更次数等于前一个节点的最少变更次数
        //   b.若当前节点没有指向了前一个节点，则当前节点的最少变更次数等于前一个节点的最少变更次数+1
        // 4.算出所有树

        // 用BFS从节点0 开始遍历整个树，逆向思维，遍历方向正向权重为1，反向为0
        // 这颗树用邻接矩阵表示，注意计算权重即可
        return 0;
    }

    public static void main(String[] args) {

    }
}
