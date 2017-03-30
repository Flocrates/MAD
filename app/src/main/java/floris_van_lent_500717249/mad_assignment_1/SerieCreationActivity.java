package floris_van_lent_500717249.mad_assignment_1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import floris_van_lent_500717249.mad_assignment_1.database.DataSource;

public class SerieCreationActivity extends AppCompatActivity {
    DataSource dataSource;
    EditText titleField;
    EditText yearField;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataSource = new DataSource(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_creation);

        titleField = (EditText) findViewById(R.id.titleField);
        yearField = (EditText) findViewById(R.id.yearField);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(titleField.getText()) && !TextUtils.isEmpty(yearField.getText())) {

                    long id = (dataSource.getSeriesCount() + 1);
                    String title = titleField.getText().toString();
                    String year = yearField.getText().toString();
                    System.out.println("" + id);
                    Serie serie = new Serie("" + id, title, year);
                    dataSource.createSerie(serie);
                    finish();
                }
            }
        });
    }
}
