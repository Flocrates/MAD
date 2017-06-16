package floris_van_lent_500717249.mad_assignment_2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
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
import floris_van_lent_500717249.mad_assignment_2.database.TaskTable;
import floris_van_lent_500717249.mad_assignment_2.database.TasksProvider;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private List<Task> taskList = new ArrayList<>();
    private List<Task> tasksFromDatabase = new ArrayList<>();
    private RecyclerView recyclerView;
    private TasksRecyclerAdapter mAdapter;

    DataSource mDataSource;

    CursorAdapter cursorAdapter;


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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskCreationActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        getLoaderManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = getContentResolver().query(TasksProvider.CONTENT_URI, TaskTable.ALL_COLUMNS, null, null, null, null);


        mDataSource.open();
        tasksFromDatabase = mDataSource.retrieveAllTasks();
        Toast.makeText(this, "Tasks updated!", Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TasksRecyclerAdapter(tasksFromDatabase, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        cursor.close();
    }

    private void mockTasks() {
        Task task1 = new Task("8", "Pizza Bakken", "Floris", "22/06/2017 18:15", "0");
        Task task2 = new Task("1", "Naar school fietsen", "Floris", "16/06/2017 11:15", "1");
        Task task3 = new Task("3", "Badkamer schoonmaken", "Henk", "17/06/2017 20:00", "0");
        Task task4 = new Task("4", "Afwas doen", "Henk", "18/06/2017 14:00", "0");
        Task task5 = new Task("5", "Hond wassen", "Henk", "19/06/2017 16:00", "0");
        Task task6 = new Task("6", "Uit eten met schoonouders :(", "Floris", "20/06/2017 12:00", "0");
        Task task7 = new Task("2", "Floris een 7,0 geven", "Marco", "16/06/2017 11:45", "0");

        taskList.add(task2);
        taskList.add(task7);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task1);

        for (Task task : taskList) {
            try {
                getContentResolver().insert(TasksProvider.CONTENT_URI, task.toValues());
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, TasksProvider.CONTENT_URI, TaskTable.ALL_COLUMNS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
