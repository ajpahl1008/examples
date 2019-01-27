import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ElasticHLC {
    public static void main(String[] args) {


        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("<ID>", "<PASSWORD>"));

        RestClientBuilder builder = RestClient.builder(new HttpHost("IP_OR_DNS", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestHighLevelClient client = new RestHighLevelClient(builder);

        try {
            SearchRequest searchRequest = new SearchRequest();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("dvd_cost","3127.72"));
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse =  client.search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            System.out.println("Found " + hits.totalHits + " Documents");
            for (SearchHit hit : hits) {
//                System.out.println("Doc Id: " + hit.getSourceAsString());
                System.out.println("description: " + hit.getSourceAsMap().get("description"));
            }
            client.close();

        } catch (Exception ioe) {

        }
    }
}
