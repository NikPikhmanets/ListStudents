package com.example.studentslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studentslist.listview.ListViewActivity;
import com.example.studentslist.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonListView;
    Button buttonRecyclerView;

    Button buttonListViewApi;
    Button buttonRecyclerViewApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonListView = (Button) findViewById(R.id.start_lv_activity);
        buttonRecyclerView = (Button) findViewById(R.id.start_rv_activity);
        buttonListViewApi = (Button) findViewById(R.id.start_lv_activity_api);
        buttonRecyclerViewApi = (Button) findViewById(R.id.start_rv_activity_api);

        buttonListView.setOnClickListener(this);
        buttonRecyclerView.setOnClickListener(this);
        buttonListViewApi.setOnClickListener(this);
        buttonRecyclerViewApi.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.start_lv_activity:
//                Toast.makeText(getApplicationContext(), "Sorry, поки що не доробив((", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ListViewActivity.class);
                intent.putExtra("api", false);
                startActivity(intent);
                break;
            case R.id.start_rv_activity:
                intent = new Intent(this, RecyclerViewActivity.class);
                intent.putExtra("api", false);
                startActivity(intent);
                break;
            case R.id.start_lv_activity_api:
                Toast.makeText(getApplicationContext(), "Sorry, поки що не доробив((", Toast.LENGTH_SHORT).show();
//                intent = new Intent(this, ListViewActivity.class);
//                intent.putExtra("api", true);
//                startActivity(intent);
                break;
            case R.id.start_rv_activity_api:
                intent = new Intent(this, RecyclerViewActivity.class);
                intent.putExtra("api", true);
                startActivity(intent);
                break;
        }
    }
}
