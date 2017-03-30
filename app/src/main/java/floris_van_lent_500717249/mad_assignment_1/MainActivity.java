package floris_van_lent_500717249.mad_assignment_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Serie> seriesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SeriesRecyclerAdapter mAdapter;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new SeriesRecyclerAdapter(seriesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mockSeries();
    }

    private void mockSeries() {
        Serie serie1  = new Serie ("How I Met Your Mother", "2005 - 2012");
        Serie serie2  = new Serie ("FRIENDS", "1995 - 2002");
        Serie serie3  = new Serie ("The Newsroom", "2005");
        Serie serie4  = new Serie ("Orphan Black", "2012");
        Serie serie5  = new Serie ("Game of Thrones", "2011");
        Serie serie6  = new Serie ("Attack on Titan", "2007");
        Serie serie7  = new Serie ("Fullmetal Alchemist", "2010");
        Serie serie8  = new Serie ("Tom & Jerry", "1965");
        Serie serie9  = new Serie ("Spongebob Squarepants", "1996");
        Serie serie10 = new Serie ("Fairly Odd Parents", "1998");

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

        mAdapter.notifyDataSetChanged();
    }
}
