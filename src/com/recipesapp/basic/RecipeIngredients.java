package com.recipesapp.basic;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeIngredients extends ListActivity implements OnClickListener  {
   
    EditText ingredientText;
    Button ingredientAdd;
    ListView ingredientList;

    private static class ListViewAdapter extends BaseAdapter {
        private LayoutInflater ingredientInflater;

	    public ListViewAdapter(Context context) 
	    {
	        ingredientInflater = LayoutInflater.from(context);
	    }
	    public int getCount() 
	    {
	        return IngredientListContents.size();
	    }
	    public Object getItem(int position) 
	    {
	        return position;
	    }
	    public long getItemId(int position) 
	    {
	        return position;
	    }
	    public View getView(int position, View view, ViewGroup group) {
	       
	        ListContent contents;
	      
	        if (view == null) 
	        {
	            view = ingredientInflater.inflate(R.layout.recipe_tab_ingredients_inflate, null);
	            contents = new ListContent();
	            contents.text = (EditText) view.findViewById(R.id.ingredient_first);
	            contents.text.setCompoundDrawables(view.getResources().getDrawable(R.drawable.arrow_black), null, null, null);
	            view.setTag(contents);
	        } 
	        else 
	        {
	            contents = (ListContent) view.getTag();
	        }
	        
	        contents.text.setText(IngredientListContents.get(position));
	              return view;
	    }
	
	    static class ListContent {
	        TextView text;
	
	    }
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_tab_ingredients);
        IngredientListContents.add("Salt");
        IngredientListContents.add("Pepper");
        
        ingredientText=(EditText)findViewById(R.id.ingredient_text);
        ingredientAdd=(Button)findViewById(R.id.ingredient_button);
        ingredientAdd.setOnClickListener(this);
        setListAdapter(new ListViewAdapter(this));
    }
	private static final ArrayList<String> IngredientListContents = new ArrayList<String>();
	
	public void onClick(View v) {
		if(v==ingredientAdd)
		{
			IngredientListContents.add(ingredientText.getText().toString());
			setListAdapter(new ListViewAdapter(this));
		}

		
	}
}
