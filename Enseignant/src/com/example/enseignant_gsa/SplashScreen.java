package com.example.enseignant_gsa;
 

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
 

public class SplashScreen extends Activity {
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash); 
         
        new Handler().postDelayed(new Runnable() {
            

            @Override
            public void run() {
                
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);

                finish();
            }
        }, 3500);
    }

} 