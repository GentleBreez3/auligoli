package com.example.auligoli.entity;

public class ServiceOrganization {
    private ServiceType serviceType;
    private String organizationName;
    private String location;
    private String contactNumber;
    private String details;

    public ServiceOrganization(ServiceType serviceType, String organizationName, String location, String contactNumber, String details) {
        this.serviceType = serviceType;
        this.organizationName = organizationName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.details = details;
    }

    public ServiceType getServiceType() {
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
}
