package floris_van_lent_500717249.mad_assignment_2.database;

/**
 * Created by Floris on 30-03-2017.
 *
 */

public class TaskTable {

    // Table name
    public static final String TABLE_SERIES = "series";

    // Series Table Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PERSON = "year";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_TITLE, COLUMN_PERSON};

    // Creation SQL statement
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_SERIES + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_PERSON + " TEXT" + ");";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE " + TABLE_SERIES;
}