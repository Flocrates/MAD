package floris_van_lent_500717249.mad_assignment_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import floris_van_lent_500717249.mad_assignment_1.database.DataSource;

public class SerieUpdateActivity extends AppCompatActivity {
    DataSource dataSource;
    EditText titleField;
    EditText yearField;
    Button updateButton;
    Button deleteButton;
    String currentSerieId;
    Serie currentSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_update);
        dataSource = new DataSource(this);

        titleField = (EditText) findViewById(R.id.titleField);
        yearField = (EditText) findViewById(R.id.yearField);
        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        currentSerieId = getIntent().getStringExtra("id");
        currentSerie = dataSource.retrieveSerie(currentSerieId);

        titleField.setText(currentSerie.getTitle());
        yearField.setText(currentSerie.getYear());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(titleField.getText()) && !TextUtils.isEmpty(yearField.getText())) {

                    String id = currentSerieId;
                    String title = titleField.getText().toString();
                    String year = yearField.getText().toString();
                    Serie serie = new Serie(id, title, year);
                    dataSource.updateSerie(serie);
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

    // Overrides up butten to have similar functionality as onBackPressed so previous activitu isn't reloaded
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
