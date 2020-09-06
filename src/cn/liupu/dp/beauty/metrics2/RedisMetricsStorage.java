package cn.liupu.dp.beauty.metrics2;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: liupu1
 * @create: 2020/9/6 2:00 PM
 **/
public class RedisMetricsStorage implements MetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        //...
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeMillis) {
        //...
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        //...
        return null;
    }
}
