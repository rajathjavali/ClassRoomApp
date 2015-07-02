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
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Login extends ActionBarActivity {

    EditText userid, pass;
    ProgressDialog dialog;
    Intent i;
    List<NameValuePair> nameValuePairs;
    Thread t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //setRequestedOrientation(orientation);
        i=getIntent();

        if(!Singleton.getInstance().getfname().equals("")||!Singleton.getInstance().getsname().equals("")){
            Singleton.getInstance().clear();
        }
        if(Singleton.getInstance().getIp().equals("")){
            Intent a = new Intent(this, ipadd.class);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            finish();
        }

        if(!Singleton2.getInstance().getAy().equals("")){
            Singleton2.getInstance().clear2();
        }
        userid = (EditText) findViewById(R.id.usn);
        userid.setText(Singleton.getInstance().getid());
        pass = (EditText) findViewById(R.id.pass);
        Button b = (Button) findViewById(R.id.login);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = ProgressDialog.show(Login.this, "" , "Validating user...", true);
                dialog.setCanceledOnTouchOutside(true);
                t = new Thread(new Runnable() {
                    public void run() {

                        login();

                    }
                });
                t.start();


            }
        });




    }


    public void login() {
        try {

            String url="http://"+Singleton.getInstance().getIp()+"/Teacher/android_login.php";

            nameValuePairs = new ArrayList<NameValuePair>(2);
            String uid = userid.getText().toString();
            Singleton.getInstance().setid(uid);
            String pa = pass.getText().toString();
            nameValuePairs.add(new BasicNameValuePair("username", uid.trim()));
            nameValuePairs.add(new BasicNameValuePair("password", pa.trim()));

            connection c = new connection(nameValuePairs, url);
            final String response = c.getValue();

            runOnUiThread(new Runnable() {
                public void run() {

                    dialog.dismiss();
                }
            });

            if (response.equalsIgnoreCase("User Found")) {
                startActivity(new Intent(Login.this, home.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            } else {
                showAlert();
            }

        } catch (Exception e) {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ipadd.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    public void showAlert(){
        Login.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Login Error.");
                builder.setMessage("User Credentials Error.")
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

   /* @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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