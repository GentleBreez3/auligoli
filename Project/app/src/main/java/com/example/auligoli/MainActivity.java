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

    public void busSearching(View view) {
        Button btnBus=(Button) findViewById(R.id.bus_searching_button);
        Intent intent=new Intent(MainActivity.this,BusSearching.class);
        startActivity(intent);
    }

    public void routeSearching(View view) {
        Button btnRoute=(Button) findViewById(R.id.route_searching_button);
        Intent intent=new Intent(MainActivity.this,RouteSearching.class);
        startActivity(intent);
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

    public void policeStation(View view) {
        Button btnHospital=(Button) findViewById(R.id.police_station_button);
        Intent intent=new Intent(MainActivity.this,PoliceStation.class);
        startActivity(intent);
    }

    public void restaurantService(View view) {
        Button btnHospital=(Button) findViewById(R.id.restaurant_service_button);
        Intent intent=new Intent(MainActivity.this,RestaurantService.class);
        startActivity(intent);
    }

    public void foodDelivery(View view) {
        Button btnHospital=(Button) findViewById(R.id.food_delivery_button);
        Intent intent=new Intent(MainActivity.this,FoodDelivery.class);
        startActivity(intent);
    }

    public void hotelService(View view) {
        Button btnHospital=(Button) findViewById(R.id.hotel_service_button);
        Intent intent=new Intent(MainActivity.this,HotelService.class);
        startActivity(intent);
    }

    public void roomReservation(View view) {
        Button btnHospital=(Button) findViewById(R.id.room_reservation_button);
        Intent intent=new Intent(MainActivity.this,RoomReservation.class);
        startActivity(intent);
    }

    public void rentCar(View view) {
        Button btnRentCar=(Button) findViewById(R.id.rent_car_button);
        Intent intent=new Intent(MainActivity.this,RentCar.class);
        startActivity(intent);
    }

    public void complainBox(View view) {
        Button btnComplain=(Button) findViewById(R.id.complain_box_button);
        Intent intent=new Intent(MainActivity.this,ComplainBox.class);
        startActivity(intent);
    }
}
