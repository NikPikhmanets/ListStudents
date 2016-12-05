package com.example.studentslist.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.studentslist.MainActivity;
import com.example.studentslist.R;
import com.example.studentslist.Students;

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
        adapter = new RecyclerViewAdapter(this, students);
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
}
