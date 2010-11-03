package com.recipesapp.basic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class NewRecipe extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_new);
    }
    
/*    private String selectCuisineDialog(){
    	new AlertDialog.Builder(this)
    		.setTitle(R.string.main_new_button)
    		.setItems(R.array.opt_cuisine_list, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					setResult(which);
				}
			})
			
			.show();
    }
*/
}
