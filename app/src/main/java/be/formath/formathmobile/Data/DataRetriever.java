package be.formath.formathmobile.Data;

import java.util.GregorianCalendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.formath.formathmobile.Model.Medal;

public class DataRetriever {
    private static DataRetriever instance;

    private FormathDbHelper dbHelper;
    private SQLiteDatabase database;

    private DataRetriever(Context context) {
        dbHelper = new FormathDbHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public static DataRetriever getInstance(Context context) {
        if (instance == null) {
            instance = new DataRetriever(context);
        }
        return instance;
    }

    public String getUserPassword(String login) {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FormathDBContract.TableUser.COLUMN_NAME_PASSWORD
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FormathDBContract.TableUser.COLUMN_NAME_USER_NAME + " = ?";
        String[] selectionArgs = { login };

        Cursor c = database.query(
                FormathDBContract.TableUser.TABLE_NAME,   // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        if (c.getCount() == 1) {
            c.moveToFirst();
            return c.getString(0);
        }
        else
            return null;
    }

    public Medal getMedal(String user, String category, String level) {
        String[] projection = {
                FormathDBContract.TableMedal.COLUMN_NAME_TYPE,
                FormathDBContract.TableMedal.COLUMN_NAME_OBTAINING_DATE
        };
        String selection = FormathDBContract.TableMedal.COLUMN_NAME_CATEGORY + " = ?" +
                " AND " + FormathDBContract.TableMedal.COLUMN_NAME_LEVEL + " = ?" +
                " AND " + FormathDBContract.TableMedal.COLUMN_NAME_USER + " = ?";
        String[] selectionArgs = { category, level, user };

        Cursor c = database.query(
                FormathDBContract.TableMedal.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.getCount() == 1) {
            c.moveToFirst();
            Medal med = new Medal();
            med.setCategory(category);
            med.setLevel(level);
            String str_date = c.getString(1);
            GregorianCalendar date = new GregorianCalendar();
            med.setObtainingDateTime(date);
            return new Medal();
        }
        return null;
    }
}
