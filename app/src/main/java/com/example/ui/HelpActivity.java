package com.example.ui;
/**
 * 帮助页面
 */
import android.view.KeyEvent;

import com.example.BaseActivity;
import com.example.R;

public class HelpActivity extends BaseActivity{

	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_help;
	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initializedData() {
		// TODO Auto-generated method stub
		
	}
	 @Override
	   	public boolean onKeyDown(int keyCode, KeyEvent event) {
	   		// TODO Auto-generated method stub
	   		if(keyCode==KeyEvent.KEYCODE_BACK)
	   		 {
	   		 this.finish();
	   			return true;
	   		 }
	   		return false;
	   	}	

}
