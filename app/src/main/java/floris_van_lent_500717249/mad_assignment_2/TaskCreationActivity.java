package floris_van_lent_500717249.mad_assignment_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import floris_van_lent_500717249.mad_assignment_2.database.DataSource;

public class TaskCreationActivity extends AppCompatActivity {
    DataSource dataSource;
    EditText titleField;
    EditText personField;
    EditText deadlineField;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);
        dataSource = new DataSource(this);

        titleField = (EditText) findViewById(R.id.titleField);
        personField = (EditText) findViewById(R.id.personField);
        deadlineField = (EditText) findViewById(R.id.deadlineField);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(titleField.getText()) && !TextUtils.isEmpty(personField.getText())) {
                    if (!TextUtils.isEmpty(deadlineField.getText())) {

                        long id = (dataSource.getTasksCount() + 100);
                        String title = titleField.getText().toString();
                        String person = personField.getText().toString();
                        String deadline = deadlineField.getText().toString();
                        Task task = new Task("" + id, title, person, deadline, "0");
                        dataSource.createTask(task);
                        finish();
                    }
                }
            }
        });
    }

    // Overrides up button to have similar functionality as onBackPressed so previous activity isn't reloaded
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
