package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Account_AddActivity extends AppCompatActivity {

    EditText account_username, account_password ;
    Button account_add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        account_username = findViewById(R.id.account_username);
        account_password = findViewById(R.id.account_password);

        account_add_button = findViewById(R.id.account_add_button);
        account_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Account_AddActivity.this);
//                        Integer.valueOf(pages_input.getText().toString().trim()));
                myDB.add_account(
                        account_username.getText().toString().trim(),
                        account_password.getText().toString().trim()
                );
            }
        });
    }
}