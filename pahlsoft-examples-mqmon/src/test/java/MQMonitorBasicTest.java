import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MQMonitorBasicTest {

    private static MQMonitorBasic mqMonitorBasic;

    @Before
    public void init() {
       mqMonitorBasic = new MQMonitorBasic("dev");
    }
    @Test
    public void sendMessage() {
        int initialValue = mqMonitorBasic.getCurrentQueueDepth();
        sendTestMessages(1);
        int secondaryValue = mqMonitorBasic.getCurrentQueueDepth();
        Assert.assertTrue(initialValue<secondaryValue);
        mqMonitorBasic.purgeQueue();
    }

    @Test
    public void purgeQueue() {
        mqMonitorBasic.purgeQueue();
        Assert.assertEquals(0,mqMonitorBasic.getCurrentQueueDepth());
    }

    @Test
    public void purgeQueue10() {
        sendTestMessages(10);
        mqMonitorBasic.purgeQueue();
        Assert.assertEquals(0,mqMonitorBasic.getCurrentQueueDepth());
    }

    private void sendTestMessages(int messageCount) {
        for (int i=0; i<messageCount; i++) {
            mqMonitorBasic.sendMessage("Test "+ i);
        }
    }

    @Test
    public void checkforEmptyQueue() {
        mqMonitorBasic.purgeQueue();
        Assert.assertEquals(0,mqMonitorBasic.getCurrentQueueDepth());
    }

    @Test
    public void checkForOneMessage() {
        mqMonitorBasic.purgeQueue();
        mqMonitorBasic.sendMessage("UnitTest");
        Assert.assertEquals(1,mqMonitorBasic.getCurrentQueueDepth());
        mqMonitorBasic.purgeQueue();
    }

    @Test
    public void checkMaxDepth() {
        Assert.assertEquals(5000,mqMonitorBasic.getMaxDepth());
    }

    @Test
    public void reportCurrentQueueDepth() {
        sendTestMessages(10);
        Assert.assertEquals(10, mqMonitorBasic.getCurrentQueueDepth());
        mqMonitorBasic.purgeQueue();
    }

}
