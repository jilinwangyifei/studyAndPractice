package DataStructureStudy.JianzhiOffer;

import java.util.Arrays;

public class Sloution {

    //题库地址 https://github.com/doocs/coding-interview/blob/master/docs/coding-interview.md


    /**
     * 斐波那契数列 1 1 2 3 5 8 13
     * @param target
     * @return
     */
    int solution10_1 (int target){
        if (target < 3) {
            return 1;
        }
        return solution10_1(target-1) + solution10_1(target-2) ;
    }

    /**
     * 青蛙 跳台阶  一只青蛙可以跳1级 可以跳2级 求n级的台阶有多少种跳法
     * @param target
     * @return
     */
    int solution10_2 (int target){
        if (target < 3) {
            return target;
        }
        int a = 1;
        int b = 2;
        for (int i = 3; i < target; ++i ) {
            b = a + b;
            a = b - a;
        }
        return b;
    }

    /**
     * 青蛙 变态跳台阶  一只青蛙可以跳1级 可以跳2级 也可以跳n级 求n级的台阶有多少种跳法
     * @param target
     * @return
     */
    int solution10_3 (int target){
        if (target < 3) {
            return target;
        }
        int res[] = new int[target+1];
        Arrays.fill(res,1);
        for (int i = 2; i < target; ++i ) {
            for (int j = 1; j < i; ++j) {
                res[i] += res[j];
            }
        }

        return res[target];
    }

    /**
     * 矩形覆盖 2*1的矩形覆盖2*n的矩形 有多少种方法
     * @param target
     * @return
     */
    int solution10_4 (int target){
        if (target < 3) {
            return target;
        }
        int res[] = new int[target+1];
        res[1] = 1;
        res[2] = 2;

        for (int i = 3; i <= target; ++i ) {
            res[i] = res[i-1] + res[i-2];
        }

        return res[target];
    }


    /**
     * 旋转数组中的最小数字
     * @param array
     * @return
     */
    int solution11 (int array[]){

        return 0;
    }

}
