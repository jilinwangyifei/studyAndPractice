package DataStructureStudy.Practice;

public class Day7_Greedy_Partition_Back_DynamicProgram {
    /**
     * 贪心 分治 回溯 动态规划  todo
     *
     * 分治
     *
     * 利用分治算法求一组数据的逆序对个数
     *
     * 回溯
     *
     * 利用回溯算法求解八皇后问题
     *
     * 利用回溯算法求解 0-1 背包问题
     *
     * 动态规划
     *
     * 0-1 背包问题
     *
     * 最小路径和（详细可看 @Smallfly 整理的 Minimum PathSum）
     *
     * 编程实现莱文斯cLIE坦最短编辑距离
     *
     * 编程实现查找两个字符串的最长公共子序列
     *
     * 编程实现一个数据序列的最长递增子序列
     *
     */

    //分治：利用分治算法求一个数组的逆序对个数
    private int num = 0;

    int count(int[] a, int n) {
        num = 0;
        mergerSortCounting(a, 0, n - 1);
        return num;
    }

    void mergerSortCounting(int [] a, int p, int r) {
        if (p >= r) return;
        int q = (p +r)/2;
        int k = 0;
        mergerSortCounting(a, p, q);
        mergerSortCounting(a, q+1, r);
        merge(a, p, q, r);
    }

    void merge (int[] a, int p, int q, int r) {
        int i = p;
        int j = q;
        int k = 0;
        int[] tmp = new int[r-p+1];
        while (i <= q && j <= r ){
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += q - i + 1;
                tmp[k++] = a[j++];
            }
        }

        while (i <= q) {
            tmp[k ++] = a[i ++];
        }

        while (j <= r) {
            tmp[k ++] = a[j ++];
        }

        for (int f = 0; f <= r-p; f ++) {
            a[p + f] = tmp[f];
        }
    }

    //回溯：利用回溯算法求解八皇后问题
    int[] result = new int[8];

    void cal8queens(int row) { //可以直接调用 cal8queens(0);
        if(row == 8) {
            printQueens(result);
            return;
        }
        for (int colume = 0; colume < 8; colume++) {
            if (isOk(row, colume)){
                result[row] = colume;
                cal8queens(row+1);
            }
        }
    }

    private boolean isOk(int row, int colume) {
        int leftUp = colume - 1;
        int rightUp = colume + 1;
        for (int i = row - 1; i > 0; i--) {
            if (result[i] == colume) return false;
            if (leftUp >= 0) {
                if (result[i] == leftUp) return false;
            }
            if (rightUp < 8) {
                if (result[i] == rightUp) return false;
            }
            --leftUp; ++ rightUp;
        }
        return true;
    }

    void printQueens(int[] result) {
        for (int row = 0; row < 8; row ++) {
            for (int colume = 0; colume < 8; colume ++){
                if (result[row] == colume) System.out.println("Q ");
                else System.out.println("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //回溯：利用回溯算法求解0-1背包问题
    void f (int i, int cw, int[] items, int n, int w) {
        int maxW = Integer.MAX_VALUE;
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {
            f(i + 1, cw+items[i], items, n, w);
        }
    }

    //动态规划：0-1 背包问题  简化版 利用一维数组解决二维数组占用空间问题
    int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1];
        states[0] = true;
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 0; i < n ; i++) {
            for (int j = w - items[i]; j >= 0; --j) {
                if (states[items[j]] == true) states[j+items[i]] = true;
            }
        }
        for (int i = w; i >= 0 ; --i) {
            if (states[i] == true) return i;
        }
        return 0;
    }

    //动态规划：最小路径和

    //动态规划：编程实现莱文斯坦最短编辑距离

    //动态规划：编程实现查找两个字符串的最长公共子序列

    //动态规划：编程实现一个数据序列的最长递增子序列
}
