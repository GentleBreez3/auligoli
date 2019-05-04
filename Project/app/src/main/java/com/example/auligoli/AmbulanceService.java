package com.example.auligoli;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class AmbulanceService extends AppCompatActivity {
    TextView clickHospitalNameForAmbulance;
    TextView hospitalName;
    TextView hospitalLocation;
    TextView contactUs;
    Toolbar toolbarAmbulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_service);

        class OrganizationViewHolder extends RecyclerView.ViewHolder{
            private TextView textViewForServiceInfo;

            public OrganizationViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewForServiceInfo = itemView.findViewById(R.id.TextViewForServiceInfo);

            }
        }
    }
}
