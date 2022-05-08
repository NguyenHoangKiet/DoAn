package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        rent_add_activity_regno_spinner.setOnItemSelectedListener(this);
        load_car_spinner_data();
    }

    private void load_car_spinner_data() {
        MyDatabaseHelper db = new MyDatabaseHelper(Rent_AddActivity.this);
        List<String> labels = db.get_all_car_spinner();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        rent_add_activity_regno_spinner.setAdapter(dataAdapter);
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