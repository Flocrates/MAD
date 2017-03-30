package floris_van_lent_500717249.mad_assignment_1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public List<Serie> retrieveAllSeries() {
        List<Serie> seriesList = new ArrayList<>();
        Cursor cursor = mDatabase.query(SeriesTable.TABLE_SERIES, SeriesTable.ALL_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Serie serie = new Serie();
            serie.setId(cursor.getString(cursor.getColumnIndex(SeriesTable.COLUMN_ID)));
            serie.setTitle(cursor.getString(cursor.getColumnIndex(SeriesTable.COLUMN_TITLE)));
            serie.setYear(cursor.getString(cursor.getColumnIndex(SeriesTable.COLUMN_YEAR)));
            seriesList.add(serie);
        }
        return seriesList;
    }

    public Serie retrieveSerie(String id) {
        Cursor cursor = mDatabase.query(SeriesTable.TABLE_SERIES, SeriesTable.ALL_COLUMNS, SeriesTable.COLUMN_ID + "=?", new String[] {id}, null, null, null);
        cursor.moveToFirst();

        Serie serie = new Serie();
        serie.setId(cursor.getString(cursor.getColumnIndex(SeriesTable.COLUMN_ID)));
        serie.setTitle(cursor.getString(cursor.getColumnIndex(SeriesTable.COLUMN_TITLE)));
        serie.setYear(cursor.getString(cursor.getColumnIndex(SeriesTable.COLUMN_YEAR)));
        return serie;
    }

    public void updateSerie(Serie serie) {
        mDatabase.update(SeriesTable.TABLE_SERIES, serie.toValues(), "id =? ", new String[] {serie.getId()});
    }

    public void deleteSerie(String id) {
        mDatabase.delete(SeriesTable.TABLE_SERIES, "id =? ", new String[] {id});
    }

    public long getSeriesCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, SeriesTable.TABLE_SERIES);
    }
}
