package edu.washington.danishb.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String phoneNumber = intent.getStringExtra("Number");
        String message = intent.getStringExtra("Message");
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, null, null);
    }
}
