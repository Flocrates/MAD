package floris_van_lent_500717249.mad_assignment_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import floris_van_lent_500717249.mad_assignment_2.database.DataSource;

public class TaskUpdateActivity extends AppCompatActivity {
    DataSource dataSource;
    EditText titleField;
    EditText personField;
    EditText deadlineField;
    EditText doneField;
    Button updateButton;
    Button deleteButton;
    String currentTaskId;
    Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_update);
        dataSource = new DataSource(this);

        titleField = (EditText) findViewById(R.id.titleField);
        personField = (EditText) findViewById(R.id.personField);
        deadlineField = (EditText) findViewById(R.id.deadlineField);
        doneField = (EditText) findViewById(R.id.doneField);
        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        currentTaskId = getIntent().getStringExtra("id");
        currentTask = dataSource.retrieveTask(currentTaskId);

        titleField.setText(currentTask.getTitle());
        personField.setText(currentTask.getPerson());
        deadlineField.setText(currentTask.getDeadline());
        doneField.setText(currentTask.getDone());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(titleField.getText()) && !TextUtils.isEmpty(personField.getText())) {
                    if(!TextUtils.isEmpty(deadlineField.getText()) && !TextUtils.isEmpty(doneField.getText())) {
                        String id = currentTaskId;
                        String title = titleField.getText().toString();
                        String person = personField.getText().toString();
                        String deadline = deadlineField.getText().toString();
                        String done = doneField.getText().toString();
                        Task task = new Task(id, title, person, deadline, done);
                        dataSource.updateTask(task);
                        finish();
                    }
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.deleteTask(currentTaskId);
                finish();
            }
        });
    }

    // Overrides up button to have similar functionality as onBackPressed so previous activitu isn't reloaded
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
