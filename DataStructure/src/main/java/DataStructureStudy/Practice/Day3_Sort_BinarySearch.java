package DataStructureStudy.Practice;

public class Day3_Sort_BinarySearch {
    /**
     * 排序和二分查找
     *
     * 归并 最好最坏平均都是 O(nlogn)  空间复杂度 O(n^2)
     * 快排   O(nlogn) -->  O(n^2)   空间复杂度 O(n^2)
     *
     * 原地排序 特指空间复杂度O(1)的排序算法
     * 稳定的排序算法是指 如果待排序的序列中存在值相等的元素，经过排序之后，相等元素之间原有的先后顺序不变
     *
     *
     *  归并 快排 插入 冒泡 选择 二分
     *
     *  桶排序  计数排序  基数排序
     *  */

    //归并
    void mergeSort(int array[], int n) {
        mergeSort(array, 0, n);
    }

    void mergeSort(int array[], int begin, int end) {
        if (begin >= end) return;
        int middle = (begin + end) / 2;
        mergeSort(array, begin, middle);
        mergeSort(array, middle + 1, end);
        merge(array, begin, middle + 1, end);
    }

    void merge(int array[], int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        while (leftPos < leftEnd && rightPos < rightEnd) {
            if (array[leftPos] <= array[rightPos]) {
                array[tmpPos++] = array[leftPos++];
            } else {
                array[tmpPos++] = array[rightPos++];
            }
        }
        while (leftPos < rightEnd) {
            array[tmpPos++] = array[leftPos++];
        }

        while (rightPos < rightEnd) {
            array[tmpPos++] = array[rightPos++];
        }
    }

    //快排
    void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    void quickSort(int[] array, int begin, int end) {
        if (begin >= end) return;

        int q = partiton(array, begin, end);
        quickSort(array, begin, q);
        quickSort(array, q + 1, end);
    }

    int partiton(int[] array, int begin, int end) {
        int i = begin;
        int middlePoint = array[end];
        for (int j = i; j < end - 1; j++) {
            if (array[j] < middlePoint) {
                //swap arrray[i] array[j]
                swapRenfence(array, i, j);
                array[i] = array[j];
                i++;
            }
        }
        //swap arrray[i] array[end]
        swapRenfence(array, i, end);
        return i;
    }

    void swapRenfence(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    //插入
    void insertSort(int array[]) {
        if (array.length <= 1) return;
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (array[j] > value) {
                    array[j + 1] = value;
                } else {
                    break;
                }
            }
            array[j + 1] = value;
        }
    }

    //冒泡
    void bubbleSort(int array[], int n) {
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = i; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
                flag = true;
            }
            if (!flag) break;
        }
    }

    //选择
    void selectSort(int array[], int n) {
        for (int i = 0; i < n; i++) {
            int tmp = array[i];
            for (int j = i; j < n; j++) {
                tmp = array[j];
                if (tmp > array[j]) {
                    tmp = array[j];
                }
            }
            array[i] = tmp;
        }
    }

    /**
     * 在O(n)时间复杂度的情况下找到第k大的数
     */
    void findKth(int array[], int n, int k) {
        findKth(array, 0, n, k);
    }

    int findKth(int array[], int begin, int end, int k) {
        int partiton = partiton(array, begin, end);
        if (partiton == k) {
            return partiton;
        } else if (partiton < k) {
            findKth(array, partiton + 1, end, k);
        } else {
            findKth(array, begin, partiton - 1, k);
        }
        return -1;
    }


    /**
     * 二分查找
     */
    void binarySearch(int array[],int findX) {
        binarySearch(array, 0 , array.length-1, findX);
    }

    int binarySearch(int array[], int begin, int end, int findX) {

        while (begin <= end) {
            int binary = (begin + end)/2;
            if (array[binary] > findX) {
                end = binary - 1;
            } else if (array[binary] < findX) {
                begin = binary + 1;
            } else {
                return binary;
            }
        }
        return -1;
    }

    /**
     * 模糊二叉查找算法 大于等于给定值的第一个元素
     */
    void firstGreaterThanFindxSearch(int array[],int findX) {
        firstGreaterThanFindxSearch(array, 0 , array.length-1, findX);
    }

    int firstGreaterThanFindxSearch(int array[], int begin, int end, int findX) {

        while (begin <= end) {
            int binary = (begin + end)/2;
            if (array[binary] >= findX) {
                if (binary == 0 || array[binary-1] < findX) return binary;
                else end = binary - 1;
            } else {
                begin = binary + 1;
            }
        }
        return -1;
    }

    //计数排序
    void jishuSort(int [] array, int n) {

        int max = 0;
        for (int a : array){
            if (a > max) max = a;
        }

        //计数数组
        int [] c = new int[max];

        for (int i = 0; i < n; i ++) {
            c[array[i]] ++;
        }

        for (int i = 0; i < max; i ++) {
            c[i] = array[i-1] + c[i];
        }

        int r[] = new int[n];
        //计数排序关键
        for (int i = n-1; i >= 0; --n) {
            int index = c[array[i]] - 1;
            r[index] = array[i];
            c[array[i]] --;
        }

        for (int i = 0; i < n; i++) {
            array[i] = c[i];
        }

    }


}