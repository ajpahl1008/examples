package com.pahlsoft.utilities.engine;


import com.pahlsoft.utilities.generator.TransactionGenerator;
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

public class CreditCardTransactionEngine implements TransactionEngine {

    static Logger log = LoggerFactory.getLogger(CreditCardTransactionEngine.class);

    private Properties properties = new Properties();

    public CreditCardTransactionEngine() throws Exception {
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

        log.info("Starting Credit Card Transaction Engine " + Thread.currentThread().getId());
        RestHighLevelClient client = null;
        IndexRequest indexRequest = null;

        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(this.properties.getProperty("elasticsearch.host"), Integer.parseInt(this.properties.getProperty("elasticsearch.port")), "http"))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }));
        while(true) {
            try {
                indexRequest = new IndexRequest("fintech-credit", "doc").source(TransactionGenerator.buildTransaction());
                client.index(indexRequest, RequestOptions.DEFAULT);
                log.debug("|CR|");
            } catch (Exception ioe) {

            }
            try {
                Thread.sleep(randomSleepTime(1000,10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static long randomSleepTime(int min, int max) {
        double delay_time = Math.random() * (max - min) + min;
        return (long) delay_time;

    }

}