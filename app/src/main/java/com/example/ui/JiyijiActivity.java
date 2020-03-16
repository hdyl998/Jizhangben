package com.example.ui;

/**
 * 页面
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.baodian.widget.SoftInfoDialog;
import com.example.BaseActivity;
import com.example.MyApplication;
import com.example.R;
import com.example.adapter.ViewPagerAdapter;
import com.example.bean.BaseBean;

import com.example.db.DBHelper;
import com.example.db.DBManager;
import com.example.db.ShouzhiColumn;
import com.example.listener.PageChangerListner;




public class JiyijiActivity extends BaseActivity {

    private final String TAG="JiyijiActivity";
    
	
	private LinearLayout ll_jiyiji_menu;
	private int []titlebg;
	private ViewPager viewPager;
	private ImageView btn_more;
	private ImageView btn_help;

	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_jiyiji;
	}

	@Override
	protected void setupView() {
		
		btn_help=(ImageView)findViewById(R.id.btn_help);
		btn_help.setVisibility(View.VISIBLE);
		btn_help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activityManager.Jump2Activity(JiyijiActivity.this, HelpActivity.class);
			}
		});
		btn_more=(ImageView)findViewById(R.id.btn_more);
		btn_more.setVisibility(View.VISIBLE);
		btn_more.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activityManager.Jump2Activity(JiyijiActivity.this, MoreActivity.class);
			}
		});
		titlebg=new int[] { R.drawable.tab_out, R.drawable.tab_into
				};

		ll_jiyiji_menu=(LinearLayout)findViewById(R.id.ll_jiyiji_menu);
				
		Button btn_jiyiji_zhichu=(Button)findViewById(R.id.btn_jiyiji_zhichu);
		btn_jiyiji_zhichu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				ll_jiyiji_menu.setBackgroundResource(titlebg[0]);
				viewPager.setCurrentItem(0);
			}
		});
		
		Button btn_jiyiji_shouru=(Button)findViewById(R.id.btn_jiyiji_shouru);
		btn_jiyiji_shouru.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				ll_jiyiji_menu.setBackgroundResource(titlebg[1]);
				viewPager.setCurrentItem(1);
			}
		});
		
		 //支出
		 View  jiyiji_zhichu= LayoutInflater.from(JiyijiActivity.this).inflate(R.layout.ll_jiyiji,
				null);
		 final SoftInfoDialog softInfoDialog_zhichu= new SoftInfoDialog(JiyijiActivity.this);
		  final EditText et_money=(EditText)jiyiji_zhichu.findViewById(R.id.et_money);
		  final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  final TextView tv_date=(TextView)jiyiji_zhichu.findViewById(R.id.tv_date);
		  tv_date.setText("请选择时间:"+formatter.format(new Date()));
		  tv_date.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				softInfoDialog_zhichu.show();
				softInfoDialog_zhichu.setTv_date(tv_date);
			}
		});
		  final Spinner sp_shouzhi=(Spinner)jiyiji_zhichu.findViewById(R.id.sp_shouzhi);
		 
		  List <String> list_shouzhi=new ArrayList<String>();
		  list_shouzhi.add("现金");
		  list_shouzhi.add("储蓄卡");
		  list_shouzhi.add("其他");
		  list_shouzhi.add("交通卡");		  
		  list_shouzhi.add("借记卡");
		  list_shouzhi.add("信用卡");
		  list_shouzhi.add("支付宝");
		  list_shouzhi.add("财付通");
		
		  
		  
	
		  ArrayAdapter adapter_shouzhi = new ArrayAdapter<String>(this,
					R.layout.spinner_item, list_shouzhi);
		  adapter_shouzhi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  sp_shouzhi.setAdapter(adapter_shouzhi);
		 
		  final Spinner sp_shouzhileimu=(Spinner)jiyiji_zhichu.findViewById(R.id.sp_shouzhileimu);
		  List <String> list=new ArrayList<String>();
		  list.add("美食");
		  list.add("交通");
		  list.add("家居");
		  list.add("装扮");
		  list.add("娱乐");
		  list.add("数码");
		  list.add("旅游");
		  list.add("其他");
		  		  
		  ArrayAdapter adapter = new ArrayAdapter<String>(this,
					R.layout.spinner_item, list);
		  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  sp_shouzhileimu.setAdapter(adapter);
		  final EditText et_beizhu=(EditText)jiyiji_zhichu.findViewById(R.id.et_beizhu);
		 
		  ImageView iv_confirm=(ImageView)jiyiji_zhichu.findViewById(R.id.iv_confirm);
		  iv_confirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(et_money.length()==0||et_money.equals("")){
					Toast.makeText(JiyijiActivity.this, "金额不可为0", 2000).show();
					return;
				}	
				
				ContentValues contentValues=new ContentValues();
				contentValues.put(ShouzhiColumn.MONEY, et_money.getText().toString().trim());
				if(softInfoDialog_zhichu.getDate()!=null)
				contentValues.put(ShouzhiColumn.DATE, softInfoDialog_zhichu.getDate());
				else{
					contentValues.put(ShouzhiColumn.DATE, formatter.format(new Date()));
				}
				contentValues.put(ShouzhiColumn.FANGSHI, sp_shouzhi.getSelectedItem().toString());
				contentValues.put(ShouzhiColumn.LEIMU, sp_shouzhileimu.getSelectedItem().toString());
				contentValues.put(ShouzhiColumn.BEIZHU, et_beizhu.getText().toString());
				contentValues.put(ShouzhiColumn.STATUS, "0");//0代表支出
			    if(insertintoDB(((MyApplication)getApplication()).getDbManager(), contentValues))
				{
			    	Toast.makeText(JiyijiActivity.this, "账单添加成功", 2000).show();		    
				}else{
					Toast.makeText(JiyijiActivity.this, "账单添加失败", 2000).show();		    
			    }
			}
		});
		  
		  //收入
		  View  jiyiji_shouru= LayoutInflater.from(JiyijiActivity.this).inflate(R.layout.ll_jiyiji,
					null);
		  final SoftInfoDialog softInfoDialog_shouru= new SoftInfoDialog(JiyijiActivity.this);
		  final EditText et_shouru_money=(EditText)jiyiji_shouru.findViewById(R.id.et_money);
		  final TextView tv_shouru_date=(TextView)jiyiji_shouru.findViewById(R.id.tv_date);
		  tv_shouru_date.setText("选择日期:"+formatter.format(new Date()));
		  tv_shouru_date.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				softInfoDialog_shouru.show();	
				softInfoDialog_shouru.setTv_date(tv_shouru_date);
			}
		});
		  final Spinner sp_shouru_shouzhi=(Spinner)jiyiji_shouru.findViewById(R.id.sp_shouzhi);
		 
		  List <String> list_shouru_shouzhi=new ArrayList<String>();
		  list_shouru_shouzhi.add("现金");
		  list_shouru_shouzhi.add("储蓄卡");
		  list_shouru_shouzhi.add("羊城通");
	
		  ArrayAdapter adapter_shouru_shouzhi = new ArrayAdapter<String>(this,
					R.layout.spinner_item, list_shouru_shouzhi);
		  adapter_shouru_shouzhi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  sp_shouru_shouzhi.setAdapter(adapter_shouru_shouzhi);
		 
		  final Spinner sp_shouru_shouzhileimu=(Spinner)jiyiji_shouru.findViewById(R.id.sp_shouzhileimu);
		  List <String> shouru_list=new ArrayList<String>();
		  shouru_list.add("工资");
		  shouru_list.add("奖金");
		  shouru_list.add("其他");
		  		  
		  ArrayAdapter shouru_adapter = new ArrayAdapter<String>(this,
					R.layout.spinner_item, shouru_list);
		  shouru_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  sp_shouru_shouzhileimu.setAdapter(shouru_adapter);
		  final EditText et_shouru_beizhu=(EditText)jiyiji_shouru.findViewById(R.id.et_beizhu);
		 
		  ImageView iv_shouru_confirm=(ImageView)jiyiji_shouru.findViewById(R.id.iv_confirm);
		  iv_shouru_confirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(et_shouru_money.equals("")){
					Toast.makeText(JiyijiActivity.this, "金额不可为0", 2000).show();
					return;
				}	
				
				ContentValues contentValues=new ContentValues();
				contentValues.put(ShouzhiColumn.MONEY, et_shouru_money.getText().toString().trim());
				if(softInfoDialog_shouru.getDate()!=null)
					contentValues.put(ShouzhiColumn.DATE, softInfoDialog_shouru.getDate());
					else{
						contentValues.put(ShouzhiColumn.DATE, formatter.format(new Date()));
					}
				contentValues.put(ShouzhiColumn.FANGSHI, sp_shouru_shouzhi.getSelectedItem().toString());
				contentValues.put(ShouzhiColumn.LEIMU, sp_shouru_shouzhileimu.getSelectedItem().toString());
				contentValues.put(ShouzhiColumn.BEIZHU, et_shouru_beizhu.getText().toString());
				contentValues.put(ShouzhiColumn.STATUS, "1");//1代表收入
			    if(insertintoDB(((MyApplication)getApplication()).getDbManager(), contentValues))
				{
			    	Toast.makeText(JiyijiActivity.this, "账单添加成功", 2000).show();		    
				}else{
					Toast.makeText(JiyijiActivity.this, "账单添加失败", 2000).show();		    
			    }
			}
		});
		  
		   /*************************************************/
		   List views=new ArrayList<View>();
		   viewPager=(ViewPager) findViewById(R.id.jiyiji_vPager); 	
		   viewPager.setOnPageChangeListener(new PageChangerListner(ll_jiyiji_menu,titlebg)); 
		   
		   views.add(jiyiji_zhichu);
		   views.add(jiyiji_shouru);
		   
		   viewPager.setAdapter(new ViewPagerAdapter(views));
		   viewPager.setCurrentItem(0);
		
		   	   		   
	}

	private boolean insertintoDB(DBManager dbManager,ContentValues contentValues){
		DBHelper dbHelper=dbManager.getDbHelper();		
		if(dbHelper.insert(ShouzhiColumn.TABLE_NAME, contentValues)!=-1){
			return true;		
		}
		return false;
		
	}
	@Override
	protected void initializedData() {
				    
 
	}


}
