package com.roundarch.codetest.part3;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.roundarch.codetest.model.ZipModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Part3Service extends IntentService {

    private final String TAG = this.getClass().getSimpleName();
    String dataAPI=" http://gomashup.com/json.php?fds=geo/usa/zipcode/state/IL";

    // TODO - we can use this as the broadcast intent to filter for in our Part3Fragment
    public static final String ACTION_SERVICE_DATA_UPDATED = "com.roundarch.codetest.ACTION_SERVICE_DATA_UPDATED";

    private List<Map<String,String>> data = null;

    public Part3Service(String name) {
        super(name);
    }
    public Part3Service() {
       super(null);
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return START_STICKY;
//    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO - this interface needs to be implemented to allow consumers
        // TODO - access to the data we plan to download
        return new Part3ServiceBinder();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Modell", "On handle intent called");
        HttpURLConnection httpURLConnection;
        URL url = null;
        String jsonString,jsonData;
        ZipModel zipModel = null;
        try {
            url = new URL(dataAPI);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Log.i("model","inside try");
            httpURLConnection = (HttpURLConnection)url.openConnection();
            Log.i("httpurl", httpURLConnection.getResponseMessage().toString());
            int statusCode = httpURLConnection.getResponseCode();
            Log.i("model", String.valueOf(statusCode));
            if(statusCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonData = bufferedReader.readLine()) != null) {
                    broadcastDataUpdated(jsonData);
                }


            }else{
                Log.i("Failed to get model","ttttttttttt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void updateData() {
        // TODO - start the update process for our data
    }

    private void broadcastDataUpdated(String string) {
        // TODO - send the broadcast

        Intent intentSend=new Intent();
        intentSend.setAction(ACTION_SERVICE_DATA_UPDATED);
        intentSend.putExtra("updated",string);
        sendBroadcast(intentSend);
    }

    public final class Part3ServiceBinder extends Binder {
        // TODO - we need to expose our public IBinder API to clients
//        Part3Service getService() {
//            return Part3Service.this;
//        }
    }

    // TODO - eventually we plan to request JSON from the network, so we need
    // TODO - to implement a way to perform that off the main thread.  Then, once we
    // TODO - have the data we can parse it as JSON (using standard Android APIs is fine)
    // TODO - before finally returning to the main thread to store our data on the service.
    // TODO - Keep in mind that the service will keep a local copy and will need an interface
    // TODO - to allow clients to access it.

    // TODO - if you need a simple JSON endpoint, you can obtain the ZIP codes for the state
    // TODO - of Illinois by using this URL:
    //
    // TODO - http://gomashup.com/json.php?fds=geo/usa/zipcode/state/IL



}


