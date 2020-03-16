package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	
    private final String TAG="DBManager";
	private Context mContext;
	private DBHelper dbHelper;
	private SQLiteDatabase mSQLiteDB;

	public DBManager(Context context) {
		this.mContext = context;
		this.dbHelper = new DBHelper(context);
		this.mSQLiteDB = dbHelper.getReadableDatabase();
		Log.i(TAG, "DBManager has been created");
	}

	public DBHelper getDbHelper() {
		return dbHelper;
	}

	public void setDbHelper(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public SQLiteDatabase getmSQLiteDB() {
		return mSQLiteDB;
	}

	public void setmSQLiteDB(SQLiteDatabase mSQLiteDB) {
		this.mSQLiteDB = mSQLiteDB;
	}
	
	public void deleteDatabase(Context context){
	context.deleteDatabase(DBHelper.DB_NAME);  
}
}