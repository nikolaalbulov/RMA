package com.example.recepti;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ReceptDetalji extends AppCompatActivity {

    private static List<Recepti> sacuvaniRecepti = new ArrayList<>();
    private Recepti trenutniRecept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recept_detalji_activity);

        ImageView imageView = findViewById(R.id.detalji_slika);
        TextView nameTextView = findViewById(R.id.detalji_ime);
        TextView ingredientsTextView = findViewById(R.id.detalji_sastojci);
        TextView instructionsTextView = findViewById(R.id.detalji_instrukcije);
        CheckBox saveCheckBox = findViewById(R.id.sacuvan_cb);


        String name = getIntent().getStringExtra("ime");
        String ingredients = getIntent().getStringExtra("sastojci");
        String instructions = getIntent().getStringExtra("instrukcije");
        int imageResId = getIntent().getIntExtra("slika", 0);
        boolean isSaved = getIntent().getBooleanExtra("sacuvan", false);


        trenutniRecept = new Recepti(name, ingredients, instructions, imageResId, isSaved);

        nameTextView.setText(name);
        ingredientsTextView.setText(ingredients);
        instructionsTextView.setText(instructions);
        imageView.setImageResource(imageResId);


        saveCheckBox.setChecked(trenutniRecept.isSacuvan());


        saveCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            trenutniRecept.setSacuvan(isChecked);

            if (isChecked) {
                if (!sacuvaniRecepti.contains(trenutniRecept)) {
                    sacuvaniRecepti.add(trenutniRecept);
                    Toast.makeText(this, "Recept sačuvan.", Toast.LENGTH_SHORT).show();
                }
            } else {
                sacuvaniRecepti.removeIf(recept -> recept.getName().equals(trenutniRecept.getName()));
                Toast.makeText(this, "Recept uklonjen iz sačuvanih.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static List<Recepti> getSacuvaniRecepti() {
        return sacuvaniRecepti;
    }
}
