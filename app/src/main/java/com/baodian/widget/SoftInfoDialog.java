package com.baodian.widget;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author user
 */
public class SoftInfoDialog extends Dialog implements
		android.view.View.OnClickListener {
	private Window window = null;
	private Context context = null;
	public Button softInfo = null;
	public Button softInfoButton = null;
	public static boolean flag = true;
	private NumericWheelAdapter year_adapter = null;
	private NumericWheelAdapter month_adapter=null;
	private NumericWheelAdapter day_adapter=null;
	private RelativeLayout btn_sure = null;
	private RelativeLayout btn_cancel=null;
	private String date=null;
	
	private TextView tv_date;
	

	
	public TextView getTv_date() {
		return tv_date;
	}

	public void setTv_date(TextView tv_date) {
		this.tv_date = tv_date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public SoftInfoDialog(final Context context) {
		super(context, R.style.dialog);
		this.context = context;
		setContentView(R.layout.time_layout);
		btn_sure = (RelativeLayout) findViewById(R.id.lay_left);
		btn_cancel=(RelativeLayout) findViewById(R.id.lay_right);
		btn_sure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String year = year_adapter.getValues();
				String month=month_adapter.getValues();
				String day=day_adapter.getValues();
				setDate(year+"-"+month+"-"+day);
				getTv_date().setText("选择日期："+getDate());
				
				dismiss();
			}
		});
		
		btn_cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		 WheelView year = (WheelView) this.findViewById(R.id.year);
		 year.setLabel("年");
		 WheelView	month = (WheelView) this.findViewById(R.id.month);
		 month.setLabel("月");
		 WheelView day=(WheelView)this.findViewById(R.id.day);
		 day.setLabel("日");
		 
		year_adapter = new NumericWheelAdapter(2012, 2100);
		month_adapter=new  NumericWheelAdapter(1, 12);
		day_adapter=new NumericWheelAdapter(1,30);
		
		Calendar c = Calendar.getInstance();
		
		int curMonth = c.get(Calendar.MONTH);
		int curYear=c.get(Calendar.YEAR);
		int curDay=c.get(Calendar.DAY_OF_MONTH);
		
		year.setAdapter(year_adapter);
		year.setCurrentItem(curYear-2012);
		month.setAdapter(month_adapter);
		month.setCurrentItem(curMonth);
		day.setAdapter(day_adapter);
		day.setCurrentItem(curDay-1);
	
		
		
		
		
		Display d = getWindow().getWindowManager().getDefaultDisplay();
		window = getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = 0;
		 wl.height = (int) (d.getHeight() * 0.46); 
		wl.width = (int) (d.getWidth() * 0.7);
		wl.alpha = 1.0f;
		window.setAttributes(wl);
		
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}
}
