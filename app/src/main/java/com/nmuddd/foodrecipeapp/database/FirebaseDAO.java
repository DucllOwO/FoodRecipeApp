package com.nmuddd.foodrecipeapp.database;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nmuddd.foodrecipeapp.database.Listeners.TaskListener;
import com.nmuddd.foodrecipeapp.database.Listeners.RetrievalEventListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class FirebaseDAO<T> {
    private static String DatabaseURLScheme = "https://food-recipe-app-374eb-default-rtdb.asia-southeast1.firebasedatabase.app";
    protected static final DatabaseReference dbReference = FirebaseDatabase.getInstance(DatabaseURLScheme).getReference();
    protected String tableName;
    public FirebaseDAO(String tableName)
    {
        this.tableName = tableName;
    }

    public String GetNewKey()
    {
        return dbReference.child(tableName).push().getKey();
    }

    protected abstract void parseDataSnapshot(DataSnapshot dataSnapshot, RetrievalEventListener<T> retrievalEventListener);

    public void getAll(final RetrievalEventListener<List<T>> retrievalEventListener){
        DatabaseReference rowReference = dbReference.child(tableName);
        rowReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<T> list = new ArrayList<T>();
                final long len = dataSnapshot.getChildrenCount();
                if(len == 0)
                {
                    retrievalEventListener.OnDataRetrieved(list);
                    return;
                }
                RetrievalEventListener<T> listRetrievalEventListener = new RetrievalEventListener<T>() {
                    @Override
                    public void OnDataRetrieved(T t) {
                        list.add(t);
                        if(list.size() == len){
                            retrievalEventListener.OnDataRetrieved(list);
                        }
                    }
                };
                for(DataSnapshot currentDataSnapshot : dataSnapshot.getChildren())
                    parseDataSnapshot(currentDataSnapshot, listRetrievalEventListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void save(T t, String id, final TaskListener taskListener){
        Task<Void> task = dbReference.child(tableName).child(id).setValue(t);
        task.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                taskListener.OnSuccess();
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                taskListener.OnFail();
            }
        });
    }
    //id này là id của cái phần cần delete chứ k phải id trên firebase
    //idName là tên thuộc tính id của element cần xoá vd của meal favorite là idMeal
    public void delete(String id, String idName){
        Query query = dbReference.child(tableName).orderByChild(idName).equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    item.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
