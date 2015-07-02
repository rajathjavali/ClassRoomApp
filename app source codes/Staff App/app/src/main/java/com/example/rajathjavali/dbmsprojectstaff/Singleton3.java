package com.example.rajathjavali.dbmsprojectstaff;

/**
 * Created by RajathJavali on 4/4/2015.
 */
public class Singleton3 {
    private static Singleton3 obj = null;
    private String name;
    private String data;

    private String no;
    private Singleton3(){
        no=name = data = "";
    }
    public static Singleton3 getInstance(){
        if(obj == null)
        {
            obj = new Singleton3();
        }
        return obj;
    }

    public String getData(){
        return this.data;
    }

    public void setData(String value){
        data = value;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String value){
        name = value;
    }
    public String getNo(){
        return this.no;
    }

    public void setNo(String value){
        no = value;
    }
    public void clear(){
        name = no = data ="";
    }
    public void cleardata(){
        data ="";
    }
}
