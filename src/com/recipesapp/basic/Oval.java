package com.recipesapp.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path.Direction;
import android.view.View;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint;

	public class Oval extends View {
		private Path oval;
		private RectF bounds;
		private Paint cyan;
				
		public Oval (Context context) {
			super(context);
		
			bounds = new RectF();
			bounds.bottom = 320;
			bounds.left = 20;
			bounds.right = 120;
			bounds.top = 20;
			
			cyan = new Paint();
			cyan.setColor(Color.CYAN);
		
			oval = new Path();
			oval.addOval(bounds, Direction.CCW);
			
		}	
		protected void onDraw(Canvas canvas) {
			canvas.drawPath(oval, cyan);
		}
	}