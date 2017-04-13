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
    private List<Task> taskList = new ArrayList<>();
    private List<Task> tasksFromDatabase = new ArrayList<>();
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
        long tasksCount = mDataSource.getTasksCount();
        if (tasksCount == 0) {
            mockTasks();
            Toast.makeText(this, "Added mock tasks to database!", Toast.LENGTH_LONG).show();
        }

        // Instantiate series list from database
//        tasksFromDatabase = mDataSource.retrieveAllTasks();
//        Toast.makeText(this, "Series retrieved!", Toast.LENGTH_SHORT).show();
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        mAdapter = new TasksRecyclerAdapter(tasksFromDatabase);
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
        tasksFromDatabase = mDataSource.retrieveAllTasks();
        Toast.makeText(this, "Tasks updated!", Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TasksRecyclerAdapter(tasksFromDatabase);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void mockTasks() {
        Task task1 = new Task("1", "App afmaken", "Floris", "14/04/2017 17:00", "1");
        Task task2 = new Task("2", "Naar school fietsen", "Floris", "14/04/2017 17:15", "1");
        Task task7 = new Task("3", "Floris een 8,0 geven", "Marco", "14/04/2017 17:30", "0");
        Task task3 = new Task("4", "Badkamer schoonmaken", "Henk", "14/04/2017 20:00", "0");
        Task task4 = new Task("5", "Afwas doen", "Henk", "15/04/2017 14:00", "0");
        Task task5 = new Task("6", "Hond wassen", "Henk", "15/04/2017 16:00", "0");
        Task task6 = new Task("7", "Pasen met schoonouders :(", "Floris", "16/05/2017 12:00", "0");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);

        for (Task task : taskList) {
            try {
                mDataSource.createTask(task);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }
}
