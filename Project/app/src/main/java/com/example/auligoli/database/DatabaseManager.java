package com.example.auligoli.database;

import android.util.Log;

import com.example.auligoli.entity.ServiceOrganization;
import com.example.auligoli.entity.ServiceType;

import java.util.ArrayList;

public class DatabaseManager {
    private static final String DEBUG_TAG = "DatabaseManager";

    private static DatabaseManager databaseManager;
    public static DatabaseManager getInstance(){
        if(databaseManager == null){
            databaseManager = new DatabaseManager();
            databaseManager.initializeWithDemoData();
        }
        return databaseManager;
    }

    private ArrayList<ServiceOrganization> serviceOrganizations;

    public ArrayList<ServiceOrganization> getServiceOrganizationList(ServiceType serviceType, String location){
        ArrayList<ServiceOrganization> possibleValues = new ArrayList<>();
        for (ServiceOrganization organization : serviceOrganizations){
            if(organization.getServiceType() == serviceType && organization.getLocation().equalsIgnoreCase(location)){
                possibleValues.add(organization);
            }
        }
        return possibleValues;
    }

    private void initializeWithDemoData() {
        serviceOrganizations = new ArrayList<>();
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Labaid", "Dhanmondi","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Enam Medical", "Savar","16465", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Prime", "Savar","465452656", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "IBN-SINA", "Dhanmondi","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "IBN-SINA", "Savar","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "IBN-SINA", "Mirpur","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Bardem", "Dhanmondi","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Square", "Dhanmondi","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Square", "Mirpur","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Square", "Kollyanpur","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Dip clinic", "Savar","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Podma General Hospital", "Savar","0124569765", "no details"));
        serviceOrganizations.add(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Sima General Hospital", "Savar","0124569765", "no details"));
    }

}
