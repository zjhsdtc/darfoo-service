package utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjh on 15-1-7.
 */
public class HttpUtils {
    private HttpClient httpClient;

    public int sendCacheRequest(String backendUrl){
        if (httpClient == null){
            httpClient = new DefaultHttpClient();
        }

        HttpGet request = new HttpGet(backendUrl);

        try {
            HttpResponse response = httpClient.execute(request);
            int statuscode = response.getStatusLine().getStatusCode();
            return statuscode;
        } catch (IOException e) {
            e.printStackTrace();
            return 500;
        }
    }

    public int sendStatisticRequest(String backendUrl){
        if (httpClient == null){
            httpClient = new DefaultHttpClient();
        }

        HttpGet request = new HttpGet(backendUrl);

        try {
            HttpResponse response = httpClient.execute(request);
            int statuscode = response.getStatusLine().getStatusCode();
            return statuscode;
        } catch (IOException e) {
            e.printStackTrace();
            return 500;
        }
    }

    public String getRequest(String backendUrl){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(backendUrl);
        try {
            HttpResponse response = client.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
            System.out.println(result);
            return result.toString();
        } catch (IOException e) {
            //e.printStackTrace();
            return "";
        }
    }

    public String postRequest(String backendUrl, List<NameValuePair> urlParameters){
        HttpClient client = new DefaultHttpClient();

        try {
            client.getParams().setParameter("http.protocol.content-charset",HTTP.UTF_8);
            client.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
            client.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
            client.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,HTTP.UTF_8);

            // Post请求
            HttpPost post = new HttpPost(backendUrl);

            //设置post编码
            post.getParams().setParameter("http.protocol.content-charset",HTTP.UTF_8);
            post.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
            post.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
            post.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);

            post.setEntity(new UrlEncodedFormEntity(urlParameters, HTTP.UTF_8));
            //post.setHeader("Content-Type", "text/json;charset=UTF-8");
            HttpResponse response = client.execute(post);
            System.out.println("\nSending 'POST' request to URL : " + backendUrl);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());
            return result.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (ClientProtocolException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }
}
