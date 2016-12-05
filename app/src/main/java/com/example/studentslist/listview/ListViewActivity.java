package com.example.studentslist.listview;

import android.os.Bundle;
import android.widget.ListView;

import com.example.studentslist.MainActivity;
import com.example.studentslist.R;
import com.example.studentslist.Students;

import java.util.List;

public class ListViewActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_main);

        ListView listV = (ListView) findViewById(R.id.listview_students);

        final List<Students> students = new Students().getStudents();

        // TODO: 08.11.2016  добавить определение api (true/false)
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, students);
        listV.setAdapter(listViewAdapter);
    }
}
