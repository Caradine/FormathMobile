package be.formath.formathmobile.Data;

import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import be.formath.formathmobile.Model.Game;
import be.formath.formathmobile.Model.Operation;

public class DataWriter {
    private static DataWriter instance;

    private FormathDbHelper dbHelper;
    private SQLiteDatabase database;

    private DataWriter(Context context) {
        dbHelper = new FormathDbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public static DataWriter getInstance(Context context) {
        if (instance == null) {
            instance = new DataWriter(context);
        }
        return instance;
    }

    public void saveGame(Game game) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        String strDate = game.getGameStartDateTime().get(Calendar.YEAR) + "-" +
                game.getGameStartDateTime().get(Calendar.MONTH) + "-" +
                game.getGameStartDateTime().get(Calendar.DAY_OF_MONTH) + " " +
                game.getGameStartDateTime().get(Calendar.HOUR) + ":" +
                game.getGameStartDateTime().get(Calendar.MINUTE) + ":" +
                game.getGameStartDateTime().get(Calendar.SECOND) + "." +
                game.getGameStartDateTime().get(Calendar.MILLISECOND);
        values.put(FormathDBContract.TableGame.COLUMN_NAME_DATE, strDate);
        values.put(FormathDBContract.TableGame.COLUMN_NAME_USER, game.getUser().getUserName());
        values.put(FormathDBContract.TableGame.COLUMN_NAME_RESULT, game.getResult());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(FormathDBContract.TableGame.TABLE_NAME, null, values);

        for (Operation oper : game.getListOperation()) {
            ContentValues valOper = new ContentValues();
            valOper.put(FormathDBContract.TableOperation.COLUMN_NAME_GAME_ID, newRowId);
            valOper.put(FormathDBContract.TableOperation.COLUMN_NAME_CODE, oper.getCode());
            valOper.put(FormathDBContract.TableOperation.COLUMN_NAME_GIVEN_RESPONSE, oper.getGivenResponse());
            valOper.put(FormathDBContract.TableOperation.COLUMN_NAME_LABEL, oper.getLabel());
            valOper.put(FormathDBContract.TableOperation.COLUMN_NAME_RAW_RESPONSE, oper.getRawResponse());
            valOper.put(FormathDBContract.TableOperation.COLUMN_NAME_RESPONSE, oper.getResponse());
            long id = database.insert(FormathDBContract.TableOperation.TABLE_NAME, null, valOper);
        }
    }
}
