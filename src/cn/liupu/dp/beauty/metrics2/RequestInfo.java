package cn.liupu.dp.beauty.metrics2;

/**
 * @description:
 * @author: liupu1
 * @create: 2020/9/6 1:56 PM
 **/
public class RequestInfo {

    private String apiName;

    private double responseTime;

    private long timestamp;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
