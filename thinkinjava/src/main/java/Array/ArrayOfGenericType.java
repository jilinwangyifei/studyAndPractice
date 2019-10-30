package Array;

/**
 * Created by wangbo on 2018/9/23.
 * 类或者方法的内部 擦除通常会使泛型变得不适用
 */
public class ArrayOfGenericType<T> {
    T[] array;

    public ArrayOfGenericType(int size) {
        //return new T[size];
        array = (T[])new Object[size];
    }

    /*public <U> U[] makeArray() {
        return new U[10];
    }*/
}
