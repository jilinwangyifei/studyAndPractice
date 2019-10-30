package Class;

import java.util.Random;

/**
 * Created by wangbo on 2018/8/26.
 */
public class ClassInitialization {

    public static Random random = new Random(47);

    public static void main(String[] args) throws ClassNotFoundException {
        Class initable = Initable.class;
        System.out.println("after creating Initable ref");

        System.out.println(Initable.staticFinal);

        System.out.println(Initable.staticFinal2);

        System.out.println(Initable2.staticNonFinal);

        Class initable3 = Class.forName("Class.Initable3");
        System.out.println("after creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);

    }
}

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.random.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}


class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}

/**
 * 输出
 * after creating Initable ref
 * 47
 * Initializing Initable
 * 258 //random值
 * Initializing Initable2
 * 147
 * Initializing Initable3
 * after creating Initable3 ref
 * 74
 *
 *
 * 总结
 * 1. static final 是编译期常量 比如 Initable.staticFinal 不需要对Initable初始化就可以读取
 *      但是有例外 比如Initializing Initable2 将强制初始化 因为他不是一个编译期常量
 * 2. static not final 对他的访问之前 必须进行加载 初始化
 */


