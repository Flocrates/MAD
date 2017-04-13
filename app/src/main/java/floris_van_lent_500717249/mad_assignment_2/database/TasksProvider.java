package floris_van_lent_500717249.mad_assignment_2.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Floris on 13-04-2017.
 */

public class TasksProvider extends ContentProvider{
    private static final String AUTHORITY = "com.floris_van_lent_500717249.mad_assignment_2.tasksprovider";
    private static final String BASE_PATH = "tasks";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation
    private static final int TASKS = 1;
    private static final int TASKS_ID = 2;

    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, TASKS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TASKS_ID);
    }

    private SQLiteDatabase mDatabase;

    @Override
    public boolean onCreate() {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        mDatabase = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return mDatabase.query(TaskTable.TABLE_TASKS, TaskTable.ALL_COLUMNS, s, null, null, null, null, null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = mDatabase.insert(TaskTable.TABLE_TASKS, null, contentValues);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return mDatabase.delete(TaskTable.TABLE_TASKS, s, strings);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return mDatabase.update(TaskTable.TABLE_TASKS, contentValues, s, strings);
    }
}
