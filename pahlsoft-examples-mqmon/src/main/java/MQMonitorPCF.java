


import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.headers.CMQC;
import com.ibm.mq.pcf.PCFMessage;
import com.ibm.mq.pcf.PCFMessageAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;


public class MQMonitorPCF {

    private static int openOptions = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED;
    private static Properties properties = new Properties();
    private static String environment = "";
    private static MQQueueManager queueManager;
    private static MQQueue queue;
    private static Logger LOG = LoggerFactory.getLogger(MQMonitorBasic.class);

    public void MQMonitorPCF(String[] args) {
        loadProperties();
        String timeDateStamp = new Date().toString();
    }

    private void loadProperties() {
        try {
            properties.load(MQMonitorPCF.class.getResourceAsStream("mq-monitor.properties"));
            MQEnvironment.hostname = properties.getProperty(environment + ".hostName");
            MQEnvironment.port = Integer.parseInt(properties.getProperty(environment + ".port"));
            MQEnvironment.channel = properties.getProperty(environment + ".channelName");
            MQEnvironment.userID = properties.getProperty(environment + ".mqUserID");
            MQEnvironment.password = properties.getProperty(environment + ".mqPwd");
            queueManager = new MQQueueManager(properties.getProperty(environment + ".queueManagerName"));
            queue = queueManager.accessQueue(properties.getProperty(environment + ".queueName"), openOptions);
        } catch (Exception e) {
            LOG.error("Unable to load properties.");
        }

    }
    public boolean startChannel() {
        PCFMessageAgent pcfMessageAgent = new PCFMessageAgent();
        try {
            pcfMessageAgent.connect(queueManager);

        } catch (MQException e) {
            e.printStackTrace();
        }
        PCFMessage pcfmessage = new PCFMessage(28);
        pcfmessage.addParameter(3501, "dude");
        try
        {
            pcfMessageAgent.send(pcfmessage);
            return true;
        }
        catch(IOException ioexception)
        {
            LOG.info("I/O error startChannel");
            return false;
        } catch (MQException mqe) {
            LOG.info("error startChannel: " + mqe.reasonCode);
        }
        return false;
    }

}
