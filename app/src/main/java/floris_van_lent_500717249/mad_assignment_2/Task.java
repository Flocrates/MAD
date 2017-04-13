package floris_van_lent_500717249.mad_assignment_2;

import android.content.ContentValues;

import floris_van_lent_500717249.mad_assignment_2.database.TaskTable;

/**
 * Created by Floris on 30-03-2017.
 */

public class Task {
    private String id;
    private String title;
    private String person;
    private String deadline;
    private String done;

    public Task() {
    }

    public Task(String id, String title, String person, String deadline, String done) {
        this.id = id;
        this.title = title;
        this.person = person;
        this.deadline = deadline;
        this.done = done;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public ContentValues toValues() {
        ContentValues values = new ContentValues(3);

        values.put(TaskTable.COLUMN_ID, id);
        values.put(TaskTable.COLUMN_TITLE, title);
        values.put(TaskTable.COLUMN_PERSON, person);
        values.put(TaskTable.COLUMN_DEADLINE, deadline);
        values.put(TaskTable.COLUMN_DONE, done);

        return values;
    }
}
