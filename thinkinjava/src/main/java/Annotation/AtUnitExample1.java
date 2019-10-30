package Annotation;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

/**
 * Created by wangbo on 2018/10/1.
 */
public class AtUnitExample1 {

    public String methodOne() {
        return "this is methodOne";
    }

    public int methodTwo() {
        System.out.println("this is methodOne");
        return 2;
    }

    @Test boolean methodOneTest() {
       return methodOne().equals("this is methodOne");
    }

    public static void main(String[] args) {
        OSExecute.command("java net.mindview.atunit.AtUnit Annotation.AtUnitExample1");
    }
}
