package com.example.recepti;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recepti.KupovinaAdapter;
import java.util.ArrayList;

public class ListaKupovinaActivity extends AppCompatActivity {

    private ArrayList<String> shoppingList;
    private KupovinaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupovina);

        shoppingList = new ArrayList<>();
        adapter = new KupovinaAdapter(this, shoppingList);

        ListView listView = findViewById(R.id.lista_kupovina_view);
        listView.setAdapter(adapter);

        EditText itemInput = findViewById(R.id.item_input);
        Button addButton = findViewById(R.id.dodaj_button);

        addButton.setOnClickListener(view -> {
            String item = itemInput.getText().toString();
            if (!item.isEmpty()) {
                shoppingList.add(item);
                adapter.notifyDataSetChanged();
                itemInput.setText("");
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_sacuvani_recepti:
                    startActivity(new Intent(ListaKupovinaActivity.this, SacuvaniReceptiActivity.class));
                    return true;
                case R.id.navigation_home:
                    startActivity(new Intent(ListaKupovinaActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_kupovina:
                    return true;
            }
            return false;
        });
    }
}
