package com.example.db;

import java.util.LinkedHashMap;
import java.util.Map;

import android.net.Uri;

public class UserColumn extends DatabaseColumn{
	
	public static final String TABLE_NAME="user";

	public static final String NAME="name";
	public static final String PASSWORD="password";	

  
    private  final Uri	CONTENT_URI	=Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
    private  final Map<String,String> mColumnMap = new LinkedHashMap<String,String>();
	  {	 	
		  mColumnMap.put(_ID, "integer primary key autoincrement");
		  mColumnMap.put(NAME, "text not null ");
		  mColumnMap.put(PASSWORD, "text not null");	
	
		
	 }
	  
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_NAME;
	}

	@Override
	public Uri getTableContent() {
		// TODO Auto-generated method stub
		return CONTENT_URI;
	}

	@Override
	protected Map<String, String> getTableMap() {
		// TODO Auto-generated method stub
		return mColumnMap;
	}

}
