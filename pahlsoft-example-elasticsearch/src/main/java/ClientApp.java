import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.List;

/**
 * Created By: aj
 */
public class ClientApp {

    public static void main(String[] args) throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("6bb237b4100b7992b1390ac51a4c741d.us-east-1.aws.found.io"), 9200));


// on shutdown
        List<DiscoveryNode> nodes = client.connectedNodes();
        System.out.println("Nodes: " + nodes.size());
        client.close();
    }
}
