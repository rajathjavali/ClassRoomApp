package com.example.rajathjavali.dbmsprojectstaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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


public class home extends ActionBarActivity {

    Intent i;
    TextView a;
    List<NameValuePair> nameValuePairs;
    Thread t;
    //private ViewGroup mContainerView;


    @Override
    public void onBackPressed() {
        showAlert();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //mContainerView = (ViewGroup) findViewById(R.id.container);

        //int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //setRequestedOrientation(orientation);
        i=getIntent();
        a = (TextView) findViewById(R.id.disp);
        a.setTextSize(30);


        if(Singleton.getInstance().getIp().equals("")){
            Intent a = new Intent(this, ipadd.class);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }


        if(!Singleton2.getInstance().getAy().equals("")){
            Singleton2.getInstance().clear2();}

        if(Singleton.getInstance().getsname().equals("")) {
            t = new Thread(new Runnable() {
                public void run() {
                    prof();
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (Singleton.getInstance().getsname().equals("")) {
            a.setText("Welcome " + Singleton.getInstance().getid());
        } else {
            a.setText("Welcome " + Singleton.getInstance().getsname());
        }

    }



    public void prof()
    {
        String name = Singleton.getInstance().getfname();
        String email =  Singleton.getInstance().getemail();
        if(name.equals("") && email.equals(""))
        {
            try
            {
                String url = "http://" + Singleton.getInstance().getIp() + "/Teacher/android_retrieve.php";

                nameValuePairs = new ArrayList<>(2);
                nameValuePairs.add(new BasicNameValuePair("id", Singleton.getInstance().getid()));


                connection c = new connection(nameValuePairs, url);
                final String response = c.getValue();
                if (response != null)
                {

                    List <String> list= new ArrayList<String>();

                    Collections.addAll(list, response.split("-", 6));
                    Singleton.getInstance().setfname(list.get(0));
                    Singleton.getInstance().setmname(list.get(1));
                    Singleton.getInstance().setlname(list.get(2));
                    Singleton.getInstance().setph(list.get(3));
                    Singleton.getInstance().setemail(list.get(4));
                    Singleton.getInstance().setsname(list.get(5));
                   /* Singleton.getInstance().status=1;
                    startActivity(new Intent(this,home.class));
                    finish();*/
                }
            }

            catch (Exception e)
            {
                showAlert2();
                System.out.println("Exception : " + e.getMessage());
            }

        }

    }
    public void post(View v){
        startActivity(new Intent(this,postit.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void list(View v){
        startActivity(new Intent(this,course.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void showAlert(){
        home.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
                builder.setTitle("Logout");
                builder.setMessage("Confirm Logout")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(home.this, "Log Out Successful", Toast.LENGTH_SHORT).show();
                                Singleton.getInstance().clear();
                                Intent i2 = new Intent(home.this, Login.class);
                                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i2);
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                finish();
                            }
                        });
                builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }

                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void showAlert2(){
        home.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(home.this,"Retry Later",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(home.this,home.class));
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        if(id == R.id.profile){
            startActivity(new Intent(this,profile.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


        }
        if(id == R.id.pass){
            startActivity(new Intent(this,pass.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

        }
        if(id == R.id.logout){
            showAlert();
        }

        return super.onOptionsItemSelected(item);
    }
}
