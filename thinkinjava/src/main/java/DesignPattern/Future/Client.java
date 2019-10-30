package DesignPattern.Future;

/**
 * Created by wangbo on 2018/3/3.
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        //此处创建单独线程执行耗时的构建工作 而主函数调用request方法时
        //异步构造的futureData已经返回
        new Thread() {
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }

    public static void  main(String[] args){
        Client client = new Client();
        Data data =  client.request("name");
        try {
            //模拟其他的业务操作
            //Thread.sleep(2000);
        }catch ( Exception e){

        }
        System.out.print("数据"+data.getResult());
    }

}
