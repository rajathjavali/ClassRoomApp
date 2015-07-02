package com.example.rajathjavali.dbmsprojectstaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class marksstud extends ActionBarActivity {
    List<NameValuePair> nameValuePairs;
    List<String> list,list2;
    Button b2;
    TextView disp;
    Thread t;
    String no,studentlist;
    String test,s;
    int i;
    Intent i1;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, testpicker.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendstud);
        i1=getIntent();
        test = i1.getStringExtra("test");
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
            t.setText(Singleton3.getInstance().getName()+" Marks");
        }
        no = studentlist = "";

        if(no.equals("")) {
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
        if(!studentlist.equals("")) {
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);
            int j = Integer.parseInt(no);
            list = new ArrayList<String>();
            list2 = new ArrayList<String>();
            if(j>1) {
                Collections.addAll(list, studentlist.split("-", j));
                for(i=0;i<j;i++){

                    for(String ret: list.get(i).split(" ",2)){
                        list2.add(ret);break;
                    }
                }
            }
            else
            {
                for(String ret: studentlist.split(" ", 2)){
                    list2.add(ret);break;
                }
            }

            LinearLayout t ;
            LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,0.75f);
            p1.topMargin=20;
            int maxLength = 2;

            TextView r;
            switch (test) {
                case "1":
                case "2":
                case "3":
                    disp = new TextView(this);
                    disp.setText("USN --- Theory --- Quiz");
                    disp.setTextColor(Color.WHITE);
                    disp.setGravity(Gravity.CENTER);
                    disp.setTextSize(15);
                    l.addView(disp,p1);
                    for (int i = 0; i < j; i++) {
                        t = new LinearLayout(this);
                        t.setWeightSum(2.5f);
                        t.setGravity(Gravity.CENTER);
                        r = new TextView(this);
                        p.topMargin = 20;
                        r.setTextColor(Color.WHITE);
                        p.gravity = Gravity.CENTER;
                        r.setText(list2.get(i));
                        //r.setWidth(400);
                        r.setTextSize(15);
                        t.addView(r, p);

                        EditText e1 = new EditText(this);
                        e1.setHint("Text(50)");
                        e1.setTextSize(15);
                        e1.setTextColor(Color.WHITE);
                        e1.setHintTextColor(Color.WHITE);
                        e1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new InputFilterMinMax("0", "50")});
                        e1.setInputType(InputType.TYPE_CLASS_NUMBER);
                        e1.setGravity(Gravity.CENTER);
                        e1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        e1.setSingleLine(true);
                        //e1.setMaxWidth(2);
                        t.addView(e1, p);

                        EditText e2 = new EditText(this);
                        e2.setHint("Quiz(15)");
                        e2.setTextSize(15);
                        e2.setTextColor(Color.WHITE);
                        e2.setHintTextColor(Color.WHITE);

                        e2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new InputFilterMinMax("0", "15")});
                        e2.setInputType(InputType.TYPE_CLASS_NUMBER);
                        e2.setGravity(Gravity.CENTER);
                        //e2.setMaxWidth(2);
                        e2.setSingleLine(true);
                        if (i != j - 1)
                            e2.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        t.addView(e2, p);
                        l.addView(t, p1);
                    }
                    break;
                case "4": {
                    disp = new TextView(this);
                    disp.setText("USN --- Lab");
                    disp.setTextColor(Color.WHITE);
                    disp.setGravity(Gravity.CENTER);
                    disp.setTextSize(15);
                    l.addView(disp,p1);

                    for (int i = 0; i < j; i++) {
                        t = new LinearLayout(this);
                        t.setWeightSum(1.5f);
                        t.setGravity(Gravity.CENTER);
                        r = new TextView(this);
                        p.topMargin = 20;
                        r.setTextColor(Color.WHITE);
                        p.gravity = Gravity.CENTER;
                        r.setText(list2.get(i));
                        //r.setWidth(300);
                        r.setTextSize(15);
                        t.addView(r, p);

                        EditText e1 = new EditText(this);
                        e1.setHint("Lab(50)");
                        e1.setTextSize(15);
                        e1.setTextColor(Color.WHITE);
                        e1.setHintTextColor(Color.WHITE);
                        e1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new InputFilterMinMax("0", "50")});
                        e1.setInputType(InputType.TYPE_CLASS_NUMBER);
                        e1.setGravity(Gravity.CENTER);
                        e1.setSingleLine(true);
                        t.addView(e1, p);
                        l.addView(t,p1);
                    }

                    break;
                }
                default: {
                    disp = new TextView(this);
                    disp.setText("USN --- Assignment");
                    disp.setTextColor(Color.WHITE);
                    disp.setGravity(Gravity.CENTER);
                    disp.setTextSize(15);
                    l.addView(disp,p1);

                    for (int i = 0; i < j; i++) {
                        t = new LinearLayout(this);
                        t.setWeightSum(1.5f);
                        t.setGravity(Gravity.CENTER);
                        r = new TextView(this);
                        r.setTextColor(Color.WHITE);
                        p.topMargin = 20;

                        p.gravity = Gravity.CENTER;
                        r.setText(list2.get(i));
                        //r.setWidth(400);
                        r.setTextSize(15);
                        t.addView(r, p);

                        EditText e1 = new EditText(this);
                        e1.setHint("Assign(20)");
                        e1.setTextSize(15);
                        e1.setTextColor(Color.WHITE);
                        e1.setHintTextColor(Color.WHITE);
                        e1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength), new InputFilterMinMax("0", "20")});
                        e1.setInputType(InputType.TYPE_CLASS_NUMBER);
                        e1.setGravity(Gravity.CENTER);
                        e1.setSingleLine(true);
                        t.addView(e1, p);
                        l.addView(t,p1);
                    }
                    break;
                }
            }

        }
        else{
            Toast.makeText(this,"No Students",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,course.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }

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
                no = list.get(0);
                studentlist = list.get(1);

            }
            else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(marksstud.this,response + " in this course",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(marksstud.this,course.class));
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
            String url = "http://" + Singleton.getInstance().getIp() + "/Teacher/marks.php";
            LinearLayout l = (LinearLayout) findViewById(R.id.lin);
            List<NameValuePair> list = new ArrayList<NameValuePair>(2);
            List<String> List2 = new ArrayList<>();
            int studcount = 0;
            //String s ="";
            for (int i = 0; i < l.getChildCount(); i++) {
                View child = l.getChildAt(i);
                if (child instanceof LinearLayout) {

                    for(int j = 0 ; j<((LinearLayout) child).getChildCount();j++){
                        View child2 = ((LinearLayout) child).getChildAt(j);


                        if (child2 instanceof EditText) {
                            EditText st = (EditText) child2;
                            if(!st.getText().toString().equals("")) {
                                List2.add(st.getText().toString());
                                //s+=st.getText().toString()+" ";
                            }
                            else {
                                List2.add("na");
                                //s+="na ";
                            }
                        }
                        else if (child2 instanceof TextView) {
                            studcount++;
                            list.add(new BasicNameValuePair("usn" + studcount, ((TextView) child2).getText().toString()));
                            //s+=((TextView) child2).getText().toString()+" ";
                        }
                    }
                }

            }
            /*final String s1 = s;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(marksstud.this,s1,Toast.LENGTH_SHORT).show();
                }
            });*/
            list.add(new BasicNameValuePair("no",""+studcount+""));

            if(test.equals("1")||test.equals("2")||test.equals("3")){
                int k=1;
                for(int i = 0 ; i < 2*studcount ; i=i+2){
                    list.add(new BasicNameValuePair("test"+k,List2.get(i)));
                    list.add(new BasicNameValuePair("quiz"+k,List2.get(i+1)));
                    k++;
                }
            }
            else {

                for (int i = 0; i < studcount; i++) {
                    list.add(new BasicNameValuePair("test" + (i+1), List2.get(i)));
                }
            }
            list.add(new BasicNameValuePair("acy",Singleton2.getInstance().getAy()));
            list.add(new BasicNameValuePair("sem",Singleton2.getInstance().getSem()));
            list.add(new BasicNameValuePair("sec",Singleton2.getInstance().getSec()));
            list.add(new BasicNameValuePair("cname",Singleton3.getInstance().getName()));
            list.add(new BasicNameValuePair("type",test));
            connection c = new connection(list , url);
            final String response = c.getValue();

            if(response.equalsIgnoreCase("Successful")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(marksstud.this,response,Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(marksstud.this, testpicker.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            }
            else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(marksstud.this,response + " Retry!",Toast.LENGTH_SHORT).show();
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
       marksstud.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(marksstud.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(marksstud.this, "Retry Later", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(marksstud.this,home.class));
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
