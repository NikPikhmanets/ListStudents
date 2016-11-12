package com.example.studentslist.showprofile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.studentslist.MainActivity;
import com.example.studentslist.R;

public class ShowProfileActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        if (intent != null) {
            Uri uri = getIntent().getData();
            if(uri != null) {
                String data = uri.toString();
                int pos;
                int posGit = data.indexOf("github.com");
                int posGplus = data.indexOf("https://plus.google.com/u/0/");

                if (posGit != -1) {

                    data = data.substring(19);
                    if (data.length() > 0) {
                        pos = data.indexOf("/");
                        if (pos != -1)
                            data = data.substring(0, pos);
                        intent.putExtra("gitHubID", data);
                    }
                } else if (posGplus != -1) {

                    data = data.substring(28);
                    intent.putExtra("googlePlusID", data);
                }
            }
        }

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ShowProfileFragment())
                    .commit();
        }
    }
}
