package com.jovanovic.stefan.sqlitetutorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Account_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> thamso_tenthamso, thamso_giatri;
    ArrayList<String> account_id,account_username, account_password;
    CustomAdapter customAdapter;
    Account_CustomAdapter accountCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.account_mainactivity_add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_MainActivity.this, Account_AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(Account_MainActivity.this);
//        myDB.add_account(
//                "admin0",
//                "000"
//        );
//        myDB.add_account(
//                "admin1",
//                "111"
//        );
//        myDB.add_account(
//                "admin2",
//                "222"
//        );

        thamso_tenthamso = new ArrayList<>();
        thamso_giatri = new ArrayList<>();

        account_id = new ArrayList<>();
        account_password = new ArrayList<>();
        account_username = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(Account_MainActivity.this,this,
                thamso_tenthamso,
                thamso_giatri
        );

        accountCustomAdapter = new Account_CustomAdapter(Account_MainActivity.this,this,
                account_id,
                account_username,
                account_password);

        recyclerView.setAdapter(accountCustomAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Account_MainActivity.this));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.read_all_account();
//        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                account_id.add(cursor.getString(0));
                account_username.add(cursor.getString(1));
                account_password.add(cursor.getString(2));
//                thamso_tenthamso.add(cursor.getString(0));
//                thamso_giatri.add(cursor.getString(1));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa tất cả?");
        builder.setMessage("Bạn chắc nhắn muốn xóa tất cả tài khoản?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Account_MainActivity.this);
                myDB.delete_all_account();
                //Refresh Activity
                Intent intent = new Intent(Account_MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}