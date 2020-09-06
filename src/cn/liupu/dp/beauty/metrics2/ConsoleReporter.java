package cn.liupu.dp.beauty.metrics2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: liupu1
 * @create: 2020/9/6 2:29 PM
 **/
public class ConsoleReporter {

    private MetricsStorage metricsStorage;

    private ScheduledExecutorService executor;


    public ConsoleReporter(MetricsStorage metricsStorage, ScheduledExecutorService executor) {
        this.metricsStorage = metricsStorage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {

        executor.scheduleAtFixedRate(() -> {
            //第1个代码逻辑: 根据给定时间区间,从数据库中拉取数据
            long durationInMillis = durationInSeconds * 1000;
            long endTimeInMillis = System.currentTimeMillis();
            long startTimeInMillis = endTimeInMillis - durationInMillis;

            Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
            Map<String, RequestStat> statsMap = new HashMap<>();

            for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                String apiName = entry.getKey();
                List<RequestInfo> requestInfoPerApi = entry.getValue();

                //第2个代码逻辑: 根据原始数据,计算得到统计数据
                RequestStat requestStat = Aggregator.aggregate(requestInfoPerApi, durationInMillis);
                statsMap.put(apiName, requestStat);
            }

            //第3个代码逻辑: 将统计数据显示到终端(命令行或邮件)
            System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis + "]");
            System.out.println(statsMap);

        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }


}
