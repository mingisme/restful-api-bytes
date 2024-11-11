package com.envisioniot.enos;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final String URL = "http://localhost:8080/api/v1/data/upload";
    private static final OkHttpClient client = new OkHttpClient();

    public static void main( String[] args )
    {

        byte[] byteArray = "Hello world!".getBytes();

        DataRequest dataRequest = new DataRequest();
        dataRequest.setId("12345");
        dataRequest.setContent(byteArray);

        String jsonPayload = JSON.toJSONString(dataRequest); //"{\"id\": \"12345\", \"content\": \"" + base64Content + "\"}";

        RequestBody requestBody = RequestBody.create(
                jsonPayload,
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Response: " + response.body().string());
            } else {
                System.err.println("Request failed with status code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
