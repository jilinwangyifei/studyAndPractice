package DesignPattern.Adapter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by wangbo on 2018/9/15.
 */
public class CollectionDataTest {

    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>(
                new CollectionData<String>(new Government(),4));
        set.addAll(CollectionData.list(new Government(),4));
        System.out.println(set);
    }
}

class Government implements Generator<String> {

    String ss[] = "this is a test".split(" ");

    private int index;
    @Override
    public String next() {
        return ss[index++];
    }
}
