package Atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by wangbo on 2018/3/8.
 *
 * ABA问题
 *
 * 逻辑漏洞：如果一个变量V初次读取的时候是A值，并且在准备赋值的时候检查到它仍然是A值，
 * 那我们就能说明它的值没有被其他线程修改过了吗？如果在这段期间它的值曾经被改成了B，然后又改回A，
 * 那CAS操作就会误认为它从来没有被修改过。这个漏洞称为CAS操作的”ABA”问题。
 * java.util.concurrent包为了解决这个问题，提供了一个带有标记的原子引用类”AtomicStampedReference”，
 * 它可以通过控制变量值的版本来保证CAS的正确性。不过目前来说这个类比较”鸡肋”，大部分情况下ABA问题并不会影响程序并发的正确性，
 * 如果需要解决ABA问题，使用传统的互斥同步可能回避原子类更加高效。
 *
 */
public class AtomicStampedRefenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19,0);

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            final int timeStamp = money.getStamp();
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        Integer m = money.getReference();
                        if(m < 20 ){
                            if(money.compareAndSet(m, m+20,timeStamp,timeStamp+1)){
                                System.out.println("余额小于20元，充值成功。余额："+money.getReference());
                                break;
                            }
                        }else{
                            System.out.println("余额大于20元，无需充值");
                            break;
                        }
                    }
                }
            }.start();
        }

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 3 ; i++) {
                    while (true){
                        Integer m = money.getReference();
                        int timeStamp = money.getStamp();
                        if(m > 10 ){
                            if(money.compareAndSet(m, m-10,timeStamp,timeStamp+1)){
                                System.out.println("成功消费10元，余额："+money.getReference());
                                break;
                            }
                        }else{
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                }
            }
        }.start();
    }

}
