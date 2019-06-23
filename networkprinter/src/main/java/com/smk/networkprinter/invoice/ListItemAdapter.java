package com.smk.networkprinter.invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smk.networkprinter.R;

import java.util.List;

public class ListItemAdapter extends BaseAdapter {

	private Context ctx;
	private List<Object> list;
	private LayoutInflater mInflater;

	public ListItemAdapter(Context ctx, List<Object> list){
		this.ctx = ctx;
		this.list = list;
		mInflater = LayoutInflater.from(ctx);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item_invoice, null);
			holder.name = (TextView) convertView.findViewById(R.id.txt_name);
			holder.price = (TextView) convertView.findViewById(R.id.txt_price);
			holder.qty = (TextView) convertView.findViewById(R.id.txt_qty);
			holder.amount = (TextView) convertView.findViewById(R.id.txt_amount);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Item item = (Item) list.get(arg0);
		holder.name.setText(item.getName());
		holder.price.setText(item.getPrice()+"");
		holder.qty.setText("x"+item.getQty());
		holder.amount.setText(item.getAmount()+"");
		return convertView;
	}


	static class ViewHolder{
		TextView name, price, qty, amount;

	}

}
