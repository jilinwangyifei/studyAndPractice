package Basic;

public class CloneStudy implements Cloneable {

    //浅克隆 创建好的对象通过赋值拷贝对象 如果你的类型包含引用类型 那么指向相同的引用内容
    //      这样发生在可变的字段上将反映到他们引用的共同内容上 避免这种情况使用深度克隆

    //深克隆

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
