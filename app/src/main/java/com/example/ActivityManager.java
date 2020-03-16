package com.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.ui.HelpActivity;
import com.example.ui.MoreActivity;

import java.util.List;

public class ActivityManager {
    MyApplication myApplication;

    List<Activity> activities;

    public ActivityManager(List<Activity> list, MyApplication application) {
        this.activities = list;
        this.myApplication = application;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void Jump2Activity(Context moreActivity, Class<?> helpActivityClass) {
        moreActivity.startActivity(new Intent(moreActivity,helpActivityClass));
    }
}
