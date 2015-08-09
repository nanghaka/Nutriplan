package com.app.cryptotunnel.nutriplan.database;

/**
 * Created by codephillip on 6/20/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    private static final String TABLE_WEIGHT_TRACKER = "weighttrackertable";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    //WEIGHT TRACKER COLUMN NAMES
    private static final String KEY_ID_WEIGHT = "id_weight";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_WEIGHT_TIME = "weighttime" ;

    // bbn table: barcode, bc-birth_certificate_id , nutritips
    private static final String KEY_ID_BBN = "id_weight";
    private static final String KEY_NUTRITIPS = "nutritips";
    private static final String KEY_BARCODE= "barcode";
    private static final String KEY_BC = "bc";


    public Contact contact;
    public WeightTrackerContract wtc;
    public

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        String CREATE_WEIGHT_TRACKER_TABLE = "CREATE TABLE " + TABLE_WEIGHT_TRACKER + "("
                + KEY_ID_WEIGHT + " INTEGER PRIMARY KEY," + KEY_WEIGHT + " TEXT,"+ KEY_WEIGHT_TIME + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_WEIGHT_TRACKER_TABLE);
        Log.d("SQL", "tables created" + CREATE_CONTACTS_TABLE + "++##+++" + CREATE_WEIGHT_TRACKER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT_TRACKER);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
        Log.d("SQL", "inserting contact data" + values.toString());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
    //ADDING NEW WEIGHT
    public void addWeight(WeightTrackerContract weightTrackerContract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WEIGHT, weightTrackerContract.get_weight()); // ADDING WEIGHT TO THE DATABASE
        values.put(KEY_WEIGHT_TIME, weightTrackerContract.get_weight_time()); // ADDING CURRENT TIME

        // Inserting Row
        db.insert(TABLE_WEIGHT_TRACKER, null, values);
        Log.d("SQL****", "inserting weight data" + values.toString());
        db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("SQL", "getting single contact");

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        try {
             contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));

        }catch (NullPointerException e){
            Log.d("Sql", "NullPointer Exception"+e);

        }

        // return contact
        return contact;
    }

    // GETTING SINGLE WEIGHT
    WeightTrackerContract getWeight(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("SQL", "getting single weight");
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID_WEIGHT,
                        KEY_WEIGHT,KEY_WEIGHT_TIME }, KEY_ID_WEIGHT + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        try {

            wtc = new WeightTrackerContract(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1));
        }catch (NullPointerException e){
            Log.d("Sql", "NullPointer Exception"+e);
        }

        // return contact
        return wtc;
    }



    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // GETTING ALL WEIGHTS
    public List<WeightTrackerContract> getAllWeights() {
        List<WeightTrackerContract> WeightList = new ArrayList<WeightTrackerContract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WEIGHT_TRACKER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                WeightTrackerContract wtc = new WeightTrackerContract();
                wtc.set_id(Integer.parseInt(cursor.getString(0)));
                wtc.set_weight(cursor.getString(1));
                wtc.set_weight_time(cursor.getString(2));
                WeightList.add(wtc);

//                Contact contact = new Contact();
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setWe(cursor.getString(1));
               // contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                //contactList.add(contact);
            } while (cursor.moveToNext());
        }
       Log.d("SQL","getting all weights");
        // return contact list
        return WeightList;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
     //   db.delete(TABLE_CONTACTS, KEY_ID + " = ?");
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int getWeightCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WEIGHT_TRACKER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}