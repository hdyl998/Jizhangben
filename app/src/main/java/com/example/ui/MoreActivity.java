package com.example.ui;

import android.content.Intent;
import android.database.Cursor;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.BaseActivity;
import com.example.MyApplication;
import com.example.R;
import com.example.db.ShouzhiColumn;

/**
 * 更多
 * @author Administrator
 *
 */
public class MoreActivity extends BaseActivity {

	private ImageView btn_clear;
	private ImageView btn_help;
	private ImageView btn_shared;
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_more;
	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub
		btn_clear=(ImageView)findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String SQL="select * from shouzhi";
				Cursor cursor=dbManager.getDbHelper().rawQuery(SQL, null);
				while(cursor.moveToNext()){
					int id=cursor.getInt(cursor.getColumnIndexOrThrow(ShouzhiColumn._ID));
					dbManager.getDbHelper().delete(ShouzhiColumn.TABLE_NAME, id);
				}			
				
				Toast.makeText(MoreActivity.this, "数据清除成功", 2000).show();
			}
		});
		btn_help=(ImageView)findViewById(R.id.btn_help1);
		btn_help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			activityManager.Jump2Activity(MoreActivity.this, HelpActivity.class);	
			}
		});
		
		btn_shared=(ImageView)findViewById(R.id.btn_shared1);
		btn_shared.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Intent.ACTION_SEND);
			      
			      intent.setType("text/plain");
			      intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			      intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
			      startActivity(Intent.createChooser(intent, getTitle()));
			      
			      Toast.makeText(MoreActivity.this, "正在分享。。。", 2000).show();
			}
		});
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
