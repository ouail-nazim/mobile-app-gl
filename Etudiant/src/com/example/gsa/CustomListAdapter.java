package com.example.gsa;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomListAdapter extends BaseAdapter{
	List<Absence> listAbsences;                                               
	LayoutInflater layoutInflater;    
	
	public CustomListAdapter(Context context,List<Absence> listAbsences) {           
		this.listAbsences= listAbsences;                                         
		layoutInflater = LayoutInflater.from(context);              
		}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listAbsences.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		 return listAbsences.get(arg0);  
	}
 
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;  
	}
	static class ViewHolder {                                                          
		TextView module;                                                              
		TextView enseignant;   
		TextView seance;                                                            
		TextView date;    
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (arg1==null)
		{
		arg1= layoutInflater.inflate(R.layout.row_list, null);
		holder = new ViewHolder();

		holder.enseignant = (TextView) arg1.findViewById(R.id.date);
		holder.seance = (TextView) arg1.findViewById(R.id.module);
		holder.module = (TextView) arg1.findViewById(R.id.seance);
		holder.date = (TextView) arg1.findViewById(R.id.heure);

		arg1.setTag(holder);
		}
		else
		{
		holder = (ViewHolder) arg1.getTag();
		}
		holder.module.setText(listAbsences.get(arg0).getModule());
		holder.seance.setText(listAbsences.get(arg0).getSeance());
		holder.enseignant.setText(listAbsences.get(arg0).getEnseignant());
		holder.date.setText(listAbsences.get(arg0).getDate());


		return arg1;

	}

}
