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
    private static final String TABLE_DIARY = "diary";
    private static final String TABLE_WEIGHT_TRACKER = "weighttrackertable";
    private static final String TABLE_BBN = "bbnContract";
    private static final String TABLE_MEAL_PLAN = "mealplan";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOTE = "note";
    private static final String KEY_TIME = "time";

    //WEIGHT TRACKER COLUMN NAMES
    private static final String KEY_ID_WEIGHT = "id_weight";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_WEIGHT_TIME = "weighttime" ;

    // bbnContract table: barcode, bc-birth_certificate_id , nutritips
    private static final String KEY_ID_BBN = "id_weight";
    private static final String KEY_NUTRITIPS = "nutritips";
    private static final String KEY_BARCODE= "barcode";
    private static final String KEY_BC = "bc";
    private static final String KEY_URL = "url";

    // MealPlanContract table
    private static final String KEY_ID_MEALPLAN = "id_mealPlan";
    private static final String KEY_DAY = "day";
    private static final String KEY_BREAKFAST= "breakfast";
    private static final String KEY_LUNCH = "lunch";
    private static final String KEY_DINNER = "dinner";


    public DiaryContract diaryContract;
    public WeightTrackerContract wtc;
    public BbnContract bbnContract;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DIARY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOTE + " TEXT,"
                + KEY_TIME + " TEXT" + ")";
        String CREATE_WEIGHT_TRACKER_TABLE = "CREATE TABLE " + TABLE_WEIGHT_TRACKER + "("
                + KEY_ID_WEIGHT + " INTEGER PRIMARY KEY,"
                + KEY_WEIGHT + " TEXT,"+ KEY_WEIGHT_TIME + " TEXT" +")";

        String CREATE_BBN_TABLE = "CREATE TABLE " + TABLE_BBN + "("
                + KEY_ID_BBN + " INTEGER PRIMARY KEY," + KEY_BARCODE
                + " TEXT,"+ KEY_BC + " TEXT," +  KEY_NUTRITIPS+ " TEXT," +  KEY_URL+ " TEXT" +")";

        String CREATE_MEALPLAN_TABLE = "CREATE TABLE " + TABLE_MEAL_PLAN + "("
                + KEY_ID_MEALPLAN + " INTEGER PRIMARY KEY," + KEY_DAY
                + " TEXT,"+ KEY_BREAKFAST + " TEXT," +  KEY_LUNCH+ " TEXT," +  KEY_DINNER+ " TEXT" +")";


        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_WEIGHT_TRACKER_TABLE);
        db.execSQL(CREATE_BBN_TABLE);
        db.execSQL(CREATE_MEALPLAN_TABLE);
        Log.d("SQL", "tables created" + CREATE_CONTACTS_TABLE + "++##+++" + CREATE_WEIGHT_TRACKER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT_TRACKER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BBN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL_PLAN);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new diaryContract
    public void addContact(DiaryContract diaryContract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, diaryContract.getNote()); // DiaryContract note
        values.put(KEY_TIME, diaryContract.getTime()); // DiaryContract time
        Log.d("SQL", "inserting diaryContract data" + values.toString());

        // Inserting Row
        db.insert(TABLE_DIARY, null, values);
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

    //ADDING DATA TO BBN_TABLE
    public void addBbnData(BbnContract bbnContract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BARCODE, bbnContract.getBarcode()); // ADDING barcode TO THE DATABASE
        values.put(KEY_BC, bbnContract.getBc()); // ADDING birth_certificate_id
        values.put(KEY_NUTRITIPS, bbnContract.getNutritips());
        values.put(KEY_URL, bbnContract.getUrl());

        // Inserting Row
        db.insert(TABLE_BBN, null, values);
        Log.d("SQL****", "inserting weight data" + values.toString());
        db.close(); // Closing database connection
    }

    //ADDING DATA TO MEAL_PLAN
    public void addMealPlan(MealPlanContract mealPlanContract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DAY, mealPlanContract.getDay()); // ADDING barcode TO THE DATABASE
        values.put(KEY_BREAKFAST, mealPlanContract.getBreakfast()); // ADDING birth_certificate_id
        values.put(KEY_LUNCH, mealPlanContract.getLunch());
        values.put(KEY_DINNER, mealPlanContract.getDinner());

        // Inserting Row
        db.insert(TABLE_MEAL_PLAN, null, values);
        Log.d("SQL****", "inserting weight data" + values.toString());
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public List<DiaryContract> getAllContacts() {
        List<DiaryContract> diaryContractList = new ArrayList<DiaryContract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DIARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DiaryContract diaryContract = new DiaryContract();
                diaryContract.setID(Integer.parseInt(cursor.getString(0)));
                diaryContract.setNote(cursor.getString(1));
                diaryContract.setTime(cursor.getString(2));
                // Adding diaryContract to list
                diaryContractList.add(diaryContract);
            } while (cursor.moveToNext());
        }

        // return diaryContract list
        return diaryContractList;
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
            } while (cursor.moveToNext());
        }
       Log.d("SQL","getting all weights");
        // return diaryContract list
        return WeightList;
    }

    // GETTING ALL WEIGHTS
    public List<BbnContract> getBbnData() {
        List<BbnContract> bbnList = new ArrayList<BbnContract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BBN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                BbnContract wtc = new BbnContract();
                wtc.set_id_bbn(Integer.parseInt(cursor.getString(0)));
                wtc.setBarcode(cursor.getString(1));
                wtc.setBc(cursor.getString(2));
                wtc.setNutritips(cursor.getString(3));
                wtc.setUrl(cursor.getString(4));
                bbnList.add(wtc);

            } while (cursor.moveToNext());
        }
        Log.d("SQL","getting all weights");
        // return diaryContract list
        return bbnList;
    }

    // GETTING ALL WEIGHTS
    public List<MealPlanContract> getMealPlan() {
        List<MealPlanContract> mealPlanContractList = new ArrayList<MealPlanContract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEAL_PLAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                MealPlanContract wtc = new MealPlanContract();
                wtc.set_id_mealPlan(Integer.parseInt(cursor.getString(0)));
                wtc.setDay(cursor.getString(1));
                wtc.setBreakfast(cursor.getString(2));
                wtc.setLunch(cursor.getString(3));
                wtc.setDinner(cursor.getString(4));
                mealPlanContractList.add(wtc);

            } while (cursor.moveToNext());
        }
        Log.d("SQL","getting all weights");
        // return diaryContract list
        return mealPlanContractList;
    }
//    // Updating single diaryContract
//    public int updateContact(DiaryContract diaryContract) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NOTE, diaryContract.getNote());
//        values.put(KEY_TIME, diaryContract.getTime());
//
//        // updating row
//        return db.update(TABLE_DIARY, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(diaryContract.getID()) });
//    }
//
//    // Deleting single diaryContract
    public void deleteAll(BbnContract bbnContract) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BBN, KEY_ID + " = ?",
                new String[] { String.valueOf(bbnContract.get_id_bbn()) });
//     //   db.delete(TABLE_DIARY, KEY_ID + " = ?");
        Log.d("SQL", "deleting content form meal_plan and bbn_table");
//        db.execSQL("DELETE FROM "+ TABLE_MEAL_PLAN);
//        db.execSQL("DELETE FROM "+ TABLE_BBN);
        db.close();
    }
//
//
//    // Getting contacts Count
//    public int getContactsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_DIARY;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }
//
//    public int getWeightCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_WEIGHT_TRACKER;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }

}