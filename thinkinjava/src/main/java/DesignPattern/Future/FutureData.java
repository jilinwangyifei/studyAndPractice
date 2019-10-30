package DesignPattern.Future;

/**
 * Created by wangbo on 2018/3/3.
 */
public class FutureData implements Data {

    protected RealData realData;

    protected Boolean isready = false;

    public synchronized void setRealData(RealData realData){
        while(isready) {
            return;
        }
        this.realData = realData;
        isready = true;
        notifyAll();
    }

    public String getResult(){
        while (!isready){
            try {
                wait();
            }catch (Exception e){

            }
        }
        return realData.result;
    }
}
