package com.gdx.gade;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.states.MenuState;
import com.gdx.utility.Assets;

/*====================================================================
 * GADE ENGINE BY AHMED FAISAL, BUILD INITIALLY STARTED SATURDAY SEP 6, 2017
 * 
 * Todo: (+ indicates completion)
 * 
 * Config Parser
 * Initial 2D array level loading system
 * Entity System
 * Reworked HUD 
 * Reworked GUI
 * Timer system
 * 
 *====================================================================
 */

public class Gade extends Game implements ApplicationListener{

	
	public static int WINDOW_WIDTH = 0;
	public static int WINDOW_HEIGHT = 0; 
	
	
	public static Assets assets;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//assets = new Assets();
		Assets.create();
		
		WINDOW_WIDTH = Gdx.graphics.getWidth();
		WINDOW_HEIGHT = Gdx.graphics.getHeight(); 
		
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(Assets.CURSOR_PASSIVE, 0,31));
		
		this.setScreen(new MenuState(this));
	}

	@Override
	public void render () {
		super.render();
		
		
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
