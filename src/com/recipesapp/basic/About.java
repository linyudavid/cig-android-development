package com.recipesapp.basic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.SharedPreferences;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class About extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        


            Translate.setHttpReferrer("www.yoursite.com");

            String translatedText = null;
			try {
				translatedText = Translate.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);
			} catch( Exception e ) {
				e.printStackTrace();
			}

            System.out.println(translatedText);
            Toast.makeText(this, translatedText, Toast.LENGTH_SHORT).show();    
    }
      
    	
        
    
    
    private static final String AboutPreference = "AboutLanguagePreference";
    private void savePreferences() {
    	SharedPreferences aboutSharedPreferences = getSharedPreferences(AboutPreference, About.MODE_PRIVATE);
    	SharedPreferences.Editor aboutEdit = aboutSharedPreferences.edit();
    	aboutEdit.putString( "Language", "English" );
    	aboutEdit.commit();
    }
    
    private void onActivityResult()
    {
    	
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
