package com.example.auligoli.entity;

import android.annotation.SuppressLint;

public class ServiceOrganization {
    private int serviceType;
    private String organizationName;
    private String location;
    private String contactNumber;
    private String details;

    public ServiceOrganization(int serviceType, String organizationName, String location, String contactNumber, String details) {
        this.serviceType = serviceType;
        this.organizationName = organizationName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.details = details;
    }

    public int getServiceType() {
        return serviceType;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getLocation() {
        return location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDetails() {
        return details;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("[%d][%s][%s][%s][%s]",serviceType, organizationName, location, contactNumber, details);
    }
}
