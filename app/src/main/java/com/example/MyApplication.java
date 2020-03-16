package com.example;


import java.io.File;
import java.util.ArrayList;

import com.example.bean.User;
import com.example.db.DBManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 功能描述：用于存放全局变量和公用的资源等
 */
public class MyApplication extends Application {

    private ActivityManager activityManager;//窗体管理员


    private DBManager dbManager;//数据库管理员
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        activityManager = new ActivityManager(new ArrayList<Activity>(), this);

        dbManager = new DBManager(this);

    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public DBManager getDbManager() {
        return dbManager;
    }


    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }


    public ActivityManager getActivityManager() {
        return activityManager;
    }

    public void setActivityManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }

    //退出程序对话框
    public void promptExit(final Context context) {
        LayoutInflater li = LayoutInflater.from(context);
        View exitV = li.inflate(R.layout.dialog_exit, null);
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setView(exitV);
        ab.setPositiveButton(R.string.exit, new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                exitApp(context);
            }
        });
        ab.setNegativeButton(R.string.cancle, null
        );
        ab.show();
    }

    //退出程序
    public void exitApp(Context context) {
        for (Activity ac : activityManager.getActivities()) {
            ac.finish();
        }
    }

}
