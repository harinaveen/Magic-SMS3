package com.example.magic_sms;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class SendSMS extends Activity {
EditText etmn,etmsg,ettime;
Button btnmn,btntime,btnok;
TimePicker tp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_sms);
		etmn=(EditText) findViewById(R.id.editText1);
		etmsg=(EditText) findViewById(R.id.editText2);
		tp=(TimePicker) findViewById(R.id.timePicker1);
		btnmn=(Button) findViewById(R.id.button2);
		btnok=(Button) findViewById(R.id.button1);
		btnmn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i=new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(i, 0);}});
		btnok.setOnClickListener(new View.OnClickListener() {
			   public void onClick(View arg0) {
				Intent i=new Intent(SendSMS.this,MyReceiver.class);
				i.putExtra("number", etmn.getText().toString());
				i.putExtra("text", etmsg.getText().toString());
				PendingIntent pi=PendingIntent.getBroadcast(SendSMS.this, 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
				AlarmManager alarm=(AlarmManager) getSystemService(ALARM_SERVICE);
				int c = tp.getCurrentHour();
			int a=tp.getCurrentHour();
			int b=tp.getCurrentMinute();
	
			if(a>12){
			c= a -12 ;
			}
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("h");
			SimpleDateFormat sdf1=new SimpleDateFormat("m");
			String hour=sdf.format(cal.getTime());
			String time=sdf1.format(cal.getTime());
			int newhour=c-Integer.parseInt(hour);
			newhour=Math.abs(newhour);
			if (newhour==0) {
				newhour=1;
			}
			int newminute=b-Integer.parseInt(time);
			
				alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(newhour*newminute*60*1000), pi);
				Toast.makeText(getApplicationContext(), "Msg Sent After : "+c+":"+ b+"" +"Clock", Toast.LENGTH_LONG).show();
				etmn.setText("");
				etmsg.setText("");	
			
				
				
			}
		});	
	
	
	}
public void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			switch (requestCode) {
			 case (0) :
			   if (resultCode == Activity.RESULT_OK) {

			     Uri contactData = data.getData();
			     Cursor c =  managedQuery(contactData, null, null, null, null);
			     if (c.moveToFirst()) {
            String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
            String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

			           if (hasPhone.equalsIgnoreCase("1")) {
			          Cursor phones = getContentResolver().query( 
			                       ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
			                       ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
			                       null, null);
			             phones.moveToFirst();
			            String  cNumber = phones.getString(phones.getColumnIndex("data1"));
			            etmn.setText(cNumber);
			           }
			      //   String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


			     }
			   }
			   break;
			 }
			
			
			
			
			
			
			
			
			
			
			
			
			
}}