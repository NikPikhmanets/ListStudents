package com.example.studentslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public boolean api;

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
                api = false;
                intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.start_rv_activity:
                api = false;
                intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.start_lv_activity_api:
                api = true;
                intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.start_rv_activity_api:
                api = true;
                intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
