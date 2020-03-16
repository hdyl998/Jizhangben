package com.example.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.R;
import com.example.bean.Zhangmu;
import com.example.db.DBManager;
import com.example.db.ShouzhiColumn;
import com.example.ui.ChayichaActivity;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

/**
 * 
 * 
 *
 */
public class ChayichaAdapter extends BaseAdapter {

	private List list;
	private Context context;
	private DBManager dbManager;
	private Handler handler;
	
	private final String TAG="ChayichaAdapter";
		
	private List icons=new ArrayList();

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public ChayichaAdapter(List list, Context context,DBManager dbManager,Handler handler) {
		super();
		this.list = list;
		this.context = context;
		this.dbManager=dbManager;
		this.handler=handler;
	}

	@Override
	public int getCount() {
		if(list!=null&&list.size()!=0&&!list.isEmpty())
		return list.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(list.size()!=0&&!list.isEmpty())
			return list.get(position);
			return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.lv_chayicha_item,
					null);
			viewHolder.tv_chayicha_leimu=(TextView)convertView.findViewById(R.id.tv_chayicha_leimu);
			viewHolder.tv_chayicha_money=(TextView)convertView.findViewById(R.id.tv_chayicha_money);
			viewHolder.tv_chayicha_fangshi=(TextView)convertView.findViewById(R.id.tv_chayicha_fangshi);
			viewHolder.tv_chayicha_riqi=(TextView)convertView.findViewById(R.id.tv_chayicha_riqi);
			
			viewHolder.iv_left_icon=(ImageView)convertView.findViewById(R.id.iv_left_icon);
			viewHolder.iv_right_icon=(ImageView)convertView.findViewById(R.id.iv_right_icon);
			
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		viewHolder.tv_chayicha_leimu.setText(((Zhangmu)getItem(position)).getLeimu());
		viewHolder.tv_chayicha_money.setText(((Zhangmu)getItem(position)).getJine()+"");
		viewHolder.tv_chayicha_fangshi.setText(((Zhangmu)getItem(position)).getFangshi());
		String date=((Zhangmu)getItem(position)).getDate().toString();
		viewHolder.tv_chayicha_riqi.setText(date.substring(date.lastIndexOf("-")+1, date.length())+"日");
		
		if(((Zhangmu)getItem(position)).getStatus().equals("0")){
			viewHolder.iv_left_icon.setBackgroundResource(R.drawable.icon_down);
					
		}
		else if(((Zhangmu)getItem(position)).getStatus().equals("1")){
			viewHolder.iv_left_icon.setBackgroundResource(R.drawable.icon_add);
		}
		
		viewHolder.iv_right_icon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				dbManager.getDbHelper().delete(ShouzhiColumn.TABLE_NAME, ((Zhangmu)getItem(position)).getId());
			    handler.sendEmptyMessage(ChayichaActivity.DELETE);
				
			}
		});
		icons.add(viewHolder.iv_right_icon);
		return convertView;
	}
	
	public void showRight_icon(){
		for(int i=0;i<icons.size();i++){
		((ImageView)icons.get(i)).setVisibility(View.VISIBLE);
		}
	}
static class ViewHolder{
	TextView tv_chayicha_leimu;
	TextView tv_chayicha_money;
	ImageView iv_left_icon;
	
	TextView tv_chayicha_fangshi;
	TextView tv_chayicha_riqi;
	ImageView iv_right_icon;
}

}
