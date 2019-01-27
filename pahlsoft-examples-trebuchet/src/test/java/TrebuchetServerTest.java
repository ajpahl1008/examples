
import com.pahlsoft.trebuchet.TrebuchetServer;
import org.junit.Assert;
import org.junit.Test;

public class TrebuchetServerTest {


    @Test
    public void longWaitTime() {
        TrebuchetServer server = new TrebuchetServer(9001);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse(server.isListening());
        server.stop();

    }

}


