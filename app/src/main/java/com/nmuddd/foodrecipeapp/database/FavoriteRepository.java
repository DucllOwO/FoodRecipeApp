package com.nmuddd.foodrecipeapp.database;

import android.app.Application;

import com.nmuddd.foodrecipeapp.model.MealFavorite;

import java.util.List;

public class FavoriteRepository {

    private FavoriteDAO favoriteDAO;
    
    public FavoriteRepository(Application application) {
        FavoriteDB database = FavoriteDB.getDatabase(application);
        favoriteDAO = database.favoriteDAO();
    }
    
    public void insert(MealFavorite meal) {
        favoriteDAO.insert(meal);
    }
    
    public void delete(String name) {
        favoriteDAO.delete(name);
    }
    
    public List<MealFavorite> select() {
        return favoriteDAO.select();
    }
    
    public boolean isFavorite(String name) {
        return favoriteDAO.isFavorite(name);
    }
}
