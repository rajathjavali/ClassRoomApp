package com.example.rajathjavali.dbmsprojectstaff;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RajathJavali on 4/3/2015.
 */
public class connection {

    HttpPost httppost;
    //HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    private String resp;
    public connection(List<NameValuePair> nvp,String filename)throws IOException{


        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 4000);
        httpclient = new DefaultHttpClient(params);
        httppost = new HttpPost(filename);
        nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs = nvp;
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        //response = httpclient.execute(httppost);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        resp = httpclient.execute(httppost, responseHandler);

    }

    public String getValue(){
        return this.resp;
    }
}
