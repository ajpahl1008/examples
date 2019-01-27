import org.junit.Assert;
import org.junit.Test;

public class MQMonitorPCFTest {

    public static MQMonitorPCF mqMonitorPCF = new MQMonitorPCF();
    @Test
    public void testCreation() {
        Assert.assertNotNull(mqMonitorPCF);
    }

    @Test
    public void testStartChannel() {
        Assert.assertTrue(mqMonitorPCF.startChannel());
    }

}
