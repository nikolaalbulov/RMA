package com.example.recepti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class KupovinaAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> items;

    public KupovinaAdapter(Context context, List<String> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_kupovina_item, parent, false);
        }

        TextView itemText = convertView.findViewById(R.id.item_text);
        Button removeButton = convertView.findViewById(R.id.ukloni_button);

        String item = items.get(position);
        itemText.setText(item);


        removeButton.setOnClickListener(v -> {
            items.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
