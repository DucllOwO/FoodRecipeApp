package com.nmuddd.foodrecipeapp.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nmuddd.foodrecipeapp.database.Listeners.RetrievalEventListener;
import com.nmuddd.foodrecipeapp.model.MealFavorite;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MealFavoriteDAO extends FirebaseDAO<MealFavorite> {
    public MealFavoriteDAO() {
        super("MealFavorite");
    }
    public List<MealFavorite> mealFavoriteList;
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
