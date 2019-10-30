package DesignPattern.Future;

/**
 * Created by wangbo on 2018/3/3.
 */
public class RealData implements Data {

    String result;

    public RealData(String para){
       //在此处构造一个很慢的过程 模拟延迟操作
       StringBuffer str= new StringBuffer();
       for(int i = 0;i < 10 ; i++){
           str.append(i);
       }
       try{
           //使用sleep代替一个很慢的过程
           Thread.sleep(10000);
       }catch (Exception e){

       }
       result = str.toString();
    }

    public String getResult(){
        return result;
    }
}
