package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Account_UpdateActivity extends AppCompatActivity {

    EditText account_update_username;
    EditText account_update_password;
    Button account_update_button, account_delete_button;

    String id, username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_update);

        account_update_username = findViewById(R.id.account_update_username);
        account_update_password = findViewById(R.id.account_update_password);
        account_update_button = findViewById(R.id.account_update_button);
        account_delete_button = findViewById(R.id.account_delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(username);
        }

        account_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(Account_UpdateActivity.this);
                username = account_update_username.getText().toString().trim();
                password = account_update_password.getText().toString().trim();
              myDB.updateData(id, username,password);
            }
        });
        account_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("username") &&
                getIntent().hasExtra("password") ){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            username = getIntent().getStringExtra("title");
            password = getIntent().getStringExtra("author");

            //Setting Intent Data
            account_update_username.setText(username);
            account_update_password.setText(password);

            Log.d("stev", username+" "+password);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + username + " ?");
        builder.setMessage("Are you sure you want to delete " + username + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Account_UpdateActivity.this);
//                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}