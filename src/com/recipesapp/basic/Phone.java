package com.recipesapp.basic;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.content.SharedPreferences;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Phone extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
  
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
        	public void onCallStateChanged(int state, String incomingNumber) {}
        	public void onServiceStateChanged(ServiceState serviceState) {}
        	public void onSignalStrengthsChanged(int asu) {}
        	};
    
        String myTelephony = Context.TELEPHONY_SERVICE;
        TelephonyManager _TelephonyManager = (TelephonyManager) getSystemService( myTelephony );
        _TelephonyManager.listen( phoneStateListener,
        			PhoneStateListener.LISTEN_CALL_STATE |
        			PhoneStateListener.LISTEN_SERVICE_STATE |
        			PhoneStateListener.LISTEN_SIGNAL_STRENGTHS );
        	
    }
    
    
}
