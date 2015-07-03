package com.example.rajathjavali.dbmsproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;


public class profile extends ActionBarActivity {

    Intent i;
    TextView name,ph,email,us;
    Button b;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,home.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /*int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);*/
        i=getIntent();
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }
        String name = Singleton.getInstance().getfname();
        if(name.equals("") ){
            Toast.makeText(this,"Proceeding to Registration ...",Toast.LENGTH_SHORT).show();
            Intent i2 = new Intent(this,MainActivity.class);
            i2.putExtra("name","Registration");
            startActivity(i2);

        }
        else{
            setval();
        }

    }

    public void setval(){
        b = (Button) findViewById(R.id.edit);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.emailid);
        ph=(TextView)findViewById(R.id.phno);
        us =(TextView) findViewById(R.id.usn);

        name.setText("Name: "+Singleton.getInstance().getfname() + " " + Singleton.getInstance().getmname() + " " + Singleton.getInstance().getlname() + " ");
        us.setText("USN: "+ Singleton.getInstance().getusn());
        ph.setText("Ph No: "+Singleton.getInstance().getph() + " ");
        email.setText("Email ID: "+Singleton.getInstance().getemail() + " ");
    }

    public void edit(View v){
        Intent i1 = new Intent(this,MainActivity.class);
        i1.putExtra("name","Edit");
        startActivity(i1);
    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
