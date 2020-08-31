package com.epam.cdp.kzta2020.api_tests;

import com.epam.cdp.kzta2020.common.Configuration;
import com.epam.cdp.kzta2020.entities.Advert;
import com.epam.cdp.kzta2020.entities.User;
import com.epam.cdp.kzta2020.utils.ConfigUtil;
import com.epam.cdp.kzta2020.utils.HtmlUtils;
import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KolesaAPITest {

    final Configuration configuration = ConfigUtil.getConfiguration();

    @Test
    public void checkStatusCode() {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(configuration.getBaseURI() + "/mototehnika/karaganda/");
            HttpResponse response = httpclient.execute(httpGet);

            Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
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
    public void checkAdvertQuantity() {
        try {
            Document document = Jsoup.connect(configuration.getBaseURI() + "/cars/toyota/corolla/karaganda/").get();
            String id = document.select("div[id*=\"advert-1\"]").toString();
            List<Advert> adverts = new ArrayList<>();

            for (String s : HtmlUtils.selectText(id)) {
                adverts.add(new Advert(s));
            }
            Assert.assertEquals(adverts.size(),21);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkResponseContent() {
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
