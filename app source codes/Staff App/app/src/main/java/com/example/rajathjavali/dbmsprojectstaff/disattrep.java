package com.example.rajathjavali.dbmsprojectstaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class disattrep extends ActionBarActivity {

    Intent i;
    Thread t;
    String filename;
    String acy, sem,sec, cname, month, year, value, datef;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    int no;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, course.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disattrep);
        i = getIntent();
        filename = "http://" + Singleton.getInstance().getIp() + "/Teacher/statistics.php";
        acy = Singleton2.getInstance().getAy();
        sem = Singleton2.getInstance().getSem();
        sec  = Singleton2.getInstance().getSec();
        cname = Singleton3.getInstance().getName();
        month = i.getStringExtra("month");//"Apr" Singleton2.getInstance().getMonth();
        year = i.getStringExtra("year"); //"2015" Singleton2.getInstance().getYear();

        if (Singleton.getInstance().getIp().equals("")) {
            startActivity(new Intent(this, ipadd.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
        no = 0;
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
        if (no != 0) {
            list = new ArrayList<String>();
            if (no > 1) {
                Collections.addAll(list, value.split("\\$", no));
            } else {
                list.add(value);
            }
            TextView a = (TextView) findViewById(R.id.header);
            a.setText(cname+" "+month+"-"+year);
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            p.topMargin = 30;
            p.gravity= Gravity.CENTER;
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    TextView t2 = new TextView(this);
                    if(!list.get(i).equals("")&&!list.get(i).equals("No Classes Held"))
                        t2.setText(list.get(i)+"%");
                    else
                        t2.setText(list.get(i));
                    t2.setTextColor(Color.WHITE);
                    t2.setTextSize(15);
                    l.addView(t2, p);
                }
            }
        }
        else{
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);
            TextView v = new TextView(this);
            v.setGravity(Gravity.CENTER);
            v.setTextColor(Color.WHITE);
            v.setText("No entries");
            l.addView(v);

        }
    }
    public void query() {
        try {
            datef = month+"-"+year;
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(disattrep.this,datef,Toast.LENGTH_SHORT).show();
                }
            });*/
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("cname", cname));
            nameValuePairs.add(new BasicNameValuePair("acy", acy));
            nameValuePairs.add(new BasicNameValuePair("sem", sem));
            nameValuePairs.add(new BasicNameValuePair("sec", sec));
            nameValuePairs.add(new BasicNameValuePair("cdate", datef));
            connection c = new connection(nameValuePairs, filename);
            final String response = c.getValue();
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(disattrep.this, response, Toast.LENGTH_SHORT).show();
                }
            });*/
            if (response.equals("0")) {
                no = 1;
                value = "No Classes Held";
            } else {
                List<String> list2 = new ArrayList<>();
                Collections.addAll(list2, response.split("\\$", 2));
                if (list2.size() == 2) {

                    no = Integer.parseInt(list2.get(0));
                    value = list2.get(1);
                }

            }

        } catch (IOException e) {
            showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void showAlert() {
        disattrep.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(disattrep.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(disattrep.this, "Returning To Home", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(disattrep.this, home.class));
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
