package com.recipesapp.basic;

import android.app.Activity;
import android.os.Bundle;


public class RecipeEntry extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_tab_new);
	}
}
