import com.pahlsoft.trebuchet.Dialogue;
import com.pahlsoft.trebuchet.TrebuchetClient;
import com.pahlsoft.trebuchet.TrebuchetServer;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrebuchetClientTest {

    static TrebuchetClient trebuchetClient;
    static TrebuchetServer trebuchetServer;


    @BeforeClass
    public static void init() throws Exception {
        trebuchetServer = new TrebuchetServer(9000);
        Thread.sleep(1000);
        trebuchetClient = new TrebuchetClient("localhost", 9000);
    }

    @Test
    public void sendSalutation() {
        Assert.assertEquals(trebuchetClient.send(Dialogue.SALUTATION),Dialogue.SALUTATION);
    }

    @Test
    public void sendSalutation2() {
        Assert.assertEquals(trebuchetClient.send(Dialogue.SALUTATION),Dialogue.SALUTATION);
    }

    @Test
    public void spawnServer() {
        Assert.assertEquals(trebuchetClient.send(Dialogue.START_TEST), Dialogue.ACK_START_TEST);
        Assert.assertEquals(trebuchetClient.send(1025), Dialogue.ACK_TARGET_PORT);
        TrebuchetClient tc = new TrebuchetClient("localhost", 1025);
        Assert.assertEquals(tc.send(Dialogue.SALUTATION), Dialogue.SALUTATION);
    }

    @AfterClass
    public static void disconnect() {
        trebuchetClient.disconnect();
    }

}
