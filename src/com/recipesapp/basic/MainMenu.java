package com.recipesapp.basic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener; 
import android.util.Log;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        Button about = (Button)this.findViewById(R.id.main_about_button);
        about.setOnClickListener(this);
        Button newRecipe = (Button)this.findViewById(R.id.main_new_button);
        newRecipe.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inf = getMenuInflater();
    	inf.inflate(R.menu.main_menu, menu);
//    	menu.findItem(R.id.main_menu_new).setIntent(
//    			new Intent(this, NewRecipe.class));
//    	menu.findItem(R.id.main_menu_search).setIntent(
//    			new Intent(this, SearchRecipe.class));
    	menu.findItem(R.id.main_menu_options).setIntent(
    			new Intent(this, Options.class));
   		return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem itm) {
    	super.onOptionsItemSelected(itm);
    	Intent menuIntent = itm.getIntent();
    	if (menuIntent != null)
    		startActivity(menuIntent);
    	return true;
    }
    
    public void onClick(View thisView) {
    	Intent doMenuClick;
    	switch (thisView.getId()) {
    	case R.id.main_new_button:
    		doMenuClick = new Intent(this, NewRecipe.class);
    		startActivity(doMenuClick);
    		break;
    	case R.id.main_about_button:
    		doMenuClick = new Intent(this, About.class);
    		startActivity(doMenuClick);
    		break;
    	}
     }
    
}
