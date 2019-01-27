
import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class MQMonitorBasic {

    private static int openOptions = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED + CMQC.MQOO_OUTPUT
            + CMQC.MQGMO_NO_WAIT + CMQC.MQGMO_ACCEPT_TRUNCATED_MSG;
    private static Properties properties = new Properties();
    private static MQQueueManager queueManager;
    private static MQQueue queue;
    private static MQMessage mqMessage;
    private static String queueName = "undefined";
    private static String queueManagerName = "undefined";
    private static Logger LOG = LoggerFactory.getLogger(MQMonitorBasic.class);

    public MQMonitorBasic(String environment) {
        try {
            LOG.info("Loading Properties for " + environment);
            loadProperties(environment);
            connectToQueueManager();
        } catch (Exception e) {
            LOG.error("Unable to Load Properties for " + environment);
        }
    }

    private void loadProperties(String environment) throws IOException {
        properties.load(MQMonitorBasic.class.getResourceAsStream("mq-monitor.properties"));
        MQEnvironment.hostname = properties.getProperty(environment + ".hostName");
        MQEnvironment.port = Integer.parseInt(properties.getProperty(environment + ".port"));
        MQEnvironment.channel = properties.getProperty(environment + ".channelName");
        MQEnvironment.userID = properties.getProperty(environment + ".mqUserID");
        MQEnvironment.password = properties.getProperty(environment + ".mqPwd");
        queueName = properties.getProperty(environment + ".queueName");
        queueManagerName = properties.getProperty(environment + ".queueManagerName");
    }

    public String getQueueName() {
        return queueName;
    }

    public String getQueueManagerName() {
        return queueManagerName;
    }

    private void connectToQueueManager() throws MQException {
        queueManager = new MQQueueManager(queueManagerName);
        if (queueManager.isConnected()) LOG.info("Connected: " + queueManager.getName());
    }

    private int checkQueueDepth() throws MQException {
        connect();
        return queue.getCurrentDepth();
    }

    public int getCurrentQueueDepth() {
        connect();
        int queueDepth = -1;
        try {
            return checkQueueDepth();
        } catch (MQException e) {
            LOG.error("MQ Connection Error. Reason Code: " + e.getReason());
        }
        return queueDepth;
    }

    public int getMaxDepth() {
        connect();
        try {
            return queue.getMaximumDepth();
        } catch (MQException e) {
            LOG.error("Error Getting Queue Max Queue Depth. Reason Code: " + e.reasonCode);
        }
        return -1;
    }

    public void sendMessage(String msg) {
        connect();
        mqMessage = new MQMessage();
        try {
            mqMessage.messageType = 8;
            try {
                mqMessage.writeString(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            queue.put(mqMessage);
        } catch (MQException e) {
            LOG.error("Error Sending Message. Reason Code: " + e.reasonCode);
        }
    }

    public void purgeQueue() {
        connect();
        int depth = 0;
        boolean stillHasMessages = true;
        try {
            depth = queue.getCurrentDepth();
        } catch (MQException e) {
            LOG.error("Unable to determine queue Depth.");
        }
        if (depth > 0) {
            try {
                while (stillHasMessages) {
                    mqMessage = new MQMessage();
                    try {
                        queue.get(mqMessage);
                    } catch (MQException e) {
                        if (e.completionCode == 1 && e.reasonCode == MQException.MQRC_TRUNCATED_MSG_ACCEPTED) {
                            LOG.info("Message was purged");
                        } else {
                            stillHasMessages = false;
                            if (e.completionCode == 2 && e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) {
                                LOG.info("Queue has been depleted");
                            } else {
                                LOG.error("Error trying to purge Queue. Reason Code: " + e.reasonCode);
                            }

                        }
                    }
                }
            } catch (Exception e) {
                LOG.error("Error processing purge request.");
            }
        } else {
            LOG.info("No messages to purge.");
        }
    }

    private void connect() {
        try {
            queueManager = new MQQueueManager(queueManagerName);
            queue = queueManager.accessQueue(queueName, openOptions);
        } catch (MQException e) {
            e.printStackTrace();
        }
    }

}
