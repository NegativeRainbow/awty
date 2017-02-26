package edu.washington.danishb.arewethereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    PendingIntent toSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            AlarmManager manager = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
            @Override
            public void onClick(View view) {
                if(button.getText().equals("Start")){
                    EditText editPhoneNumber = (EditText) findViewById(R.id.editNumber);
                    EditText editMessage = (EditText) findViewById(R.id.editMessage);
                    EditText editInterval = (EditText) findViewById(R.id.editInterval);
                    String message = editMessage.getText().toString();
                    String phoneNumber = editPhoneNumber.getText().toString();
                    int interval = 1000 * 60 * parseInt(editInterval.getText().toString());
                    if(message.length() == 0) {
                        Toast.makeText(MainActivity.this, "Your message cannot be blank", Toast.LENGTH_SHORT).show();
                    } else if(!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
                            Toast.makeText(MainActivity.this, "You must enter a valid phone number", Toast.LENGTH_SHORT).show();
                    } else if(interval <= 0) {
                        Toast.makeText(MainActivity.this, "Your interval must be longer than 0", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                        intent.putExtra("Number", editPhoneNumber.getText().toString());
                        intent.putExtra("Message", message);
                        toSend = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                        manager.setRepeating(AlarmManager.RTC_WAKEUP, interval, interval, toSend);
                        button.setText("Stop");
                    }
                } else {
                    manager.cancel(toSend);
                    button.setText("Start");
                }
            }
        });
    }
}
