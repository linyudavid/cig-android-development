package com.recipesapp.basic;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class RecipeNew extends TabActivity implements OnTabChangeListener {
	
	TabHost _tabHost;
	Resources _res;
	
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.recipe_tabs );
        
        _tabHost = getTabHost();
        _res = getResources();
        _tabHost.setOnTabChangedListener( this );
        
        TabHost.TabSpec _tabSpec;
        
        _tabSpec = _tabHost.newTabSpec("recipes").setIndicator( "Recipe", 
        							  _res.getDrawable( R.drawable.recipes_tab ) )
        				 .setContent( new Intent( this,RecipeEntry.class ) );
        _tabHost.addTab( _tabSpec );
        
        _tabSpec = _tabHost.newTabSpec("ingredients").setIndicator( "Ingredients", 
        							   _res.getDrawable( R.drawable.ingredients_tab ) )
        				   .setContent( new Intent( this,RecipeIngredients.class ) );	
        _tabHost.addTab( _tabSpec );        
      
        
        for(int i=0; i<_tabHost.getTabWidget().getChildCount(); i++)
        {
        	_tabHost.getTabWidget().getChildAt(i).setBackgroundColor( Color.LTGRAY );
        }
        
        _tabHost.getTabWidget().setCurrentTab(1);
        _tabHost.getTabWidget().getChildAt(1).setBackgroundColor( Color.DKGRAY );
        
    }
    
	
	public void onTabChanged(String tabId) {
		for(int i=0; i<_tabHost.getTabWidget().getChildCount(); i++)
        {
        	_tabHost.getTabWidget().getChildAt(i).setBackgroundColor( Color.LTGRAY );
        } 
				
		_tabHost.getTabWidget().getChildAt( _tabHost.getCurrentTab() ).setBackgroundColor( Color.GRAY );
	}
    
}