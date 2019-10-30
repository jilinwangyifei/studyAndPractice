package DesignPattern.Adapter;

import java.util.*;

/**
 * Created by wangbo on 2018/9/15.
 *
 * 适配器模式
 * 用于解决接口不兼容问题的有效方法
 */
public class CollectionData<T> extends ArrayList<T> {

    public CollectionData(Generator<T> gen, int quantity){
        for (int i = 0; i < quantity; i++) {
            add(gen.next());
        }
    }

    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
}
