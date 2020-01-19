package DataStructureStudy.Practice;

import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;

public class Day7_Greedy_Partition_Back_DynamicProgram {
    /**
     * 贪心 分治 回溯 动态规划
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

    void mergerSortCounting(int[] a, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        int k = 0;
        mergerSortCounting(a, p, q);
        mergerSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q;
        int k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += q - i + 1;
                tmp[k++] = a[j++];
            }
        }

        while (i <= q) {
            tmp[k++] = a[i++];
        }

        while (j <= r) {
            tmp[k++] = a[j++];
        }

        for (int f = 0; f <= r - p; f++) {
            a[p + f] = tmp[f];
        }
    }

    //回溯：利用回溯算法求解八皇后问题
    int[] result = new int[8];

    void cal8queens(int row) { //可以直接调用 cal8queens(0);
        if (row == 8) {
            printQueens(result);
            return;
        }
        for (int colume = 0; colume < 8; colume++) {
            if (isOk(row, colume)) {
                result[row] = colume;
                cal8queens(row + 1);
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
            --leftUp;
            ++rightUp;
        }
        return true;
    }

    void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int colume = 0; colume < 8; colume++) {
                if (result[row] == colume) System.out.println("Q ");
                else System.out.println("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //回溯：利用回溯算法求解0-1背包问题
    void f(int i, int cw, int[] items, int n, int w) {
        int maxW = Integer.MAX_VALUE;
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);  //不放第i个物品
        if (cw + items[i] <= w) {      //放第i个物品
            f(i + 1, cw + items[i], items, n, w);
        }
    }

    //动态规划：0-1 背包问题  简化版 利用一维数组解决二维数组占用空间问题
    int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = w - items[i]; j >= 0; --j) {
                if (states[items[j]] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) {
            if (states[i] == true) return i;
        }
        return 0;
    }

    //动态规划：最小路径和
    //1 状态转移表法
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                states[i][j] = matrix[i][j] + Math.min(states[i - 1][j], states[i][j - 1]);
            }
        }

        return states[n - 1][n - 1];
    }

    //2 状态转移方程
    private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private int n = 4;
    private int[][] mem = new int[4][4];

    public int minDist(int i, int j) { // 调用minDist(n-1, n-1);
        if (i == 0 & j == 0) return matrix[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minDist(i - 1, j);
        }
        int minUp = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minDist(i, j - 1);
        }
        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    //动态规划：编程实现莱文斯坦最短编辑距离
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) minDist[0][j] = j;
            if (j != 0) minDist[0][j] = minDist[0][j - 1] + 1;
            else minDist[0][j] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) minDist[i][0] = i;
            if (i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
            else minDist[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    matrix[i][j] = min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1, matrix[i][j]);
                } else {
                    matrix[i][j] = min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1, matrix[i][j] + 1);
                }
            }
        }
        return minDist[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }

    //动态规划：编程实现查找两个字符串的最长公共子序列
    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) maxlcs[0][j] = 1;
            if (j != 0) maxlcs[0][j] = maxlcs[0][j - 1];
            else maxlcs[0][j] = 0;
        }

        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) maxlcs[i][0] = 1;
            if (i != 0) maxlcs[i][0] = maxlcs[i - 1][0];
            else maxlcs[i][0] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    matrix[i][j] = max(matrix[i - 1][j], matrix[i][j - 1], matrix[i][j] + 1);
                } else {
                    matrix[i][j] = max(matrix[i - 1][j], matrix[i][j - 1], matrix[i][j]);
                }
            }
        }
        return maxlcs[n - 1][m - 1];
    }

    private int max(int x, int y, int z) {
        int maxv = Integer.MIN_VALUE;
        if (x > maxv) maxv = x;
        if (y > maxv) maxv = y;
        if (z > maxv) maxv = z;
        return maxv;
    }

    //动态规划：编程实现一个数据序列的最长递增子序列
    public int zczxl(char[] a) {
        int d[] = new  int[a.length];
        for (int i = 0; i < a.length ; i++) {
            d[i] = 1;
        }
        for (int i = 0; i < a.length ; i++) {
            for (int j = 0; j < i ; j++) {
                if (a[j] < a[i] && d[j] >= d[i]) {
                    d[i] = d[j]+1;
                }
            }
        }

        int max = 0;
        for (int i : d) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}