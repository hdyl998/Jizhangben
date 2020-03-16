package com.example.ui;
/***
 * 页面
 */
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.BaseActivity;
import com.example.MyApplication;
import com.example.R;
import com.example.db.DBManager;
import com.example.db.ShouzhiColumn;

public class WodeActivity extends BaseActivity{
	

	
	private final String TAG="WodeActivity";
	
	private TextView tv_zongzichan;
	private TextView tv_xianji;
	private TextView tv_gongshangka;
	private TextView tv_yikatong;
	private TextView tv_jiaotongka;
	private TextView tv_jiejika;
	private TextView tv_xinyongka;
	private TextView tv_zhifubao;
	private TextView tv_caifutong;
	
	private ImageView btn_more;

private DBManager dbManager;
	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_wo;//设置功能视图
	}

	@Override
	protected void setupView() {
		
		btn_more=(ImageView)findViewById(R.id.btn_more);
		btn_more.setBackgroundResource(R.drawable.btn_share1);
		btn_more.setVisibility(View.VISIBLE);
		btn_more.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(Intent.ACTION_SEND);
			      
			      intent.setType("text/plain");
			      intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			      intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
			      startActivity(Intent.createChooser(intent, getTitle()));
			      
			      Toast.makeText(WodeActivity.this, "正在分享。。。", 2000).show();
			}
		});
		dbManager=((MyApplication)getApplication()).getDbManager();
	
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		initximu();
				
	}
	
	
	private void initximu() {
		
		tv_zongzichan=(TextView)findViewById(R.id.tv_zongzichan);
		tv_xianji=(TextView)findViewById(R.id.tv_xianjin);
		tv_xianji.setText(checkMoney(ShouzhiColumn.FANGSHI, "现金", dbManager)+"");
		
		tv_gongshangka=(TextView)findViewById(R.id.tv_gongshangka);
		tv_gongshangka.setText(checkMoney(ShouzhiColumn.FANGSHI, "储蓄卡", dbManager)+"");
		
		tv_yikatong=(TextView)findViewById(R.id.tv_yikatong);
		tv_yikatong.setText(checkMoney(ShouzhiColumn.FANGSHI, "其他", dbManager)+"");
		
		
		tv_jiejika=(TextView)findViewById(R.id.tv_jiejika);
		tv_jiejika.setText(checkMoney(ShouzhiColumn.FANGSHI, "借记卡", dbManager)+"");
		
		tv_xinyongka=(TextView)findViewById(R.id.tv_xinyong);
		tv_xinyongka.setText(checkMoney(ShouzhiColumn.FANGSHI, "信用卡", dbManager)+"");
		
		tv_jiaotongka=(TextView)findViewById(R.id.tv_jiaotongka);
		tv_jiaotongka.setText(checkMoney(ShouzhiColumn.FANGSHI, "交通卡", dbManager)+"");
		
		
		
		tv_zhifubao=(TextView)findViewById(R.id.tv_zhifubao);
		tv_zhifubao.setText(checkMoney(ShouzhiColumn.FANGSHI, "支付宝", dbManager)+"");
		
		tv_caifutong=(TextView)findViewById(R.id.tv_caifutong);
		tv_caifutong.setText(checkMoney(ShouzhiColumn.FANGSHI, "财付通", dbManager)+"");
		
		//
		int xianjin=Integer.parseInt(tv_xianji.getText().toString());
		int cunxu=Integer.parseInt(tv_gongshangka.getText().toString());
		int yikatong=Integer.parseInt(tv_yikatong.getText().toString());
		int jiejika=Integer.parseInt(tv_jiejika.getText().toString());
		int xingyongka=Integer.parseInt(tv_xinyongka.getText().toString());
		int jiaotongka=Integer.parseInt(tv_jiaotongka.getText().toString());
		int zhifubao=Integer.parseInt(tv_zhifubao.getText().toString());
		int caifutong=Integer.parseInt(tv_caifutong.getText().toString());
		
		int zong=xianjin+cunxu+yikatong+jiejika+xingyongka+jiaotongka+zhifubao+caifutong;
		tv_zongzichan.setText(zong+"");
	}

	private int checkMoney(String type,String value,DBManager dbManager){
		
		   int totalmoney=0;	
		   Cursor cursor=dbManager.getDbHelper().query(ShouzhiColumn.TABLE_NAME, new String[]{ShouzhiColumn.MONEY,ShouzhiColumn.STATUS}, type+"=?", new String[]{value});
		   while(cursor.moveToNext()){
		   String status=cursor.getString(cursor.getColumnIndexOrThrow(ShouzhiColumn.STATUS));
		   int money=0;
		   if(status.equals("1")){//收入
			   money =cursor.getInt(cursor.getColumnIndexOrThrow(ShouzhiColumn.MONEY));	
		   }
		   else{//支出
			   money =(-1)*cursor.getInt(cursor.getColumnIndexOrThrow(ShouzhiColumn.MONEY));	
		   }	  
		   totalmoney=totalmoney+money;
		   }
		   return totalmoney;
		}
	
	@Override
	protected void initializedData() {
		// TODO Auto-generated method stub
		
	}

}
