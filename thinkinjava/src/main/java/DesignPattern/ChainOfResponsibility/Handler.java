package DesignPattern.ChainOfResponsibility;

/**
 * Created by wangbo on 2018/10/9.
 */

/**
 * 责任链模式
 */

public abstract class Handler {

    protected Handler handler;


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public abstract String handleFeeRequest(String user, double fee);

}
