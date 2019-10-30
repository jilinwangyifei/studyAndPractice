package Generic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by wangbo on 2018/10/3.
 */
public class RandomList<T> {
    private  ArrayList<T> storage = new ArrayList<T>();
    private Random random = new Random(47);
    public void add(T t) {
        storage.add(t);
    }
    public T select() {
        return storage.get(random.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> randomList = new RandomList<>();
        for (String s : "a b c".split(" ")) {
           randomList.add(s);
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(randomList.select() + " ");
        }
    }
}
