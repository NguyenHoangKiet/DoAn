package com.jovanovic.stefan.sqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "RentCarDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // region TABLE THAMSO
    private static final String TABLE_THAMSO = "THAMSO";
    private static final String KEY_TENTHAMSO = "TenThamSo";
    private static final String KEY_GIATRI = "GiaTri";

    void addThamSo(String tenthamso, int giatri){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TENTHAMSO, tenthamso);
        cv.put(KEY_GIATRI, giatri);

        long result = db.insert(TABLE_THAMSO,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed" + " "+tenthamso +" "+giatri, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_THAMSO;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    // endregion

    // region table account
    private static final String TABLE_ACCOUNT = "Account";
    private static final String KEY_ACCOUNT_ID = "Account_Id";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_PASSWORD = "Password";

    private static final String CREATE_TABLE_ACCOUNT =
            "CREATE TABLE " + TABLE_ACCOUNT +
                    " (" +
                    KEY_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_USERNAME + " TEXT," +
                    KEY_PASSWORD + " TEXT" +
                    ")";

    void add_account(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_USERNAME, username);
        cv.put(KEY_PASSWORD, password);

        long result = db.insert(TABLE_ACCOUNT,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor read_all_account(){
        String query = "SELECT * FROM " + TABLE_ACCOUNT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_USERNAME, username);
        cv.put(KEY_PASSWORD, password);

        long result = db.update(TABLE_ACCOUNT, cv, KEY_ACCOUNT_ID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }

    }

    void delete_one_account(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_ACCOUNT, KEY_ACCOUNT_ID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_all_account(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ACCOUNT);
    }
    // endregion

    // region table customer
    private static final String TABLE_CUSTOMER = "Customer";
    private static final String KEY_CUSID = "CusId";
    private static final String KEY_CUSNAME = "CusName";
    private static final String KEY_CUSADD = "CusAdd";
    private static final String KEY_PHONE = "Phone";

    private static final String CREATE_TABLE_CUSTOMER =
            "CREATE TABLE " + TABLE_CUSTOMER +
                    " (" +
                    KEY_CUSID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_CUSNAME + " TEXT," +
                    KEY_CUSADD + " TEXT," +
                    KEY_PHONE + " TEXT" +
                    ")";

    void add_customer(String cusname, String cusadd, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CUSNAME, cusname);
        cv.put(KEY_CUSADD, cusadd);
        cv.put(KEY_PHONE, phone);

        long result = db.insert(TABLE_CUSTOMER,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor read_all_customer(){
        String query = "SELECT * FROM " + TABLE_CUSTOMER;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData_customer(String row_id,String cusname, String cusadd, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CUSNAME, cusname);
        cv.put(KEY_CUSADD, cusadd);
        cv.put(KEY_PHONE, phone);

        long result = db.update(TABLE_CUSTOMER, cv, KEY_CUSID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }

    }

    void delete_one_customer(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_CUSTOMER, KEY_CUSID+"=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_all_customer(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CUSTOMER);
    }
    // endregion

    // region table rent
    private static final String TABLE_RENT = "Rent";
    private static final String KEY_RENTID = "RentId";
    private static final String KEY_CARREG = "CarReg";
    private static final String KEY_RENT_CUSID = "Rent_CusId";
    private static final String KEY_RENTALDATE = "RentalDate";
    private static final String KEY_RETURNDATE = "ReturnDate";
    private static final String KEY_FEES = "Fees";

    private static final String CREATE_TABLE_RENT =
            "CREATE TABLE " + TABLE_RENT +
                    " (" +
                    KEY_RENTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_CARREG+ " TEXT," +
                    KEY_RENT_CUSID+ " INTEGER," +
                    KEY_RENTALDATE+ " DATETIME," +
                    KEY_RETURNDATE+ " DATETIME," +
                    KEY_FEES+ " INTEGER" +
                    ")";

    void add_rent(String carreg, Integer rent_cusid,String rentaldate,String renturndate, Integer fees){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CARREG,carreg);
        cv.put(KEY_RENT_CUSID,rent_cusid);
        cv.put(KEY_RENTALDATE,rentaldate);
        cv.put(KEY_RETURNDATE,renturndate);
        cv.put(KEY_FEES,fees);

        long result = db.insert(TABLE_RENT,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor read_all_rent(){
        String query = "SELECT * FROM " + TABLE_RENT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData_rent(String row_id,String carreg, Integer rent_cusid,String rentaldate,String renturndate, Integer fees){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CARREG,carreg);
        cv.put(KEY_RENT_CUSID,rent_cusid);
        cv.put(KEY_RENTALDATE,rentaldate);
        cv.put(KEY_RETURNDATE,renturndate);
        cv.put(KEY_FEES,fees);

        long result = db.update(TABLE_RENT, cv, KEY_RENTID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }

    }

    void delete_one_rent(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_RENT, KEY_RENTID+"=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_all_rent(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RENT);
    }
    // endregion

    // region table car
    private static final String TABLE_CAR = "Car";
    private static final String KEY_CAR_ID = "Car_Id";
    private static final String KEY_REGNO = "Regno";
    private static final String KEY_BRAND = "Brand";
    private static final String KEY_MODEL = "Model";
    private static final String KEY_PRICE = "Price";
    private static final String KEY_AVAILABLE = "Available";

    private static final String CREATE_TABLE_CAR =
            "CREATE TABLE " + TABLE_CAR +
                    " (" +
                    KEY_CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_REGNO + " TEXT," +
                    KEY_BRAND + " TEXT," +
                    KEY_MODEL + " TEXT," +
                    KEY_PRICE + " INTEGER," +
                    KEY_AVAILABLE + " BOOLEAN" +
                    ")";

    void add_car(String regno, String brand, String model, Integer price, Boolean available){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_REGNO, regno);
        cv.put(KEY_BRAND, brand);
        cv.put(KEY_MODEL, model);
        cv.put(KEY_PRICE, price);
        cv.put(KEY_AVAILABLE, available);

        long result = db.insert(TABLE_CAR,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor read_all_car(){
        String query = "SELECT * FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData_car(String row_id, String regno, String brand, String model, Integer price, Boolean available){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_REGNO, regno);
        cv.put(KEY_BRAND, brand);
        cv.put(KEY_MODEL, model);
        cv.put(KEY_PRICE, price);
        cv.put(KEY_AVAILABLE, available);

        long result = db.update(TABLE_CAR, cv, KEY_CAR_ID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }

    }

    void delete_one_car(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_CAR, KEY_CAR_ID+"=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_all_car(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CAR);
    }

    public List<String> get_all_car_spinner(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1) + "_" +
                        cursor.getString(2)+ "_"+
                        cursor.getString(3));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
    // endregion

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //   COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        String query = "CREATE TABLE " + TABLE_THAMSO +
                        " (" +
                        KEY_TENTHAMSO + " TEXT," +
                        KEY_GIATRI + " INTEGER" +
                        ")";
        db.execSQL(query);
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_RENT);
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_CAR);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THAMSO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        onCreate(db);
    }
}
