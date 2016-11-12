package com.example.studentslist.showprofile;

import android.os.Bundle;

import com.example.studentslist.MainActivity;
import com.example.studentslist.R;

public class ShowProfileActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Intent intent = getIntent();
//        String action = intent.getAction();
//        Uri data = intent.getData();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ShowProfileFragment())
                    .commit();
        }
    }
}
