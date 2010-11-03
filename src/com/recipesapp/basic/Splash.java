package com.recipesapp.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;
import android.widget.ImageView;

public class Splash extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        beginAnimation();
    }

    //Begin the splash screen animation
    private void beginAnimation() {
        
    	// Header animation
        TextView header = (TextView) findViewById(R.id.SplashHeader);
        Animation headerAnim = AnimationUtils.loadAnimation(this, R.anim.splash_text);
        header.startAnimation(headerAnim);
       
        // Footer animation
        TextView footer = (TextView) findViewById(R.id.SplashFooter);
        Animation footerAnim = AnimationUtils.loadAnimation(this, R.anim.splash_text);
        footer.startAnimation(footerAnim);
       
        // Image Animation
        ImageView image = (ImageView) findViewById(R.id.SplashImageCenter);
        Animation imageAnim = AnimationUtils.loadAnimation(this, R.anim.splash_image);
        image.startAnimation(imageAnim);
        
        // Prepare end of animation event
        imageAnim.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                
            	// All done, open the Main Menu
                startActivity(new Intent(Splash.this, MainMenu.class));
                
            }
            
            //Nothing to do here
            public void onAnimationRepeat(Animation animation) {
            }
            //Nothing to do here
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        
        // Clear the animation. Start fresh on resume.
        TextView header = (TextView) findViewById(R.id.SplashHeader);
        header.clearAnimation();
        
        TextView footer = (TextView) findViewById(R.id.SplashFooter);
        footer.clearAnimation();
        
        ImageView image = (ImageView) findViewById(R.id.SplashImageCenter);
        image.clearAnimation();        
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        //Start the animation from the beginning.
        beginAnimation();
    }

}

