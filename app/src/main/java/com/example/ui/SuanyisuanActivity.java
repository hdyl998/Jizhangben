package com.example.ui;
/**
 * 
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.BaseActivity;
import com.example.MyApplication;
import com.example.R;
import com.example.bean.Phone;
import com.example.db.DBManager;
import com.example.db.ShouzhiColumn;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;



public class SuanyisuanActivity extends BaseActivity{

	private TextView tv_suanyisuan_middle;
	private ImageView iv_suanyisuan_zeng;
	private ImageView iv_suanyisuan_jian;
	
	private TextView tv_shouru;
	private TextView tv_zhichu;
	private TextView tv_gongzi;
	private TextView tv_jiangjin;
	private TextView tv_meishi;
	private TextView tv_jiaotong;
	private TextView tv_jiaju;
	private TextView tv_zhuangban;
	private TextView tv_yule;
	private TextView tv_shuma;
	private TextView tv_lvyou;
	private TextView tv_qita;
	
	
	private int i_year;
	private int i_month;
	
	private DBManager dbManager;

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_suanyisuan;
	}

	@Override
	protected void setupView() {
		
		dbManager=((MyApplication)getApplication()).getDbManager();
		iv_suanyisuan_zeng=(ImageView)findViewById(R.id.iv_suanyisuan_zeng);
		tv_suanyisuan_middle=(TextView)findViewById(R.id.tv_suanyisuan_middle);	
		iv_suanyisuan_jian=(ImageView)findViewById(R.id.iv_suanyisuan_jian);
		
		Calendar c = Calendar.getInstance();
		int curMonth= c.get(Calendar.MONTH)+1;
		int curYear=c.get(Calendar.YEAR);
		int curDay=c.get(Calendar.DAY_OF_MONTH);
		if(curMonth<10){
			tv_suanyisuan_middle.setText(curYear+"-0"+curMonth);	
		}else{
			tv_suanyisuan_middle.setText(curYear+"-"+curMonth);
		}
		
		
		String suanyisuan=tv_suanyisuan_middle.getText().toString().trim();
		String year=suanyisuan.substring(0,suanyisuan.indexOf("-"));
		String month=suanyisuan.substring(suanyisuan.lastIndexOf("-")+1, suanyisuan.length());
		i_year=Integer.parseInt(year);
		i_month=Integer.parseInt(month);		
		iv_suanyisuan_zeng.setOnClickListener(new View.OnClickListener() {
			
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
					tv_suanyisuan_middle.setText(i_year+"-0"+i_month);	
				}else{
					tv_suanyisuan_middle.setText(i_year+"-"+i_month);
				}
				
				initximu();
				
			}
		});
		
		iv_suanyisuan_jian=(ImageView)findViewById(R.id.iv_suanyisuan_jian);
		iv_suanyisuan_jian.setOnClickListener(new View.OnClickListener() {
			
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
					tv_suanyisuan_middle.setText(i_year+"-0"+i_month);	
				}else{
					tv_suanyisuan_middle.setText(i_year+"-"+i_month);
				}
				initximu();
			}
			
		});		
				
		
		
		
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
			
		initximu();
				
	}

	private int checkMoney(String type,String value,DBManager dbManager){
		
	   int totalmoney=0;	
	   Cursor cursor=dbManager.getDbHelper().query(ShouzhiColumn.TABLE_NAME, new String[]{ShouzhiColumn.MONEY,ShouzhiColumn.STATUS}, type+"=? and date like '"+tv_suanyisuan_middle.getText().toString().trim()+ "%'", new String[]{value});
	   while(cursor.moveToNext()){
	   String status=cursor.getString(cursor.getColumnIndexOrThrow(ShouzhiColumn.STATUS));
	   int money=0;
	   if(status.equals("1")){
		   money =cursor.getInt(cursor.getColumnIndexOrThrow(ShouzhiColumn.MONEY));	
	   }
	   else{
		   money =(-1)*cursor.getInt(cursor.getColumnIndexOrThrow(ShouzhiColumn.MONEY));	
	   }
	  
	   totalmoney=totalmoney+money;
	   }
	   return totalmoney;
	}

	
	private void initximu(){
		
		/***********************细目******************************/
		tv_shouru=(TextView)findViewById(R.id.tv_shouru);
		tv_shouru.setText("收入："+checkMoney("status","1",dbManager));
		
		
		tv_zhichu=(TextView)findViewById(R.id.tv_zhichu);
		tv_zhichu.setText("支出："+checkMoney("status","0",dbManager));
		
		tv_gongzi=(TextView)findViewById(R.id.tv_gongzi);
		tv_gongzi.setText(checkMoney("leimu","工资",dbManager)+"");
		
		
		tv_jiangjin=(TextView)findViewById(R.id.tv_jiangjin);
		tv_jiangjin.setText(checkMoney("leimu","奖金",dbManager)+"");
		
		
		tv_meishi=(TextView)findViewById(R.id.tv_meishi);
		tv_meishi.setText(checkMoney("leimu","美食",dbManager)+"");
		
		tv_jiaotong=(TextView)findViewById(R.id.tv_jiaotong);
		tv_jiaotong.setText(checkMoney("leimu","交通",dbManager)+"");
		
		tv_jiaju=(TextView)findViewById(R.id.tv_jiaju);
		tv_jiaju.setText(checkMoney("leimu","家居",dbManager)+"");
		
		
		tv_zhuangban=(TextView)findViewById(R.id.tv_zhuangban);
		tv_zhuangban.setText(checkMoney("leimu","装扮",dbManager)+"");
		
		tv_yule=(TextView)findViewById(R.id.tv_yule);
		tv_yule.setText(checkMoney("leimu","娱乐",dbManager)+"");
		
		tv_shuma=(TextView)findViewById(R.id.tv_shuma);
		tv_shuma.setText(checkMoney("leimu","数码",dbManager)+"");
		
		
		tv_lvyou=(TextView)findViewById(R.id.tv_lvyou);
		tv_lvyou.setText(checkMoney("leimu","旅游",dbManager)+"");
		
		tv_qita=(TextView)findViewById(R.id.tv_qita);
		tv_qita.setText(checkMoney("leimu","其他",dbManager)+"");

	}
	@Override
	protected void initializedData() {
					
		
		
	}

}
