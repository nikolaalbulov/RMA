package com.example.recepti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import com.example.recepti.R;

public class MainActivity extends AppCompatActivity {

    private List<Recepti> receptiList;
    private List<Recepti> filteredList;
    private ReceptAdapter receptAdapter;
    private ListView receptiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receptiList = new ArrayList<>();
        receptiList.add(new Recepti("Pasta", "Pasta, Pelat, Parmezan, Paradajz, Patlidzan",
                "Kuvajte pastu 12min, kuvajte paradajz sos i dinstajte luk, paradajz i patlidzan, prespite pastu u tiganj, promesajte i dodajte parmezan.", R.drawable.pasta, false));
        receptiList.add(new Recepti("Pizza", "Testo, Pelat, Sir, Sunka, Zacini",
                "Razvucite testo i premazite ga pelatom (paradajz sosom), preko stavite sunku i sir po zelji (pozeljno trapist ili mozarela za najbolji ukus), dodajte zacine po ukusu (origano, bosiljak,...).", R.drawable.pizza, false));
        receptiList.add(new Recepti("Cezar salata", "Zelena salata, Paradajz, Sir, Piletina, Majonez",
                "Isecite piletinu na komadice i ispecite, kada je piletina gotova prebacite je u ciniju i pomesajte je sa ostatkom sastojaka, zaciniti po zelji.", R.drawable.cezar,false));

        filteredList = new ArrayList<>(receptiList);


        receptiListView = findViewById(R.id.recepti_list_view);
        receptAdapter = new ReceptAdapter(this, filteredList);
        receptiListView.setAdapter(receptAdapter);


        receptiListView.setOnItemClickListener((parent, view, position, id) -> {
            Recepti selectedRecipe = filteredList.get(position);
            Intent intent = new Intent(MainActivity.this, ReceptDetalji.class);
            intent.putExtra("ime", selectedRecipe.getName());
            intent.putExtra("sastojci", selectedRecipe.getSastojci());
            intent.putExtra("instrukcije", selectedRecipe.getInstrukcije());
            intent.putExtra("slika", selectedRecipe.getImageResId());
            startActivity(intent);
        });


        ImageView filterIcon = findViewById(R.id.filter_icon);
        filterIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FilterActivity.class);
            startActivityForResult(intent, 1);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_sacuvani_recepti:
                    startActivity(new Intent(MainActivity.this, SacuvaniReceptiActivity.class));
                    return true;
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_kupovina:
                    startActivity(new Intent(MainActivity.this, ListaKupovinaActivity.class));
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            ArrayList<String> selectedFilters = data.getStringArrayListExtra("selectedFilters");

            if (selectedFilters != null && !selectedFilters.isEmpty()) {
                filterByIngredients(selectedFilters);
            } else {
                filteredList = new ArrayList<>(receptiList);
                receptAdapter.updateData(filteredList);
            }
        }
    }

    private void filterByIngredients(List<String> filters) {
        List<Recepti> filteredList = new ArrayList<>();

        for (Recepti recipe : receptiList) {
            for (String filter : filters) {
                if (recipe.getSastojci().toLowerCase().contains(filter.toLowerCase())) {
                    filteredList.add(recipe);
                    break;
                }
            }
        }
        
        this.filteredList = filteredList;
        receptAdapter.updateData(filteredList);
    }


}