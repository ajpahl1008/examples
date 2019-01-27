import com.pahlsoft.watchdog.utility.CommandLineRunner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

public class CommandLineRunnerTest extends TestNG {
    @Test
    public void testCommandLine() {
        JSONObject sample = CommandLineRunner.execute("echo","echo hello");
        Assert.assertEquals("[\"hello\"]", sample.get("echo").toString());
    }

}
