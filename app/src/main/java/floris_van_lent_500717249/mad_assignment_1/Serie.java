package floris_van_lent_500717249.mad_assignment_1;

import android.content.ContentValues;

import floris_van_lent_500717249.mad_assignment_1.database.SeriesTable;

/**
 * Created by Floris on 30-03-2017.
 */

public class Serie {
    private String id;
    private String title;
    private String year;

    public Serie() {
    }

    public Serie(String id, String title, String year) {
        this.id = id;
        this.title = title;
        this.year = year;
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
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ContentValues toValues() {
        ContentValues values = new ContentValues(3);

        values.put(SeriesTable.COLUMN_ID, id);
        values.put(SeriesTable.COLUMN_TITLE, title);
        values.put(SeriesTable.COLUMN_YEAR, year);

        return values;
    }
}
