package floris_van_lent_500717249.mad_assignment_2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Floris on 30-03-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_FILE_NAME = "tasks.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TaskTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TaskTable.SQL_DELETE_TABLE);
        onCreate(db);
    }
}
