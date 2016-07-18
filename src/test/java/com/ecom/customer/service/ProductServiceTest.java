package com.ecom.customer.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.mapping.PutMapping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by jcordones13 on 7/16/16.
 */
public class ProductServiceTest {

    private JestClient jestClient;

    private String productMapping = "{\n" +
            "      \"products\": {\n" +
            "        \"properties\": {\n" +
            "          \"id\": {\n" +
            "            \"type\": \"long\"\n" +
            "          },\n" +
            "          \"name\": {\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"description\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"null_value\": \"N/A\"\n" +
            "          },\n" +
            "          \"price\": {\n" +
            "            \"type\": \"double\"\n" +
            "          },\n" +
            "          \"salePrice\": {\n" +
            "            \"type\": \"double\"\n" +
            "          },\n" +
            "          \"url\": {\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"thumbnailUrl\": {\n" +
            "            \"type\": \"string\"\n" +
            "          },\n" +
            "          \"picUrl\": {\n" +
            "            \"type\": \"string\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "}\n";

    @Before
    public void setUp() throws Exception {
        HttpClientConfig clientConfig = new HttpClientConfig
                .Builder("http://localhost:32769/")
                .multiThreaded(true)
                .build();
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(clientConfig);
        jestClient = factory.getObject();
    }

    //@Test
    public void createESMappingTest(){
        PutMapping mapping = new PutMapping.Builder("service", "products", productMapping).build();

        try {
            JestResult result = jestClient.execute(mapping);
            System.out.println("----- result " + result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {

    }
}