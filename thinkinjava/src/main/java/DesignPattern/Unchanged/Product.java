package DesignPattern.Unchanged;

/**
 * Created by wangbo on 2018/3/4.
 *
 * 不变模式
 * 注意四点
 * 1 去除setter方法以及所有修改自身属性的方法
 * 2 将所有属性设置为私有，并用final标记，确保其不可修改
 * 3 确保没有子类可以重载修改它的行为
 * 有个可以创建完整对象的构造函数
 */
public final class Product { //确保无子类

    private final String no;
    private final String name;
    private final String price;

    public Product( String no,String name,String price){
        //在创建对象时 必须制定数据 因为创建之后无法进行修改
        this.name = name;
        this.no = no;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
