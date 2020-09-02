package com.example.gsa;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
 
public class AbsencesActivity extends Activity {
        CustomProgressDialog  progressDialog;
        String urlGet="http://192.168.1.2/gsa/absence.php"; 
        GetDataAsyncTask getData;
        String message;   
        int success;    
        ListView lv;  
        List<Absence> myListofData ; 

        String s1 = null;
         
        @Override 
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        		requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.activity_list_data);
                if (this.getIntent().getExtras()!=null){
                s1=this.getIntent().getExtras().getString("passe");}
                progressDialog = new CustomProgressDialog(this, R.drawable.loading_throbber);
                progressDialog.setCancelable(true);
                lv=(ListView)findViewById(R.id.listView1);
                myListofData = new ArrayList<Absence>();
                getData=new GetDataAsyncTask();
                getData.execute();       
                
        }
       

 
       
        private class GetDataAsyncTask extends  AsyncTask<Void, Void, Void> {
                @Override
                protected void onPreExecute() {
                        Log.i("add", "onPreExecute");
                        super.onPreExecute();
                        progressDialog.show();
                }
             
                
                @Override
                protected Void doInBackground(Void... params) {
                        Log.i("add", " start doInBackground");
                        ServiceHandler sh = new ServiceHandler();
                        
                        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
                        nameValuePair.add(new BasicNameValuePair("moncef",s1));
                        Log.i("pass", s1);
                        
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
                                        
 
                                        String valCol3= values.getString("module");
                                        String valCol4= values.getString("seance");
                                        String valCol6= values.getString("date_absence");
                                        String valCol7=values.getString("heure_absence");


                                        
                                        myListofData.add(new Absence(valCol3,valCol4+"-","Le "+valCol6,"à "+valCol7));
                                        Log.i("Row "+(j+1),valCol3+" - "+valCol4);
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
                        if (progressDialog.isShowing())
                        { 
                                progressDialog.dismiss();
                        }
                        if(success==1)
                        { 
                                Toast.makeText(getApplicationContext(), "Vous avez "+myListofData.size()+" absence(s)", Toast.LENGTH_LONG).show();

                                lv.setAdapter(new CustomListAdapter(getApplicationContext(),myListofData));  
                        }
                        else
                        {
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                        }
     
     
                }
               
        }
       
 
       
 
}