import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.*;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.System;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ElasticRestTest {

    private static App app;
    private static models.System system;
    private static List<Transaction> transactions;

    public static void main(String[] args) {

        System.out.println("Building Message");
        app = new App();

        app.setName("JavaTest");
        app.setPid(123456);
        app.setProcessTitle("java");

        ArrayList<String> argvs = new ArrayList();
            argvs.add("GCFLAG");
            argvs.add("PROGRAM_NAME");
        app.setArgv(argvs);

        RunTime runTime = new RunTime();
        runTime.setName("java");
        runTime.setVersion("jdk_1.8.0_144");
        app.setRuntime(runTime);

        Agent agent = new Agent();
        agent.setName("elasticagent");
        agent.setVersion("0.0.1");
        app.setAgent(agent);

        Framework framework = new Framework();
        framework.setName("gradle");
        framework.setVersion("4.0.2");
        app.setFramework(framework);

        system = new models.System();
        system.setHostname("maddog");
        system.setArchitecture("mac");
        system.setPlatform("darwin");

        transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setName("GetClassName");
            transaction.setType("setScopeHere");
            transaction.setDuration(444);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
                Date date = new Date();
            transaction.setTimestamp(dateFormat.format(date));
            transaction.setResult("PASS");

        Context context = new Context();
            context.setCustom(new Custom());
            context.setTags(new Tags());
            context.setUser(new User());
        transaction.setContext(context);
        transaction.setTraces(null);

        transactions.add(transaction);

        Gson  gson = new GsonBuilder().create();

        JSONObject combined = new JSONObject();
        combined.put("app", gson.toJson(app));
        combined.put("system", gson.toJson(system));
        combined.put("transactions", gson.toJson(transactions));

        APMMessage apmMessage = new APMMessage();
        apmMessage.setApp(app);
        apmMessage.setSystem(system);
        apmMessage.setTransactions(transactions);

        try {

            URL url = new URL("http://192.168.0.214:8200/v1/transactions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            String input = gson.toJson(apmMessage);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode() + conn.getResponseMessage());
            } else {
                System.out.println("Sending Message to Server");
                System.out.println("Response... \n");
                System.out.println(conn.getResponseMessage() + " " + input);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
