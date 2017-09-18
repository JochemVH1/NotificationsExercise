package com.dev.jvh.notificationsexercise;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import static android.R.attr.visibility;
import static com.dev.jvh.notificationsexercise.SettingsActivity.SettingsFragment.KEY_INFO_NOTIFICATION;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MAIN_ACTIVITY","OnCreateExecuted");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.settings:{
                goToSettings();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToSettings()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(sharedPreferences.getBoolean(key,false)) {
            buildNotification();
        }
        if(!sharedPreferences.getBoolean(key,false)){
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.cancel(101);
        }
    }

    public void buildNotification()
    {
        Log.d("MAIN_ACTIVItY","building notification");
        int notification_id = 101;
        // create a new notification
        Notification notification  = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Info")
                .setContentText("Hello, this is some information")
                .setSmallIcon(R.drawable.info)
                .setAutoCancel(true)
                .setVisibility(visibility).build();
        // connect notification manager
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // make a new notification with a new unique id
        notificationManager.notify(notification_id, notification);
    }
}
