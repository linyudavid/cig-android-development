package com.recipesapp.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener; 
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {
    
	public static final String RecipeNamesPref = "RecipeNames";
	public static final String NamePref = "Name";
	public static final String CuisinePref = "Cuisine";
	public static final String DescriptionPref = "Description";
	public static final String StepsPref = "Steps";
	public static final String SelectedRecipe = "SelectedRecipe";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        Button about = (Button)this.findViewById(R.id.main_about_button);
        about.setOnClickListener(this);
        Button newRecipe = (Button)this.findViewById(R.id.main_new_button);
        newRecipe.setOnClickListener(this);
        Button listRecipe = (Button)this.findViewById(R.id.main_list_button);
        listRecipe.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inf = getMenuInflater();
    	inf.inflate(R.menu.main_menu, menu);
    	menu.findItem(R.id.main_menu_new).setIntent(
    			new Intent(this, RecipeNew.class));
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
    		doMenuClick = new Intent(this, RecipeNew.class);
    		startActivity(doMenuClick);
    		break;
    	case R.id.main_about_button:
    		doMenuClick = new Intent(this, About.class);
    		startActivity(doMenuClick);
    		break;
    	case R.id.main_list_button:
    		doMenuClick = new Intent(this, RecipeList.class);
    		startActivity(doMenuClick);
    		break;	
    	}
     }
    
}
