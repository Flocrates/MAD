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

import floris_van_lent_500717249.mad_assignment_2.database.DataSource;

public class MainActivity extends AppCompatActivity {
    private List<Task> seriesList = new ArrayList<>();
    private List<Task> seriesFromDatabase = new ArrayList<>();
    private RecyclerView recyclerView;
    private TasksRecyclerAdapter mAdapter;

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
            Toast.makeText(this, "Added mock tasks to database!", Toast.LENGTH_LONG).show();
        }

        // Instantiate series list from database
//        seriesFromDatabase = mDataSource.retrieveAllSeries();
//        Toast.makeText(this, "Series retrieved!", Toast.LENGTH_SHORT).show();
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        mAdapter = new TasksRecyclerAdapter(seriesFromDatabase);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskCreationActivity.class);
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
        Toast.makeText(this, "Tasks updated!", Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TasksRecyclerAdapter(seriesFromDatabase);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void mockSeries() {
        Task task1 = new Task("1", "Keuken Schoonmaken", "31/03/2017 14:00");
        Task task2 = new Task("2", "Boodschappen doen", "02/04/2017 18:00");
        Task task3 = new Task("3", "Badkamer opruimen", "08/04/2017 20:00");
        Task task4 = new Task("4", "Afwas doen", "12/04/2017 14:00");
        Task task5 = new Task("5", "Hond wassen", "14/04/2017 16:00");
        Task task6 = new Task("6", "Pasen met schoonouders :(", "16/05/2017 13:00");
        Task task7 = new Task("7", "Vuilnis buitenzetten", "18/05/2017 08:00");

        seriesList.add(task1);
        seriesList.add(task2);
        seriesList.add(task3);
        seriesList.add(task4);
        seriesList.add(task5);
        seriesList.add(task6);
        seriesList.add(task7);

        for (Task task : seriesList) {
            try {
                mDataSource.createSerie(task);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }
}
