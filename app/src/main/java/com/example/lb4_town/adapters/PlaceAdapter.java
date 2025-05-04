package com.example.lb4_town.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lb4_town.R;
import com.example.lb4_town.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    private List<Place> places;

    public PlaceAdapter(List<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        holder.textViewName.setText(place.getName());
        holder.textViewDescription.setText(place.getDescription());
        holder.imageView.setImageResource(place.getImageResId()); // Устанавливаем локальное изображение
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        PlaceViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.logo_place);
            textViewDescription = itemView.findViewById(R.id.description_place);
            imageView = itemView.findViewById(R.id.image_place);
        }
    }
}