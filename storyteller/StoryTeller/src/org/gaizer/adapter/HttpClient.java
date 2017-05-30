package org.gaizer.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;


public class HttpClient {
	
	public static void sendHandlerMsg(Handler h, String sUrl) {
		try {
			// Perform action on click
			URL url = new URL(sUrl);
			URLConnection conn = url.openConnection();
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				Message msg = new Message();
				msg.obj = line;
				msg.what = 0;
				h.sendMessage(msg);
			}
		} catch (Exception e) {
		}
	}	
	
	
	public static void sendSms(String recipient, String msg) {
		SmsManager sm = SmsManager.getDefault();
		sm.sendTextMessage("+34_destination number", null, msg, null,
				null);
	}
}
