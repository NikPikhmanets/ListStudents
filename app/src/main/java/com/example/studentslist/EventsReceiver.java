package com.example.studentslist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class EventsReceiver extends BroadcastReceiver {

    boolean flag = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            int microphone = intent.getIntExtra("microphone", -1);
            String messageToast;
            String endMessage;

            if (microphone == 0) {
                messageToast = "Наушники";
                endMessage = "ы!";
            } else {
                messageToast = "Гарнитура";
                endMessage = "а!";
            }

            switch (state) {
                case 0:
                    if (flag) {

                        flag = false;
                        Toast.makeText(context, messageToast + " отключен" + endMessage, Toast.LENGTH_SHORT)
                                .show();
                    }
                    break;
                case 1:
                    flag = true;
                    Toast.makeText(context, messageToast + " подключен" + endMessage, Toast.LENGTH_SHORT)
                            .show();
                    break;
            }
        }
    }
}
