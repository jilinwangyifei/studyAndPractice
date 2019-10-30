package EventListener;

import java.util.EventObject;

/**
 * Created by wangbo on 2018/10/28.
 */
public class MethodMonitorEvent extends EventObject {

    // 时间戳，用于记录方法开始执行的时间
    public long timestamp;

    public MethodMonitorEvent(Object source) {
        super(source);
    }
}
