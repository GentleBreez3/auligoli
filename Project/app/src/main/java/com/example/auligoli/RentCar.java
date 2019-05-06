package com.example.auligoli;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auligoli.database.DatabaseManager;
import com.example.auligoli.entity.ServiceOrganization;
import com.example.auligoli.entity.ServiceType;

import java.util.ArrayList;

public class RentCar extends AppCompatActivity {

    private static final String DEBUG_TAG = "RentCarService";

    private RecyclerView recyclerView;
    private RestaurantService.MyRecyclerViewAdapter recyclerViewAdapter;

    private EditText editTextForRentCarLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);

        editTextForRentCarLocation = findViewById(R.id.edit_location_for_rent_car);
        recyclerView = findViewById(R.id.RecyclerViewForShowingRentCarList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RentCar.MyRecyclerViewAdapter(new ArrayList<>()));
    }

    public void showRentCarList(View v){
        Log.d(DEBUG_TAG, "showHospitalList called");
        String location = editTextForRentCarLocation.getText().toString().trim();
        if(location.trim().length() == 0){
            Toast.makeText(this, "Location is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        ArrayList<ServiceOrganization> rentCarList = databaseManager.getServiceOrganizationList(ServiceType.RENT_CAR, location);
        Log.d(DEBUG_TAG, "size of list = " + rentCarList.size());
        updateListView(rentCarList);
    }

    private void updateListView(ArrayList<ServiceOrganization> rentCarServiceList) {

        RentCar.MyRecyclerViewAdapter adapter = new RentCar.MyRecyclerViewAdapter(rentCarServiceList);
        recyclerView.setAdapter(adapter);
    }


    class MyRecyclerViewAdapter extends RecyclerView.Adapter<RentCar.MyRecyclerViewAdapter.OrganizationViewHolder>{

        private ArrayList<ServiceOrganization> rentCartServicesToShow;
        MyRecyclerViewAdapter(ArrayList<ServiceOrganization> rentCarServiceList){
            this.rentCartServicesToShow = rentCarServiceList;
        }

        @NonNull
        @Override
        public RentCar.MyRecyclerViewAdapter.OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_search_result_design, viewGroup, false);
            return new RentCar.MyRecyclerViewAdapter.OrganizationViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RentCar.MyRecyclerViewAdapter.OrganizationViewHolder organizationViewHolder, int i) {
            organizationViewHolder.setValuesAndDisplay(rentCartServicesToShow.get(i));
        }

        @Override
        public int getItemCount() {
            return this.rentCartServicesToShow.size();
        }

        class OrganizationViewHolder extends RecyclerView.ViewHolder{

            private TextView textViewForServiceInfo;
            private String contactNumber;

            private OrganizationViewHolder(View itemView) {
                super(itemView);
                textViewForServiceInfo = itemView.findViewById(R.id.TextViewForServiceInfo);
                itemView.findViewById(R.id.ButtonForServiceCalling).setOnClickListener(v -> {
                    //show calling activity (phone default) with saved number
                    Log.d("Phn_Number", "contact number: " + contactNumber);
                    Intent intentPhone=new Intent(Intent.ACTION_DIAL);
                    intentPhone.setData(Uri.parse("tel:"+contactNumber));
                    startActivity(intentPhone);
                });
            }

            private void setValuesAndDisplay(ServiceOrganization values){
                this.contactNumber = values.getContactNumber();
                textViewForServiceInfo.setText(values.getOrganizationName());
            }
        }
    }
}
