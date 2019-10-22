package com.codingminds.fittracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FoodListListAdapter extends ArrayAdapter<FoodEntry> {
    private Context context;
    private int resource;
    private List<FoodEntry> foodList;

    public FoodListListAdapter(@NonNull Context context, int resource, @NonNull List<FoodEntry> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.foodList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, parent,false);

        TextView dateTimeTextView = view.findViewById(R.id.textViewDateTime);
        TextView foodNameTextView = view.findViewById(R.id.textViewFoodName);
        TextView calorieCountTextView = view.findViewById(R.id.textViewCalorieCount);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date(foodList.get(position).getTimestamp());

        dateTimeTextView.setText(sf.format(date));
        foodNameTextView.setText(foodList.get(position).getName());
        calorieCountTextView.setText("" + foodList.get(position).getCalTotal());
        return view;
    }
}
