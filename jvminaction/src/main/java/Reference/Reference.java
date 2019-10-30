package Reference;

/**
 * Created by wangbo on 2018/3/19.
 */
public class Reference {
    /**
     *  可达对象   通过根对象进行引用搜索 最终可以到达的对象
     *  不可达对象 通过根对象进行引用搜索 最终没有被引用到的对象、
     *
     *  可触及的 从根节点开始 可以到达这个对象
     *  可复活的 对象的所有引用都被释放 但对象可能在finalize中复活
     *  不可触及的 finalize函数调用 并且没有复活 那么进入不可触及状态
     *            不可触及不可复活 因为finalize只会调用一次
     *
     *
     *
     */
}
