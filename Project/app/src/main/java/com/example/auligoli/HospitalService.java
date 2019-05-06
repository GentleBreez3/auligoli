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

public class HospitalService extends AppCompatActivity {
    private static final String DEBUG_TAG = "HospitalService";

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter recyclerViewAdapter;

    private EditText editTextForHospitalLocation;
    RecyclerView hospitalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_service);

        editTextForHospitalLocation = findViewById(R.id.edit_location_for_hospital);
        recyclerView = findViewById(R.id.RecyclerViewForShowingHospitalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MyRecyclerViewAdapter(new ArrayList<>()));


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Log.d(TAG,"onClick: clicked");
                Intent intent=new Intent(HospitalService.this,AmbulanceService.class);
                startActivity(intent);
            }
        });
    }


    public void showHospitalList(View v){
        Log.d(DEBUG_TAG, "showHospitalList called");
        String location = editTextForHospitalLocation.getText().toString().trim();
        if(location.trim().length() == 0){
            Toast.makeText(this, "Location is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        ArrayList<ServiceOrganization> hospitalList = databaseManager.getServiceOrganizationList(ServiceType.HOSPITAL_SERVICE, location);
        Log.d(DEBUG_TAG, "size of list = " + hospitalList.size());
        updateListView(hospitalList);
    }

    private void updateListView(ArrayList<ServiceOrganization> hospitalList) {

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(hospitalList);
        recyclerView.setAdapter(adapter);
    }


    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.OrganizationViewHolder>{

        private ArrayList<ServiceOrganization> hospitalListToShow;
        MyRecyclerViewAdapter(ArrayList<ServiceOrganization> hospitalList){
            this.hospitalListToShow = hospitalList;
        }

        @NonNull
        @Override
        public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_search_result_design, viewGroup, false);
            return new OrganizationViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull OrganizationViewHolder organizationViewHolder, int i) {
            organizationViewHolder.setValuesAndDisplay(hospitalListToShow.get(i));
        }

        @Override
        public int getItemCount() {
            return this.hospitalListToShow.size();
        }

        class OrganizationViewHolder extends RecyclerView.ViewHolder{

            private TextView textViewForServiceInfo;
            private String contactNumber;
            private String hospitalName;

            private OrganizationViewHolder(View itemView) {
                super(itemView);
                textViewForServiceInfo = itemView.findViewById(R.id.TextViewForServiceInfo);
                itemView.findViewById(R.id.TextViewForServiceInfo).setOnClickListener(v -> {
                    Log.d("Hospital_Name","Ambulance: "+hospitalName);
                    Intent intentHospitalName=new Intent(HospitalService.this,AmbulanceService.class);
                    startActivity(intentHospitalName);
                });
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
