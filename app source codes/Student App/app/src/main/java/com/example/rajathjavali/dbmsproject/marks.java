package com.example.rajathjavali.dbmsproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class marks extends ActionBarActivity {

    String acy , sem ;
    Intent i;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    Thread t;

    public void onBackPressed() {
        Singleton2.getInstance().clear();
        startActivity(new Intent(this,home.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        /*int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);*/
        i = getIntent();
        acy = i.getStringExtra("acy");
        sem = i.getStringExtra("sem");
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }
        String x = Singleton2.getInstance().getNo();
        if (x.equals("")) {

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

        if(!Singleton2.getInstance().getData().equals("")) {
            int j = Integer.parseInt(Singleton2.getInstance().getNo());

            list = new ArrayList<String>();
            String result = Singleton2.getInstance().getData();
            Collections.addAll(list, result.split("-", j));

            LinearLayout l = (LinearLayout) findViewById(R.id.lin);

            RadioGroup rg = new RadioGroup(this);
            final RadioButton[] rb = new RadioButton[j];
            rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.HORIZONTAL
            for (int i = 0; i < j; i++) {
                rb[i] = new RadioButton(this);
                rb[i].setText(list.get(i));
                rb[i].setId(i);
                rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            }
            l.addView(rg);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.topMargin = 20;
            Button b1 = new Button(this);
            b1.setText("Marks");
            b1.setBackgroundColor(Color.TRANSPARENT);
            l.addView(b1, p);
            p.topMargin=5;
            Button b2 = new Button(this);
            b2.setText("Attendance");
            b2.setBackgroundColor(Color.TRANSPARENT);
            l.addView(b2, p);
            Button b3 = new Button(this);
            b3.setText("Contacts");
            b3.setBackgroundColor(Color.TRANSPARENT);
            l.addView(b3, p);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Marks();
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attendance();
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact();
                }
            });

        }
        else {
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);
            TextView t = new TextView(this);
            t.setTextSize(20);
            t.setGravity(Gravity.CENTER_HORIZONTAL);

            t.setText("No Courses Registered");
            Button b1 = new Button(this);
            b1.setText("Back");
            l.addView(t);
            l.addView(b1);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(marks.this, home.class));
                    finish();
                }
            });
        }

    }

    public void course(){
        try {

            String url="http://"+Singleton.getInstance().getIp()+"/Student/android_course_list.php";

            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("usn", Singleton.getInstance().getusn()));
            nameValuePairs.add(new BasicNameValuePair("acy", acy));
            nameValuePairs.add(new BasicNameValuePair("sem", sem));
            connection c = new connection(nameValuePairs,url);
            String response = c.getValue();


            if (response.equals("0")) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(marks.this, "No Data Found !! Retry !", Toast.LENGTH_LONG).show();
                    }
                });
                startActivity(new Intent(this, home.class));
            } else {
                int i = 0;
                String[] result = new String[2];
                for (String retval : response.split("-", 2)) {

                    result[i] = retval;
                    i++;
                }
                Singleton2.getInstance().setNo(result[0]);
                Singleton2.getInstance().setData(result[1]);
                /*startActivity(new Intent(marks.this,marks.class));
                finish();*/

            }

        }
        catch (Exception e) {
            //showAlert();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void showAlert(){
        marks.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(marks.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(marks.this,"Returning To Home",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(marks.this,home.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    public void Marks() {

        LinearLayout l = (LinearLayout) findViewById(R.id.lin);

        View child = l.getChildAt(0);
        if (child instanceof RadioGroup) {
            //Support for RadioGroups
            RadioGroup radio = (RadioGroup) child;

            int rg = radio.getCheckedRadioButtonId();
            if(rg!=-1) {
                if(!Singleton3.getInstance().getName().equals(list.get(rg))){
                    Singleton3.getInstance().clear();
                    Singleton3.getInstance().setName( list.get(rg));
                }
                startActivity(new Intent(this, marksdisp.class));
            }
            else{
                Toast.makeText(this,"Please Select a Subject",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void attendance(){
        LinearLayout l = (LinearLayout) findViewById(R.id.lin);

        View child = l.getChildAt(0);
        if (child instanceof RadioGroup) {
            //Support for RadioGroups
            RadioGroup radio = (RadioGroup) child;

            int rg = radio.getCheckedRadioButtonId();
            if(rg!=-1) {
                if(!Singleton3.getInstance().getName().equals(list.get(rg))){
                    Singleton3.getInstance().clear();
                    Singleton3.getInstance().setName( list.get(rg));
                }

                startActivity(new Intent(this, attenddisp.class));
            }
            else{
                Toast.makeText(this,"Please Select a Subject",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void contact(){
        LinearLayout l = (LinearLayout) findViewById(R.id.lin);

        View child = l.getChildAt(0);
        if (child instanceof RadioGroup) {
            //Support for RadioGroups
            RadioGroup radio = (RadioGroup) child;

            int rg = radio.getCheckedRadioButtonId();
            if(rg!=-1) {
                if(!Singleton3.getInstance().getName().equals(list.get(rg))){
                    Singleton3.getInstance().clear();
                    Singleton3.getInstance().setName( list.get(rg));
                }

                startActivity(new Intent(this, contact.class));
            }
            else{
                Toast.makeText(this,"Please Select a Subject",Toast.LENGTH_SHORT).show();
            }
        }
    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marks, menu);
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
