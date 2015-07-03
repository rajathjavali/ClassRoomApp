package com.example.rajathjavali.dbmsproject;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class contact extends ActionBarActivity {
    Intent i;
    String usn, acy, sem, cname;
    String filename;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    Thread t;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, marks.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        /*int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);*/
        i = getIntent();
        filename= "http://" + Singleton.getInstance().getIp() + "/Student/contact.php";
        cname = Singleton3.getInstance().getName();
        acy = Singleton2.getInstance().getAy();
        sem = Singleton2.getInstance().getSem();
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }


        if (Singleton3.getInstance().getData().equals("")) {

            t = new Thread(new Runnable() {
                public void run() {
                    course();
                }
            });t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        if(!Singleton3.getInstance().getCno().equals("")) {
            int j = Integer.parseInt(Singleton3.getInstance().getCno());


            list = new ArrayList<String>();
            String result = Singleton3.getInstance().getData();
            Collections.addAll(list, result.split("-", j));

            LinearLayout l = (LinearLayout) findViewById(R.id.linear);
            final Button[] tv = new Button[j];


            for (int i = 0; i < j - 1; i = i + 2) {
                /*LinearLayout a = new LinearLayout(this);
                a.setOrientation(LinearLayout.HORIZONTAL);*/
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                p.topMargin = 20;
                p.height = 250;
                p.gravity = Gravity.CENTER;


                tv[i] = new Button(this);
                tv[i].setText(list.get(i).toUpperCase());
                tv[i].setTextSize(15);

                tv[i].setId(i);
                l.addView(tv[i], p);
                final int z = i;
                tv[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Make Call", "");
                        String phno = "tel:" + list.get(z+1);//tv[z + 1];
                        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                        phoneIntent.setData(Uri.parse(phno));
                        try {
                            startActivity(phoneIntent);
                            finish();
                            Log.i("Finished of making call", "");
                        } catch (ActivityNotFoundException ex) {
                            Toast.makeText(contact.this, "Call failed try again later", Toast.LENGTH_LONG).show();

                        }
                    }

                });
            }

        }else {
            LinearLayout l = (LinearLayout) findViewById(R.id.linear);
            TextView t = new TextView(this);
            t.setText("No Contacts");
            t.setTextSize(20);
            l.addView(t);
        }

    }


    public void course() {
        try {


            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("usn", Singleton.getInstance().getusn()));
            nameValuePairs.add(new BasicNameValuePair("acy", acy));
            nameValuePairs.add(new BasicNameValuePair("sem", sem));
            nameValuePairs.add(new BasicNameValuePair("cname",Singleton3.getInstance().getName()));
            connection c = new connection(nameValuePairs, filename);
            String response = c.getValue();


            if (response.equals("0")) {

                /*runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(contact.this, "No Data Found !! ", Toast.LENGTH_LONG).show();
                    }
                });
                startActivity(new Intent(this, marks.class));*/
            } else {
                int i = 0;
                String[] result = new String[2];
                for (String retval : response.split("-", 2)) {

                    result[i] = retval;
                    i++;
                }
                Singleton3.getInstance().setCno(result[0]);
                Singleton3.getInstance().setData(result[1]);
                /*startActivity(new Intent(contact.this, contact.class));
                finish();*/

            }

        }  catch (Exception e) {
            showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void showAlert() {
        contact.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(contact.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(contact.this, "Returning To Home", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(contact.this, home.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
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










