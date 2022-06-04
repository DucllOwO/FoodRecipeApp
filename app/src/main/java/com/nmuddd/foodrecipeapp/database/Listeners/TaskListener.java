package com.nmuddd.foodrecipeapp.database.Listeners;

public abstract class TaskListener extends AbstractEventListener {
    public abstract void OnSuccess();
    public abstract void OnFail();
}
