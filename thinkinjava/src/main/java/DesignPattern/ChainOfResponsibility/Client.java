package DesignPattern.ChainOfResponsibility;

/**
 * Created by wangbo on 2018/10/10.
 */
public class Client {
    public static void main(String[] args) {
        Handler h1 = new DeptManager();
        Handler h2 = new ProjectManager();
        h2.setHandler(h1);
        String test = h2.handleFeeRequest("张三",12);
    }
}
