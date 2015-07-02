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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class attenddisp extends ActionBarActivity {

    Intent i;
    String usn,acy,sem,cname;
    TextView t,per,att,tot;
    String filename;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    Thread th;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //finish();
        startActivity(new Intent(this,marks.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attenddisp);
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
        t.setGravity(Gravity.CENTER_HORIZONTAL);
        t.setText(cname + " Attendance");
        per = (TextView) findViewById(R.id.per);
        att = (TextView) findViewById(R.id.attend);
        tot = (TextView) findViewById(R.id.tot);
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
        if(!Singleton3.getInstance().getData().equals("")) {
            list = new ArrayList<String>();
            Collections.addAll(list, Singleton3.getInstance().getData().split("-", 11));

            if (list.size()==11 && !list.isEmpty()) {
                att.setText(list.get(8));
                tot.setText(list.get(9));
                per.setText(list.get(10) + "%");
            }
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
                if (!Singleton3.getInstance().getData().equals(response)) {
                    Singleton3.getInstance().setData(response);
                    /*startActivity(new Intent(this,attenddisp.class));
                    finish();*/
                }

        }catch (IOException e){
            showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void showAlert(){
        attenddisp.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(attenddisp.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(attenddisp.this, "Returning To Home", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(attenddisp.this,home.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void done(View v){
        startActivity(new Intent(this, marks.class));
        finish();

    }
    public void details(View v){
        startActivity(new Intent(this,details.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attenddisp, menu);
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
            startActivity(new Intent(this,attenddisp.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
