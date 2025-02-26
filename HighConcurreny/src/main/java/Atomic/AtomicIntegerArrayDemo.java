package Atomic;

import sun.misc.BASE64Decoder;

import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by wangbo on 2018/3/8.
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    static class AddThread implements Runnable{

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                array.incrementAndGet(j%array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Thread[] threads = new Thread[10];

        for (int j = 0; j < 10 ; j++) {
            threads[j] = new Thread(new AddThread());
        }

        for (int j = 0; j < 10; j++) {
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }
        System.out.println(array);*/
        Double nowPrice = 2.08;
        Double vme = ((nowPrice-1.121) / 1.121)*60000 ;
        double vma = ((nowPrice-1.494) / 1.494)*50000 + (((nowPrice-1.935) / 1.935)*30000)/3*2 + (((nowPrice-2.431) / 2.431)*80000)/8*5;
        double vjie = ((nowPrice-1.295) / 1.295)*30000 + (((nowPrice-1.935) / 1.935)*30000)/3 + (((nowPrice-2.431) / 2.431)*80000)/8*3;
        double vh3w = ((nowPrice-1.935) / 1.935)*30000; //15500
        double vh = ((nowPrice-2.431) / 2.431)*80000; //32900  共48400股 平均成本2.272
        double vz = vme+vma+vjie;
        System.out.println(String.format("我:%.2f",vme));
        System.out.println(String.format("妈:%.2f",vma));
        System.out.println(String.format("姐:%.2f",vjie));
        System.out.println(String.format("后加3w:%.2f,收益率%.3f",vh3w,(nowPrice-1.935) / 1.935));
        System.out.println(String.format("后加8w:%.2f,收益率:%.3f",vh,(nowPrice-2.431) / 2.431));
        System.out.println(String.format("总和:%.2f",vz));
    }
}