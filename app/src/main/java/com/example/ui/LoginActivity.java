package com.example.ui;



import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.BaseActivity;
import com.example.MyApplication;
import com.example.R;
import com.example.bean.User;
import com.example.db.UserColumn;



public class LoginActivity extends BaseActivity{

	private AutoCompleteTextView au_user;
	private EditText password;
	
	private Button loginButton;
	private Button registerbutton;

	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_login;
	}

	@Override
	protected void setupView() {
		
		
	au_user=(AutoCompleteTextView)findViewById(R.id.user);
	password=(EditText)findViewById(R.id.password);
	
	loginButton=(Button)findViewById(R.id.loginButton);
	registerbutton=(Button)findViewById(R.id.registerbutton);
	
	loginButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(au_user.getText().toString().trim().equals("")){
				Toast.makeText(LoginActivity.this, "用户名不能为空", 2000).show();
				return;
			}
			else if(password.getText().toString().trim().equals("")){
				Toast.makeText(LoginActivity.this, "密码不能为空", 2000).show();
				return;
			}
			
			if(checkPassWord())
			{
				activityManager.Jump2Activity(LoginActivity.this, MainActivity.class);
			}
			
			
		}
	});
	registerbutton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			activityManager.Jump2Activity(LoginActivity.this, RegisterActivity.class);
		}
	});
		
	}
	
	private boolean checkPassWord(){
		String SQL="select * from user where name='"+au_user.getText().toString()+"'";
		Cursor cursor=dbManager.getDbHelper().rawQuery(SQL, null);
		while(cursor.moveToNext()){
			String name=cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.NAME));
			String passwordstr=cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.PASSWORD));
						
			if(passwordstr.equals(password.getText().toString())){
				User user=new User();		
				user.setUsername(name);
				user.setPassword(passwordstr);
				
				((MyApplication)getApplication()).setUser(user);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void initializedData() {
		// TODO Auto-generated method stub
		
	}

}
