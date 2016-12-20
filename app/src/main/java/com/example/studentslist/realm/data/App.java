package com.example.studentslist.realm.data;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration =
                new RealmConfiguration.Builder()
                .name("liststudents.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
