package com.recipesapp.basic;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class About extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
    
    private static final String AboutPreference = "AboutLanguagePreference";
    private void savePreferences() {
    	SharedPreferences aboutSharedPreferences = getSharedPreferences(AboutPreference, About.MODE_PRIVATE);
    	SharedPreferences.Editor aboutEdit = aboutSharedPreferences.edit();
    	aboutEdit.putString( "Language", "English" );
    	aboutEdit.commit();
    }
    
    private void restorePreferences() {
    	SharedPreferences aboutSharedPreferences = getSharedPreferences(AboutPreference, About.MODE_PRIVATE);
    	String language = aboutSharedPreferences.getString( "Language", null );
    }
    
    private void saveItToFile(String filename, String content){
    	try
    	{
	    	FileOutputStream fos = openFileOutput(filename, About.MODE_PRIVATE);
	    	fos.write(content.getBytes());
	    	fos.close();
    	}
    	catch(IOException e){}
    	
    }
}
