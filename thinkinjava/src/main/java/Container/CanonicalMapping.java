package Container;

import java.util.WeakHashMap;

/**
 * Created by wangbo on 2018/9/22.
 */
public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 100;
        if (args.length > 0)
            size = new Integer(args[0]);
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> wh =
                new WeakHashMap<Key, Value>();
        for (int i = 0; i < size; i++) {
            Key key = new Key(Integer.toString(i));
            Value value = new Value(Integer.toString(i));
            if (i%3 == 0) {
                keys[i] = key;
            }
            wh.put(key, value);
        }
        System.gc();
        System.out.println(wh);
    }
}

class Element{
    private String ident;
    public Element(String id) {
        ident = id;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Element &&
            ident.equals(((Element)obj).ident);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize "+ident);
    }
}

class Key extends Element {
   public Key(String id) {
       super(id);
   }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}