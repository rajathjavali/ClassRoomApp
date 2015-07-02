package com.example.rajathjavali.dbmsprojectstaff;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.List;


public class ipadd extends ActionBarActivity {
    EditText e;
    String url;
    ProgressDialog dialog;
    Thread t;
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipadd);
        //int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //setRequestedOrientation(orientation);
        e = (EditText) findViewById(R.id.ip);
        e.setText(Singleton.getInstance().getIp());
        Button b = (Button) findViewById(R.id.connect);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(ipadd.this, "", "Connecting ...", true);
                dialog.setCanceledOnTouchOutside(true);
                t = new Thread(new Runnable() {
                    public void run() {

                        check();

                    }
                });
                t.start();
            }
        });

    }
    public void check(){
        try {
            String ip = e.getText().toString();
            url = "http://"+ip+"/Teacher/check.php";
            Singleton.getInstance().setIp(ip.trim());

            List nameValuePairs = new ArrayList<>(2);
            nameValuePairs.add(new BasicNameValuePair("hello","hello"));

            connection c = new connection(nameValuePairs, url);
            final String response = c.getValue();

            runOnUiThread(new Runnable() {
                public void run() {

                    dialog.dismiss();
                }
            });

            if(response.equalsIgnoreCase("Connected")){
                startActivity(new Intent(this,Login.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }

        }catch(Exception e){
            dialog.dismiss();
            showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert(){
        ipadd.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ipadd.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ipadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}