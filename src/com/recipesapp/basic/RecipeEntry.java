package com.recipesapp.basic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener; 
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RecipeEntry extends Activity implements OnClickListener  {
	
	private static String _SelectedRecipe;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_tab_new);
		
        Button saveButton = (Button)this.findViewById( R.id.save_new_recipe );
        saveButton.setOnClickListener( this );
        Button cancelButton = (Button)this.findViewById( R.id.cancel_new_recipe );
        cancelButton.setOnClickListener( this );
        
        SharedPreferences selectedRecipe = getSharedPreferences( MainMenu.SelectedRecipe, RecipeEntry.MODE_WORLD_READABLE);
        _SelectedRecipe = selectedRecipe.getString( MainMenu.SelectedRecipe, "New Recipe" );
        
        if("New Recipe" != _SelectedRecipe)
        {
        	SharedPreferences thisRecipe = getSharedPreferences( _SelectedRecipe + "_Detail", RecipeEntry.MODE_WORLD_READABLE);
        	EditText NameText = (EditText)this.findViewById( R.id.name_new );
        	NameText.setText( thisRecipe.getString( MainMenu.NamePref, "" ) );
        	EditText DescriptionText = (EditText)this.findViewById( R.id.description_new );
        	DescriptionText.setText( thisRecipe.getString( MainMenu.DescriptionPref, "" ) );
        	EditText StepsText = (EditText)this.findViewById( R.id.steps_new );
        	StepsText.setText( thisRecipe.getString( MainMenu.StepsPref, "" ) );
        	Spinner CuisineSelect = (Spinner)this.findViewById( R.id.cuisine_new );
        	CuisineSelect.setSelection( thisRecipe.getInt( MainMenu.CuisinePref, 0 ));
        }
	}
	
    public void onClick(View thisView) {
    	Intent doMenuClick;
    	switch (thisView.getId()) {
    	case R.id.save_new_recipe:
    		saveRecipe();
    		doMenuClick = new Intent( this, MainMenu.class );
    		startActivity( doMenuClick );
    		break;
    	case R.id.cancel_new_recipe:
    		doMenuClick = new Intent( this, MainMenu.class );
    		startActivity( doMenuClick );
    		break;
    	}
     }
	    
	public void saveRecipe() {
		
		//Create or Open a Preference for all Recipe Names
		SharedPreferences recipeName = getSharedPreferences( MainMenu.RecipeNamesPref, RecipeEntry.MODE_WORLD_READABLE);
    	String recipeList = recipeName.getString( MainMenu.RecipeNamesPref, "New Recipe" );
		SharedPreferences.Editor recipeNameEdit = recipeName.edit();
    	EditText newRecipeNameView = (EditText)this.findViewById( R.id.name_new ); 
    	String newRecipeName = (String)newRecipeNameView.getText().toString();
    	if( null != newRecipeName )
    	{
    		recipeList = recipeList + "," + newRecipeName;
    		recipeNameEdit.putString( MainMenu.RecipeNamesPref, recipeList );
    		recipeNameEdit.commit();
    	
	    	String newRecipeDetail = newRecipeName + "_Detail";
			SharedPreferences newRecipe = getSharedPreferences( newRecipeDetail, RecipeEntry.MODE_WORLD_READABLE);
	    	SharedPreferences.Editor newRecipeEdit = newRecipe.edit();
	    	newRecipeEdit.putString( MainMenu.NamePref, newRecipeName );
	    	newRecipeEdit.putString( MainMenu.DescriptionPref, ((EditText)this.findViewById( R.id.description_new )).getText().toString() );
	    	newRecipeEdit.putInt( MainMenu.CuisinePref, ((Spinner)this.findViewById( R.id.cuisine_new )).getSelectedItemPosition() );
	    	newRecipeEdit.putString( MainMenu.StepsPref, ((EditText)this.findViewById( R.id.steps_new )).getText().toString() );
	    	newRecipeEdit.commit();
	    	
	    	/*ListView IngredientsView = (ListView)this.findViewById( android.id.list );
	    	ListAdapter IngredientsAdapter = IngredientsView.getAdapter();
	    	EditText IngredientText;
	    	for(int i=0; i<=IngredientsAdapter.getCount()-1;i++)
	    	{
	    		IngredientText = (EditText)IngredientsAdapter.getView( i, null, null );
	    		newRecipeEdit.putString( "Ingredient_" + i, IngredientText.getText().toString() );
	    	}*/
    	}
	}
		

}
