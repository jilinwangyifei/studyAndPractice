package HotSwap;

import java.lang.reflect.Method;

/**
 * Created by wangbo on 2018/3/12.
 */

/**
 * -Xbootclasspath
 */
public class DoopRun {

    public static void main(String[] args) {
        while (true){
            try {
                MyClassLoader classLoader = new MyClassLoader("/Users/wangbo/Downloads/study/LearningOfThinkInJava-master");
                Class cls = classLoader.loadClass("HotSwap.DemoA");
                Object demo = cls.newInstance();
                Method method = demo.getClass().getMethod("hot",new Class[]{});
                method.invoke(demo,new Object[]{});
                System.out.println(classLoader);
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println("not found");
            }
        }
    }
}
