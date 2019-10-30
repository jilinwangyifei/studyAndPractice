package Basic;

public class StaticStudy {

    public static int X = 100;

    public final static int Y = 200;

    public StaticStudy() {
        System.out.println("Test构造函数执行");
    }
    static {
        System.out.println("static语句块执行");
    }

    public static void display() {
        System.out.println("静态方法被执行");
    }

    public void display_1() {
        System.out.println("实例方法被执行");
    }

    public static void main(String[] args) throws Exception{
        //1.当访问类的静态常量时，如果编译器可以计算出常量的值，会加载类
        //System.out.println(StaticStudy.Y);
        //2.第二个参数是否初始化的含义
        //Class.forName("Basic.StaticStudy",false,StaticStudy.class.getClassLoader());
    }

}
