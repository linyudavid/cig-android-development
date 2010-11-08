package com.recipesapp.basic;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RecipeList extends ListActivity implements OnItemClickListener  {
   
	private static final ArrayList<String> _RecipeListContents = new ArrayList<String>();
    private ListView recipeListView;
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.recipe_list );
		
		SharedPreferences recipeNames = getSharedPreferences( MainMenu.RecipeNamesPref, RecipeEntry.MODE_WORLD_READABLE);
		String[] recipeList = recipeNames.getString( MainMenu.RecipeNamesPref, "Make New" ).split( "," );
		for(String recipe: recipeList)
		{
			_RecipeListContents.add( recipe );			
		}
		String[] anarray = {"hey","you"};
		
		recipeListView = (ListView) findViewById(android.R.id.list);
		recipeListView.setAdapter( new ListViewAdapter(this) );
		recipeListView.setTextFilterEnabled(true);
		recipeListView.setOnItemClickListener( this );
		
	}
	
	//setListAdapter( new ListViewAdapter(this) );
	public void onClick( View v ) {
		// TODO Auto-generated method stub
	}

	private static class ListViewAdapter extends BaseAdapter {
		private LayoutInflater recipeInflater;
        
		public ListViewAdapter( Context context ) 
		{
			recipeInflater = LayoutInflater.from( context );
		}
		public int getCount() 
		{
			return _RecipeListContents.size();
		}
		public Object getItem(int position) 
		{
			return position;
		}
		public long getItemId(int position) 
		{
			return position;
		}
		public View getView( int position, View view, ViewGroup group ) {
	      
			ListContent contents;
	      
			if (view == null) 
			{
				view = recipeInflater.inflate( R.layout.recipe_list_inflate, null );
				contents = new ListContent();
				contents.text = (TextView) view.findViewById( R.id.recipe_first );
				contents.text.setCompoundDrawables( view.getResources().getDrawable(R.drawable.arrow_black), null, null, null );
				view.setTag(contents);
			} 
			else 
			{
				contents = (ListContent) view.getTag();
			}
	        
			contents.text.setText( _RecipeListContents.get(position) );
			return view;
		}
	
		static class ListContent {
			TextView text;
		}
	}

	public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {
		
		SharedPreferences selectedRecipe = getSharedPreferences( MainMenu.SelectedRecipe, RecipeEntry.MODE_WORLD_READABLE);
		Editor selectedEdit = selectedRecipe.edit();
		selectedEdit.putString( MainMenu.SelectedRecipe, _RecipeListContents.get( arg2 ) );
		selectedEdit.commit();
		
		Intent doRecipeClick = new Intent(this, RecipeNew.class);
    	startActivity(doRecipeClick);
	}
   
}
