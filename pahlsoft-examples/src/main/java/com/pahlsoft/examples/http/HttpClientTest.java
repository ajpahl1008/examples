package com.pahlsoft.examples.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import scala.util.parsing.json.JSONObject;

import java.io.IOException;

public class HttpClientTest {
    public static void main(String[] args) {
//        HttpClientPool httpClientPool = new HttpClientPool();
//
//        httpClientPool.getClient().execute()
    }

    // General query of the website. Takes an object of type Q and returns one of class R.
    public static <Q extends JSONObject, R> R query(String urlBase, String op, Q q, Class<R> r) throws IOException {
        // The request.
        final HttpRequestBase request;
        //postRequest.addHeader("Accept-Encoding", "gzip,deflate");
        if (q != null) {
            // Prepare the post.
            HttpPost postRequest = new HttpPost(urlBase + op);
            // Get it all into a JSON string.
            String// General query of the website. Takes an object of type Q and returns one of class R.
  public static <Q extends JSONObject, R> R query(String urlBase, String op, Q q, Class<R> r) throws IOException {
    // The request.
    final HttpRequestBase request;
    //postRequest.addHeader("Accept-Encoding", "gzip,deflate");
    if (q != null) {
      // Prepare the post.
      HttpPost postRequest = new HttpPost(urlBase + op);
      // Get it all into a JSON string.
      StringEntity input = new StringEntity(asJSONString(q));
      input.setContentType("application/json");
      postRequest.setEntity(input);
      // Use that one.
      request = postRequest;
    } else {
      // Just get.
      request = new HttpGet(urlBase + op);
    }
    log.debug("> " + urlBase + op + (q == null ? "" : " " + q));
    // Post it and wait.
    return readResponse(request, HttpClientPool.getClient().execute(request), r);
  }
  public static <R> R readResponse(HttpRequestBase request, CloseableHttpResponse response, Class<R> r) throws IOException {
    // What was read.
    R red = null;
    try {
      // What happened?
      if (response.getStatusLine().getStatusCode() == 200) {
        // Roll out the results
        HttpEntity entity = response.getEntity();
        if (entity != null) {
          InputStream content = entity.getContent();
          try {
            // Roll it directly from the response stream.
            JsonParser rsp = getFactory().createJsonParser(content);
            // Bring back the response.
            red = rsp.readValueAs(r);
          } finally {
            // Always close the content.
            content.close();
          }
        }
      } else {
        // The finally below will clean up.
        throw new IOException("HTTP Response: " + response.getStatusLine().getStatusCode());
      }
    } finally {
      // Always close the response.
      response.close();
    }

    if (red == null) {
      log.debug("< {null}");
    } else {
      log.debug("< {}", red.getClass().isArray() ? Arrays.toString((Object[]) red) : red.toString());
    }
    return red;
  }Entity input = new StringEntity(asJSONString(q));
            input.setContentType("application/json");
            postRequest.setEntity(input);
            // Use that one.
            request = postRequest;
        } else {
            // Just get.
            request = new HttpGet(urlBase + op);
        }
        log.debug("> " + urlBase + op + (q == null ? "" : " " + q));
        // Post it and wait.
        return readResponse(request, HttpClientPool.getClient().execute(request), r);
    }
    public static <R> R readResponse(HttpRequestBase request, CloseableHttpResponse response, Class<R> r) throws IOException {
        // What was read.
        R red = null;
        try {
            // What happened?
            if (response.getStatusLine().getStatusCode() == 200) {
                // Roll out the results
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream content = entity.getContent();
                    try {
                        // Roll it directly from the response stream.
                        JsonParser rsp = getFactory().createJsonParser(content);
                        // Bring back the response.
                        red = rsp.readValueAs(r);
                    } finally {
                        // Always close the content.
                        content.close();
                    }
                }
            } else {
                // The finally below will clean up.
                throw new IOException("HTTP Response: " + response.getStatusLine().getStatusCode());
            }
        } finally {
            // Always close the response.
            response.close();
        }

        if (red == null) {
            log.debug("< {null}");
        } else {
            log.debug("< {}", red.getClass().isArray() ? Arrays.toString((Object[]) red) : red.toString());
        }
        return red;
    }
}
