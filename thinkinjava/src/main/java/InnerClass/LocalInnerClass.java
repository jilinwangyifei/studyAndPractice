package InnerClass;

/**
 * Created by wangbo on 2018/10/22.
 */

interface  Counter {
    int next();
}

public class LocalInnerClass {

    private int count = 0;

    Counter getCounter(final String name) {
        class  LocalCounter implements Counter {
            public LocalCounter() {
                System.out.println("LocalCounter()");
            }
            @Override
            public int next() {
                System.out.print(name);
                return count ++;
            }
        }
        return new LocalCounter();
    }

    Counter getCounter2(final String name) {
        return new Counter() {
            {
                System.out.println("Counter()");
            }
            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        LocalInnerClass localInnerClass = new LocalInnerClass();
        Counter c1 = localInnerClass.getCounter("local inner "),
                c2 = localInnerClass.getCounter2("anonymous inner ");
        for (int i = 0; i < 5; i++) {
            System.out.println(c1.next());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(c2.next());
        }
    }
}
