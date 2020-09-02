package com.example.enseignant_gsa;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class EtudiantAdapter extends BaseAdapter{
	List<Etudiant> listEtudiants;                                               
	LayoutInflater layoutInflater;        
	 
	public EtudiantAdapter(Context context,List<Etudiant> listEtudiants) {
		this.listEtudiants = listEtudiants;
		this.layoutInflater = layoutInflater.from(context); } 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listEtudiants.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listEtudiants.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;  
	}

	static class ViewHolder {   
		TextView num;                                                              
		TextView nom;   
		TextView prenom;                                     
		ImageView image ;
	}
	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (arg1==null)
		{
		arg1= layoutInflater.inflate(R.layout.etudiant_liste, null);
		holder = new ViewHolder();

		holder.num = (TextView) arg1.findViewById(R.id.seance);
		holder.nom = (TextView) arg1.findViewById(R.id.heure);
		holder.prenom = (TextView) arg1.findViewById(R.id.date);
		holder.image = (ImageView) arg1.findViewById(R.id.imageView1);

	
		arg1.setTag(holder);
		}
		else
		{
		holder = (ViewHolder) arg1.getTag();
		}
		holder.num.setText(String.valueOf(listEtudiants.get(arg0).getNum()));
		holder.nom.setText(listEtudiants.get(arg0).getNom());
		holder.prenom.setText(listEtudiants.get(arg0).getPrenom());
		holder.image.setImageDrawable(listEtudiants.get(arg0).getLogo());


		return arg1;
	}

}
