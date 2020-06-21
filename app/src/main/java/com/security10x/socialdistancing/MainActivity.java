package com.security10x.socialdistancing;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private int mInterval = 5000;
    private Context mContext;
    private Handler mHandler;
    Vibrator v;
    private static final String CHANNEL_ID = "social_distancing";
    private static final String TAG = "MainActivity";
    CardView quote, resource, piechart, helpline_nums;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quote = (CardView) findViewById(R.id.quote);
        resource = (CardView) findViewById(R.id.resource);
        piechart = (CardView) findViewById(R.id.piechart);
        helpline_nums = (CardView) findViewById(R.id.helpline_numbers);

//        checkBTPermissions();
//        checkPermission();

        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
                startActivity(intent);
            }
        });

        resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.security10x.socialdistancing.ResourceActivity.class);
                startActivity(intent);
            }
        });

        piechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.security10x.socialdistancing.ChartActivity.class);
                startActivity(intent);
            }
        });

        helpline_nums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.security10x.socialdistancing.HelplineActivity.class);
                startActivity(intent);
            }
        });

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mContext = getApplicationContext();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mHandler = new Handler();
        startRepeatingTask();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, intentFilter);

        Intent blintent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        blintent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
        startActivity(blintent);
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void checkBTPermissions() {
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
//            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
//            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
//            if (permissionCheck != 0) {
//
//                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
//            }
//        }else{
////            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
//        }
//    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                }, 1);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        } else {
            checkPermission();
        }
    }

    Runnable mStatusChecker = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void run() {
            try {
                updateStatus(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    private void displayNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_warning_black_18dp)
                .setContentTitle(getString(R.string.Social_Distancing_App))
                .setContentText(getString(R.string.Please_maintain_social_distancing))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, mBuilder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void updateStatus() {
        if (mBluetoothAdapter == null) {
//            Log.d(TAG, "enableDisabled: Does not have BT capabilities.");
            Toast.makeText(this, R.string.Bluetooth_Not_Supported,
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);
            mBluetoothAdapter.startDiscovery();
//            checkBTPermissions();

        }
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.startDiscovery();
//            checkBTPermissions();
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                calculateDistance(rssi);
//                Log.i("BT", device.getName() + "\n" + device.getAddress());
            }
        }
    };

    private void calculateDistance(int rssi) {
        String distance = "";

        if ((rssi > -45) || (-45 > rssi && rssi >= -47) || (-47 > rssi && rssi >= -51) || (-51 > rssi && rssi >= -54)) {
            Log.d(TAG, "calculateDistance: " + rssi + " rssi " + rssi);
            distance = "0 meter";
            displayNotification();
            createNotificationChannel();
            v.vibrate(1500);
//            alertUser();

        }
        else {
            distance = "Far away";
        }
    }

    public void alertUser(){
        Uri alarmSound =
                RingtoneManager.getDefaultUri (RingtoneManager.TYPE_RINGTONE);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), alarmSound);
        mediaPlayer.start();
    }
}
