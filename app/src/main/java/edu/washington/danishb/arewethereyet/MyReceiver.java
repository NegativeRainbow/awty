package edu.washington.danishb.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String phoneNumber = intent.getStringExtra("Number");
        String message = intent.getStringExtra("Message");
        int length = phoneNumber.length();
        String formattedPhoneNumber = "-" + phoneNumber.substring(length-4);
        formattedPhoneNumber = ") " + phoneNumber.substring(length - 7, length -4) + formattedPhoneNumber;
        formattedPhoneNumber = "(" + phoneNumber.substring(length - 10, length-7) + formattedPhoneNumber;
        formattedPhoneNumber = phoneNumber.substring(length-10) + formattedPhoneNumber;
        Toast.makeText(context, formattedPhoneNumber + ": " + message, Toast.LENGTH_LONG).show();
    }
}
