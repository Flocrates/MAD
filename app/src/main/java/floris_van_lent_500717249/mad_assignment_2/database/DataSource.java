package floris_van_lent_500717249.mad_assignment_2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import floris_van_lent_500717249.mad_assignment_2.Task;

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

    public Task createSerie(Task task) {
        ContentValues values = task.toValues();
        mDatabase.insert(TaskTable.TABLE_SERIES, null, values);
        return task;
    }

    public List<Task> retrieveAllSeries() {
        List<Task> seriesList = new ArrayList<>();
        Cursor cursor = mDatabase.query(TaskTable.TABLE_SERIES, TaskTable.ALL_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_ID)));
            task.setTitle(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_TITLE)));
            task.setYear(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_PERSON)));
            seriesList.add(task);
        }
        return seriesList;
    }

    public Task retrieveSerie(String id) {
        Cursor cursor = mDatabase.query(TaskTable.TABLE_SERIES, TaskTable.ALL_COLUMNS, TaskTable.COLUMN_ID + "=?", new String[] {id}, null, null, null);
        cursor.moveToFirst();

        Task task = new Task();
        task.setId(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_ID)));
        task.setTitle(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_TITLE)));
        task.setYear(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_PERSON)));
        return task;
    }

    public void updateSerie(Task task) {
        mDatabase.update(TaskTable.TABLE_SERIES, task.toValues(), "id =? ", new String[] {task.getId()});
    }

    public void deleteSerie(String id) {
        mDatabase.delete(TaskTable.TABLE_SERIES, "id =? ", new String[] {id});
    }

    public long getSeriesCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, TaskTable.TABLE_SERIES);
    }
}
