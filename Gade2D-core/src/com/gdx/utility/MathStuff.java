package com.gdx.utility;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	
	
	/* 
	 * Method which calculates ScreenCoords as WorldCoords
	 */
	public static Vector3 generateWorldCoordsGeneric(OrthographicCamera c, float x, float y) {
		return c.unproject(new Vector3(x, y, 0));
	}
	
	
	/*
	 * Method which calculates MouseCoords into WorldCoords
	 */
	public static Vector3 generateWorldMouseCoords(OrthographicCamera c) {
		return c.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	
}
