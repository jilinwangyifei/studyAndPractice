package MultiplexingClass;

/**
 * Created by wangbo on 2018/9/4.
 *
 * 必须在域的定义处或者每个构造器中用表达式对final进行赋值
 * 这是final域在使用前总是被初始化的原因
 */
public class BlankFinal {
    private final int i = 0;
    private final int j;
    private final Poppet poppet;

    BlankFinal(){
        j = i;
        poppet = new Poppet(1);
    }

    public static void main(String[] args) {
        new BlankFinal();
    }
}

class Poppet{
    private int i;
    Poppet(int i) {
        this.i = i;
    }
}