package com.example.magic_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	public void onReceive(Context arg0, Intent intent) {
		SmsManager sms=SmsManager.getDefault();
		String number=intent.getStringExtra("number");
		String text=intent.getStringExtra("text");
		sms.sendTextMessage(number.toString(), null, text.toString(), null, null);
		//Toast.makeText(arg0, "Msg Sent After"+number+":"+ text+"O Clock", Toast.LENGTH_LONG).show();
		Log.d(number, text);
		
	}

}
