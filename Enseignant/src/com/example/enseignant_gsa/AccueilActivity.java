package com.example.enseignant_gsa;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
 
  
public class AccueilActivity extends Activity {
        CustomProgressDialog  progressDialog;
        String urlGet="http://192.168.1.2/gsa_enseignant/seancesParJour.php";
        GetDataAsyncTask getData;
        String message; 
        int success; 
        ListView lv;  
        List<Seance> myListofData ;

        String s1 = null; 
         
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.acceuil_activity);
                if (this.getIntent().getExtras()!=null){
                s1=this.getIntent().getExtras().getString("passe");}
               
                lv=(ListView)findViewById(R.id.listView1);
                myListofData = new ArrayList<Seance>();
                getData=new GetDataAsyncTask();
                getData.execute();       
                
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
                        
                        // Making a request to url and getting response
                        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
                        nameValuePair.add(new BasicNameValuePair("idEnseignant",s1));
                String jsonStr = sh.makeServiceCall(urlGet, ServiceHandler.POST,nameValuePair);
 
                 Log.d("Response: ",jsonStr);
                         
                if (jsonStr != null) {
                try {  
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        // return value of success
                        success=jsonObj.getInt("success");
                        Log.i("success", String.valueOf(success));
                        if (success==0) 
                        {
                                // success=0 ==> there is a string = message
                                message=jsonObj.getString("message");
                                Log.i("message", message);
                        }
                        else if (success==1)
                        {
                        // success=1 ==> there is an array of data = valeurs
                                JSONArray dataValues = jsonObj.getJSONArray("valeurs");
                                // loop each row in the array
                                for(int j=0;j<dataValues.length();j++)
                                {
                                        JSONObject values = dataValues.getJSONObject(j);
                                        // return values of col1 in valCol1
                                        String valCol1= values.getString("module");
                                        String valCol2= values.getString("groupe");

                                        String valCol3= values.getString("seance");
                                        String valCol4= values.getString("section");
                                        
                                        String valCol5= values.getString("specialite");
                                        String valCol6=values.getString("date");
                                        String valCol7= values.getString("heure");
                                        int valCol8= values.getInt("idSeance");
                                        String valCol9 = values.getString("annee");




                                        
                                        //add a string witch contains all of data getted from the response
                                        myListofData.add(new Seance(valCol2,valCol5,valCol1,valCol6,valCol7, valCol8,valCol3+"-", valCol9,valCol4));
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
                                Toast.makeText(getApplicationContext(), "Vous avez "+myListofData.size()+" seance(s) aujourd'hui", Toast.LENGTH_LONG).show();
                                lv.setAdapter(new SeanceAdapter(getApplicationContext(), myListofData));  
                                lv.setOnItemClickListener(new OnItemClickListener() {
 
									@Override
									public void onItemClick(AdapterView<?> arg0, View view,int arg2, long arg3) {
										// TODO Auto-generated method stub
								        Seance a =((Seance)lv.getItemAtPosition(arg2));
								        Intent i = new Intent(getApplicationContext(),EtudiantActivity.class);
								        i.putExtra("idseance",String.valueOf(a.id));
								        i.putExtra("specialite", a.specialite);
								        i.putExtra("section", a.section);
								        i.putExtra("groupe",a.groupe); 
								        i.putExtra("annee", a.annee_specialite);
								        i.putExtra("date", a.date);
								        i.putExtra("heure", a.heure);

								        


								        startActivity(i);
								        Log.i("aaaaaaaaaaaaaa",String.valueOf(a.id+" "+a.annee_specialite+" "+a.section+" "+a.groupe+" "+a.specialite));
 									
									}  
                                	
								});
                        }
                        else
                        {
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                        }
     
     
                }
               
        }
       
 
       
 
}