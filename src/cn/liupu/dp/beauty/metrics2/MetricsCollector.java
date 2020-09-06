package cn.liupu.dp.beauty.metrics2;

/**
 * @description:
 * @author: liupu1
 * @create: 2020/9/6 2:01 PM
 **/
public class MetricsCollector {

    //基于接口而非实现编程
    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    //用一个函数代替了最小原型中的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || requestInfo.getApiName() == null || requestInfo.getApiName().equals("")) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
