package com.roundarch.codetest.part3;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roundarch.codetest.R;
import com.roundarch.codetest.ZipAdapter;
import com.roundarch.codetest.model.ZipModel;

public class Part3Fragment extends Fragment {
    private ResponseReceiver receiver;
    TextView outputTextView ;
    ZipModel zipModel;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part3, null);
        View emptyView = (View) view.findViewById(R.id.empty_textview);
        recyclerView= (RecyclerView) view.findViewById(R.id.part3_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
         outputTextView = (TextView)view.findViewById(R.id.textView);
        Intent inputIntent = new Intent(this.getActivity(),Part3Service.class);
//        inputIntent.putExtra(Part3Service.ACTION_SERVICE_DATA_UPDATED, "send");
        getActivity().startService(inputIntent);
        // TODO - the listview will need to be provided with a source for data

        // TODO - (optional) you can set up handling to list item selection if you wish

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // TODO - when the fragment resumes, it would be a good time to register to receieve broadcasts
        // TODO - from the service.  The broadcast will serve as a way to inform us that data is available
        // TODO - for consumption

        // TODO - this is also a good place to leverage the Service's IBinder interface to tell it you want
        // TODO - to refresh data
        ResponseReceiver receiver = new ResponseReceiver();
        IntentFilter broadcastFilter = new IntentFilter(
                ResponseReceiver.LOCAL_ACTION);
         getActivity().registerReceiver(receiver,broadcastFilter);
        Log.i("onResume","broadcast reciever registered");
    }

    @Override
    public void onPause() {
        super.onPause();


    }

    // TODO - our listView needs a source of data, and here might be a good place to create that

    // TODO - we also need a means of responding to the Broadcasts sent by our Service

    public class ResponseReceiver extends BroadcastReceiver {
        String result;
        String jsonSring;
        StringBuilder stringBuilder;
        public static final String LOCAL_ACTION =
                "com.roundarch.codetest.ACTION_SERVICE_DATA_UPDATED";

        public ResponseReceiver() {
            super();
            stringBuilder = new StringBuilder();
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("data recieved","Recieved");
         result= intent.getStringExtra("updated");
            stringBuilder.append(result);


            if(result.endsWith(")")) {
                jsonSring= stringBuilder.toString().replaceAll("[()]", "");
                zipModel = new Gson().fromJson(jsonSring, new TypeToken<ZipModel>(){}.getType());
                recyclerView.setAdapter(new ZipAdapter(zipModel.getResult(), R.layout.part3_listview_item, getActivity().getApplicationContext()));

            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
