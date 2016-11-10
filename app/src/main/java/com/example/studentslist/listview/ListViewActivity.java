package com.example.studentslist.listview;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.studentslist.MainActivity;
import com.example.studentslist.R;
import com.example.studentslist.Students;

import java.util.List;

public class ListViewActivity extends MainActivity {

    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_main);

        ListView listV = (ListView) findViewById(R.id.listview_students);

        final List<Students> students = new Students().getStudents();

        // TODO: 08.11.2016  добавить определение api (true/false)
        listViewAdapter = new ListViewAdapter(this, students, true);
        listV.setAdapter(listViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_RV) {
            finish();
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
