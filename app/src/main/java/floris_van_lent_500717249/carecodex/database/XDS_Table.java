package floris_van_lent_500717249.carecodex.database;

/**
 * Created by Floris on 30-03-2017.
 *
 */

public class XDS_Table {

    // Table name
    public static final String TABLE_NAME = "xds";

    // Series Table Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_VALUE = "value";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_TYPE, COLUMN_VALUE};

    // Creation SQL statement
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_TYPE + " TEXT NOT NULL, " +
                    COLUMN_VALUE + " TEXT NOT NULL" + ");";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE " + TABLE_NAME;
}