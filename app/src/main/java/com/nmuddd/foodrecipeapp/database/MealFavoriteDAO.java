package com.nmuddd.foodrecipeapp.database;

import androidx.room.Dao;

import com.google.firebase.database.DataSnapshot;
import com.nmuddd.foodrecipeapp.database.Listeners.RetrievalEventListener;
import com.nmuddd.foodrecipeapp.model.MealFavorite;

public class MealFavoriteDAO extends FirebaseDAO<MealFavorite> {

    public MealFavoriteDAO() {
        super("MealFavorite");
    }

    @Override
    protected void parseDataSnapshot(DataSnapshot dataSnapshot, RetrievalEventListener<MealFavorite> retrievalEventListener) {
        final MealFavorite mealFavorite = new MealFavorite();
        mealFavorite.idMeal = dataSnapshot.getKey();
        //  ----------------------------------------------------------------------------------------
        // | IMPORTANT NOTE: make sure that the variable name is EXACTLY the same as the node name. |
        //  ----------------------------------------------------------------------------------------
        //       ↓                           ↓
        mealFavorite.strMeal = dataSnapshot.child("strMeal").getValue().toString();
        //          ↓                                                 ↓
        mealFavorite.strMealThumb = dataSnapshot.child("strMealThumb").getValue().toString();

        // Now we have parsed all of the attributes of the Student object. We will feed it to the callback
        retrievalEventListener.OnDataRetrieved(mealFavorite);
    }
}
