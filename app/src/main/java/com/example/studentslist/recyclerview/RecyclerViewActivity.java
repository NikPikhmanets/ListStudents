package com.example.studentslist.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.example.studentslist.MainActivity;
import com.example.studentslist.R;
import com.example.studentslist.Students;
import com.example.studentslist.listview.ListViewActivity;

import java.util.List;

import static com.example.studentslist.R.id.recyclerView;

public class RecyclerViewActivity extends MainActivity {

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_main);
        RecyclerView rv = (RecyclerView) findViewById(recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        List<Students> students = new Students().getStudents();
        adapter = new RecyclerViewAdapter(this, students, getIntent().getBooleanExtra("api", false));
        rv.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(rv);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView1, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                adapter.removeItem(viewHolder.itemView, viewHolder.getAdapterPosition());
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_LV:
                Intent intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.refreshMain:
//                initialiseData();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
