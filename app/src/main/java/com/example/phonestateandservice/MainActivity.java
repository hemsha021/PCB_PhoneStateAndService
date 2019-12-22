package com.example.phonestateandservice;

import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);

        //Which will listen to call state
        PhoneStateChangeListener pscl = new PhoneStateChangeListener();

        //This class will handle all the events
        TelephonyManager tm = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        tm.listen(pscl,PhoneStateListener.LISTEN_CALL_STATE);

        /*
            Content Registered receivers
         */
        //add the action(events) to recieve
        IntentFilter broadcastIntentFilter = new IntentFilter();
        broadcastIntentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        broadcastIntentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);

        //Which will recieve the event and send it to listner
        PhoneReceiver state = new PhoneReceiver();

        //Registering the Receiver
        registerReceiver(state,broadcastIntentFilter);

    }

    private class PhoneStateChangeListener extends PhoneStateListener{


        @Override
        public void onCallStateChanged(int state, String phoneNumber) {

            if(state == TelephonyManager.CALL_STATE_IDLE){
                Toast.makeText(MainActivity.this,"CALL_STATE_IDLE",Toast.LENGTH_LONG).show();
                txt.append("/nCALL_STATE_IDLE");

            }else if(state == TelephonyManager.CALL_STATE_OFFHOOK){
                Toast.makeText(MainActivity.this,"CALL_STATE_OFFHOOK",Toast.LENGTH_LONG).show();
                txt.append("/nCALL_STATE_OOFHOOk");
            }else if(state == TelephonyManager.CALL_STATE_RINGING){
                Toast.makeText(MainActivity.this,"CALL_STATE_RINGING",Toast.LENGTH_LONG).show();
                txt.append("/nCALL_STATE_RINGING");
            }

            super.onCallStateChanged(state, phoneNumber);
        }
    }

    class PhoneReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)){

            }
            if(action.equals(Intent.ACTION_NEW_OUTGOING_CALL)){

            }
        }
    }

}
