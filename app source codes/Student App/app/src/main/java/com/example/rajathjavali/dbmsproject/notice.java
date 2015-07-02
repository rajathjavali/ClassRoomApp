package com.example.rajathjavali.dbmsproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class notice extends ActionBarActivity {

    SQLiteDatabase db;
    List<NameValuePair> nameValuePairs;
    List<String> list,list1;
    Thread t;
    ArrayAdapter<String> adapter;
    ListView l1;
    Button b;
    int flag;
    int a , pending;

    @Override
    public void onBackPressed() {
        Singleton2.getInstance().clear();
        startActivity(new Intent(this,home.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        if(Singleton.getInstance().getIp().equals("")){
            startActivity(new Intent(this,ipadd.class));
            finish();
        }
        flag = 0;
        b = (Button)findViewById(R.id.but);
        b.setEnabled(false);
        db=openOrCreateDatabase("NoticeDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notice(no VARCHAR(1000) PRIMARY KEY ,info VARCHAR(1000),date VARCHAR(12)," +
                "time VARCHAR(5),sn VARCHAR(20),status VARCHAR(1) default 'N');");
        Cursor c = db.rawQuery("select * from notice where 1",null);
        pending = 0;
        a = c.getCount();
        c.close();
        t = new Thread(new Runnable() {
            public void run() {
                querynotice();
            }
        });t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!list.isEmpty())
        disp(10,'N');
        if(flag == 1) {
            t = new Thread(new Runnable() {
                public void run() {
                    noticeupdate();
                }
            });
            t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*retrieving the notices from the server and storing all the notices in the local database*/
    public void querynotice(){
        try {

            String url="http://"+Singleton.getInstance().getIp()+"/Student/notice.php";

            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("usn", Singleton.getInstance().getusn()));
            connection c = new connection(nameValuePairs,url);
            final String response = c.getValue();
            list = new ArrayList<>();
            list1 = new ArrayList<>();

            if (!response.equals("0")) {
                int i = 0;
                String[] result = new String[2];
                for (String retval : response.split("\\$", 2)) {

                    result[i] = retval;
                    i++;
                }
                /*  gets the number of notices retrieved in the format 2$notice1$notice2   */
                int j = Integer.parseInt(result[0]);
                if(j>2){
                    Collections.addAll(list, result[1].split("\\$", j));
                }
                else
                    list.add(result[1]);

                /*  retrieves the individual fields in the notice which is of format info#date#time#staff_shortname  */
                for(int k=0;k<list.size();k++){
                    Collections.addAll(list1, list.get(k).split("#", 5));
                }
                //String s = "";
                for(int k=0;k<list1.size()/5;k++) {

                    Cursor c1 = db.rawQuery("Select * from notice where no = '" + list1.get(5 * k) + "'", null);
                    /* and info = '"+list1.get(4*k+1)+"' " +"and date = '"+list1.get(4*k+2)+"' and
                    time = '"+list1.get(4*k+3)+"' and sn = '"+list1.get(4*k+4)+"'",null);*/
                    if (c1.getCount() == 0) {
                        db.execSQL("INSERT INTO notice (no, info , date , time , sn , status )VALUES('" + list1.get(5 * k) + "','" +
                                list1.get(5 * k + 1) + "','" + list1.get(5 * k + 2) + "','" + list1.get(5 * k + 3) + "','" +
                                list1.get(5 * k + 4) + "','N');");

                        /*s += "INSERT INTO notice (no, info , date , time , sn , status )VALUES('" + list1.get(5 * k) + "','" +
                                list1.get(5 * k + 1) + "','" + list1.get(5 * k + 2) + "','" + list1.get(5 * k + 3) + "','" +
                                list1.get(5 * k + 4) + "','N');";*/
                    }
                    c1.close();
                }

                /*final String s1 = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(notice.this,s1,Toast.LENGTH_SHORT).show();
                    }
                });*/

            }
            else
            {
                list.add("no new values");
            }

        }
        catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void noticeupdate(){
        try {
            String url="http://"+Singleton.getInstance().getIp()+"/Student/checks.php";
            connection c = new connection(nameValuePairs,url);
            final String response = c.getValue();
        }
        catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }



    public void disp(int n,char a){
        l1 = (ListView) findViewById(R.id.l1);
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        nameValuePairs = get(n,a);
        if(!nameValuePairs.isEmpty())flag=1;
        adapter = new ArrayAdapter<String>(this , R.layout.list,list);
        l1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                                    final int position, long id) {
                disp2(list1.get(position));

            }});
    }

    public ArrayList<NameValuePair> get(int n,char a){
        if(db.isOpen()) {
            List<NameValuePair> name = new ArrayList<NameValuePair>(2);
            String query;
            if(a!='X')
                query =   "SELECT * FROM notice where status = '" + a + "' ORDER BY no DESC;";
            else
                query = "SELECT * FROM notice where 1 ORDER BY no DESC;";

            Cursor c = db.rawQuery(query, null);
            if ((pending = c.getCount()) == 0) {
                //Toast.makeText(this, "No Notices", Toast.LENGTH_SHORT).show();
                if (a == 'N') {
                    list.add("No New Notices");
                    list1.add("No Notices");
                } else {
                    list.add("No Old Notices");
                    list1.add("No Notices");
                }
                return (ArrayList<NameValuePair>)name;
            }
            if(a=='N') {

                int i = 0;
                name = new ArrayList<NameValuePair>(2);
                name.add(new BasicNameValuePair("usn", Singleton.getInstance().getusn()));
                name.add(new BasicNameValuePair("number", "" + c.getCount()));
                while (c.moveToNext()) {
                    //System.out.println("No of entry in dbms: \n" + c.getCount());
                    if (i == n) break;
                    list.add(c.getString(4) + " posted at " + c.getString(3) + " on " + c.getString(2));
                    list1.add(c.getString(1));
                    db.execSQL("UPDATE notice SET status='Y' WHERE no='" + c.getString(0) + "'");
                    name.add(new BasicNameValuePair(i + "", c.getString(0)));
                    i++;
                }

                if (pending > n) {
                    pending = pending - n;
                    b.setEnabled(true);
                }
                c.close();
                return (ArrayList<NameValuePair>)name;
            }
            else
            {
                n=c.getCount();
                int i = 0;
                while (c.moveToNext()) {
                    if (i == n) break;
                    list.add(c.getString(4) + " posted at " + c.getString(3) + " on " + c.getString(2));
                    list1.add(c.getString(1));
                    i++;
                }
                c.close();
                return (ArrayList<NameValuePair>)name;
            }

        }
        else
        {
            Toast.makeText(this, "No Table", Toast.LENGTH_SHORT).show();
        }
        return new ArrayList<NameValuePair>();
    }

    public void disp2(final String msg){

        notice.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(notice.this);
                builder.setTitle("Notice Details:");
                builder.setMessage(msg)
                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void next(View v){
        if(pending != 0)
            disp(10,'N');
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.count){
            Toast.makeText(notice.this,"No : "+a,Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.old){
            disp(10,'Y');
        }
        if(id == R.id.all){
            disp(a,'X');
        }
        return super.onOptionsItemSelected(item);
    }
}
