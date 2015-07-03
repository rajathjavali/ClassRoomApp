package com.example.rajathjavali.dbmsproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import java.util.List;


public class home extends ActionBarActivity {

    Intent i;
    List<NameValuePair> nameValuePairs;
    String acy , sem;
    Thread t ;
    @Override
    public void onBackPressed() {
        showAlert();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        /*int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);*/
        i=getIntent();


        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }


        if(Singleton2.getInstance().getAy().equals("")){
        Singleton2.getInstance().clear();}


        if(!Singleton2.getInstance().getAy().equals("") && !Singleton2.getInstance().getSem().equals("")){
            TextView q = (TextView) findViewById(R.id.show);
            q.setText("Academic Year : "+Singleton2.getInstance().getAy() + " Semester : "+ Singleton2.getInstance().getSem());

        }
        else{
            TextView q = (TextView) findViewById(R.id.show);
            q.setText("Please select academic year and sem");
        }
        if(Singleton.getInstance().status==0) {
            t = new Thread(new Runnable() {
                public void run() {
                    prof();
                }
            });t.start();
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView a = (TextView) findViewById(R.id.disp);
        a.setTextSize(30);
        if(Singleton.getInstance().getfname().equals("")){
            a.setText("Welcome "+ Singleton.getInstance().getusn());
        }else{
            a.setText("Welcome "+ Singleton.getInstance().getfname());
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

                String url = "http://" + Singleton.getInstance().getIp() + "/Student/android_retrieve.php";

                nameValuePairs = new ArrayList<>(2);
                nameValuePairs.add(new BasicNameValuePair("usn", Singleton.getInstance().getusn()));


                connection c = new connection(nameValuePairs, url);
                final String response = c.getValue();
                if (response != null)
                {
                    int i = 0;
                    String[] result = new String[5];
                    for (String retval : response.split("-", 5))
                    {

                        result[i] = retval;
                        i++;
                    }
                    Singleton.getInstance().setfname(result[0]);
                    Singleton.getInstance().setmname(result[1]);
                    Singleton.getInstance().setlname(result[2]);
                    Singleton.getInstance().setph(result[3]);
                    Singleton.getInstance().setemail(result[4]);
                    /*Singleton.getInstance().status=1;
                    startActivity(new Intent(home.this,home.class));
                    finish();*/

                }
            }

            catch (Exception e)
            {
                //showAlert2();
                System.out.println("Exception : " + e.getMessage());
            }

        }

    }

    public void notice(View v){
        startActivity(new Intent(this,notice.class));
    }

    public void marks (View v){
        acy = Singleton2.getInstance().getAy();
        sem = Singleton2.getInstance().getSem();
        if(acy.equals("") && sem.equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Details Required");
            //alert.setMessage("PLEASE ENTER DETAILS");


            final LinearLayout a = new LinearLayout(this);
            a.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            p.topMargin=20;
            p.gravity=Gravity.CENTER_HORIZONTAL;
            p.gravity=Gravity.CENTER;
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //p2.leftMargin=30;
            p2.width=170;
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //p3.leftMargin=175;
            p3.width=130;

            final LinearLayout a1 = new LinearLayout(this);
            a1.setOrientation(LinearLayout.HORIZONTAL);
            final TextView t = new TextView(this);
            t.setText("Academic Year");
            t.setTextSize(20);
            int maxLength = 4;
            a1.addView(t);
            final EditText i = new EditText(this);
            i.setHint("2014");
            i.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
            i.setGravity(Gravity.CENTER);
            i.setInputType(InputType.TYPE_CLASS_NUMBER);
            a1.addView(i,p2);
            a.addView(a1,p);

            final LinearLayout a2 = new LinearLayout(this);
            a2.setOrientation(LinearLayout.HORIZONTAL);
            final TextView t2 = new TextView(this);
            t2.setText("Semester");
            t2.setTextSize(20);
            a2.addView(t2);
            maxLength=1;
            final EditText i2 = new EditText(this);
            i2.setHint("6");
            i2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength),
                    new InputFilterMinMax("1", "8")});

            i2.setGravity(Gravity.CENTER);
            //i2.setHint("Semester");
            i2.setInputType(InputType.TYPE_CLASS_NUMBER);
            a2.addView(i2, p3);
            a.addView(a2,p);

            alert.setView(a);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    acy = i.getText().toString();
                    sem = i2.getText().toString();
                    if(!(acy.equals("")||sem.equals(""))) {
                        Singleton2.getInstance().setAy(acy);
                        Singleton2.getInstance().setSem(sem);
                        Intent x = new Intent(home.this, marks.class);
                        x.putExtra("acy", acy);
                        x.putExtra("sem", sem);
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

            Intent x = new Intent(home.this, marks.class);
            x.putExtra("acy", acy);
            x.putExtra("sem", sem);
            startActivity(x);
        }

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
                                startActivity(new Intent(home.this, Login.class));
                                Singleton.getInstance().clear();
                                Singleton2.getInstance().clear2();
                                Singleton3.getInstance().clear();
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

   /* public void showAlert2(){
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
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
*/

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

        }
        if(id == R.id.logout){
            showAlert();
        }
        if(id==R.id.pass){
            startActivity(new Intent(this,pass.class));
        }
        if(id==R.id.clear){
            TextView q = (TextView) findViewById(R.id.show);
            q.setVisibility(View.INVISIBLE);
            Singleton2.getInstance().clear2();
        }

        return super.onOptionsItemSelected(item);
    }

}
