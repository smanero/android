package org.stt.activity;

import org.stt.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * http://developer.android.com/reference/android/webkit/WebView.html
 * http://developer.android.com/guide/webapps/webview.html
 * 
 * @author gaizer
 */
public class WebViewActivity extends Activity {

   private WebView _wv = null;
   private  Activity _activity = null;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      // display the progress in the activity title bar, like the browser app does.
      getWindow().requestFeature(Window.FEATURE_PROGRESS);
      // define activty layout
      setContentView(R.layout.activity_webview);
      
      _activity = this;
      _wv = (WebView) findViewById(R.id.webview);
      // debugging tasks
      _wv.setWebChromeClient(new MyWebChromeClient(this));
      // to open links clicked by the user in the same WebView, simply provide a WebViewClient
      _wv.setWebViewClient(new MyWebViewClient(this));
      // using JavaScript in WebView
      _wv.getSettings().setJavaScriptEnabled(true);
      // binding JavaScript code to Android code
      _wv.addJavascriptInterface(new MyWebAppInterface(this), "Android");
      
      // Simplest usage: note that an exception will NOT be thrown
      // if there is an error loading this page (see below).
      _wv.loadUrl("http://slashdot.org/");

      // OR, you can also load from an HTML string:
      String summary = "<html><body>You scored <b>192</b> points.</body></html>";
      _wv.loadData(summary, "text/html", null);
      // ... although note that there are restrictions on what this HTML can do.
      // See the JavaDocs for loadData() and loadDataWithBaseURL() for more info.
   }

   /**
    * Navigating web page history When your WebView overrides URL loading, it automatically accumulates a history of
    * visited web pages. You can navigate backward and forward through the history with goBack() and goForward().
    */
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event) {
      // Check if the key event was the Back button and if there's history
      if ((keyCode == KeyEvent.KEYCODE_BACK) && _wv.canGoBack()) {
         _wv.goBack();
         return true;
      } else if ((keyCode == KeyEvent.KEYCODE_FORWARD) && _wv.canGoForward()) {
         _wv.goForward();
         return true;
      }
      // If it wasn't the Back key or there's no web page history, bubble up to the default
      // system behavior (probably exit the activity)
      return super.onKeyDown(keyCode, event);
   }
   
   /**
    * WebChromeClient subclass. 
    * This class is called when something that might impact a browser UI happens, for instance,
    * progress updates and JavaScript alerts are sent here (see Debugging Tasks).
    * @author gaizer
    */   
   private class MyWebChromeClient extends WebChromeClient {
      Context _ctx;

      /** Instantiate the interface and set the context */
      MyWebChromeClient(Context c) {
         _ctx = c;
      }
      
      public void onProgressChanged(WebView view, int progress) {
         // Activities and WebViews measure progress with different scales.
         // The progress meter will automatically disappear when we reach 100%
         _activity.setProgress(progress * 1000);
      }      
   }

   /**
    * Handling Page Navigation
    * Creating and setting a WebViewClient subclass. It will be called when things happen that impact the rendering of the content, 
    * eg, errors or form submissions. You can also intercept URL loading here
    * @author gaizer
    */   
   private class MyWebViewClient extends WebViewClient {
      Context _ctx;

      /** Instantiate the interface and set the context */
      MyWebViewClient(Context c) {
         _ctx = c;
      }
      
      /**
       * Tratamiento errores en el procesamiento de la pagina
       */
      public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
         Toast.makeText(_activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
      }

      /**
       * Control over where a clicked link
       */
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
         if (Uri.parse(url).getHost().equals("www.example.com")) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
         }
         // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
         Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
         startActivity(intent);
         return true;
      }
   }

   /**
    * Binding JavaScript code to Android code
    * Injecting Java objects into the WebView using the addJavascriptInterface(Object, String) method. 
    * This method allows you to inject Java objects into a page's JavaScript context, so that they can be accessed by JavaScript in the page.
    * @author gaizer
    */
   private class MyWebAppInterface {
      Context _ctx;

      /** Instantiate the interface and set the context */
      MyWebAppInterface(Context c) {
         _ctx = c;
      }

      /** Show a toast from the web page */
      @JavascriptInterface
      public void showToast(String msg) {
         Toast.makeText(_ctx, msg, Toast.LENGTH_SHORT).show();
      }
      
//      <input type="button" value="Say hello" onClick="showAndroidToast('Hello Android!')" />
//
//      <script type="text/javascript">
//          function showAndroidToast(toast) {
//              Android.showToast(toast);
//          }
//      </script>
   }
}
