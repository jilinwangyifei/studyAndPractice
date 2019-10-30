package Container;



import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangbo on 2018/10/30.
 * todo why not equals
 */
public class SetContains {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(70);
        Long a = 70L;
        System.out.println(set.contains(a));
    }
}
