package com.example.version3_355app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.version3_355app.ui.to_do.TodoModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String TD_TABLE = "TD_table";
    public static final String TO_DO_TABLE = "TO_DO_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ASSIGNMENT = "ASSIGNMENT";
    public static final String COLUMN_COURSE = "COURSE";
    public static final String COLUMN_MONTH = "MONTH";
    public static final String COLUMN_DAY = "DAY";
    public static final String COLUMN_YEAR = "YEAR";
    public static final String COLUMN_TIME = "TIME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "toDo.db", null, 1);
    }



    //create a new database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String createTableStatement= "CREATE TABLE " + TD_TABLE + " ("
//                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + COLUMN_ASSIGNMENT + " TEXT,"
//                + COLUMN_COURSE + " TEXT,"
//                + COLUMN_MONTH + " INT,"
//                + COLUMN_DAY + " INT,"
//                + COLUMN_YEAR + " INT,"
//                + COLUMN_TIME + " TIME)";
        sqLiteDatabase.execSQL("CREATE TABLE " + TO_DO_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ASSIGNMENT + " TEXT," + COLUMN_COURSE + " TEXT," + COLUMN_MONTH + " INT," + COLUMN_DAY + " INT," + COLUMN_YEAR + " INT," + COLUMN_TIME + " TIME)");
    }

    //this is called if the database version number changes. It prevents users apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TO_DO_TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);

    }

    public boolean addOne(TodoModel todoModel){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues cv= new ContentValues();
        cv.put(COLUMN_ASSIGNMENT, todoModel.getAssignment());
        cv.put(COLUMN_COURSE, todoModel.getCourse());
        cv.put(COLUMN_MONTH, todoModel.getMonth());;
        cv.put(COLUMN_DAY, todoModel.getDay());
        cv.put(COLUMN_YEAR, todoModel.getYear());
        cv.put(COLUMN_TIME, String.valueOf(todoModel.getTime()));

       long insert = db.insert(TO_DO_TABLE, null,cv );
        if (insert==-1){
            return false;
        }
        else{
            return true;
        }
        }
        public List<String> assignments(){
            List <String>returnlist= new ArrayList<>();
            String queryString= "SELECT * FROM " + TO_DO_TABLE;

            SQLiteDatabase db= this.getReadableDatabase();
            Cursor cursor= db.rawQuery(queryString, null);

            if (cursor.moveToFirst()){
                do{

                    String assignment= cursor.getString(1);
                    String course= cursor.getString(2);
                    returnlist.add(assignment+"\n"+course);
                }
                while (cursor.moveToNext());

            }
            else    {

                //failure. do not add anything to the list
            }
                cursor.close();
                db.close();

            return returnlist;
        }
    public List<String> dates(){
        List <String>returnlist= new ArrayList<>();
        String queryString= "SELECT * FROM " + TO_DO_TABLE;

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{

                int month= cursor.getInt(3);
                int day= cursor.getInt(4);
                int year= cursor.getInt(5);
                String time= cursor.getString(6);
                returnlist.add(month +"/"+day+"/"+year +"\n" +time);
            }
            while (cursor.moveToNext());

        }
        else    {

            //failure. do not add anything to the list
        }
        cursor.close();
        db.close();

        return returnlist;
    }

        public List<TodoModel> everything () {
            List<TodoModel> returnlist= new ArrayList<>();

            String queryString= "SELECT * FROM " + TO_DO_TABLE;
            SQLiteDatabase db= this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);
            if (cursor.moveToFirst()){
                do{
                    int id= cursor.getInt(0);
                    String assignment= cursor.getString(1);
                    String course= cursor.getString(2);
                    int month= cursor.getInt(3);
                    int day= cursor.getInt(4);
                    int year= cursor.getInt(5);
                    String time= cursor.getString(6);

                    TodoModel newtoDo= new TodoModel(id,assignment, month, day,year, Time.valueOf(time), course);
                    newtoDo.setId(id);
                    returnlist.add(newtoDo);
                }
                while (cursor.moveToNext());

            }
            else    {

                //failure. do not add anything to the list
            }
            cursor.close();
            db.close();
            return (List<TodoModel>) returnlist;
        }

        public boolean deleteOne(TodoModel todoModel){
            SQLiteDatabase db= this.getWritableDatabase();
            String queryString= "DELETE FROM "+ TO_DO_TABLE + " WHERE " + COLUMN_ID + " = "+ todoModel.getId();

            Cursor cursor = db.rawQuery(queryString, null);
            if(cursor.moveToFirst()){
                return true;
            }
            else{
                return false;
            }

        }
}
