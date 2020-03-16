package com.example.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.example.BaseActivity;
import com.example.R;

public class WelcomeActivity extends BaseActivity{
  
	private final String TAG="LoadingActivity";
	private ImageView iv_adimg;
	 			 	  
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_loading;
	}

	@Override
	protected void setupView() {
		
		// TODO Auto-generated method stub
		iv_adimg= (ImageView) findViewById(R.id.iv_adimg);   
		  
        AlphaAnimation alphaAnimation = new AlphaAnimation((float) 0.1, 1);   
        alphaAnimation.setDuration(7000);//设定动画时间   
        alphaAnimation.setAnimationListener(new AnimationListener() {   
            @Override  
            public void onAnimationStart(Animation animation) {   
            }   
  
            @Override  
            public void onAnimationRepeat(Animation animation) {   
            }   
  
            @Override  
            public void onAnimationEnd(Animation animation) {   
            	activityManager.Jump2Activity(WelcomeActivity.this, LoginActivity.class);
            	WelcomeActivity.this.finish();
            }   
        });   
  
        iv_adimg.setAnimation(alphaAnimation);   
        iv_adimg.setVisibility(View.VISIBLE);   
	}


	@Override
	protected void initializedData() {
		// TODO Auto-generated method stub
		
	}


}
