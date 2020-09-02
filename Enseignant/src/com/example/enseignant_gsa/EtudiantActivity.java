package com.example.enseignant_gsa;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
 
  
public class EtudiantActivity extends Activity {
    
         
         String idseance;
		 String section;
		 String annee;
		 String groupe;
		 String heure;
		 String specialite;
		 String date ; 
		 ListView lv;
	     List<Etudiant> myListofData ;
	     List<Etudiant> ListofData ;
	     String ID;
	     String DATE;
	     String da;
	     int NUM;

	     String urlGet="http://192.168.1.2/gsa_enseignant/etudiants.php";
	     String url="http://192.168.1.2/gsa_enseignant/marquer.php";

	  	 int success;
	 	 Button envoyer ;
	 	 String message;
	 	 GetDataAsyncTask getetudiants ;
	 	 Drawable drawable;
	 	 
		@Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);

                    idseance=this.getIntent().getExtras().getString("idseance");
                    specialite =this.getIntent().getExtras().getString("specialite");
                    section = this.getIntent().getExtras().getString("section");
                    groupe = this.getIntent().getExtras().getString("groupe");
                    annee = this.getIntent().getExtras().getString("annee");
                    date = this.getIntent().getExtras().getString("date");
                    heure = this.getIntent().getExtras().getString("heure");
                                 
                myListofData = new ArrayList<Etudiant>();
                setContentView(R.layout.etudiant_activity);
                lv=(ListView)findViewById(R.id.listView1);
                envoyer = (Button)findViewById(R.id.envoyer);
                myListofData = new ArrayList<Etudiant>();

                envoyer.setOnClickListener(new OnClickListener() {

				@Override 
		         public void onClick(View v) {
						// TODO Auto-generated method stub
                     ArrayList<Marque> liste = new ArrayList<Marque>();
						for(int i=0;i<myListofData.size();i++){
							
							if(myListofData.get(i).isSelected()==true){
								ID = myListofData.get(i).seance;
								        da = myListofData.get(i).date;
								DATE = da.substring(6,10)+"-"+da.substring(3,5)+"-"+da.substring(0,2)+" "+heure+"|";
								NUM = myListofData.get(i).num; 
								liste.add(new Marque(ID, DATE, NUM));
				                }
							 
							} 
						 for(int i=0;i<liste.size();i++){
						new MarquerAbsence().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,liste.get(i)); 
						 }
		                    Toast.makeText(getApplicationContext(),liste.size()+" absences marquées", Toast.LENGTH_LONG).show();

				}				
				}
                );
                getetudiants = new GetDataAsyncTask();
                getetudiants.execute();
               }
		
		private class GetDataAsyncTask extends  AsyncTask<Void, Void, Void> {
       
			@Override
            protected void onPreExecute() {
                    Log.i("add", "onPreExecute");
                    super.onPreExecute();
            }
             
            
            @Override
            protected Void doInBackground(Void... params) {
                    Log.i("add", " start doInBackground");
                    ServiceHandler sh = new ServiceHandler();
                    
                    
                    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
                    nameValuePair.add(new BasicNameValuePair("idseance",idseance));
                    nameValuePair.add(new BasicNameValuePair("specialite",specialite));
                    nameValuePair.add(new BasicNameValuePair("section",section));
                    nameValuePair.add(new BasicNameValuePair("groupe",groupe));
                    nameValuePair.add(new BasicNameValuePair("annee",annee));
 
            String jsonStr = sh.makeServiceCall(urlGet, ServiceHandler.POST,nameValuePair);

             Log.d("Response: ",jsonStr);
                     
            if (jsonStr != null) {
            try {  
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    
                    success=jsonObj.getInt("success");
                    Log.i("success", String.valueOf(success));
                    if (success==0) 
                    {
                            message=jsonObj.getString("message");
                            Log.i("message", message);
                    }
                    else if (success==1)
                    {
                            JSONArray dataValues = jsonObj.getJSONArray("valeurs");
                            for(int j=0;j<dataValues.length();j++)
                            {
                            	
                                    JSONObject values = dataValues.getJSONObject(j);

                                    int valCol1= values.getInt("num");
                                    String valCol2= values.getString("nom");
                                    String valCol3= values.getString("prenom");

                                    Drawable im= getResources().getDrawable(R.drawable.present_badge);
                                    myListofData.add(new Etudiant(valCol2,valCol3,valCol1,im,date,idseance,false));
                            }
                    }
                                   
            } catch (JSONException e) {
                    e.printStackTrace();
            }
            } else {
                    Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            Log.i("add", " end doInBackground");
            return null;
            } 
           
            @Override
            protected void onPostExecute(Void result) {
                    Log.i("add", "onPostExecute");
                     super.onPostExecute(result);
                 
                    if(success==1)
                    {  
                    	    EtudiantAdapter etu = new EtudiantAdapter(getApplicationContext(),myListofData);
                            lv.setAdapter(etu);  
                            lv.setOnItemClickListener( new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long arg3) {
									
									//  DO Auto-generated method stub
	                                Drawable aaa= getResources().getDrawable(R.drawable.absent_badge);
	                                Drawable bbb= getResources().getDrawable(R.drawable.present_badge);

							        Etudiant a =((Etudiant)lv.getItemAtPosition(arg2));
							          
							        if(a.isSelected()){
							        	a.setLogo(bbb);	
								        a.setSelected(false);
							        }else{
							        	a.setLogo(aaa);	
								        a.setSelected(true);
							        }
							        Log.i("LIST",String.valueOf(myListofData.size()));
							        int x =myListofData.size();
							        lv.removeViews(0,x);
								}}
                            );
                    }
                    else
                    {
                             Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                    }
            }
		  }
		private class MarquerAbsence extends  AsyncTask<Marque,Void,Void> {
			
			 
        @Override
		protected Void doInBackground(Marque...params) {
        
        	ServiceHandler sh = new ServiceHandler();		                 
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);

            nameValuePair.add(new BasicNameValuePair("id",params[0].id));
            nameValuePair.add(new BasicNameValuePair("date",params[0].date));
            nameValuePair.add(new BasicNameValuePair("num",String.valueOf(params[0].num)));
           
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST,nameValuePair);
            Log.d("Response: ",jsonStr);
            if (jsonStr != null) {
                try {                                        
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        success = jsonObj.getInt("success");
                        message = jsonObj.getString("message");
                        Log.i("suucess", String.valueOf(success));
                        Log.i("message", message);
                       
                } catch (JSONException e) {
                       
                        e.printStackTrace();
                }
        }
   	     return null;}}
		
		
		
}