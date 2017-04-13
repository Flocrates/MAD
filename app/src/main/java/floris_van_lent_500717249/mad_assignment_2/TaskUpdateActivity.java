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
    EditText yearField;
    Button updateButton;
    Button deleteButton;
    String currentSerieId;
    Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_update);
        dataSource = new DataSource(this);

        titleField = (EditText) findViewById(R.id.titleField);
        yearField = (EditText) findViewById(R.id.yearField);
        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        currentSerieId = getIntent().getStringExtra("id");
        currentTask = dataSource.retrieveSerie(currentSerieId);

        titleField.setText(currentTask.getTitle());
        yearField.setText(currentTask.getPerson());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(titleField.getText()) && !TextUtils.isEmpty(yearField.getText())) {

                    String id = currentSerieId;
                    String title = titleField.getText().toString();
                    String year = yearField.getText().toString();
                    Task task = new Task(id, title, year);
                    dataSource.updateSerie(task);
                    finish();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.deleteSerie(currentSerieId);
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
