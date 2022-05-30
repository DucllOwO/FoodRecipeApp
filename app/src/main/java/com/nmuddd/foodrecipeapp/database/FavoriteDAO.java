package com.nmuddd.foodrecipeapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nmuddd.foodrecipeapp.model.MealFavorite;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(MealFavorite meal);
    
    @Query("SELECT * FROM favorite")
    List<MealFavorite> select();

    @Query("DELETE FROM favorite WHERE strMeal = :name")
    void delete(String name);
    
    @Query("SELECT * FROM favorite WHERE strMeal = :name")
    boolean isFavorite(String name);
}
