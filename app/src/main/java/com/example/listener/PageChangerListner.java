package com.example.listener;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.widget.LinearLayout;

public class PageChangerListner implements OnPageChangeListener{

	private final String TAG="PageChangerListner";
	private LinearLayout ll_menu;
	private int []titlebg;
	

	public PageChangerListner(LinearLayout ll_menu, int[] titlebg) {
		super();
		this.ll_menu = ll_menu;
		this.titlebg = titlebg;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		Log.d(TAG,"onPageScrollStateChanged arg0--->"+arg0);
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		Log.d(TAG,"onPageScrolled arg0--->"+arg0+"---->arg1"+arg1+"---->arg2"+arg2);
		
	}

	@Override
	public void onPageSelected(int arg0) {
		this.ll_menu.setBackgroundResource(titlebg[arg0]);
		Log.d(TAG,"onPageSelected arg0--->"+arg0);
		
	}

}
