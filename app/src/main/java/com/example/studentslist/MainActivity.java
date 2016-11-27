package com.example.studentslist;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.studentslist.contact.PhoneBookActivity;
import com.example.studentslist.listview.ListViewActivity;
import com.example.studentslist.recyclerview.RecyclerViewActivity;

import static com.example.studentslist.R.id.PhoneBookBtn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonListView;
    Button buttonRecyclerView;

    Button buttonListViewApi;
    Button buttonRecyclerViewApi;

    Button imageButton;
    Button phoneButton;

    private EventsReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonListView = (Button) findViewById(R.id.start_lv_activity);
        buttonRecyclerView = (Button) findViewById(R.id.start_rv_activity);
        buttonListViewApi = (Button) findViewById(R.id.start_lv_activity_api);
        buttonRecyclerViewApi = (Button) findViewById(R.id.start_rv_activity_api);
        imageButton = (Button) findViewById(R.id.image_button);
        phoneButton = (Button) findViewById(R.id.PhoneBookBtn);

        buttonListView.setOnClickListener(this);
        buttonRecyclerView.setOnClickListener(this);
        buttonListViewApi.setOnClickListener(this);
        buttonRecyclerViewApi.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        phoneButton.setOnClickListener(this);

        myReceiver = new EventsReceiver();

//        Intent intent = getIntent();
//        if (intent.getAction() != null) {
//            Toast.makeText(this, "opa", Toast.LENGTH_SHORT).show();
//        }
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.start_lv_activity:
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
                intent = new Intent(this, ListViewActivity.class);
                intent.putExtra("api", true);
                startActivity(intent);
                break;
            case R.id.start_rv_activity_api:
                intent = new Intent(this, RecyclerViewActivity.class);
                intent.putExtra("api", true);
                startActivity(intent);
                break;
            case R.id.image_button:
                intent = new Intent(this, ImageActivity.class);
                startActivity(intent);
                break;
            case PhoneBookBtn:
                intent = new Intent(this, PhoneBookActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }
}
