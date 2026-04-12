package com.politecnico.TouristSitesColombia;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static final String PREF_NAME = "TurismoAppPrefs";
    private static final String KEY_FAVORITES = "mis_favoritos";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public FavoritesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public List<TurismoItem> getFavorites() {
        String json = sharedPreferences.getString(KEY_FAVORITES, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<ArrayList<TurismoItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void saveFavorites(List<TurismoItem> favorites) {
        String json = gson.toJson(favorites);
        sharedPreferences.edit().putString(KEY_FAVORITES, json).apply();
    }

    public boolean toggleFavorite(TurismoItem item) {
        List<TurismoItem> favorites = getFavorites();
        boolean isAdded;

        if (favorites.contains(item)) {
            favorites.remove(item);
            isAdded = false;
        } else {
            favorites.add(item);
            isAdded = true;
        }

        saveFavorites(favorites);
        return isAdded;
    }

    public boolean isFavorite(TurismoItem item) {
        return getFavorites().contains(item);
    }
}