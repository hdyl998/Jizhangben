package com.example.ui;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.BaseActivity;
import com.example.R;
import com.example.bean.User;
import com.example.db.UserColumn;


public class RegisterActivity extends BaseActivity{

	private EditText et_user;
	private EditText et_password;
	private EditText et_comfirmpassword;
	
	private Button btn_confirm;
	private Button btn_cancle;

	
	
private final int REGISTER=0;
	
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			String str=(String)msg.obj;
			switch (msg.what) {
			case REGISTER:
				if(str.trim().equals("success")){
					Toast.makeText(RegisterActivity.this, "注册成功", 2000).show();
					activityManager.Jump2Activity(RegisterActivity.this, LoginActivity.class);
				}
				else{
					Toast.makeText(RegisterActivity.this, "注册失败", 2000).show();
				}
				break;
			}
		}
	};
	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_register;
	}

	@Override
	protected void setupView() {
		
		et_user=(EditText)findViewById(R.id.et_user);		
		et_password=(EditText)findViewById(R.id.et_password);
		et_comfirmpassword=(EditText)findViewById(R.id.et_comfirmpassword);
		
		btn_confirm=(Button)findViewById(R.id.btn_confirm);
		btn_cancle=(Button)findViewById(R.id.btn_cancle);
		
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(et_user.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "用户名不能为空", 2000).show();
					return;
				}
				else if(et_password.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "密码不能为空", 2000).show();
					return;
				}
				if(!et_password.getText().toString().trim().equals(et_comfirmpassword.getText().toString().trim())){
				Toast.makeText(RegisterActivity.this, "密码不一致", 2000).show();
				return;
				}
				User user=new User();
				
				
				user.setUsername(et_user.getText().toString());
				user.setPassword(et_password.getText().toString());
				
				
				ContentValues contentValues=new ContentValues();
				contentValues.put(UserColumn.NAME, user.getUsername());
				contentValues.put(UserColumn.PASSWORD, user.getPassword());
				
				if(dbManager.getDbHelper().insert(UserColumn.TABLE_NAME, contentValues)!=-1){
					Toast.makeText(RegisterActivity.this, "注册成功", 2000).show();
					RegisterActivity.this.finish();
				}
				else
				{
					Toast.makeText(RegisterActivity.this, "注册失败", 2000).show();
				}
					
			
				
			}
		});
		
		btn_cancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RegisterActivity.this.finish();
				
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
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return false;
	}
}

