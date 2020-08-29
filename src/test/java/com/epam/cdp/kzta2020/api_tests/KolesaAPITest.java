package com.epam.cdp.kzta2020.api_tests;

import com.epam.cdp.kzta2020.common.Configuration;
import com.epam.cdp.kzta2020.entities.User;
import com.epam.cdp.kzta2020.utils.ConfigUtil;
import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KolesaAPITest {

    final Configuration configuration = ConfigUtil.getConfiguration();

    @Test
    public void checkStatusCode() {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(configuration.getBaseURI() + "/mototehnika/karaganda/");
            HttpResponse response = httpclient.execute(httpGet);

            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkResponseHeader() {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(configuration.getBaseURI() + "/cars");
            HttpResponse response = httpclient.execute(httpGet);

            Header[] contentTypeHeader = (response.getHeaders("content-type"));
            Assert.assertTrue(Arrays.toString(contentTypeHeader).contains("text/html; charset=UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkResponseContentTest() {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(configuration.getJsonPhBaseURI() + "/users");
            HttpResponse response = httpclient.execute(httpGet);

            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            User[] users = new Gson().fromJson(br, User[].class);
            Assert.assertEquals(users.length, 10);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
