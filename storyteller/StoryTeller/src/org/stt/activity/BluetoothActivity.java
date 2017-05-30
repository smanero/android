package org.stt.activity;

import org.gaizer.adapter.HttpClient;
import org.stt.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class BluetoothActivity extends Activity {
   /** Called when the activity is first created. */
   
   private TextView tView;
   private Button btGo;
   private WebView wvCanvas;
   private Button btClose;
   
   private Handler h;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_httpclient);

      tView =  (TextView) findViewById(R.id.tView);
      btClose = (Button) findViewById(R.id.btClose);
      
      wvCanvas = (WebView) findViewById(R.id.wvCanvas); 
      btGo = (Button) findViewById(R.id.btGo);
      
      h = new BluetoothHandler(tView);

      Bundle bundle = getIntent().getExtras();
      tView.setText("http://" + bundle.getString("direccion"));
      
      
      wvCanvas.loadUrl(tView.getText().toString());
      
      // perform action on click
      btGo.setOnClickListener(new Button.OnClickListener() {
         public void onClick(View v) {
            HttpClient.sendHandlerMsg(BluetoothActivity.this.h, tView.getText().toString());
         }
      });
      
      // perform action on click
      btClose.setOnClickListener(new Button.OnClickListener() {
         public void onClick(View v) {
            finish();
         }
      });
   }
}

class BluetoothHandler extends Handler {
   private TextView tView;

   public BluetoothHandler(TextView tView) {
      this.tView = tView;
   }

   @Override
   public void handleMessage(Message msg) {
      // process incoming messages here
      switch (msg.what) {
      case 0:
         tView.append((String) msg.obj);
         break;
      }
      super.handleMessage(msg);
   }
}