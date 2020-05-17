package com.example.notify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import android.Manifest;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.UserInfo.*;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public GPSTracker gps;
    Button submit;
    Button report;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    public double latitude,longitude;
    private static final int REQUEST_CODE_PERMISSION = 2;
    DatabaseReference mDatabase;
    Button volunteerSubmit;
    FirebaseAuth auth;
    String phoneNo,message;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        counter=0;
        volunteerSubmit=(Button)findViewById(R.id.volunteer);
        //startService(new Intent(this,backgroundNotifier.class));
        report=(Button)findViewById(R.id.report);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.submit);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        gps = new GPSTracker(MainActivity.this);
    }
    public void onClick4(View view){
        startActivity(new Intent(MainActivity.this,MapsActivity.class));

           }

    public void onClick3(View view){
        startActivity(new Intent(MainActivity.this,CovidMap.class));
    }
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
    public void onClick1(View view) {
        startActivity(new Intent(MainActivity.this,ReportUnsafeArea.class));
    }
    public void onClick2(View view) {
        startActivity(new Intent(MainActivity.this,showUnsafeMap.class));
    }
    public void onClick(View view) {
        ;
        //mDatabase.child("users").child("n").setValue(user);
    }





    public class UnsafeArea {

        public String name;
        public String reason;
        public double lat;
        public double lon;
        public String time;
        public String age;
        public String gender;
        public double radius;

        public UnsafeArea() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public  UnsafeArea(double lat,double lon,String name,String reason,String time,double radius,String age,String gender) {
            this.name=name;
            this.reason=reason;
            this.lat=lat;
            this.lon=lon;
            this.time=time;
            this.age=age;
            this.gender=gender;
            this.radius=radius;
        }

    }

}
