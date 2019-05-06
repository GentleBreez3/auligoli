package com.example.auligoli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void hospitalService(View view) {
        Button btnHospital=(Button) findViewById(R.id.hospital_service_button);
        Intent intent=new Intent(MainActivity.this,HospitalService.class);
        startActivity(intent);
    }

    public void ambulanceService(View view) {
        Button btnHospital=(Button) findViewById(R.id.ambulance_service_button);
        Intent intent=new Intent(MainActivity.this,AmbulanceService.class);
        startActivity(intent);
    }

    public void fireService(View view) {
        Button btnHospital=(Button) findViewById(R.id.fire_service_button);
        Intent intent=new Intent(MainActivity.this,FireService.class);
        startActivity(intent);
    }


    public void restaurantService(View view) {
        Button btnHospital=(Button) findViewById(R.id.restaurant_service_button);
        Intent intent=new Intent(MainActivity.this,RestaurantService.class);
        startActivity(intent);
    }

    public void rentCar(View view) {
        Button btnRentCar=(Button) findViewById(R.id.rent_car_button);
        Intent intent=new Intent(MainActivity.this,RentCar.class);
        startActivity(intent);
    }

}
