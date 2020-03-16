package com.example.ui;

/**
 * 
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.example.BaseActivity;
import com.example.MyApplication;
import com.example.R;

import com.example.adapter.ChayichaAdapter;
import com.example.bean.BaseBean;
import com.example.bean.Zhangmu;
import com.example.db.DBManager;
import com.example.db.ShouzhiColumn;

public class ChayichaActivity extends BaseActivity{

private ListView lv_chayicha;
private DBManager dbManager;
private TextView tv_date_middle;
private ImageView iv_date_zeng;
private ImageView iv_date_jian;

private ChayichaAdapter chayichaAdapter;

private int i_year;
private int i_month;


private ImageView iv_titleleft;
private ImageView iv_titleview_right;
private TextView tv_titleview_middle;

public static  final int DELETE=0;
public static  final int RESUME=1;

private Handler handler=new Handler(){

	@Override
	public void handleMessage(Message msg) {
		switch(msg.what){
		case DELETE:
			chayichaAdapter.setList(getZhangmus(dbManager));
			chayichaAdapter.notifyDataSetChanged();
			Toast.makeText(ChayichaActivity.this, "删除成功", 2000).show();
			break;
		case RESUME:
			chayichaAdapter.setList(getZhangmus(dbManager));
			chayichaAdapter.notifyDataSetChanged();		
			break;
		}
	}
	
};
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_chayicha;
	}

	@Override
	protected void setupView() {
		//从数据库获取数据
		dbManager=((MyApplication)getApplication()).getDbManager();
		
		tv_date_middle=(TextView)findViewById(R.id.tv_date_middle);	
		iv_date_zeng=(ImageView)findViewById(R.id.iv_date_zeng);
		
		iv_titleleft=(ImageView)findViewById(R.id.iv_titleleft);
		iv_titleleft.setBackgroundResource(R.drawable.iv_add_background);
		iv_titleleft.setVisibility(View.VISIBLE);
		iv_titleleft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activityManager.Jump2Activity(ChayichaActivity.this, MainActivity.class);			
			}
		});
		
		iv_titleview_right=(ImageView)findViewById(R.id.iv_titleview_right);
		iv_titleview_right.setBackgroundResource(R.drawable.iv_edit_background);
		iv_titleview_right.setVisibility(View.VISIBLE);
		iv_titleview_right.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chayichaAdapter.showRight_icon();
			}
		});
		tv_titleview_middle=(TextView)findViewById(R.id.tv_titleview_middle);
		
		Calendar c = Calendar.getInstance();
		int curMonth= c.get(Calendar.MONTH)+1;
		int curYear=c.get(Calendar.YEAR);
		int curDay=c.get(Calendar.DAY_OF_MONTH);
		if(curMonth<10){
			tv_date_middle.setText(curYear+"-0"+curMonth);	
		}else{
		tv_date_middle.setText(curYear+"-"+curMonth);
		}
		
			
		String date=tv_date_middle.getText().toString().trim();
		String year=date.substring(0,date.indexOf("-"));
		String month=date.substring(date.lastIndexOf("-")+1, date.length());
		i_year=Integer.parseInt(year);
		i_month=Integer.parseInt(month);		
		iv_date_zeng.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(i_month==12){
					i_year++;
					i_month=1;
		         }
				else{
					i_month++;
				}
				if(i_month<10){
					tv_date_middle.setText(i_year+"-0"+i_month);	
				}else{
				tv_date_middle.setText(i_year+"-"+i_month);
				}
				chayichaAdapter.setList(getZhangmus(dbManager));
				chayichaAdapter.notifyDataSetChanged();
			}
		});
		
		iv_date_jian=(ImageView)findViewById(R.id.iv_date_jian);
		iv_date_jian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i_month==1){
					i_year--;
					i_month=12;
		         }
				else{
					i_month--;
				}
				if(i_month<10){
					tv_date_middle.setText(i_year+"-0"+i_month);	
				}else{
				tv_date_middle.setText(i_year+"-"+i_month);
				}
				chayichaAdapter.setList(getZhangmus(dbManager));
				chayichaAdapter.notifyDataSetChanged();
			}
			
		});
	
		List zhangmus=getZhangmus(dbManager);
		chayichaAdapter=new ChayichaAdapter(zhangmus, ChayichaActivity.this,dbManager,handler);
		lv_chayicha=(ListView)findViewById(R.id.lv_chayicha);		
		lv_chayicha.setAdapter(chayichaAdapter);
		
	}

	private List<Zhangmu> getZhangmus(DBManager dbManager){
		String [] selectionArgs=new String[]{tv_date_middle.getText().toString()};
		String SQL="select * from shouzhi where date like '"+selectionArgs[0]+"%'";
		/*(ShouzhiColumn.TABLE_NAME, null,"date like '"+selectionArgs[0]+ "%'" ,selectionArgs);*/
		Cursor cursor=dbManager.getDbHelper().rawQuery(SQL, null);
	    List <Zhangmu> zhangmus=new ArrayList<Zhangmu>();
	   
		while(cursor.moveToNext()){
			
	    	Zhangmu zhangmu=new Zhangmu();
	    	int id=cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
	    	int money=cursor.getInt(cursor.getColumnIndexOrThrow(ShouzhiColumn.MONEY));
	    	String date=cursor.getString(cursor.getColumnIndexOrThrow(ShouzhiColumn.DATE));
	    	String fangshi=cursor.getString(cursor.getColumnIndexOrThrow(ShouzhiColumn.FANGSHI));
	    	String leimu=cursor.getString(cursor.getColumnIndexOrThrow(ShouzhiColumn.LEIMU));
	    	String beizhu=cursor.getString(cursor.getColumnIndexOrThrow(ShouzhiColumn.BEIZHU));
	    	String status=cursor.getString(cursor.getColumnIndexOrThrow(ShouzhiColumn.STATUS));
	    	
	    	zhangmu.setId(id);
	    	zhangmu.setJine(money);
	    	zhangmu.setDate(date);
	    	zhangmu.setFangshi(fangshi);
	    	zhangmu.setLeimu(leimu);
	    	zhangmu.setBeizhu(beizhu);
	    	zhangmu.setStatus(status);
	    	
	    	zhangmus.add(zhangmu);
	    	
	    }
		return zhangmus;
	}
	@Override
	protected void initializedData() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		handler.sendEmptyMessage(RESUME);
	}


}
