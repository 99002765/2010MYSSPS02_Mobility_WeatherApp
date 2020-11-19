package com.example.casestudy.database;
import com.example.casestudy.database.FeedReaderContract.FeedEntry;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAccessObj {
    SQLiteDatabase database;
    DbHelper dbHelper;
    public DbAccessObj(Context context) {
        dbHelper= new DbHelper(context);
    }

    public void openDb(){
        database= dbHelper.getWritableDatabase();
    }
    private void closeDb(){}

    public void createRow(String title, String subtitle){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_CITY_NAME,title);
        values.put(FeedEntry.COLUMN_CITY_CODE,subtitle);
        database.insert(FeedEntry.TABLE_NAME,null,values);
    }
    public String readRow(){
        //query my db/table
        //database.rawQuery("select * from notes_table",null);
        Cursor cursor = database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
        //return the result as a string
        cursor.moveToLast();
        int titleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_CITY_NAME);
        int subtitleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_CITY_CODE);

        String title = cursor.getString(titleIndex);
        String subtitle = cursor.getString(subtitleIndex);
        return title +"\n" +subtitle;
    }
    private void updateRow(){}
    private void deleteRow(){}
    public Cursor getRows() {
        Cursor cursor = database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }
    public String  query(String queryParam) {
        String table = FeedEntry.TABLE_NAME;
        String[] columns = {FeedEntry.COLUMN_CITY_NAME,FeedEntry.COLUMN_CITY_CODE}; //projection = columns
        String selection = FeedEntry.COLUMN_CITY_NAME +" =?"; //selection = rows
        String[] selectionArgs = {queryParam};
        String groupBy = null;
        String having = null;
        String orderBy = FeedEntry.COLUMN_CITY_NAME+" DESC";
        String limit = "10";

        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        int subtitleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_CITY_CODE);
        cursor.moveToLast();
        String subtitle = cursor.getString(subtitleIndex);
        return subtitle;

    }
}
