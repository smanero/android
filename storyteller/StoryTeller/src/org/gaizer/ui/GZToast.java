package org.gaizer.ui;

import org.stt.R;
import org.stt.StoryTellerMAC;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GZToast extends Toast {

   private static GZToast singleton = null;

   ImageView _img;
   TextView _text;
   int _duration;

   private GZToast() {
      super(StoryTellerMAC._this);
      LayoutInflater inflater = StoryTellerMAC._this.getLayoutInflater();
      ViewGroup vg = (ViewGroup) StoryTellerMAC._this.findViewById(R.layout.gztoast);
      View layout = inflater.inflate(R.layout.gztoast, vg);
      _img = (ImageView) layout.findViewById(R.id.img);
      _text = (TextView) layout.findViewById(R.id.text);
      _duration = Toast.LENGTH_SHORT; // Toast.LENGTH_LONG
      
      super.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
      super.setDuration(_duration);
      super.setView(layout);
   }

   public static void show(Activity act, CharSequence msg) {
      if (null == singleton) {
         singleton = new GZToast();
      }
      singleton._text.setText(msg);
      singleton.show();
   }
}
