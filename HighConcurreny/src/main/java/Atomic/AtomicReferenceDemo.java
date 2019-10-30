package Atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wangbo on 2018/3/8.
 */
public class AtomicReferenceDemo {

    static AtomicReference<Integer> money = new AtomicReference<Integer>();

    public static void main(String[] args) {
        money.set(19);

        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        while (true){
                            Integer m = money.get();
                            if(m < 20 ){
                                if(money.compareAndSet(m, m+20)){
                                    System.out.println("余额小于20元，充值成功。余额："+money.get() );
                                    break;
                                }
                            }else{
                                System.out.println("余额大于20元，无需充值");
                                break;
                            }
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
                        Integer m = money.get();
                        if(m > 10 ){
                            if(money.compareAndSet(m, m-10)){
                                System.out.println("成功消费10元，余额："+money.get() );
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
