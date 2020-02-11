package com.example.mansi.databaseoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Mansi on 06-01-2017.
 */

public class MyDbHelper extends SQLiteOpenHelper{
    Context context;
    SQLiteDatabase db;

    MyDbHelper(Context context){
        super(context,"employeedb",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table emp(eid integer primary key,ename text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addEmployee(int eid,String ename){
        ContentValues cv=new ContentValues();
        cv.put("eid",eid);
        cv.put("ename",ename);
        long rid=db.insert("emp",null,cv);
        if(rid<0){
            Toast.makeText(context,"insert issue",Toast.LENGTH_LONG).show();
        }

    }

    public String getEmployee(){
        StringBuffer sb=new StringBuffer();
        Cursor cursor=db.query("emp",null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            do {
                int eid = cursor.getInt(0);
                String ename = cursor.getString(1);
                sb.append("eid= " + eid + " ename= " + ename + "\n");


            } while (cursor.moveToNext());//jya sudhi record che 1,2,3 tya sudhi get karine append
        }
        return sb.toString();
    }

    public void updateEmployee(int eid,String ename){
        ContentValues cv=new ContentValues();
        cv.put("eid",eid);
        cv.put("ename",ename);
        long rid=db.update("emp",cv,"eid = "+eid,null);
        if(rid<0){
            Toast.makeText(context,"insert issue",Toast.LENGTH_LONG).show();
        }

    }

    public void deleteEmployee(int eid){
        db.delete("emp","eid = "+eid,null);
    }
}
