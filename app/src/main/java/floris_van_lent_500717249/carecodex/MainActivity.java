package floris_van_lent_500717249.carecodex;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import floris_van_lent_500717249.carecodex.database.DataSource;

public class MainActivity extends AppCompatActivity {
    private List<XDS> seriesList = new ArrayList<>();
    private List<XDS> seriesFromDatabase = new ArrayList<>();
    private RecyclerView recyclerView;
    private EntryRecyclerAdapter mAdapter;

    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataSource = new DataSource(this);
        mDataSource.open();
        Toast.makeText(this, "Database connected!", Toast.LENGTH_SHORT).show();

        // Seed database if necessary
        long seriesCount = mDataSource.getXdsEntryCount();
        if (seriesCount == 0) {
            mockSeries();
            Toast.makeText(this, "Added mock entries to database!", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EntryCreationActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
        seriesFromDatabase = mDataSource.retrieveAllXdsEntries();
        Toast.makeText(this, "Entries updated!", Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new EntryRecyclerAdapter(seriesFromDatabase);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void mockSeries() {
        XDS entry1 = new XDS("1", "Gewicht", "65 kg");
        XDS entry2 = new XDS("2", "Bloeddruk Bovendruk", "140 mmHg");
        XDS entry3 = new XDS("3", "Bloeddruk Onderdruk", "90 mmHg");

        seriesList.add(entry1);
        seriesList.add(entry2);
        seriesList.add(entry3);

        for (XDS xds : seriesList) {
            try {
                mDataSource.createSerie(xds);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }
}
