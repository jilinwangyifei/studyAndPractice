package String;

/**
 * Created by wangbo on 2018/3/16.
 *
 * String 特点
 *1 不变性
 *2 针对常量池的优化
 *3 类的final定义
 * String.intern()始返回在常量池中的引用
 ****虚拟机专门用于存放字符串常量的常量池，在1.6之前属于永久区的一部分 1.7后被移到了堆中***
 *
 *1.6中 新生成的string并没有从value中获取自己需要的那部分。而是简单的使用了相同的value引用，
 * 只是修改了offset和count 当原始的字符串被回收时 value中多余的部分造成空间浪费
 *1.7中 不再复用原来的string的value 而是将实际用到的部分进行了复制
 *
 *intern方法:
 * 如果常量池中存在当前字符串，则直接返回当前字符串。如果常量池中没有该字符串，会将此字符串放入常量池中后再返回。
 *
 */
public class StringTest {
    public static void main(String[] args) {
       /* String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1 == s2.intern());
        System.out.println("abc" == s2.intern());
        System.out.println(s1.intern() == s2.intern());
        String s3 = new StringBuilder("ja").append("va").toString();
        System.out.println(s3.intern() == s3);*/


        /*
        String s1 = "";
        String s2 = "";
        System.out.println(s1==s2);

        String s3 = "123";
        String s4 = "123";
        System.out.println(s3==s4);

        String s5 = new String("123");
        String s6 = new String("123");
        System.out.println(s3==s5);
        System.out.println(s6==s5);

        byte a = 127;
        System.out.println(a+1);
        byte b = (byte)(a+1);
        System.out.println(b);*/

        /**
         * 以下两个例子整理自
         * https://tech.meituan.com/in_depth_understanding_string_intern.html
         * 深入解析String#intern
         */
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);

        /*String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);*/
    }
}
