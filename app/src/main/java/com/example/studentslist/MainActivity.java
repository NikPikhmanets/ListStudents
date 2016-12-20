package com.example.studentslist;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.studentslist.contact.PhoneBookActivity;
import com.example.studentslist.image.ImageActivity;
import com.example.studentslist.listview.ListViewActivity;
import com.example.studentslist.realm.RealmActivity;
import com.example.studentslist.recyclerview.RecyclerViewActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button buttonListView;
    Button buttonRecyclerView;

    Button buttonListViewApi;
    Button buttonRecyclerViewApi;

    Button imageButton;
    Button phoneButton;
    Button realmButton;

    private EventsReceiver myReceiver;

    private static final int NO_API = 0;
    private static final int API = 1;
    private static int KEY;

    @IntDef({NO_API, API})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PARAM {}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
        myReceiver = new EventsReceiver();
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.start_lv_activity:
                intent = new Intent(this, ListViewActivity.class);
                setParameter(NO_API);
                startActivity(intent);
                break;
            case R.id.start_rv_activity:
                intent = new Intent(this, RecyclerViewActivity.class);
                setParameter(NO_API);
                startActivity(intent);
                break;
            case R.id.start_lv_activity_api:
                intent = new Intent(this, ListViewActivity.class);
                setParameter(API);
                startActivity(intent);
                break;
            case R.id.start_rv_activity_api:
                intent = new Intent(this, RecyclerViewActivity.class);
                setParameter(API);
                startActivity(intent);
                break;
            case R.id.image_button:
                intent = new Intent(this, ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.PhoneBookBtn:
                intent = new Intent(this, PhoneBookActivity.class);
                startActivity(intent);
                break;
            case R.id.realmListBtn:
                intent = new Intent(this, RealmActivity.class);
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

    private void initButton() {

        buttonListView = (Button) findViewById(R.id.start_lv_activity);
        buttonRecyclerView = (Button) findViewById(R.id.start_rv_activity);
        buttonListViewApi = (Button) findViewById(R.id.start_lv_activity_api);
        buttonRecyclerViewApi = (Button) findViewById(R.id.start_rv_activity_api);
        imageButton = (Button) findViewById(R.id.image_button);
        phoneButton = (Button) findViewById(R.id.PhoneBookBtn);
        realmButton = (Button) findViewById(R.id.realmListBtn);

        buttonListView.setOnClickListener(this);
        buttonRecyclerView.setOnClickListener(this);
        buttonListViewApi.setOnClickListener(this);
        buttonRecyclerViewApi.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        phoneButton.setOnClickListener(this);
        realmButton.setOnClickListener(this);
    }

    public static int getParameter() {
        return KEY;
    }

    private void setParameter(@PARAM int key) {
        KEY = key;
    }
}
