package cn.liupu.dp.beauty.metrics2;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: liupu1
 * @create: 2020/9/6 1:57 PM
 **/
public interface MetricsStorage {

    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);

}
