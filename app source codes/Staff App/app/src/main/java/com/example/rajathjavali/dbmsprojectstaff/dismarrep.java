package com.example.rajathjavali.dbmsprojectstaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class dismarrep extends ActionBarActivity {

    Thread t;
    String filename;
    String acy, sem, cname;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    ListView l1;
    String value ;
    int no;
    ArrayAdapter<String> adapter;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, course.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dismarrep);
        if (Singleton.getInstance().getIp().equals("")) {
            startActivity(new Intent(this, ipadd.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }

        list = new ArrayList<>();
        l1 = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this , R.layout.list_item,list);
        l1.setAdapter(adapter);
        acy = Singleton2.getInstance().getAy();
        sem = Singleton2.getInstance().getSem();
        cname = Singleton3.getInstance().getName();

        no = 0;

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

            if (no > 1) {
                Collections.addAll(list, value.split("-", no));
            } else {
                list.add(value);
            }
            adapter.notifyDataSetChanged();
            l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View itemClicked,
                                        final int position, long id) {
                    t = new Thread(new Runnable() {
                        public void run() {

                            query2(list.get(position));

                        }
                    });t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
               }});
        }
        else{
            Toast.makeText(dismarrep.this,"No. is zero",Toast.LENGTH_SHORT).show();
            list.add("No Students");
            adapter.notifyDataSetChanged();


        }
    }

    public void query() {
        try {
            filename = "http://" + Singleton.getInstance().getIp() + "/Teacher/android_course_stud_retrieve.php";
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("id", Singleton.getInstance().getid()));
            nameValuePairs.add(new BasicNameValuePair("cname", cname));
            nameValuePairs.add(new BasicNameValuePair("acy", acy));
            nameValuePairs.add(new BasicNameValuePair("sec",Singleton2.getInstance().getSec()));
            nameValuePairs.add(new BasicNameValuePair("sem", sem));
            connection c = new connection(nameValuePairs, filename);
            final String response = c.getValue();
            if (response.equals("0")) {
                no = 1;
                value = "No Students";
            } else {
                List<String> list2 = new ArrayList<>();
                Collections.addAll(list2, response.split("-", 2));
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

    public void query2(String name){
        try {
            List x = new ArrayList();
            Collections.addAll(x,name.split(" ",2));
            filename = "http://" + Singleton.getInstance().getIp() + "/Student/android_course_marks.php";
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("usn", x.get(0).toString()));
            nameValuePairs.add(new BasicNameValuePair("cname", cname));
            nameValuePairs.add(new BasicNameValuePair("acy", acy));
            nameValuePairs.add(new BasicNameValuePair("sem", sem));
            connection c = new connection(nameValuePairs, filename);
            final String response = c.getValue();
            x = new ArrayList();
            Collections.addAll(x,response.split("-",11));
            disp(x);

        }catch (IOException e) {
            showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void disp(List a){

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , R.layout.list_item1);
        arrayAdapter.add("Lab   : "+a.get(0));
        arrayAdapter.add("Test 1: "+a.get(1));
        arrayAdapter.add("Quiz 1: "+a.get(2));
        arrayAdapter.add("Test 2: "+a.get(3));
        arrayAdapter.add("Quiz 2: "+a.get(4));
        arrayAdapter.add("Test 3: "+a.get(5));
        arrayAdapter.add("Quiz 3: "+a.get(6));
        arrayAdapter.add("Assign: "+a.get(7));

        dismarrep.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(dismarrep.this, R.style.ListRow));
                TextView t = new TextView(dismarrep.this);
                t.setText("Marks Card");
                t.setTextSize(20);
                t.setBackgroundColor(Color.parseColor("#FF0A5A35"));
                t.setTextColor(Color.WHITE);
                t.setGravity(Gravity.CENTER);
                builder.setCustomTitle(t);
                builder.setAdapter(arrayAdapter, null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void showAlert() {
        dismarrep.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(dismarrep.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(dismarrep.this, "Returning To Home", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(dismarrep.this, home.class));
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
