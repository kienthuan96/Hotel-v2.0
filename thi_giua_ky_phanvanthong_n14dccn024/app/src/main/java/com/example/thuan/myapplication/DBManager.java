package com.example.thuan.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Thuan on 3/25/2018.
 */

public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="bus_list";
    private static final String TABLE_NAME ="bus";
    private static final String MAXE ="id";
    private static final String TENXE ="name";
//    private static final String EMAIL ="email";
//    private static final String NUMBER ="number";
//    private static final String ADDRESS ="address";

    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                MAXE +" integer primary key, "+
                TENXE + " TEXT)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    //Add new a student
    public void addBus(Bus bus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAXE, bus.getMaXe());
        values.put(TENXE, bus.getTenXe());
        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    /*
    Select a student by ID
     */

//    public Student getSdtudentById(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, new String[] { ID,
//                        NAME, EMAIL,NUMBER,ADDRESS }, ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Student student = new Student(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
//        cursor.close();
//        db.close();
//        return student;
//    }

    /*
    Update name of student
     */

//    public int Update(Bus bus){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(NAME,student.getName());
//
//        return db.update(TABLE_NAME,values,ID +"=?",new String[] { String.valueOf(student.getId())});
//
//
//    }

    /*
     Getting All Student
      */

    public ArrayList<Bus> getAllBus() {
        ArrayList<Bus> listStudent = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Bus bus = new Bus();
                bus.setMaXe(cursor.getString(0));
                bus.setTenXe(cursor.getString(1));
                listStudent.add(bus);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }
    /*
    Delete a student by ID
     */
//    public void deleteStudent(Student student) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, ID + " = ?",
//                new String[] { String.valueOf(student.getId()) });
//        db.close();
//    }
    /*
    Get Count Student in Table Student
     */
    public int getBusCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
