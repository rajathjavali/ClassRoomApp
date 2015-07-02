package com.example.rajathjavali.dbmsprojectstaff;

/**
 * Created by RajathJavali on 3/31/2015.
 */
public class Singleton {

    private static Singleton obj = null;

    private String id;
    private String fname;
    private String lname;
    private String mname;
    private String sname;
    private String phno;
    private String email;
    private String ip;


    private Singleton(){
        id=ip=fname=lname=sname=mname=phno=email = "";
    }

    public static Singleton getInstance(){
        if(obj == null)
        {
            obj = new Singleton();
        }
        return obj;
    }

    public String getIp(){
        return this.ip;
    }

    public void setIp(String value){
        ip = value;
    }
    public String getid(){
        return this.id;
    }

    public void setid(String value){
        id = value;
    }
    public String getfname(){
        return this.fname;
    }

    public String getsname(){
        return this.sname;
    }
    public void setsname(String value){
        sname = value;
    }

    public void setfname(String value){
        fname = value;
    }
    public String getlname(){
        return this.lname;
    }

    public void setlname(String value){
        lname = value;
    }
    public String getmname(){
        return this.mname;
    }

    public void setmname(String value){
        mname = value;
    }
    public String getph(){
        return this.phno;
    }

    public void setph(String value){
        phno = value;
    }
    public String getemail(){
        return this.email;
    }

    public void setemail(String value){
        email = value;
    }
    public void clear(){
        email=sname=fname=mname=lname=phno="";
    }

}


