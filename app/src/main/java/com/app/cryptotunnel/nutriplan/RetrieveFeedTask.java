//package com.app.cryptotunnel.nutriplan;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//
//public class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
//
//   private Exception exception;
//
//   protected String doInBackground(Void... urls) {
//       try {
//           Request request = new Request.Builder()
//                   .url("http://192.168.43.243/lynda-php/jsontest2.php")
//                   .build();
//           OkHttpClient client = new OkHttpClient();
//           Response response = client.newCall(request).execute();
//           return response.body().string();
//       } catch (Exception e) {
//           e.printStackTrace();
//           Log.d("URL BUG", e.toString());
//           return null;
//       }
//   }
//
//   protected void onPostExecute(String feed) {
//       Log.d("JSON RESULT", feed);
//       // TODO: check this.exception
//       // TODO: do something with the feed
//   }
//
//
//}
