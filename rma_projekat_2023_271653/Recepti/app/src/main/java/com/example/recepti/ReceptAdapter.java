package com.example.recepti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.BaseAdapter;

import java.util.List;

public class ReceptAdapter extends BaseAdapter {

    private Context context;
    private List<Recepti> recipeList;

    public ReceptAdapter(Context context, List<Recepti> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    public void updateData(List<Recepti> newList) {
        this.recipeList = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recept_list_item, parent, false);
        }

        Recepti recipe = recipeList.get(position);

        ImageView imageView = convertView.findViewById(R.id.recept_slika);
        TextView nameTextView = convertView.findViewById(R.id.recept_ime);

        imageView.setImageResource(recipe.getImageResId());
        nameTextView.setText(recipe.getName());

        return convertView;
    }

    
}
