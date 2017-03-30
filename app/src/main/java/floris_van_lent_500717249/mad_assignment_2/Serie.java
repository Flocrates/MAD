package floris_van_lent_500717249.mad_assignment_2;

import android.content.ContentValues;

import floris_van_lent_500717249.mad_assignment_2.database.SeriesTable;

/**
 * Created by Floris on 30-03-2017.
 */

public class Serie {
    private String id;
    private String title;
    private String person;

    public Serie() {
    }

    public Serie(String id, String title, String person) {
        this.id = id;
        this.title = title;
        this.person = person;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return person;
    }

    public void setYear(String person) {
        this.person = person;
    }

    public ContentValues toValues() {
        ContentValues values = new ContentValues(3);

        values.put(SeriesTable.COLUMN_ID, id);
        values.put(SeriesTable.COLUMN_TITLE, title);
        values.put(SeriesTable.COLUMN_PERSON, person);

        return values;
    }
}
