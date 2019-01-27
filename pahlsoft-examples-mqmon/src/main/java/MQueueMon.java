import java.util.Date;

public class MQueueMon {
    public static void main(String[] args) {
        MQMonitorBasic mqMonitorBasic = new MQMonitorBasic("dev");
        int currentQueueDepth = mqMonitorBasic.getCurrentQueueDepth();
        int maxQueueDepth = mqMonitorBasic.getMaxDepth();
         String timeDateStamp = new Date().toString();
        System.out.println(timeDateStamp
                      + "|QueueQmgr:" + mqMonitorBasic.getQueueManagerName()
                      + "|QueueName:" + mqMonitorBasic.getQueueName()
                      + "|CurrentQueueDepth:" + currentQueueDepth
                      + "|CurrentMaxDepth:" + maxQueueDepth );
    }
}
