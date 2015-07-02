package com.example.rajathjavali.dbmsproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class pass extends ActionBarActivity {
    Intent i;
    EditText op,np,cp;
    String oldp,newp,confirmp;
    List<NameValuePair> nameValuePairs;
    Thread t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);
        i=getIntent();
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }
        op = (EditText) findViewById(R.id.oldpass);
        np = (EditText) findViewById(R.id.newpass);
        cp = (EditText) findViewById(R.id.newpass1);
        Button b = (Button) findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldp = op.getText().toString();
                newp = np.getText().toString();
                confirmp = cp.getText().toString();
                if(oldp.equals("")||newp.equals("" )||confirmp.equals("")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(com.example.rajathjavali.dbmsproject.pass.this, "Enter All the Fields ", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else if (confirmp.equals(newp)) {
                    t= new Thread(new Runnable() {

                        public void run() {
                            pass1();
                        }
                    });
                    t.start();

                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    showAlert("Password Doesn't Match !! ");
                }
            }
        });
        op.setText("");
        np.setText("");
        cp.setText("");
    }

    public void pass1(){

        try {

            String url = "http://" + Singleton.getInstance().getIp() + "/Student/android_pass.php";


            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("usn",Singleton.getInstance().getusn()));
            nameValuePairs.add(new BasicNameValuePair("oldpass", oldp));
            nameValuePairs.add(new BasicNameValuePair("newpass", newp));

            connection c = new connection(nameValuePairs,url);

            final String response = c.getValue();
            /*runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(pass.this, response, Toast.LENGTH_LONG).show();
                }
            });*/
            if (response.equalsIgnoreCase(newp)) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(com.example.rajathjavali.dbmsproject.pass.this, "Password Changed ", Toast.LENGTH_LONG).show();
                    }
                });
                startActivity(new Intent(com.example.rajathjavali.dbmsproject.pass.this, home.class));
                finish();
            } else {
                showAlert("Old Password Doesn't Match !! ");
            }

        }catch(Exception e){
            showAlert2();
            System.out.println("Exception : " + e.getMessage());
        }

    }
    public void showAlert(String a){
        final String b = a;
        com.example.rajathjavali.dbmsproject.pass.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(com.example.rajathjavali.dbmsproject.pass.this);
                builder.setTitle("Error");
                builder.setMessage(b)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                /*startActivity(new Intent(pass.this, pass.class));
                                finish();*/
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    public void showAlert2(){
        com.example.rajathjavali.dbmsproject.pass.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(com.example.rajathjavali.dbmsproject.pass.this);
                builder.setTitle("Connection Error.");
                builder.setMessage("Server Connection Failed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(com.example.rajathjavali.dbmsproject.pass.this,"Retry Later",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(com.example.rajathjavali.dbmsproject.pass.this,home.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pass, menu);
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
