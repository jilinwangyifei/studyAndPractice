package EventListener;
import java.util.EventListener;

/**
 * Created by wangbo on 2018/10/28.
 */
public interface MethodMonitorEventListener extends EventListener {

    // 处理方法执行之前发布的事件
    public void onMethodBegin(MethodMonitorEvent event);

    // 处理方法结束时发布的事件
    public void onMethodEnd(MethodMonitorEvent event);

}

class AbstractMethodMonitorEventListener implements MethodMonitorEventListener {

    public void onMethodBegin(MethodMonitorEvent event) {
        // 记录方法开始执行时的时间
        event.timestamp = System.currentTimeMillis();
    }

    public void onMethodEnd(MethodMonitorEvent event) {
        // 计算方法耗时
        long duration = System.currentTimeMillis() - event.timestamp;
        System.out.println("耗时：" + duration);
    }
}


