package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Rent_AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner rent_add_activity_regno_spinner;
    Spinner rent_add_activity_cusid_spinner;
    EditText rent_add_activity_rentaldate;
    EditText rent_add_activity_returndate;
    EditText rent_add_activity_fees;
    Button rent_add_activity_rentaldate_button;
    Button rent_add_activity_returndate_button;
    Button rent_add_activity_add_button;
    List<String> list_regno , list_cusid;
    String regno, cusid;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_add);

        rent_add_activity_regno_spinner = findViewById(R.id.rent_add_activity_regno_spinner);
        rent_add_activity_cusid_spinner = findViewById(R.id.rent_add_activity_cusid_spinner);
        rent_add_activity_rentaldate = findViewById(R.id.rent_add_activity_rentaldate);
        rent_add_activity_returndate = findViewById(R.id.rent_add_activity_returndate);
        rent_add_activity_fees = findViewById(R.id.rent_add_activity_fees);
        rent_add_activity_rentaldate_button = findViewById(R.id.rent_add_activity_rentaldate_button);
        rent_add_activity_returndate_button = findViewById(R.id.rent_add_activity_returndate_button);
        rent_add_activity_add_button = findViewById(R.id.rent_add_activity_add_button);

        rent_add_activity_regno_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                // On selecting a spinner item
                String label = parentView.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parentView.getContext(), list_regno.get(position),
                        Toast.LENGTH_LONG).show();

                regno = list_regno.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        rent_add_activity_cusid_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                // On selecting a spinner item
                String label = parentView.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parentView.getContext(), list_cusid.get(position),
                        Toast.LENGTH_LONG).show();

                cusid = list_cusid.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        load_car_spinner_data();
        load_customer_spinner_data();

        Calendar c = Calendar.getInstance();
        final int year_1 = c.get(Calendar.YEAR);
        final int month_1 = c.get(Calendar.MONTH);
        final int day_1 = c.get(Calendar.DAY_OF_MONTH);
        rent_add_activity_rentaldate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Rent_AddActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                        setListener,year_1,month_1,day_1);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/"+month+"/"+year;

                rent_add_activity_rentaldate.setText(date);
            }
        };


    }

    private void load_car_spinner_data() {
        MyDatabaseHelper db = new MyDatabaseHelper(Rent_AddActivity.this);
        List<String> labels = db.get_all_car_spinner();

        list_regno = db.get_all_car_regno_spinner();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        rent_add_activity_regno_spinner.setAdapter(dataAdapter);
    }

    private void load_customer_spinner_data() {
        MyDatabaseHelper db = new MyDatabaseHelper(Rent_AddActivity.this);
        List<String> labels = db.get_all_customer_spinner();

        list_cusid = db.get_all_customer_id_spinner();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        rent_add_activity_cusid_spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}