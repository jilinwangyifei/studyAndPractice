package Java8.Chapter9;


public class Test9_2 {

//抽象类和抽象接口的区别
//1.一个类只能继承一个抽象类 但是一个类能实现多个接口
//2.抽象类可以保存可以通过实例变量保存一个通用状态 而接口不能有实例变量
}

interface Sized{
    int size();

    default boolean isEmpty(){
        return size() == 0;
    }
}