package com.example.gsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ProgressBar;



public class Loading extends Activity {
	ProgressBar bar;
	String s1 =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading);
		 if (this.getIntent().getExtras()!=null){
        	 s1 = this.getIntent().getExtras().getString("passe");}
		 bar = (ProgressBar)findViewById(R.id.progressBar1);
		 new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i <= 100; i++) {
						bar.setProgress(i);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(i>99){
							
							Intent intent = new Intent(getApplicationContext(),Tab.class);
							intent.putExtra("passe", s1);
							startActivity(intent);
						}
					}
				}
			}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
