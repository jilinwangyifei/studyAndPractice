package Annotation;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by wangbo on 2018/9/16.
 * 摘自 https://mp.weixin.qq.com/s/Na5ZU4CekGQfc1xYwuKuHQ
 *
 * -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 */
public class AnnotationPrinciple {

    @Hello("hello")
    public static void main(String[] args) throws NoSuchMethodException{
        Class cls = AnnotationPrinciple.class;
        Method method = cls.getMethod("main", String[].class);
        /**
         * getAnnotation 方法去获取一个注解类实例的时候，
         * 其实 JDK 是通过动态代理机制生成一个实现我们注解（接口）的代理类。
         */
        Hello hello = method.getAnnotation(Hello.class);
    }
}

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface Hello {
    String value();
}
