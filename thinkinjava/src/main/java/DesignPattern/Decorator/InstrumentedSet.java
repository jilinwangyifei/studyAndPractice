package DesignPattern.Decorator;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangbo on 2018/9/1.
 */
public class InstrumentedSet<E> extends ForwardingSet {

    private static int count;

    public InstrumentedSet (Set<E> set) {
       super(set);
    }

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        count += c.size();
        return super.addAll(c);
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        InstrumentedSet instrumentedSet =
                new InstrumentedSet(new HashSet<String>());
        instrumentedSet.addAll(Arrays.asList("one","two","three"));
        System.out.println(instrumentedSet.getCount());
    }
}
