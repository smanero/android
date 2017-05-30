package org.stt.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * @see http://developer.android.com/guide/components/processes-and-threads.html
 * @author BIMALOSE
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

   /**
    * The system calls this to perform work in a worker thread and delivers it the parameters given to
    * AsyncTask.execute()
    */
   protected Bitmap doInBackground(String... urls) {
      // return loadImageFromNetwork(urls[0]);
      super.publishProgress();
      return null;
   }

   protected void onPreExecute() {
      super.onPreExecute();
   }

   protected void onProgressUpdate(Void... values) {
      super.onProgressUpdate(values);
   }

   /**
    * The system calls this to perform work in the UI thread and delivers the result from doInBackground()
    */
   protected void onPostExecute(Bitmap result) {
      super.onPostExecute(result);
//      mImageView.setImageBitmap(result);
   }
}
