package be.formath.formathmobile.data;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FormathDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String BOOLEAN_TYPE = " INTEGER";
    private static final String INT_TYPE = " INTEGER";
    private static final String DATETIME_TYPE = " TEXT";
    private static final String DATE_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES_ONE =
            "CREATE TABLE " + FormathDBContract.TableGame.TABLE_NAME + "(" +
                    FormathDBContract.TableGame._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    FormathDBContract.TableGame.COLUMN_NAME_DATE + DATE_TYPE + COMMA_SEP +
                    FormathDBContract.TableGame.COLUMN_NAME_USER + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableGame.COLUMN_NAME_REMAINING_TIME + INT_TYPE + COMMA_SEP +
                    FormathDBContract.TableGame.COLUMN_NAME_RESULT + INT_TYPE + ")";
    private static final String SQL_CREATE_ENTRIES_TWO =
            "CREATE TABLE " + FormathDBContract.TableOperation.TABLE_NAME + "(" +
                    FormathDBContract.TableOperation._ID + " INTEGER PRIMARY KEY," +
                    FormathDBContract.TableOperation.COLUMN_NAME_CODE + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableOperation.COLUMN_NAME_LABEL + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableOperation.COLUMN_NAME_GIVEN_RESPONSE + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableOperation.COLUMN_NAME_RAW_RESPONSE + FLOAT_TYPE + COMMA_SEP +
                    FormathDBContract.TableOperation.COLUMN_NAME_RESPONSE + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableOperation.COLUMN_NAME_USER + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableOperation.COLUMN_NAME_GAME_ID + INT_TYPE + ")";
    private static final String SQL_CREATE_ENTRIES_THREE =
            "CREATE TABLE " + FormathDBContract.TableUser.TABLE_NAME + "(" +
                    FormathDBContract.TableUser._ID + " INTEGER PRIMARY KEY," +
                    FormathDBContract.TableUser.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableUser.COLUMN_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableUser.COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableUser.COLUMN_NAME_DISTANT_IDENTIFIER + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableUser.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableUser.COLUMN_NAME_OFFLINE_USER + BOOLEAN_TYPE + ")";
    private static final String SQL_CREATE_ENTRIES_FOUR =
            "CREATE TABLE " + FormathDBContract.TableMedal.TABLE_NAME + "(" +
                    FormathDBContract.TableMedal.COLUMN_NAME_CATEGORY + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableMedal.COLUMN_NAME_LEVEL + TEXT_TYPE + COMMA_SEP +
                    FormathDBContract.TableMedal.COLUMN_NAME_OBTAINING_DATE + DATETIME_TYPE + COMMA_SEP +
                    FormathDBContract.TableMedal.COLUMN_NAME_TYPE + INT_TYPE + COMMA_SEP +
                    FormathDBContract.TableMedal.COLUMN_NAME_USER + TEXT_TYPE + ");";


    private static final String SQL_DELETE_ENTRIES_ONE =
            "DROP TABLE IF EXISTS " + FormathDBContract.TableOperation.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_TWO =
            "DROP TABLE IF EXISTS " + FormathDBContract.TableUser.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_THREE =
            "DROP TABLE IF EXISTS " + FormathDBContract.TableMedal.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_FOUR =
            "DROP TABLE IF EXISTS " + FormathDBContract.TableGame.TABLE_NAME;

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "formath.db";

    public FormathDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_ONE);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_TWO);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_THREE);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_FOUR);
        ContentValues values = new ContentValues();
        values.put(FormathDBContract.TableUser.COLUMN_NAME_USER_NAME, "demo");
        values.put(FormathDBContract.TableUser.COLUMN_NAME_PASSWORD, "{CLEAR}demo");
        values.put(FormathDBContract.TableUser.COLUMN_NAME_OFFLINE_USER, 1);

// Insert the new row, returning the primary key value of the new row
        long newRowId = sqLiteDatabase.insert(FormathDBContract.TableUser.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_ONE);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_TWO);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_THREE);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_FOUR);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onUpgrade(sqLiteDatabase, i, i1);
    }
}
