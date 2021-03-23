package com.gdx.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Window {
	
	public static final int SCALE = 1;
	public static final int LENGTH = 16;
	
	
	//WINDOW HEIGHT AND WIDTH CONSTANTS
	public static final int WIDTH_STANDARD = 350;
	public static final int WIDTH_LARGE = 500;
	
	public static final int HEIGHT_LARGE = 200;
	public static final int HEIGHT_MEDIUM = 100;
	public static final int HEIGHT_SMALL = 50;
	
	
	
	
	public TextManager tm;
	public String titleBar;
	public String dialogue;
	protected Rectangle window;
	
	public Window(float f, float g, int windowWidth, int windowHeight, String title, String dial) {
		
		window = new Rectangle(); //use rectangle to base window off of
		window.set(f, g, windowWidth, windowHeight);
		this.titleBar = title;
		this.dialogue = dial;
		
		tm = new TextManager(12);
		
	}
	
	public void setRectPos(float f, float g) { 
		window.setPosition(f, g);
	}
	
	public void render(SpriteBatch batch) { //renders a window based off of rectangle itself
		//The four corners of the Earth...
		
		batch.draw(Assets.black, window.x, window.y, window.width, -window.height);
		
		batch.draw(Assets.WindowULC, window.x, window.y,LENGTH * SCALE ,LENGTH * SCALE); //upper left corner
		batch.draw(Assets.WindowURC, + window.x + (window.width - 16), window.y,LENGTH * SCALE ,LENGTH * SCALE); //upper right corner
		batch.draw(Assets.WindowLLC, + window.x, window.y - (window.height + 16),LENGTH * SCALE ,LENGTH * SCALE); //lower left corner
		batch.draw(Assets.WindowLRC, + window.x + (window.width - 16) , window.y - (window.height + 16),LENGTH * SCALE ,LENGTH * SCALE); //lower right corner
		
		
		//And now, for the edges... near the four corners of the Earth
		batch.draw(Assets.WindowU, window.x + 16, window.y, window.width - 32, LENGTH); //up
		batch.draw(Assets.WindowD, window.x + 16, window.y - (window.height + 16), (window.width - 32), LENGTH); //down
		batch.draw(Assets.WindowLe, window.x, window.y, LENGTH, - window.height ); //left
		batch.draw(Assets.WindowR, window.x + (window.width - 16), window.y, LENGTH, - window.height); //right
		
		//drawing the text
		tm.drawTextCentered(batch, Color.GREEN, titleBar, window.x + (window.width / 2), window.y + 14);
		tm.drawTextWrapped(batch, Color.GREEN, dialogue , window.x + 16 ,  window.y, (int) window.width, 30);
		
	}
	
}
