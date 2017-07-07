package receiver.service.android.vogella.com.project1edx;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final SharedPreferences preferences = getSharedPreferences("alarm", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        final String state = preferences.getString("state", "ON");
        final String periodText = preferences.getString("period", "");
        editText = (EditText) findViewById(R.id.etSetNotification);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setText(state);
        editText.setText(periodText);
        if (state.equals("OFF")) {
            toggleButton.setChecked(true);
        }
        Toast.makeText(this, state + "Test", Toast.LENGTH_SHORT).show();

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BroadcastAlarm.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (toggleButton.getText().toString().equals("OFF")) {

                    try {
                        Calendar calendar = Calendar.getInstance();
                        //calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                        //calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                        int period = 0;
                        period = Integer.parseInt(editText.getText().toString());
                        editor.putString("state", toggleButton.getText().toString());
                        editor.putString("period", String.valueOf(period));
                        editor.apply();
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), period * 60000, pendingIntent);
                    } catch (Exception e) {
                        Toast.makeText(NotificationActivity.this, "Enter Period time", Toast.LENGTH_SHORT).show();
                        toggleButton.setChecked(false);
                    }
                }
                if (toggleButton.getText().toString().equals("ON")) {

                    alarmManager.cancel(pendingIntent);
                    editor.putString("state", toggleButton.getText().toString());
                    editor.apply();
                    Toast.makeText(NotificationActivity.this, "Notification is off", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
