package com.example.auligoli;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auligoli.database.DatabaseManager;
import com.example.auligoli.entity.ServiceOrganization;
import com.example.auligoli.entity.ServiceType;

import java.util.ArrayList;

public class PoliceStation extends AppCompatActivity {
    private static final String DEBUG_TAG = "PoliceStation";

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter recyclerViewAdapter;

    private EditText editTextForPoliceStationLocation;
    RecyclerView stationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_station);

        editTextForPoliceStationLocation = findViewById(R.id.edit_location_for_policeStation);
        recyclerView = findViewById(R.id.RecyclerViewForShowingPoliceStationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MyRecyclerViewAdapter(new ArrayList<>()));


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d(TAG,"onClick: clicked");
                //Intent intent=new Intent(PoliceStation.this,AmbulanceService.class);
                //startActivity(intent);
            }
        });
    }


    public void showPoliceStationList(View v){
        Log.d(DEBUG_TAG, "showPoliceStationList called");
        String location = editTextForPoliceStationLocation.getText().toString().trim();
        if(location.trim().length() == 0){
            Toast.makeText(this, "Location is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        ArrayList<ServiceOrganization> policeStationList = databaseManager.getServiceOrganizationList(ServiceType.POLICE_STATION_SERVICE, location);
        Log.d(DEBUG_TAG, "size of list = " + policeStationList.size());
        updateListView(policeStationList);
    }

    private void updateListView(ArrayList<ServiceOrganization> policeStationList) {

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(policeStationList);
        recyclerView.setAdapter(adapter);
    }


    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.OrganizationViewHolder>{

        private ArrayList<ServiceOrganization> policeStationListToShow;
        MyRecyclerViewAdapter(ArrayList<ServiceOrganization> policeStationList){
            this.policeStationListToShow = policeStationList;
        }

        @NonNull
        @Override
        public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_search_result_design, viewGroup, false);
            return new OrganizationViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull OrganizationViewHolder organizationViewHolder, int i) {
            organizationViewHolder.setValuesAndDisplay(policeStationListToShow.get(i));
        }

        @Override
        public int getItemCount() {
            return this.policeStationListToShow.size();
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
