package com.example.rajathjavali.dbmsproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class details extends ActionBarActivity {
    Intent i;
    Thread t;
    String filename;
    String usn,acy,sem,cname,value;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    int no;
    int status;
    public void onBackPressed() {
        //super.onBackPressed();
        //finish();
        startActivity(new Intent(this,attenddisp.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        /*int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);*/
        i=getIntent();
        filename = "http://"+Singleton.getInstance().getIp()+"/Student/android_course_attendance_details.php";
        usn = Singleton.getInstance().getusn();
        acy = Singleton2.getInstance().getAy();
        sem = Singleton2.getInstance().getSem();
        cname = Singleton3.getInstance().getName();
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }
        no=0;
        t = new Thread(new Runnable() {
            public void run() {
                query();
            }
        });t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(no!=0) {
            list = new ArrayList<String>();
            if(no>1) {
                Collections.addAll(list, value.split("\\$", no));
            }
            else
            {
                list.add(value);
            }
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.topMargin=30;
            if (!list.isEmpty()) {
                for(int i=0;i<list.size();i++){
                    TextView t = new TextView(this);
                    t.setText(list.get(i));
                    t.setTextSize(15);
                    l.addView(t,p);
                }
            }

        }
    }


    public void query(){
        try{

            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("cname", cname));
            nameValuePairs.add(new BasicNameValuePair("acy", acy));
            nameValuePairs.add(new BasicNameValuePair("sem", sem));
            nameValuePairs.add(new BasicNameValuePair("usn", usn));
            connection c = new connection(nameValuePairs, filename);
            final String response = c.getValue();
            if(response.equals("0")){
                no=1;
                value = "No Classes were held";
            }
            else{
                List<String> list2 = new ArrayList<>();
                Collections.addAll(list2, response.split("\\$", 2));
                if(list2.size()==2) {

                    no = Integer.parseInt(list2.get(0));
                    value = list2.get(1);
                }

            }

        }catch (IOException e){
            showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert(){
        details.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(details.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(details.this, "Returning To Home", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(details.this,home.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
