package com.pahlsoft.utilities.engine;


import com.pahlsoft.utilities.generator.TransactionGenerator;
import com.pahlsoft.utilities.interfaces.TransactionEngine;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;

import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
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

        log.info("Starting Credit Card Transaction Engine " + Thread.currentThread().getId());

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(this.properties.getProperty("elasticsearch.user"),this.properties.getProperty("elasticsearch.password")));

        RestHighLevelClient client;
        IndexRequest indexRequest;
        KeyStore truststore = null;
        SSLContextBuilder sslBuilder = null;

        try {
            if (this.properties.getProperty("elasticsearch.scheme") == "https") {
                sslBuilder = buildSSLContext(truststore, sslBuilder);
                final SSLContext sslContext = sslBuilder.build();
                client = getSecureClient(credentialsProvider, sslContext);

            } else {
                client = getClient(credentialsProvider);
            }

            while(true) {
                try {
                    indexRequest = new IndexRequest("fintech-credit", "doc").source(TransactionGenerator.buildTransaction());
                    client.index(indexRequest, RequestOptions.DEFAULT);
                    log.debug("|CR|");
                } catch (Exception ioe) {
                    ioe.printStackTrace();
                }

                try {
                    Thread.sleep(randomSleepTime(1000,5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private SSLContextBuilder buildSSLContext(KeyStore truststore, SSLContextBuilder sslBuilder) {
        try {
            truststore = KeyStore.getInstance("jks");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try (InputStream is = Files.newInputStream(Paths.get(this.properties.getProperty("keystore.location")))) {
            truststore.load(is, this.properties.getProperty("keystore.password").toCharArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            sslBuilder = SSLContexts.custom()
                    .loadTrustMaterial(truststore, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return sslBuilder;
    }

    private RestHighLevelClient getSecureClient(CredentialsProvider credentialsProvider, SSLContext sslContext) {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(this.properties.getProperty("elasticsearch.host"), Integer.parseInt(this.properties.getProperty("elasticsearch.port")), this.properties.getProperty("elasticsearch.scheme")))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider).setSSLContext(sslContext);
                            }
                        }));
    }

    private RestHighLevelClient getClient(CredentialsProvider credentialsProvider) {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(this.properties.getProperty("elasticsearch.host"), Integer.parseInt(this.properties.getProperty("elasticsearch.port")), this.properties.getProperty("elasticsearch.scheme")))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }));
    }


    private static long randomSleepTime(int min, int max) {
        double delay_time = Math.random() * (max - min) + min;
        return (long) delay_time;

    }

}
