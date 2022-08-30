package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL - Structured Query Language
        sqLiteDatabase.execSQL(FeEn.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FeEn.TABLE_NAME);
        onCreate(db);
    }

    public void addMatEksempel(MatEksempel matEksempel){
        SQLiteDatabase db = this.getWritableDatabase();

        db.close();
    }

    public MatEksempel getMatEksempel(int id){
        SQLiteDatabase db = this.getReadableDatabase();
    }

}