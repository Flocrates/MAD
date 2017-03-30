package floris_van_lent_500717249.mad_assignment_2;

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

import floris_van_lent_500717249.mad_assignment_1.R;
import floris_van_lent_500717249.mad_assignment_2.database.DataSource;

public class MainActivity extends AppCompatActivity {
    private List<Serie> seriesList = new ArrayList<>();
    private List<Serie> seriesFromDatabase = new ArrayList<>();
    private RecyclerView recyclerView;
    private SeriesRecyclerAdapter mAdapter;

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
        long seriesCount = mDataSource.getSeriesCount();
        if (seriesCount == 0) {
            mockSeries();
            Toast.makeText(this, "Added mock series to database!", Toast.LENGTH_LONG).show();
        }

        // Instantiate series list from database
//        seriesFromDatabase = mDataSource.retrieveAllSeries();
//        Toast.makeText(this, "Series retrieved!", Toast.LENGTH_SHORT).show();
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        mAdapter = new SeriesRecyclerAdapter(seriesFromDatabase);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SerieCreationActivity.class);
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
        seriesFromDatabase = mDataSource.retrieveAllSeries();
        Toast.makeText(this, "Series updated!", Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new SeriesRecyclerAdapter(seriesFromDatabase);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void mockSeries() {
        Serie serie1  = new Serie ("1", "How I Met Your Mother", "2005 - 2012");
        Serie serie2  = new Serie ("2", "FRIENDS", "1995 - 2002");
        Serie serie3  = new Serie ("3", "The Newsroom", "2005");
        Serie serie4  = new Serie ("4", "Orphan Black", "2012");
        Serie serie5  = new Serie ("5", "Game of Thrones", "2011");
        Serie serie6  = new Serie ("6", "Attack on Titan", "2007");
        Serie serie7  = new Serie ("7", "Fullmetal Alchemist", "2010");
        Serie serie8  = new Serie ("8", "Tom & Jerry", "1965");
        Serie serie9  = new Serie ("9", "Spongebob Squarepants", "1996");
        Serie serie10 = new Serie ("10", "Fairly Odd Parents", "1998");

        seriesList.add(serie1);
        seriesList.add(serie2);
        seriesList.add(serie3);
        seriesList.add(serie4);
        seriesList.add(serie5);
        seriesList.add(serie6);
        seriesList.add(serie7);
        seriesList.add(serie8);
        seriesList.add(serie9);
        seriesList.add(serie10);

        for (Serie serie : seriesList) {
            try {
                mDataSource.createSerie(serie);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }
}
