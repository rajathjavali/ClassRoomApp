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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class course extends ActionBarActivity {

    EditText acy,sem,sec;
    Button b;
    List<NameValuePair> nameValuePairs;
    List<String> list;
    Thread t;
    //int  i;

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,home.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
//        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//        setRequestedOrientation(orientation);

        acy = (EditText) findViewById(R.id.acy);
        sem = (EditText) findViewById(R.id.sem);
        sem.setFilters(new InputFilter[]{new InputFilterMinMax("1", "8")});
        sec = (EditText) findViewById(R.id.sec);
        b = (Button) findViewById(R.id.submit);

        if (Singleton.getInstance().getIp().equals("")) {
            Intent a = new Intent(this, ipadd.class);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
        if (!Singleton2.getInstance().getAy().equals("") && !Singleton2.getInstance().getSem().equals("")
                && !Singleton2.getInstance().getSec().equals("")) {
            acy.setText(Singleton2.getInstance().getAy());
            sem.setText(Singleton2.getInstance().getSem());
            sec.setText(Singleton2.getInstance().getSec());
        }



        //if (Singleton2.getInstance().getNo().equals("")) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = new Thread(new Runnable() {
                    public void run() {

                        courselist();

                    }
                });

                t.start();
                try {

                    t.join();
                } catch (InterruptedException e) {
                    System.out.println("Exception : " + "error here");
                    //e.printStackTrace();
                }

            }
        });

        //if (!Singleton2.getInstance().getNo().equals("")) {
            //Toast.makeText(this, Singleton2.getInstance().getData().toString(), Toast.LENGTH_SHORT).show();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin);
        if (!Singleton2.getInstance().getNo().equals("")) {
            int j = Integer.parseInt(Singleton2.getInstance().getNo());

            list = new ArrayList<String>();
            String result = Singleton2.getInstance().getData();
            Collections.addAll(list, result.split("-", j));


            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            p.topMargin = 40;
            p.gravity=Gravity.CENTER;
            RadioGroup rg = new RadioGroup(this);
            final RadioButton[] rb = new RadioButton[j];
            rg.setOrientation(LinearLayout.VERTICAL);
            rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.HORIZONTAL
            for (int i = 0; i < j; i++) {
                rb[i] = new RadioButton(this);
                rb[i].setText(list.get(i));
                rb[i].setId(i);
                rb[i].setTextColor(Color.WHITE);
                rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            }
            l.addView(rg,p);
            Button b = (Button) findViewById(R.id.go);
            b.setVisibility(View.VISIBLE);
            Button b1 = (Button) findViewById(R.id.marks);
            b1.setVisibility(View.VISIBLE);
            Button b2 = (Button) findViewById(R.id.gorep);
            b2.setVisibility(View.VISIBLE);
            Button b3 = (Button) findViewById(R.id.marksrep);
            b3.setVisibility(View.VISIBLE);


        } else if(!Singleton2.getInstance().getAy().equals("")){
            TextView t = new TextView(this);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            p.gravity= Gravity.CENTER_HORIZONTAL;
            p.topMargin=30;
            t.setText("No Courses");
            t.setTextColor(Color.WHITE);
            t.setTextSize(20);
            l.addView(t,p);
            Button b1 = (Button) findViewById(R.id.marks);
            Button b = (Button) findViewById(R.id.go);
            b.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            Button b2 = (Button) findViewById(R.id.gorep);
            b2.setVisibility(View.INVISIBLE);
            Button b3 = (Button) findViewById(R.id.marksrep);
            b3.setVisibility(View.INVISIBLE);
        }
        //}
    }
    public void marks(View v){
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

                startActivity(new Intent(this, testpicker.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
            else{
                Toast.makeText(this,"Please Select a Subject",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void go(View v){
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

                startActivity(new Intent(this, attendstud.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
            else{
                Toast.makeText(this,"Please Select a Subject",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void gorep(View v){
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


                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Details Required");
                final LinearLayout a = new LinearLayout(this);
                a.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                p.topMargin=20;
                p.gravity=Gravity.CENTER_HORIZONTAL;
                p.gravity=Gravity.CENTER;
                LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                p2.leftMargin = 20;
                p2.width=180;

                final LinearLayout a1 = new LinearLayout(this);
                a1.setOrientation(LinearLayout.HORIZONTAL);
                final TextView t = new TextView(this);
                t.setText("Month");
                t.setTextSize(20);
                int maxLength = 3;
                a1.addView(t,p2);
                final EditText i = new EditText(this);
                i.setHint("Jan");
                i.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                i.setGravity(Gravity.CENTER);
                i.setInputType(InputType.TYPE_CLASS_TEXT);
                i.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                a1.addView(i,p2);
                a.addView(a1,p);

                final LinearLayout a2 = new LinearLayout(this);
                a2.setOrientation(LinearLayout.HORIZONTAL);
                final TextView t2 = new TextView(this);
                t2.setText("Year");
                t2.setTextSize(20);
                t2.setInputType(InputType.TYPE_CLASS_NUMBER);
                a2.addView(t2,p2);
                maxLength=4;
                final EditText i2 = new EditText(this);
                i2.setHint("2015");
                i2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

                i2.setGravity(Gravity.CENTER);
                i2.setInputType(InputType.TYPE_CLASS_NUMBER);
                a2.addView(i2, p2);
                a.addView(a2,p);
                alert.setView(a);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String month = "" , year = "";
                        month = i.getText().toString();
                        year = i2.getText().toString();
                        if(!(month.equals("")||year.equals(""))) {

                            Intent x = new Intent(course.this, disattrep.class);
                            x.putExtra("month", month);
                            x.putExtra("year", year);
                            startActivity(x);
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();
            }
            else{
                Toast.makeText(this,"Please Select a Subject",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void marksrep(View v){
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

                startActivity(new Intent(this, dismarrep.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
            else{
                Toast.makeText(this,"Please Select a Subject",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void courselist(){
        try{
            Singleton2.getInstance().setAy(acy.getText().toString());
            Singleton2.getInstance().setSec(sec.getText().toString());
            Singleton2.getInstance().setSem(sem.getText().toString());

            if(Singleton2.getInstance().getSem().equals("") || Singleton2.getInstance().getAy().equals("")
                    || Singleton2.getInstance().getSec().equals("")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(course.this,"Please Enter Details",Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else {
                String url = "http://" + Singleton.getInstance().getIp() + "/Teacher/android_course_retrieve.php";
                nameValuePairs = new ArrayList<>(2);
                nameValuePairs.add(new BasicNameValuePair("id", Singleton.getInstance().getid()));
                nameValuePairs.add(new BasicNameValuePair("sem",Singleton2.getInstance().getSem()));
                nameValuePairs.add(new BasicNameValuePair("sec",Singleton2.getInstance().getSec()));
                nameValuePairs.add(new BasicNameValuePair("acy",Singleton2.getInstance().getAy()));
                connection c = new connection(nameValuePairs, url);
                final String response = c.getValue();
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(course.this,response,Toast.LENGTH_SHORT).show();

                    }
                });*/

                if (response != null && !response.equals("0")) {
                    List<String> list = new ArrayList<String>();
                    Collections.addAll(list, response.split("-", 2));
                    Singleton2.getInstance().setNo(list.get(0));
                    Singleton2.getInstance().setData(list.get(1));
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(course.this,Singleton2.getInstance().getData().toString(),Toast.LENGTH_SHORT).show();

                        }
                    });*/
                    startActivity(new Intent(course.this, course.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                }
                else{
                    Singleton2.getInstance().clear();
                    startActivity(new Intent(course.this, course.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                }
            }
        }

        catch (Exception e)
        {
            showAlert2();
            System.out.println("Exception : " + e.getMessage());
        }

    }

    public void showAlert2(){
        course.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(course.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(course.this, "Retry Later", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(course.this, home.class));
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
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
        getMenuInflater().inflate(R.menu.menu_course, menu);
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
