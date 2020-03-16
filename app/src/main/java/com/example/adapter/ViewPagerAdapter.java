package com.example.adapter;

import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter{
	 private List<View> mListViews;  
	 
     public ViewPagerAdapter(List<View> mListViews) {  
         this.mListViews = mListViews;  
     }  

     @Override  
     public void destroyItem(ViewGroup container, int position, Object object)   {     
         container.removeView(mListViews.get(position));  
     }  


     @Override  
     public Object instantiateItem(ViewGroup container, int position) {
    	  if(mListViews.get(position)!=null)
          container.addView(mListViews.get(position), position);  
          return mListViews.get(position);  
     }  

     @Override  
     public int getCount() {
    	 if(mListViews!=null)
         return  mListViews.size();
    	 return 0;
     }  
       
     @Override  
     public boolean isViewFromObject(View arg0, Object arg1) { 
         return arg0==arg1;  
     }  
 }  