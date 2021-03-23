package com.gdx.utility;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gdx.states.GameState;

/* Class of quick math methods to ease repetitive computations
 */

public class MathStuff { 
	
	/* 
	 * Method which takes the height and width of a window and spits out coordinates to set it in the MIDDLE
	 */
	public static Point CalculateWindowMiddle(int width, int height) {
		return new Point((Gdx.graphics.getWidth() / 2) - (width/2),(Gdx.graphics.getHeight() / 2) + (height/2));
	}
	
	/*
	 * Method which takes the height and width of a window and spits out coordinates to set it on the BOTTOM
	 */
	public static Point CalculateWindowBottom(int width, int height) {
		return new Point((Gdx.graphics.getWidth() / 2) - (width/2),height + 16); //add offset to y to compensate
	}
	
}
