package floris_van_lent_500717249.mad_assignment_2.database;

/**
 * Created by Floris on 30-03-2017.
 *
 */

public class TaskTable {

    // Table name
    public static final String TABLE_TASKS = "tasks";

    // Series Table Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PERSON = "person";
    public static final String COLUMN_DEADLINE = "deadline";
    public static final String COLUMN_DONE = "done";

    public static final String[] ALL_COLUMNS =
            {COLUMN_ID, COLUMN_TITLE, COLUMN_PERSON, COLUMN_DEADLINE, COLUMN_DONE};

    // Creation SQL statement
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_TASKS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_PERSON + " TEXT, " +
                    COLUMN_DEADLINE + " TEXT, " +
                    COLUMN_DONE + " TEXT" + ");";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE " + TABLE_TASKS;
}