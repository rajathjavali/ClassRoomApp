package com.example.rajathjavali.dbmsprojectstaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class postit extends ActionBarActivity {

    EditText n;
    List<NameValuePair> nameValuePairs;
    String date,time;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postit);
        if(Singleton.getInstance().getIp().equals("")){
            Intent a = new Intent(this, ipadd.class);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
    }

    public void post(View v)
    {
        n = (EditText) findViewById(R.id.notice);
        if(!n.getText().toString().equals("")) {
            Thread t;
            t = new Thread(new Runnable() {
                public void run() {

                    query();

                }
            });
            t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void query(){
        try{
            String url = "http://" + Singleton.getInstance().getIp() + "/Teacher/postit.php";
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
            date = df.format(c1.getTime());
            time=df2.format(c1.getTime());
            nameValuePairs = new ArrayList<>(2);

            nameValuePairs.add(new BasicNameValuePair("info",n.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("id",Singleton.getInstance().getid()));
            nameValuePairs.add(new BasicNameValuePair("date",date));
            nameValuePairs.add(new BasicNameValuePair("time",time));
            connection c = new connection(nameValuePairs,url);
            final String response = c.getValue();

            if(response.equals("Successful")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(postit.this,"Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(postit.this, home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();

                    }
                });

            }
            else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(postit.this,"Error",Toast.LENGTH_SHORT).show();

                    }
                });
            }


        }
        catch (Exception e)
        {
            showAlert2();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert2(){
        postit.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(postit.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(postit.this, "Retry Later", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(postit.this,home.class));
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}
