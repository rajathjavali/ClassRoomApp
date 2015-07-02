package com.example.rajathjavali.dbmsprojectstaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class attendstud extends ActionBarActivity {
    List<NameValuePair> nameValuePairs;
    List<String> list;
    Button b2;
    Thread t;
    String date,time;
    int i;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, course.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendstud);
        //int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //setRequestedOrientation(orientation);
        if(Singleton.getInstance().getIp().equals("")){
            Intent a = new Intent(this, ipadd.class);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
        b2 = (Button) findViewById(R.id.go);
        if(!Singleton3.getInstance().getName().equals("")){
            TextView t = (TextView) findViewById(R.id.disp);
            t.setText(Singleton3.getInstance().getName()+" Attendance");
        }
        if(Singleton3.getInstance().getData().equals("")) {
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

        }
        if(!Singleton3.getInstance().getData().equals("")) {
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);
            int j = Integer.parseInt(Singleton3.getInstance().getNo());

            list = new ArrayList<String>();
            String result = Singleton3.getInstance().getData();
            Collections.addAll(list, result.split("-", j));

            for (int i = 0; i < j; i++) {

                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                //p.leftMargin=300;
                //p.width=850;
                p.topMargin = 10;
                //p.leftMargin = 10;
                p.height = 60;


                Switch st = new Switch(this);
                st.setEnabled(true);
                st.setId(i + 10);
                st.setTextColor(Color.WHITE);
                st.setText(list.get(i).trim());
                st.setTextSize(15);

                l.addView(st, p);

            }
        }
        /*else{
            Toast.makeText(this,"No Students",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,home.class));
            finish();
        }*/

        i=0;
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = new Thread(new Runnable() {
                    public void run() {

                        query2();

                    }
                });
                i=1;
                t.start();


            }
        });
        if(i==1){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void query(){
        try{
            String url = "http://" + Singleton.getInstance().getIp() + "/Teacher/android_course_stud_retrieve.php";
            nameValuePairs = new ArrayList<>(2);
            nameValuePairs.add(new BasicNameValuePair("id", Singleton.getInstance().getid()));
            nameValuePairs.add(new BasicNameValuePair("acy",Singleton2.getInstance().getAy()));
            nameValuePairs.add(new BasicNameValuePair("sem",Singleton2.getInstance().getSem()));
            nameValuePairs.add(new BasicNameValuePair("sec",Singleton2.getInstance().getSec()));
            nameValuePairs.add(new BasicNameValuePair("cname",Singleton3.getInstance().getName()));
            connection c = new connection(nameValuePairs, url);
            final String response = c.getValue();

            if (!response.equalsIgnoreCase("No Students")) {
                List<String> list = new ArrayList<String>();
                Collections.addAll(list, response.split("-", 2));
                Singleton3.getInstance().setNo(list.get(0));
                Singleton3.getInstance().setData(list.get(1));
                /*startActivity(new Intent(attendstud.this, attendstud.class));
                finish();*/
            }
            else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(attendstud.this,response + " in this course",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(attendstud.this,course.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
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
    public void query2(){
        try{
            //String s = "";
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);
            List<String> list = new ArrayList<>();
            //s+=""+l.getChildCount()+"";
            list.add(""+l.getChildCount()+"");
            for (int i = 0; i < l.getChildCount(); i++) {
                View child = l.getChildAt(i);
                if (child instanceof Switch) {
                    Switch st = (Switch) child;
                    for(String retval:st.getText().toString().split(" ",2)){
                        //s+=retval;
                        list.add(retval);break;
                    }
                    if (st.isChecked()) {
                        //s+="P";
                        list.add("P");
                    }
                    else{
                        //s+="A";
                        list.add("A");
                    }
                }
                //s+=" ";
            }
            //listname.getInstance().setVal(list);
            //startActivity(new Intent(attendstud.this,update.class));
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
            date = df.format(c1.getTime());
            time=df2.format(c1.getTime());
            /*final String s1 =s ;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(attendstud.this, s1, Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(update.this,course.class));
                    //finish();
                }
            });*/
            String url = "http://" + Singleton.getInstance().getIp() + "/Teacher/attendance.php";
            nameValuePairs = new ArrayList<>(2);
            nameValuePairs.add(new BasicNameValuePair("acy",Singleton2.getInstance().getAy()));
            nameValuePairs.add(new BasicNameValuePair("sem",Singleton2.getInstance().getSem()));
            nameValuePairs.add(new BasicNameValuePair("no", list.get(0)));
            nameValuePairs.add(new BasicNameValuePair("cname",Singleton3.getInstance().getName()));
            nameValuePairs.add(new BasicNameValuePair("date",date));
            nameValuePairs.add(new BasicNameValuePair("time",time));

            int j = Integer.parseInt(list.get(0));
            int k=0;
            for(int i = 1 ; i < 2*j ; i=i+2 ){
                nameValuePairs.add(new BasicNameValuePair("usn"+k,list.get(i)));
                nameValuePairs.add(new BasicNameValuePair("status"+k,list.get(i+1)));
                k++;
            }
            connection c = new connection(nameValuePairs, url);
            final String response = c.getValue();

            if (response.contains("Successful")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(attendstud.this,"Done",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(attendstud.this, course.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                    }
                });
            }
            else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(attendstud.this,"Error! Try Again Later",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(attendstud.this,course.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                    }
                });
            }


        }
        catch (Exception e)
        {
            System.out.println("Exception : " + e.getMessage());
        }
    }



    public void showAlert2(){
       attendstud.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(attendstud.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(attendstud.this, "Retry Later", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(attendstud.this,home.class));
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    /*public void attendance(View v){

        if(b2.getText().equals("Back")){
            startActivity(new Intent(this,course.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
        else {
            //String s ="";

           *//* Toast.makeText(this, s, Toast.LENGTH_SHORT).show();*//*
        }

    }
*/

}
