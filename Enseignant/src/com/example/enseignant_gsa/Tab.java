package com.example.enseignant_gsa;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tab extends TabActivity {
            /** Called when the activity is first created. */
	String s1 =null;
            @Override
            public void onCreate(Bundle savedInstanceState)
            {
                    super.onCreate(savedInstanceState);
                    requestWindowFeature(Window.FEATURE_NO_TITLE);
                    setContentView(R.layout.main);
                    if (this.getIntent().getExtras()!=null){
                    	 s1 = this.getIntent().getExtras().getString("passe");}

                    TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
              
                    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) 
                    {
                        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                        tv.setTextColor(Color.parseColor("#aaa"));
                    } 

                    TabSpec tab1 = tabHost.newTabSpec("First Tab");
                    TabSpec tab2 = tabHost.newTabSpec("Second Tab");
                   // TabSpec tab3 = tabHost.newTabSpec("Third tab");
 
                   // Set the Tab name and Activity
                   // that will be opened when particular Tab will be selected
                    
                    tab1.setIndicator("",getResources().getDrawable(R.drawable.icon_accueil));
                    
                    Intent intent = new Intent(this,AccueilActivity.class);
                    intent.putExtra("passe",s1);
                    tab1.setContent(intent);
                    
                    Intent intent2 = new Intent(this,SeancesActivity.class);
                    intent2.putExtra("passe",s1);
                    tab2.setIndicator("",getResources().getDrawable(R.drawable.icon_seances));
                    
                    tab2.setContent(intent2);

                    //tab3.setIndicator("Tab3");
                   // tab3.setContent(new Intent(this,Tab3Activity.class));
                    
                    /** Add the tabs  to the TabHost to display. */
                    tabHost.addTab(tab1);
                    tabHost.addTab(tab2);
                   // tabHost.addTab(tab3);

            }
} 