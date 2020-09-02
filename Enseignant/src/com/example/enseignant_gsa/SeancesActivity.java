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
 
  
public class SeancesActivity extends Activity {
        CustomProgressDialog  progressDialog;
        String urlGet="http://192.168.1.2/gsa_enseignant/seancesParWeek.php";
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
                progressDialog = new CustomProgressDialog(this, R.drawable.loading_throbber);
                progressDialog.setCancelable(true);
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
                        
                       
                        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
                        nameValuePair.add(new BasicNameValuePair("idEnseignant",s1));
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
                                      
                                        String valCol1= values.getString("module");
                                        String valCol2= values.getString("groupe");
                                        String valCol3= values.getString("seance");                                        
                                        String valCol6=values.getString("date");
                                        String valCol7= values.getString("heure");




                                        
                                        //add a string witch contains all of data getted from the response
                                        myListofData.add(new Seance(valCol2,"",valCol1,"Le "+valCol6,"à "+valCol7,0,valCol3+"-", "",""));
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
                                Toast.makeText(getApplicationContext(), "Vos seances pour cette semaine", Toast.LENGTH_LONG).show();
                                lv.setAdapter(new SeanceAdapter(getApplicationContext(), myListofData));  
                                                   }
                        else
                        {
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                        }
     
     
                }
               
        }
       
 
       
 
}