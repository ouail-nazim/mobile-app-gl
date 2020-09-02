package com.example.enseignant_gsa;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class SeanceAdapter extends BaseAdapter{
	List<Seance> listSeances;                                               
	LayoutInflater layoutInflater;        
	 
	public SeanceAdapter(Context context,List<Seance> listSeances) {
		this.listSeances = listSeances;
		this.layoutInflater = layoutInflater.from(context);
	} 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listSeances.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listSeances.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;  
	}

	static class ViewHolder {                                                          
		TextView module;                                                              
		TextView heure;   
		TextView seance;                                                            
		TextView date;   
		TextView groupe;    

	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (arg1==null)
		{
		arg1= layoutInflater.inflate(R.layout.seance_list, null);
		holder = new ViewHolder();
  
		holder.groupe = (TextView) arg1.findViewById(R.id.groupe);
		holder.seance = (TextView) arg1.findViewById(R.id.seance);
		holder.module = (TextView) arg1.findViewById(R.id.module);
		holder.heure= (TextView) arg1.findViewById(R.id.heure);
		holder.date = (TextView) arg1.findViewById(R.id.date);


		arg1.setTag(holder);
		}
		else
		{
		holder = (ViewHolder) arg1.getTag();
		}
		holder.module.setText(listSeances.get(arg0).getModule());
		holder.seance.setText(listSeances.get(arg0).getSeance());
		holder.groupe.setText(listSeances.get(arg0).getGroupe());
		holder.date.setText(listSeances.get(arg0).getDate());
		holder.heure.setText(listSeances.get(arg0).getHeure());



		return arg1;
	}

}
