package ParallelBasic;

public class SychroAndLock {

    /**
     * synchronize 和 lock 区别
     *
     * 1. Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
     * 2. synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；
     *     而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
     * 3. Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
     * 4. 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
     * 5. Lock可以提高多个线程进行读操作的效率。（可以通过readwritelock实现读写分离）
     * 6. 性能上来说，在资源竞争不激烈的情形下，Lock性能稍微比synchronized差点（编译程序通常会尽可能的进行优化synchronized）。
     *     但是当同步非常激烈的时候，synchronized的性能一下子能下降好几十倍。而ReentrantLock确还能维持常态。
     *
     *
     *
     * 作者：aoho
     * 链接：https://juejin.im/post/5a43ad786fb9a0450909cb5f
     * 来源：掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
