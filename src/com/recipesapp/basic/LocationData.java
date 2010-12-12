package com.recipesapp.basic;

import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Context;

public class LocationData extends Activity implements SensorEventListener{

    private TextView myLocationText;    
    private String myLocationProvider; 
    private Location myLocation;
    private Criteria bestProvider;
    private SensorManager mySensorManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        mySensorManager = (SensorManager) getSystemService( Context.SENSOR_SERVICE );
        
        List<Sensor> Sensors = mySensorManager.getSensorList( Sensor.TYPE_ORIENTATION );
        for(Sensor s: Sensors)
        {
        	mySensorManager.registerListener( this, s, SensorManager.SENSOR_DELAY_NORMAL );
        }
    }

 	public void onAccuracyChanged( Sensor arg0, int arg1 ) {
		// Respond to changes in Accuracy
	}

	public void onSensorChanged( SensorEvent event ) {
		// Respond to new sensor data
	}

	@Override
    public void onResume() {
    	super.onResume();
    	Sensor mySensor = mySensorManager.getDefaultSensor( Sensor.TYPE_ORIENTATION );
    	mySensorManager.registerListener( this, mySensor, SensorManager.SENSOR_DELAY_NORMAL );
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    }
      
}
