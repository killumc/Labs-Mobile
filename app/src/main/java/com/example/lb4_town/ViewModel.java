package com.example.lb4_town;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lb4_town.Place;
import com.example.lb4_town.PlaceRepository;

import java.util.List;
public class ViewModel extends AndroidViewModel {
    private PlaceRepository repository;
    private MutableLiveData<List<Place>> places;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new PlaceRepository();
        places = new MutableLiveData<>();
        loadPlaces();
    }

    private void loadPlaces() {
        places.setValue(repository.getPlaces());
    }

    public LiveData<List<Place>> getPlaces() {
        return places;
    }
}
