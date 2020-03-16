package com.example.db;

import java.util.LinkedHashMap;
import java.util.Map;

import android.net.Uri;

public class ShouzhiColumn extends DatabaseColumn{
	
	public static final String TABLE_NAME="shouzhi";

	public static final String MONEY="money";
	public static final String DATE="date";
	public static final String FANGSHI="fangshi";
	public static final String LEIMU="leimu";
	public static final String BEIZHU="beizhu";
	public static final String STATUS="status";

  
    private  final Uri	CONTENT_URI	=Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
    private  final Map<String,String> mColumnMap = new LinkedHashMap<String,String>();
	  {	 	
		  mColumnMap.put(_ID, "integer primary key autoincrement");
		  mColumnMap.put(MONEY, "interger not null ");
		  mColumnMap.put(DATE, "text not null");	
		  mColumnMap.put(FANGSHI, "varchar(10) not null");	
		  mColumnMap.put(LEIMU, "varchar(10) not null");
		  mColumnMap.put(BEIZHU, "varchar(200)");
		  mColumnMap.put(STATUS, "char(1) not null");
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
