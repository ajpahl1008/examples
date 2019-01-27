package com.pahlsoft.utilities.engine;


import com.pahlsoft.utilities.generator.AccountGenerator;
import com.pahlsoft.utilities.generator.TransactionGenerator;
import com.pahlsoft.utilities.interfaces.AccountEngine;
import com.pahlsoft.utilities.interfaces.TransactionEngine;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccountGeneratorEngine implements AccountEngine {

    static Logger log = LoggerFactory.getLogger(AccountGeneratorEngine.class);

    private Properties properties = new Properties();

    public AccountGeneratorEngine() throws Exception {
        this.loadProperties();
    }

    private void loadProperties() throws IOException {
        Thread currentThread = Thread.currentThread();
        ClassLoader contextClassLoader = currentThread.getContextClassLoader();
        InputStream propertiesStream = contextClassLoader.getResourceAsStream("config.properties");
        if(propertiesStream != null) {
            this.properties.load(propertiesStream);
        } else {
            log.error("No Properties Found");
        }
    }


    @Override
    public void run() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(this.properties.getProperty("elasticsearch.user"),this.properties.getProperty("elasticsearch.password")));

        log.info("Starting Account Generation Engine " + Thread.currentThread().getId());
        RestHighLevelClient client;
        IndexRequest indexRequest;

        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(this.properties.getProperty("elasticsearch.host"), Integer.parseInt(this.properties.getProperty("elasticsearch.port")), this.properties.getProperty("elasticsearch.scheme")))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }));
        while(true) {
            try {
                indexRequest = new IndexRequest("dda-accounts", "doc").source(AccountGenerator.buildAccount());
                client.index(indexRequest, RequestOptions.DEFAULT);
                log.debug("|DDA|");
            } catch (Exception ioe) {

            }
        }
    }

}