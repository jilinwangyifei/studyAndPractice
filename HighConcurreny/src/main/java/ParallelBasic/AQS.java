package ParallelBasic;

/**
 * Created by wangbo on 2018/10/26.
 */
public class AQS {

    /**
     * AQS中的Node节点有五种状态：
     ① SIGNAL,值为-1，表示当前节点的后续节点中的线程通过park被阻塞了，当前节点在释放或取消时要通过unpark解除它的阻塞。
     ② CANCELLED，值为1，表示当前节点的线程因为超时或中断被取消了。
     ③ CONDITION，值为-2，表示当前节点在condition队列中。
     ④ PROPAGATE，值为-3，共享模式的头结点可能处于此状态，表示无条件往下传播，引入此状态是为了优化锁竞争，使队列中线程有序地一个一个唤醒。
     ⑤ 0，除了以上四种状态的第五种状态，一般是节点初始状态。



     */

}
