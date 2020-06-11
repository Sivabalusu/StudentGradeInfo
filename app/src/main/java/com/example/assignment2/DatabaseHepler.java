package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHepler extends SQLiteOpenHelper
{
    public static final String dbName="Student.db";  //Database Name
    public static final int version=1;
    public static final String Table_Name="Student";
    public static final String col1="id";
    public static final String col2="firstName";
    public static final String col3="secondName";  //column Names
    public static final String col4="marks";
    public static final String col5="progCode";
    public static final String col6="credits";
    //Storing Query in Create_Table String to Create Table
    private static final String Create_Table="CREATE TABLE "+Table_Name+"("+col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                              col2+" TEXT NOT NULL, "+col3+" TEXT NOT NULL, "+col4+" INTEGER,"+col5+" TEXT NOT NULL,"+col6+" INTEGER);";
    //Storing Query in Drop_Table String to Drop Table
    private static final String Drop_Table="DROP TABLE IF EXISTS "+Table_Name;

    public DatabaseHepler(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Create_Table);
    } //Executing Create Query

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Drop_Table);   //Executing Drop Table Query
        onCreate(db);
    }
    //Function to insert to Records to Student Table
    public boolean insertStudent(Student student)
    {
        SQLiteDatabase db=this.getWritableDatabase();      //Instantiating SQLiteDatabase Database
        ContentValues contentValues=new ContentValues();   //Storing Data in contentValues object
        contentValues.put(col2,student.getFname());        //putting individual record value in Student table
        contentValues.put(col3,student.getLname());
        contentValues.put(col4,student.getMarks());
        contentValues.put(col5,student.getProgCode());
        contentValues.put(col6,student.getCredits());
        //If Row inserted into table then Result variable will assigned to positive value
        long result=db.insert(Table_Name,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }
    //Function to Retrieve all records from Table
    public Cursor viewData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor; //Cursor stores the table data
        cursor=db.rawQuery("SELECT * FROM "+Table_Name,null);
        if(cursor!=null)
        {
            cursor.moveToNext();
        }
        return cursor;
    }
    //Function to Retrieve specific record from Table based on studentId provided
    public Cursor viewRecordData(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
        cursor=db.rawQuery("SELECT * FROM "+Table_Name+" WHERE id="+id,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
    //Function to Retrieve specific record from Table based on programCode provided
    public Cursor viewRecordsData(String course)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
        cursor=db.rawQuery("SELECT * FROM "+Table_Name+" WHERE progCode='"+course+"'",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
    //Function to delete a record
    public Integer deleteRecord(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_Name,"id="+id,null);
    }

    //Function to update a record in a Table
    public int updateStudent(Student student)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,student.getFname());
        contentValues.put(col3,student.getLname());
        contentValues.put(col4,student.getMarks());
        contentValues.put(col5,student.getProgCode());
        contentValues.put(col6,student.getCredits());
        int numRows=db.update(Table_Name,contentValues,"id= "+student.getStudeentId(),null);
        return numRows;
    }


}
