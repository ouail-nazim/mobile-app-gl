package com.example.gsa;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class MainActivity extends Activity {
        CustomProgressDialog  progressDialog;
        Button connexion;
        EditText user,password;
         String urlAdd="http://192.168.1.2/gsa/login.php";
        AddDataAsyncTask AddData;

        String message;
        int success;  
        String ID; 


        @Override 
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.activity_main);
                progressDialog = new CustomProgressDialog(this, R.drawable.loading_throbber);
                progressDialog.setCancelable(true);
                connexion=(Button)findViewById(R.id.connexion);
                user=(EditText)findViewById(R.id.user);
                password=(EditText)findViewById(R.id.password);
                AddData =new AddDataAsyncTask();
                connexion.setOnClickListener(new OnClickListener() { 
                       
                        @Override
                         public void onClick(View arg0) {
                           AddDataAsyncTask AddData = new AddDataAsyncTask();
                            AddData.execute();
                        }
                });
        }
       
       
        private class AddDataAsyncTask extends  AsyncTask<Void, Void, Void> {
                @Override
                protected void onPreExecute() {
                        Log.i("add", "onPreExecute");
                        super.onPreExecute();
                        progressDialog.show(); 
                }
                
                @Override 
                protected Void doInBackground(Void... params) {
                        Log.i("add", " start doInBackground");
                        // Creating service handler class instance
                        ServiceHandler sh = new ServiceHandler();
                       
                        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
                         
                        nameValuePair.add(new BasicNameValuePair("user",user.getText().toString()));
                        nameValuePair.add(new BasicNameValuePair("password",password.getText().toString()));
                   
                        Log.d("USER: ",user.getText().toString());
                        Log.d("PASS: ",password.getText().toString());
  

                        // Making a request to url and getting response
                        String jsonStr = sh.makeServiceCall(urlAdd, ServiceHandler.POST,nameValuePair);
                       
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
                        	Intent i = new Intent(getApplicationContext(), Loading.class);
    						i.putExtra("passe", message); 
    		                startActivity(i);
                        }
                        else
                        {
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                               
                        }
 
                }
               
        }
              
}