package com.example.rajathjavali.dbmsprojectstaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Intent i ;
    EditText fn,mn,ln,ph,em,sn;
    String name,id,email,fname,lname,sname,mname,phno;
    List<NameValuePair> list;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //setRequestedOrientation(orientation);
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }

        if(Singleton.getInstance().getIp().equals("")){
            Intent a = new Intent(this, ipadd.class);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }

        i=getIntent();


        name = i.getStringExtra("name");
        TextView na = (TextView) findViewById(R.id.name);
        na.setText(name);
        fn = (EditText) findViewById(R.id.fname);
        mn = (EditText) findViewById(R.id.mname);
        ln = (EditText) findViewById(R.id.lname);
        sn = (EditText)findViewById(R.id.sname);
        id = Singleton.getInstance().getid();
        ph = (EditText) findViewById(R.id.phno);
        em = (EditText) findViewById(R.id.email);


        if(name.equalsIgnoreCase("edit")){
            fn.setText(Singleton.getInstance().getfname());
            em.setText(Singleton.getInstance().getemail());
            mn.setText(Singleton.getInstance().getmname());
            sn.setText(Singleton.getInstance().getsname());
            ln.setText(Singleton.getInstance().getlname());
            ph.setText(Singleton.getInstance().getph());
            name = "Done";
        }



        Button b = (Button) findViewById(R.id.but);
        b.setText(name);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { new Thread(new Runnable() {
                public void run() {

                    register();

                }
            }).start();
            }
        });

    }
    public void register(){
        int flag = 0;
        fname = fn.getText().toString();
        email = em.getText().toString();
        mname = mn.getText().toString();
        lname = ln.getText().toString();
        sname= sn.getText().toString();
        phno = ph.getText().toString();
        String NamePattern = "[a-zA-Z]+";
        String NamePattern2 = "[a-zA-Z]*";
        String MobilePattern = "[7-9]{1}[0-9]{9}";
        //String email1 = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String text="",text2="";
        if(!fname.matches(NamePattern)){
            text2="Please enter valid First Name ";
            flag = 1;
        }
        if(!mname.matches(NamePattern2)){
            if(text2.equals(""))
                text2="Enter valid Middle Name ";
            flag = 1;
        }
        if(!lname.matches(NamePattern2)){
            if(text2.equals(""))
                text2="Enter Valid Last Name ";
            flag = 1;
        }
        if(!sname.matches(NamePattern2)){
            if(text2.equals(""))
                text2="Enter Valid Short Name ";
            flag = 1;
        }
        if(!phno.matches(MobilePattern) && !email.matches(emailPattern))
        {

            text="Please enter valid Phone number & Email id";
            flag =1;
        }
        else if(!phno.matches(MobilePattern)){
            text = "Please enter valid Phone number";
            flag =1;
        }
        else if (!email.matches(emailPattern)){
            text = "Please enter valid Email id";
            flag =1;
        }
        if(flag == 0) {
            final String url = "http://" + Singleton.getInstance().getIp() + "/Teacher/android_register.php";

            Singleton.getInstance().setfname(fname);
            Singleton.getInstance().setmname(mname);
            Singleton.getInstance().setsname(sname);
            Singleton.getInstance().setlname(lname);
            Singleton.getInstance().setph(phno);
            Singleton.getInstance().setemail(email);

            list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("id", id));
            list.add(new BasicNameValuePair("fname", fname));
            list.add(new BasicNameValuePair("mname", mname));
            list.add(new BasicNameValuePair("sname", sname));
            list.add(new BasicNameValuePair("lname", lname));
            list.add(new BasicNameValuePair("ph", phno));
            list.add(new BasicNameValuePair("email", email));
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
                httppost.setEntity(new UrlEncodedFormEntity(list));
                HttpResponse response = httpclient.execute(httppost);

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String resp = httpclient.execute(httppost, responseHandler);
                if (resp.equalsIgnoreCase("Successful")) {
                    startActivity(new Intent(MainActivity.this, profile.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "Error!! Retry", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            } catch (IOException e) {
                showAlert2();
                System.out.println("Exception : " + e.getMessage());
            }
        }
        else{
            final String text3 = text;
            final String text4 = text2;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String text5="";
                    if(text4.equals("")){
                        text5 = text3;
                    }
                    else
                        text5=text4;
                    Toast.makeText(MainActivity.this,
                            text5,Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }
    public void showAlert2(){
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivity.this, "Retry Later", Toast.LENGTH_SHORT).show();
                                /*startActivity(new Intent(MainActivity.this,home.class));
                                finish();*/
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
