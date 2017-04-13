package floris_van_lent_500717249.carecodex.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import floris_van_lent_500717249.carecodex.XDS;

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

    public XDS createSerie(XDS xds) {
        ContentValues values = xds.toValues();
        mDatabase.insert(XDS_Table.TABLE_NAME, null, values);
        return xds;
    }

    public List<XDS> retrieveAllXdsEntries() {
        List<XDS> seriesList = new ArrayList<>();
        Cursor cursor = mDatabase.query(XDS_Table.TABLE_NAME, XDS_Table.ALL_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext()) {
            XDS xds = new XDS();
            xds.setId(cursor.getString(cursor.getColumnIndex(XDS_Table.COLUMN_ID)));
            xds.setType(cursor.getString(cursor.getColumnIndex(XDS_Table.COLUMN_TYPE)));
            xds.setValue(cursor.getString(cursor.getColumnIndex(XDS_Table.COLUMN_VALUE)));
            seriesList.add(xds);
        }
        return seriesList;
    }

    public XDS retrieveXdsEntry(String id) {
        Cursor cursor = mDatabase.query(XDS_Table.TABLE_NAME, XDS_Table.ALL_COLUMNS, XDS_Table.COLUMN_ID + "=?", new String[] {id}, null, null, null);
        cursor.moveToFirst();

        XDS xds = new XDS();
        xds.setId(cursor.getString(cursor.getColumnIndex(XDS_Table.COLUMN_ID)));
        xds.setType(cursor.getString(cursor.getColumnIndex(XDS_Table.COLUMN_TYPE)));
        xds.setValue(cursor.getString(cursor.getColumnIndex(XDS_Table.COLUMN_VALUE)));
        return xds;
    }

    public void updateXdsEntry(XDS xds) {
        mDatabase.update(XDS_Table.TABLE_NAME, xds.toValues(), "id =? ", new String[] {xds.getId()});
    }

    public void deleteXdsEntry(String id) {
        mDatabase.delete(XDS_Table.TABLE_NAME, "id =? ", new String[] {id});
    }

    public long getXdsEntryCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, XDS_Table.TABLE_NAME);
    }
}
