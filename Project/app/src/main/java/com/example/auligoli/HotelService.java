package com.example.auligoli;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.auligoli.database.DatabaseManager;
import com.example.auligoli.entity.ServiceOrganization;
import com.example.auligoli.entity.ServiceType;

import java.util.ArrayList;

public class HotelService extends AppCompatActivity {

    private EditText editTextForHotelLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_service);

        //does not implemented
        //editTextForHotelLocation = findViewById(R.id.)
    }

    public void showHotelList(View v){
        String location = editTextForHotelLocation.getText().toString();
        if(location.trim().length() == 0){
            Toast.makeText(this, "Location is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        ArrayList<ServiceOrganization> hotelList = databaseManager.getServiceOrganizationList(ServiceType.HOTEL_SERVICE, location);

    }
}
