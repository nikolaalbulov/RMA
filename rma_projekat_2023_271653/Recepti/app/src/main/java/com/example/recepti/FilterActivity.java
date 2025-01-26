package com.example.recepti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        CheckBox sirCheckBox = findViewById(R.id.sir_cb);
        CheckBox paradajzCheckBox = findViewById(R.id.paradajz_cb);
        CheckBox pastaCheckBox = findViewById(R.id.pasta_cb);
        CheckBox piletinaCheckBox = findViewById(R.id.piletina_cb);
        CheckBox testoCheckBox = findViewById(R.id.testo_cb);
        CheckBox pelatCheckBox = findViewById(R.id.pelat_cb);

        Button applyFilterButton = findViewById(R.id.apply_filter_button);

        applyFilterButton.setOnClickListener(view -> {
            ArrayList<String> selectedFilters = new ArrayList<>();


            if (sirCheckBox.isChecked()) {
                selectedFilters.add("Sir");
            }
            if (pastaCheckBox.isChecked()) {
                selectedFilters.add("Pasta");
            }
            if (paradajzCheckBox.isChecked()) {
                selectedFilters.add("Paradajz");
            }
            if (piletinaCheckBox.isChecked()) {
                selectedFilters.add("Piletina");
            }
            if (testoCheckBox.isChecked()) {
                selectedFilters.add("Testo");
            }
            if (pelatCheckBox.isChecked()) {
                selectedFilters.add("Pelat");
            }


            Intent resultIntent = new Intent();
            resultIntent.putStringArrayListExtra("selectedFilters", selectedFilters);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
