package com.example.magic_sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
Button b1,b2,b3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=(Button) findViewById(R.id.button1);
		b2=(Button) findViewById(R.id.button2);
		b3=(Button) findViewById(R.id.button3);
		b1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
			Intent i1=new Intent(MainActivity.this,SendSMS.class);
			startActivity(i1);
				
				
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i=new Intent(MainActivity.this,Cancel.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(i);
				Toast.makeText(getApplicationContext(), "Cancel Succesfully..", Toast.LENGTH_LONG).show();
			}
		});
		
		b3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i1=new Intent(MainActivity.this,AboutUs.class);
				startActivity(i1);
					
			}
		});
		
	}
}
