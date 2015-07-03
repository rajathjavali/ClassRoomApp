package com.example.rajathjavali.dbmsproject;

/**
 * Created by RajathJavali on 4/4/2015.
 * this is used to store the attendance and marks of the subject selected
 *
 */
public class Singleton3 {
    private static Singleton3 obj = null;
    private String name;
    private String data;
    private String contactno;
    private Singleton3(){
        contactno = name = data = "";
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
    public String getCno(){
        return this.contactno;
    }

    public void setCno(String value){
        contactno = value;
    }
    public void clear(){
        name = data = contactno="";
    }
    public void cleardata(){
       data ="";
    }
}
