package com.example.ui;
/**
 * Main页面，页面底部的四个button
 */

import com.example.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity  {
	
	
	private  final String TAG="MainActivity";
    private  final String TAB_JIYIJI="jiyiji";
    private  final String TAB_CHAYICHA="chayicha";
    private  final String TAB_SUANYISUAN="suanyisuan";
    private  final String TAB_WODE="wode";
   
    private TabHost mTabHost;
	private RadioGroup mRadioGroup;
	
	private RadioButton radio_jiyiji;
	private RadioButton radio_chayicha;
	private RadioButton radio_suanyisuan;
	private RadioButton radio_wode;

    
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去除首标题
	this.setContentView(R.layout.maintabs);	//设置视图
	prepareView();
	}

	@SuppressWarnings("deprecation")
	private void prepareView() {
			 
		mTabHost=this.getTabHost();//获得tabhost对象
	    //添加深度报道tab
		TabSpec tabSpec0=mTabHost.newTabSpec(TAB_JIYIJI).setIndicator(TAB_JIYIJI);
		tabSpec0.setContent(new Intent(MainActivity.this,JiyijiActivity.class));
		mTabHost.addTab(tabSpec0);
		//添加生存备忘tab
		TabSpec tabSpec1=mTabHost.newTabSpec(TAB_CHAYICHA).setIndicator(TAB_CHAYICHA);
		tabSpec1.setContent(new Intent(MainActivity.this,ChayichaActivity.class));
		mTabHost.addTab(tabSpec1);
		//添加电话手册tab
		TabSpec tabSpec2=mTabHost.newTabSpec(TAB_SUANYISUAN).setIndicator(TAB_SUANYISUAN);
		tabSpec2.setContent(new Intent(MainActivity.this,SuanyisuanActivity.class));
		mTabHost.addTab(tabSpec2);
		//添加功能设置tab
		TabSpec tabSpec3=mTabHost.newTabSpec(TAB_WODE).setIndicator(TAB_WODE);
		tabSpec3.setContent(new Intent(MainActivity.this,WodeActivity.class));
		mTabHost.addTab(tabSpec3);
		
		
        radio_jiyiji=(RadioButton)findViewById(R.id.radio_jiyiji);
        radio_chayicha=(RadioButton)findViewById(R.id.radio_chayicha);
        radio_suanyisuan=(RadioButton)findViewById(R.id.radio_suanyisuan);
        radio_wode=(RadioButton)findViewById(R.id.radio_wode);
        radio_jiyiji.setBackgroundResource(R.drawable.foot_01_press);
           //设置radioGroup组button的按键监听事件
		this.mRadioGroup=(RadioGroup)this.findViewById(R.id.mainbar_radiogroup);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Log.d(TAG, "checkedId----"+checkedId);
				radio_jiyiji.setBackgroundResource(R.drawable.foot_01);
				radio_chayicha.setBackgroundResource(R.drawable.foot_02);
				radio_suanyisuan.setBackgroundResource(R.drawable.foot_03);
				radio_wode.setBackgroundResource(R.drawable.foot_04);
							
				switch(checkedId){
				case R.id.radio_jiyiji:
					mTabHost.setCurrentTabByTag(TAB_JIYIJI);
					radio_jiyiji.setBackgroundResource(R.drawable.foot_01_press);
					break;
				case R.id.radio_chayicha:
					mTabHost.setCurrentTabByTag(TAB_CHAYICHA);
					radio_chayicha.setBackgroundResource(R.drawable.foot_02_press);
					break;
				case R.id.radio_suanyisuan:
					mTabHost.setCurrentTabByTag(TAB_SUANYISUAN);
					radio_suanyisuan.setBackgroundResource(R.drawable.foot_03_press);
					break;
				case R.id.radio_wode:
					mTabHost.setCurrentTabByTag(TAB_WODE);
					radio_wode.setBackgroundResource(R.drawable.foot_04_press);
					break;
				}
			}
		});
	}


}