package Database;

import android.provider.BaseColumns;
/*
FeEn Feed Entry == Запись в ленте. FeEn
* */
public final class FeEn implements BaseColumns {

    private FeEn(){} // Приватный конструктор нужен чтобы случайно не создать экземпляр класа.

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public static final String TABLE_NAME = "entry";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_SUBTITLE = "subtitle";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_SUBTITLE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;








//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "test_result.db";
//    public static final String TABLE_NAME = "test_result";
//
//    public static final String COLUMN_NAME_X = "x";
//    public static final String COLUMN_NAME_Y = "y";
//    public static final String COLUMN_NAME_OPERATOR = "operator";
//    public static final String COLUMN_NAME_RESULT = "result";
//    public static final String COLUMN_NAME_MILLISECONDS = "milliseconds";
//
//    public static final String SQL_CREATE_ENTRIES =
//                    "CREATE TABLE " + TABLE_NAME + "(" +
//                    _ID + " INTEGER PRIMARY KEY,"+
//                    COLUMN_NAME_X + " INTEGER" +
//                    COLUMN_NAME_OPERATOR + " TEXT" +
//                    COLUMN_NAME_Y + " INTEGER" +
//                    COLUMN_NAME_RESULT + " INTEGER" +
//                    COLUMN_NAME_MILLISECONDS + " INTEGER" + ")";
//
//    public static final String SQL_DELETE_ENTRIES =
//                    "DROP TABLE IF EXISTS " + TABLE_NAME;



/*
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    //FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
*/

}
