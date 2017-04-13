package floris_van_lent_500717249.carecodex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import floris_van_lent_500717249.carecodex.database.DataSource;

public class EntryUpdateActivity extends AppCompatActivity {
    DataSource dataSource;
    EditText titleField;
    EditText yearField;
    Button updateButton;
    Button deleteButton;
    String currentEntryId;
    XDS currentXDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_update);
        dataSource = new DataSource(this);

        titleField = (EditText) findViewById(R.id.titleField);
        yearField = (EditText) findViewById(R.id.yearField);
        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        currentEntryId = getIntent().getStringExtra("id");
        currentXDS = dataSource.retrieveXdsEntry(currentEntryId);

        titleField.setText(currentXDS.getType());
        yearField.setText(currentXDS.getValue());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(titleField.getText()) && !TextUtils.isEmpty(yearField.getText())) {

                    String id = currentEntryId;
                    String title = titleField.getText().toString();
                    String year = yearField.getText().toString();
                    XDS xds = new XDS(id, title, year);
                    dataSource.updateXdsEntry(xds);

                    finish();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.deleteXdsEntry(currentEntryId);
                finish();
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
