package cn.liupu.dp.beauty.metrics2;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: liupu1
 * @create: 2020/9/6 2:04 PM
 **/
public class Aggregator {

    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationMillis) {

        double maxRespTime = Double.MIN_VALUE;
        double minRespTime = Double.MAX_VALUE;
        double avgRespTime = -1;
        double p999RespTime = -1;
        double p99RespTime = -1;
        double sumRespTime = 0;
        long count = 0;

        for (RequestInfo requestInfo : requestInfos) {
            ++count;
            double respTime = requestInfo.getResponseTime();
            if (maxRespTime < respTime) {
                maxRespTime = respTime;
            }
            if (minRespTime > respTime) {
                minRespTime = respTime;
            }
            sumRespTime += respTime;
        }

        if (count != 0) {
            avgRespTime = sumRespTime / count;
        }

        long tps = count / durationMillis * 1000;

        Collections.sort(requestInfos, (o1, o2) -> {
            double diff = o1.getResponseTime() - o2.getResponseTime();
            if (diff < 0.0) {
                return -1;
            } else if (diff > 0.0) {
                return 1;
            } else {
                return 0;
            }
        });

        int index999 = (int) (count * 0.999);
        int index99 = (int) (count * 0.99);

        if (count != 0) {
            p999RespTime = requestInfos.get(index999).getResponseTime();
            p99RespTime = requestInfos.get(index99).getResponseTime();
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setCount(count);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setTps(tps);

        return requestStat;
    }

}
