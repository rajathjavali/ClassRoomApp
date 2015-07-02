package com.example.rajathjavali.dbmsproject;

/**
 * Created by RajathJavali on 4/3/2015.
 *
 * this is to retrieve the course list using academic year, usn and semester
 */
public class Singleton2 {
    private static Singleton2 obj = null;

    private String number;
    private String data;
    private String acy;
    private String sem;

    private Singleton2(){
        number = acy = sem = data = "";
    }

    public static Singleton2 getInstance(){
        if(obj == null)
        {
            obj = new Singleton2();
        }
        return obj;
    }

    public String getNo(){
        return this.number;
    }

    public void setNo(String value){
        number = value;
    }
    public String getData(){
        return this.data;
    }

    public void setData(String value){
        data = value;
    }
    public String getAy(){
        return this.acy;
    }

    public void setAy(String value){
        acy = value;
    }
    public String getSem(){
        return this.sem;
    }

    public void setSem(String value){
        sem = value;
    }
    public void clear(){
        number = data ="";
    }
    public void clear2(){
        acy = sem = "";
        clear();
    }
}
