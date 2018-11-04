package com.github.jerring.algorithms.chapter1_fundamentals;

// 带权路径压缩
//public class UnionFind {
//
//    private int[] id;   // 父结点数组
//    private int[] sz;   // 各个根结点所对应的分量大小
//    private int count;  // 连通分量的数量
//
//    /**
//     * 初始化每个结点为一棵树，即每个结点的父结点指向自身，树的结点个数为 1
//     * @param n
//     */
//    public UnionFind(int n) {
//        count = n;
//        id = new int[n];
//        sz = new int[n];
//        for (int i = 0; i < n; ++i) {
//            id[i] = i;
//            sz[i] = 1;
//        }
//    }
//
//    private int find(int x) {
//        while (x != id[x]) {
//            id[x] = id[id[x]];  // 路径压缩
//            x = id[x];
//        }
//        return x;
//    }
//
//    /**
//     * 将结点个数少的树拼接到结点个数多的树
//     * @param p
//     * @param q
//     */
//    public void union(int p, int q) {
//        int i = find(p);
//        int j = find(q);
//        if (i != j) {
//            if (sz[i] < sz[j]) {
//                id[i] = j;
//                sz[j] += sz[i];
//            } else {
//                id[j] = i;
//                sz[i] += sz[j];
//            }
//            --count;
//        }
//    }
//
//    /**
//     *
//     * @param p
//     * @param q
//     * @return p 与 q 是否连通
//     */
//    public boolean connected(int p, int q) {
//        return find(p) == find(q);
//    }
//
//    /**
//     *
//     * @return 连通分量的数量
//     */
//    public int count() {
//        return count;
//    }
//}

/**
 * 并查集，用于解决动态连通性问题
 */
public class UnionFind {

    private int[] f;
    private int count;

    /**
     * 初始化每个结点为一棵树，即每个结点的父结点指向自身
     * @param n
     */
    public UnionFind(int n) {
        count = n;
        f = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = i;
        }
    }

    /**
     * 经过一次 find 操作，该路径上所有结点的父结点变为根结点
     * @param x
     * @return 结点 x 所在树的根结点
     */
    private int find(int x) {
        if (x != f[x]) {
            f[x] = find(f[x]);  // 路径压缩到根结点
        }
        return f[x];
    }

    /**
     * 经过一次 union 操作，树的深度不超过 3（整个操作过程中树的深度不超过 3）
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i != j) {
            f[i] = j;
            --count;
        }
    }

    /**
     *
     * @param p
     * @param q
     * @return p 与 q 是否连通
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     *
     * @return 连通分量的数量
     */
    public int count() {
        return count;
    }
}
