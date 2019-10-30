package ParallelBasic;

/**
 * Created by wangbo on 2018/3/6.
 */
public class AccountingSync implements Runnable {
    AccountingSync accountingSync  = new AccountingSync();
    static int i = 0;

    @Override
    public void run() {
        synchronized (accountingSync){
            i++;
        }
    }
}
