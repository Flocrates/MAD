package floris_van_lent_500717249.mad_assignment_1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import floris_van_lent_500717249.mad_assignment_1.Serie;

/**
 * Created by Floris on 30-03-2017.
 */

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDatabaseHelper;

    public DataSource(Context context) {
        this.mContext = context;
        mDatabaseHelper = new DatabaseHelper(mContext);
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        mDatabase.close();
    }

    public Serie createSerie(Serie serie) {
        ContentValues values = serie.toValues();
        mDatabase.insert(SeriesTable.TABLE_SERIES, null, values);
        return serie;
    }

    public long getSeriesCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, SeriesTable.TABLE_SERIES);
    }
}
