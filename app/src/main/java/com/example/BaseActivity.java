package com.example;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.db.DBManager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;


/**
 * 功能描述：应用中界面（Activity）的基类
 * 对原有的Activity类进行扩展
 * 
 */
public abstract class BaseActivity extends Activity {


    protected Handler mHandler;//handler类

    public ActivityManager activityManager;//窗体管理员
    public DBManager dbManager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        activityManager= ((MyApplication) this.getApplication()).getActivityManager();
        activityManager.getActivities().add(this);
        mHandler = new Handler();
        dbManager=((MyApplication)getApplication()).getDbManager();
        // 初始化组件
        setupView();
        // 初始化数据
        initializedData();
    }

    public DBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	/**
     * 布局文件ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化组件
     */
    protected abstract void setupView();

    /**
     * 初始化数据
     */
    protected abstract void initializedData();

    @Override
    protected void onPause() {
      super.onPause();
    }



    @Override
   	public boolean onKeyDown(int keyCode, KeyEvent event) {
   		// TODO Auto-generated method stub
   		if(keyCode==KeyEvent.KEYCODE_BACK)
   		 {
   		   ((MyApplication)getApplication()).promptExit(this);	
   			return true;
   		 }
   		return false;
   	}	
    
    @Override
    protected void onDestroy() {
 
        activityManager.getActivities().remove(this);
        super.onDestroy();
    }
    
      
    /**
     * 显示Toast形式的提示信息
     * @param message
     */
    protected void show(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
