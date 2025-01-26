package com.example.recepti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView; // Correct import for SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class SacuvaniReceptiActivity extends AppCompatActivity {

    private ListView savedRecipesListView;
    private ReceptAdapter receptAdapter;
    private List<Recepti> sviSacuvani;
    private List<Recepti> pretrazeniRecepti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacuvani_recepti);

        savedRecipesListView = findViewById(R.id.recepti_list_view);


        sviSacuvani = new ArrayList<>(ReceptDetalji.getSacuvaniRecepti());
        pretrazeniRecepti = new ArrayList<>(sviSacuvani);


        receptAdapter = new ReceptAdapter(this, pretrazeniRecepti);
        savedRecipesListView.setAdapter(receptAdapter);


        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterRecipes(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterRecipes(newText);
                return true;
            }
        });

        savedRecipesListView.setOnItemClickListener((parent, view, position, id) -> {
            Recepti selectedRecipe = pretrazeniRecepti.get(position);

            Intent intent = new Intent(SacuvaniReceptiActivity.this, ReceptDetalji.class);
            intent.putExtra("ime", selectedRecipe.getName());
            intent.putExtra("sastojci", selectedRecipe.getSastojci());
            intent.putExtra("instrukcije", selectedRecipe.getInstrukcije());
            intent.putExtra("slika", selectedRecipe.getImageResId());
            intent.putExtra("sacuvan", selectedRecipe.isSacuvan());
            startActivity(intent);
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_sacuvani_recepti:
                    return true;
                case R.id.navigation_home:
                    startActivity(new Intent(SacuvaniReceptiActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_kupovina:
                    startActivity(new Intent(SacuvaniReceptiActivity.this, ListaKupovinaActivity.class));
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        sviSacuvani = new ArrayList<>(ReceptDetalji.getSacuvaniRecepti());
        filterRecipes("");
    }

    private void filterRecipes(String query) {
        pretrazeniRecepti.clear();
        for (Recepti recipe : sviSacuvani) {
            if (recipe.getName().toLowerCase().contains(query.toLowerCase())) {
                pretrazeniRecepti.add(recipe);
            }
        }
        receptAdapter.notifyDataSetChanged();
    }
}
