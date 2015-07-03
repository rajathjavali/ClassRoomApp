package com.example.rajathjavali.dbmsproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class marksdisp extends ActionBarActivity {

    Intent i;
    String usn,acy,sem,cname;
    TextView t,lab,t1,q1,t2,q2,t3,q3,assign;
    String filename;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    Thread th ;
    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,marks.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marksdisp);
        /*int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);*/
        i=getIntent();
        filename = "http://"+Singleton.getInstance().getIp()+"/Student/android_course_marks.php";
        usn = Singleton.getInstance().getusn();
        acy = Singleton2.getInstance().getAy();
        sem = Singleton2.getInstance().getSem();
        cname = Singleton3.getInstance().getName();
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }


        t = (TextView) findViewById(R.id.disp);
        //t.setGravity(Gravity.CENTER_HORIZONTAL);
        t.setText(cname + " Marks");
        lab = (TextView) findViewById(R.id.lab);
        t1 = (TextView) findViewById(R.id.t1);
        q1 = (TextView) findViewById(R.id.q1);
        t2 = (TextView) findViewById(R.id.t2);
        q2 = (TextView) findViewById(R.id.q2);
        t3 = (TextView) findViewById(R.id.t3);
        q3 = (TextView) findViewById(R.id.q3);
        assign = (TextView) findViewById(R.id.assign);


        if(Singleton3.getInstance().getData().equals("")) {
            th = new Thread(new Runnable() {
                public void run() {
                    disp();
                }
            });th.start();

            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        list = new ArrayList<String>();
        Collections.addAll(list, Singleton3.getInstance().getData().split("-", 11));

        if (!list.isEmpty()) {
            lab.setText(list.get(0));
            t1.setText(list.get(1));
            q1.setText(list.get(2));
            t2.setText(list.get(3));
            q2.setText(list.get(4));
            t3.setText(list.get(5));
            q3.setText(list.get(6));
            assign.setText(list.get(7));
        }


    }

    public void disp(){
        try{

            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("cname", cname));
            nameValuePairs.add(new BasicNameValuePair("acy", acy));
            nameValuePairs.add(new BasicNameValuePair("sem", sem));
            nameValuePairs.add(new BasicNameValuePair("usn", usn));
            connection c = new connection(nameValuePairs, filename);
            final String response = c.getValue();

            Singleton3.getInstance().setData(response);
            /*startActivity(new Intent(this,marksdisp.class));
            finish();*/


        }
        catch (IOException e){
            showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }


    public void done(View v){
        startActivity(new Intent(this, marks.class));
        finish();

    }

    public void showAlert(){
        marksdisp.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(marksdisp.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(marksdisp.this,"Returning To Home",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(marksdisp.this,home.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marksdisp, menu);
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
        if (id==R.id.refresh){
            Singleton3.getInstance().cleardata();
            startActivity(new Intent(this,marksdisp.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
