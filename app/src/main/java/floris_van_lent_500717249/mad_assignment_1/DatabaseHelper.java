package floris_van_lent_500717249.mad_assignment_1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Floris on 30-03-2017.
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "seriesManager";

    // Table name
    private static final String TABLE_SERIES = "series";

    // Series Table Column names
    private static final String COLUMN_ID    = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEAR  = "year";

    // Creation SQL statement
    private static final String DATABASE_CREATION =
            "CREATE TABLE " + TABLE_SERIES +
                    "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_YEAR + " TEXT " +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create
    public void addSerie(Serie serie) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SERIES, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    // Retrieve one
    public Serie getSerie(int id) {}

    // Retrieve all
    public List<Serie> getAllSeries() {}

    // Retrieve count
    public int getSeriesCount() {}

    // Update
    public int updateSerie(Serie serie) {}

    // Delete
    public void deleteSerie(Serie serie) {}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Only drop the old table if it existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIES);

        // Recreate tables
        onCreate(db);
    }
}
