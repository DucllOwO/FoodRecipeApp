package com.nmuddd.foodrecipeapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite")
public class MealFavorite {
    @PrimaryKey @NonNull public String idMeal;
    public String strMeal;
    public String strMealThumb;

}