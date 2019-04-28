package com.example.auligoli;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auligoli.database.DatabaseManager;
import com.example.auligoli.entity.ServiceOrganization;
import com.example.auligoli.entity.ServiceType;

import java.util.ArrayList;

public class FireService extends AppCompatActivity {
    private static final String DEBUG_TAG = "FireService";

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter recyclerViewAdapter;

    private EditText editTextForFireServiceLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_service);

        editTextForFireServiceLocation = findViewById(R.id.edit_location_for_fire_service);
        recyclerView = findViewById(R.id.RecyclerViewForShowingFireServiceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MyRecyclerViewAdapter(new ArrayList<>()));
    }

    public void showFireServiceList(View v){
        Log.d(DEBUG_TAG, "showFireServiceList called");
        String location = editTextForFireServiceLocation.getText().toString();
        if(location.trim().length() == 0){
            Toast.makeText(this, "Location is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        ArrayList<ServiceOrganization> fireServiceList = databaseManager.getServiceOrganizationList(ServiceType.FIRE_SERVICE, location);
        Log.d(DEBUG_TAG, "size of list = " + fireServiceList.size());
        updateListView(fireServiceList);
    }

    private void updateListView(ArrayList<ServiceOrganization> fireServiceList) {

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(fireServiceList);
        recyclerView.setAdapter(adapter);
    }


    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.OrganizationViewHolder>{

        private ArrayList<ServiceOrganization> fireServiceListToShow;
        MyRecyclerViewAdapter(ArrayList<ServiceOrganization> fireServiceList){
            this.fireServiceListToShow = fireServiceList;
        }

        @NonNull
        @Override
        public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_search_result_design, viewGroup, false);
            return new OrganizationViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull OrganizationViewHolder organizationViewHolder, int i) {
            organizationViewHolder.setValuesAndDisplay(fireServiceListToShow.get(i));
        }

        @Override
        public int getItemCount() {
            return this.fireServiceListToShow.size();
        }

        class OrganizationViewHolder extends RecyclerView.ViewHolder{

            private TextView textViewForServiceInfo;
            private String contactNumber;

            private OrganizationViewHolder(View itemView) {
                super(itemView);
                textViewForServiceInfo = itemView.findViewById(R.id.TextViewForServiceInfo);
                itemView.findViewById(R.id.ButtonForServiceCalling).setOnClickListener(v -> {
                    //show calling activity (phone default) with saved number
                    Log.d("Phn_Number", "contact number : " + contactNumber);
                });
            }

            private void setValuesAndDisplay(ServiceOrganization values){
                this.contactNumber = values.getContactNumber();
                textViewForServiceInfo.setText(values.getOrganizationName());
            }
        }
    }
}
