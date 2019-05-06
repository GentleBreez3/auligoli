package com.example.auligoli.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.auligoli.entity.ServiceOrganization;
import com.example.auligoli.entity.ServiceType;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper{
    private static final String DEBUG_TAG = "[DatabaseManager]";

    private static DatabaseManager databaseManager;
    public static void initialize(Context context){
        Log.d(DEBUG_TAG, "initializing database manager");
        databaseManager = new DatabaseManager(context);
        //databaseManager.initializeWithDemoData();
        //databaseManager.printAllTheData();
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseManager getInstance(){
        return databaseManager;
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "service_db";

    //table 1 information
    private static final String TABLE_NAME1 = "service_table";
    private static final String T1C1 = "so_service_type";
    private static final String T1C2 = "so_organization_name";
    private static final String T1C3 = "so_location";
    private static final String T1C4 = "so_contact_number";
    private static final String T1C5 = "so_service_details";

    //table 2 information

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE " + TABLE_NAME1 + "( ");
            sb.append(T1C1 + " INT NOT NULL, ");
            sb.append(T1C2 + " TEXT NOT NULL, ");
            sb.append(T1C3 + " TEXT NOT NULL, ");
            sb.append(T1C4 + " TEXT NOT NULL, ");
            sb.append(T1C5 + " TEXT NOT NULL );");
            sqLiteDatabase.execSQL(sb.toString());
            Log.d(DEBUG_TAG, "table created!");
        }catch (Exception e){
            e.printStackTrace();
            Log.d(DEBUG_TAG, "problem occurs while creating table!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }

    private void addServiceOrganization(ServiceOrganization serviceOrganization){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(T1C1, serviceOrganization.getServiceType());
            cv.put(T1C2, serviceOrganization.getOrganizationName());
            cv.put(T1C3, serviceOrganization.getLocation());
            cv.put(T1C4, serviceOrganization.getContactNumber());
            cv.put(T1C5, serviceOrganization.getDetails());
            db.insert(TABLE_NAME1, null, cv );
            db.close();
            Log.d(DEBUG_TAG, "service organization added successfully.");
        }catch (Exception e){
            e.printStackTrace();
            Log.d(DEBUG_TAG, "problem occurs while adding service organization.");
        }
    }

    public ArrayList<ServiceOrganization> getServiceOrganizationList(int serviceType, String location){
        Log.d(DEBUG_TAG, "sercing information [" + serviceType +"] [" + location+"]");
        ArrayList<ServiceOrganization>  ret = new ArrayList<>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String[] selectedFields = new String[]{T1C1, T1C2, T1C3 , T1C4, T1C5};
            String whereCondition = String.format("%S = ? and %S = ? COLLATE NOCASE", T1C1, T1C3);
            String[] conditionValues = new String[] {String.valueOf(serviceType), location.trim()};
            Cursor rs = db.query(TABLE_NAME1, selectedFields, whereCondition, conditionValues, null, null, null, null);
            Log.d(DEBUG_TAG, "adding service organization in the list");
            if (rs.moveToFirst()) {
                do {
                    Log.d(DEBUG_TAG, "adding service organaization in the list[inside loop]");
                    int now_serviceType = rs.getInt(0);
                    String now_organizationName = rs.getString(1);
                    String now_location = rs.getString(2);
                    String now_contactNumber = rs.getString(3);
                    String now_details = rs.getString(4);
                    ServiceOrganization so = new ServiceOrganization(now_serviceType, now_organizationName, now_location, now_contactNumber, now_details);
                    ret.add(so);
                } while (rs.moveToNext());
            }
            rs.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    private void printAllTheData(){
        Log.d(DEBUG_TAG, "printing all the information");
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String query = String.format("SELECT * FROM %S",TABLE_NAME1);
            Cursor rs = db.rawQuery(query, null);
            if (rs.moveToFirst()) {
                do {
                    int now_serviceType = rs.getInt(0);
                    String now_organizationName = rs.getString(1);
                    String now_location = rs.getString(2);
                    String now_contactNumber = rs.getString(3);
                    String now_details = rs.getString(4);
                    ServiceOrganization so = new ServiceOrganization(now_serviceType, now_organizationName, now_location, now_contactNumber, now_details);
                    Log.d(DEBUG_TAG, String.format("%S", so));
                } while (rs.moveToNext());
            }
            rs.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.d(DEBUG_TAG, "printing problem occurs while printing all the information");
        }
        Log.d(DEBUG_TAG, "printing all the information done");
    }

    private void initializeWithDemoData() {
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Labaid", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Enam Medical", "Savar","16465", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Prime", "Savar","465452656", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "IBN-SINA", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "IBN-SINA", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "IBN-SINA", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Bardem", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Square", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Square", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Square", "Kollyanpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Dip clinic", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Podma General Hospital", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.HOSPITAL_SERVICE, "Sima General Hospital", "Savar","0124569765", "no details"));

        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Yum Yum", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Pasta State", "Dhanmondi","16465", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Taste Blust", "Dhanmondi","465452656", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Chillox", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Dominos", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Pizza Hut", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Yumient", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Bhoot", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Pasta State", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Pabulum", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Digonto", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Rovers Cafe", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RESTAURANT_SERVICE, "Londonas", "Savar","0124569765", "no details"));

        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Dhamondi Fire Station", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Savar Fire Station", "Savar","16465", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Savar East Fire Station", "Savar","465452656", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Jigatola Fire Station", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Polli Bidyut Fire Station", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Mirpur-10 Fire Station", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Gulshan Fire Station", "Gulshan","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Komlapur Fire Station", "Motijheel","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Pollobi Fire Station", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Shaymoli Fire Station", "Shaymoli","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Genda Fire Station", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Uttara Fire Station", "Uttara","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.FIRE_SERVICE, "Malibag Fire Station", "Malibag","0124569765", "no details"));

        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Dhamondi Police Station", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Savar Police Station", "Savar","16465", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Savar East Police Station", "Savar","465452656", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Jigatola Police Station", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Polli Bidyut Police Station", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Mirpur-10 Police Station", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Gulshan Police Station", "Gulshan","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Komlapur Police Station", "Motijheel","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Pollobi Police Station", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Shaymoli Police Station", "Shaymoli","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Genda Police Station", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Uttara Police Station", "Uttara","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.POLICE_STATION_SERVICE, "Malibag Police Station", "Malibag","0124569765", "no details"));

        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Dhanmondi wheel", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Joshim Rent a car", "Savar","16465", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Mayer Doa Rent A Car", "Savar","465452656", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Allah Sohai Rent A Car", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Pother Sathi Rent A Car", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Tanis Rent A Car", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Bardem", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Square", "Dhanmondi","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Square", "Mirpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Square", "Kollyanpur","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Savar Rent A Car", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Sima Rent A Car", "Savar","0124569765", "no details"));
        this.addServiceOrganization(new ServiceOrganization(ServiceType.RENT_CAR, "Jononi Rent A Car", "Savar","0124569765", "no details"));

    }


}
