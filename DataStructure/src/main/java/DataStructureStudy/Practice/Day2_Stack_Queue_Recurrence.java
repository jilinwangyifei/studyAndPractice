package DataStructureStudy.Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Day2_Stack_Queue_Recurrence {

    /**
     * 栈、队列和递归
     *
     * 用数组实现一个顺序栈
     *
     * 用链表实现一个链式栈  todo
     *
     * 编程模拟实现一个浏览器的前进、后退功能  todo
     *
     *
     * 用数组实现一个顺序队列
     *
     * 用链表实现一个链式队列 todo
     *
     * 实现一个循环队列  todo
     *
     *
     * 编程实现斐波那契数列求值 f(n)=f(n-1)+f(n-2)
     *
     * 编程实现求阶乘 n!
     *
     * 编程实现一组数据集合的全排列
     *
     * */

    //用数组实现一个顺序栈
    class arrayStack{
        Object [] array;
        int pos = 0;
        int length;
        arrayStack(int length){
            array = new Object[length];
            this.length = length;
        }

        void insert(Object obj){
            if (pos > length) {
                resize();
            }
            array[pos++] = obj;
        }

        Object get(){
            return array[pos];
        }

        void resize(){
            Object [] newArray = new Object[length*2];
            System.arraycopy(array,0,newArray,0,length);
            array = newArray;
        }

    }

    //用数组实现一个顺序队列
    class ArrayQueue{
        Object [] array;
        int length;
        int putInx = 0;
        int takeInx = 0;
        int count;
        ReentrantLock lock = new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();

        ArrayQueue(int length){
            array = new Object[length];
            this.length = length;
        }

        void put(Object obj) throws InterruptedException{
            lock.lock();
            try{
                if (putInx+1 == length) {
                    notFull.await();
                }
                //增加
                array[putInx] = obj;
                if (++putInx == length) {
                    putInx = 0;
                }
                count++;
                //唤醒空节点
                notEmpty.signal();
            } finally {
                lock.unlock();
            }

        }

        Object get() throws InterruptedException{
            lock.lockInterruptibly();
            try{
                if (count == 0) {
                    notEmpty.await();
                }
                if (++takeInx == length) {
                    putInx = 0;
                }
                count --;
                notFull.signal();
                return array[putInx];

            } finally {
                lock.unlock();
            }
        }

    }

    //斐波那契数列
    int feibonaqie(int n) {
        if ( n < 2) {
            return n;
        }
        return feibonaqie(n-1) + feibonaqie(n-2);
    }

    //编程实现求阶乘
   static int jiecheng(int n) {
        if (n > 0) {
            return n*jiecheng(n-1);
        } else {
            return 1;
        }
   }

   //实现一组数据集合的全排列
    public List<List<Integer>> fullPermutation(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        permute(list, 0, nums);
        return list;
    }

    void permute(List<List<Integer>> list, int start, int[] nums) {
        for (int i = start; i < nums.length; i++) {
            swapRenfence(nums, i, start);
            permute(list, start + 1, nums);
            swapRenfence(nums, start, i);
        }
        if(start == (nums.length-1)) {
            tranferList(list, nums);
        }
    }

    void swapRenfence(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    void tranferList(List<List<Integer>> list, int []nums) {
        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < nums.length ; i++) {
            l1.add(nums[i]);
        }
        list.add(l1);

    }

}
