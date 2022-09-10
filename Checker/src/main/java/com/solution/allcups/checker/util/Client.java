package com.solution.allcups.checker.util;

import com.google.gson.Gson;
import com.solution.allcups.checker.object.*;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Client {
    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    private final static String baseUrl = new BaseURL().getBaseURL() + "/api"; 

    private final static Gson gson = new Gson();

    public static void postObject(String url, SystemObject body) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost request = new HttpPost(baseUrl + url);

        request.setEntity(new StringEntity(gson.toJson(body)));
        request.setHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse response = client.execute(request);

            client.close();

            logger.info(baseUrl + url + " : " + response.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void post(String url, String body) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost request = new HttpPost(baseUrl + url);

        request.setEntity(new StringEntity(body));
        request.setHeader("Content-type", "application/json");
        
        try {
            CloseableHttpResponse response = client.execute(request);

            client.close();

            logger.info(baseUrl + url + " : " + response.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void putObject(String url, SystemObject body) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPut request = new HttpPut(baseUrl + url);

        request.setEntity(new StringEntity(gson.toJson(body)));
        request.setHeader("Content-type", "application/json");
        
        try {
            CloseableHttpResponse response = client.execute(request);

            client.close();

            logger.info(baseUrl + url + " : " + response.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void put(String url, String body) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPut request = new HttpPut(baseUrl + url);

        request.setEntity(new StringEntity(body));
        request.setHeader("Content-type", "application/json");
        
        try {
            CloseableHttpResponse response = client.execute(request);

            client.close();

            logger.info(baseUrl + url + " : " + response.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String url) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpDelete request = new HttpDelete(baseUrl + url);

        request.setHeader("Content-type", "application/json");
        
        try {
            CloseableHttpResponse response = client.execute(request);

            client.close();

            logger.info(baseUrl + url + " : " + response.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void get(String url) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet request = new HttpGet(baseUrl + url);

        try {
            CloseableHttpResponse response = client.execute(request);

            client.close();

            logger.info(baseUrl + url + " : " + response.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
