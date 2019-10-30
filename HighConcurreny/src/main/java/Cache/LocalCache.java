package Cache;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
/**
 * Created by wangbo on 2018/9/5.
 * 缓存的数据在5s之后过期
 * 试想下应用场景
 */
public class LocalCache {

    private int i = 1;
    LoadingCache<Integer, String> cityPhoneCache = CacheBuilder.newBuilder().maximumSize(50)
            .expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<Integer, String>() {
                @Override
                public String load(Integer cityId) throws Exception {
                    System.out.println("查询值"+System.currentTimeMillis());
                    if (i == 1) {
                        i++;
                        return "123";

                    } else {
                        try {
                            throw new Exception();
                        }catch (Exception e) {

                        }
                    }
                    return "";
                }
            });

    public static void main(String[] args) throws Exception{
        LocalCache localCache = new LocalCache();
        while (true) {
            System.out.println("获取值"+System.currentTimeMillis());
            System.out.println(localCache.cityPhoneCache.get(1));
            Thread.sleep(2000);
        }
    }
}

