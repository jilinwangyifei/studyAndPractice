package MultiplexingClass;

/**
 * Created by wangbo on 2018/9/4.
 *
 *  继承与初始化
 *
 */

public class Bettle extends Insect{
    private int k = printInit("Bettle.k initialized");

    public Bettle (){
        System.out.println("k =" +k);
        System.out.println("j =" +j);
    }
    private static int x2 = printInit("Bettle.x2 initialized");

    public static void main(String[] args) {
        System.out.println("Bettle constructor");
        Bettle b = new Bettle();
    }
}

class Insect {
    private int i = 9;
    protected int j;

    Insect() {
        System.out.println("i = "+ i +", j = " + j);
        j = 39;
    }
    private static int x2 =  printInit("Insect.x1 initialized");

    static int printInit(String s) {
        System.out.println(s);
        return 47;
    }

}

/*
  输出
  Insect.x1 initialized
  Bettle.x2 initialized
  Bettle constructor
  i=9 j=0
  Bettle.k initialized
  k = 47
  j = 39

  首先 加载类 初始化基类static变量 之后初始化到处类static变量
  其次 创建导出类对象 自动调用基类的构造器 （也可以使用super）
 */