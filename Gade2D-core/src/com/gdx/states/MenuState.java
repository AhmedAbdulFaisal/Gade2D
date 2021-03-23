package com.gdx.states;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdx.entity.Entity;
import com.gdx.gade.Gade;
import com.gdx.utility.Assets;
import com.gdx.utility.MathStuff;
import com.gdx.utility.TextManager;
import com.gdx.utility.Window;


public class MenuState implements Screen{

	public float scrollY;
	
	final Gade engine;
	public OrthographicCamera camera;
	public TextManager textmanager;
	public Window mainmenu, notice, leave, load, help, loadError;
	public boolean leaveP = false, loadP = false, fileEmpty;
	public File[] loadroster;
	
	public String helptext = "CONTROLS\n========"
						   + "\nWASD: Move"
						   + "\nSPACE: Jump"
						   + "\nRETURN: Next Level"
						   + "\n#1-4: Weapons"
						   + "\nI: Inventory"
						   + "\nSHIFT: Sprint"
						   + "\nESC: Pause"
						   + "\nRMB: Interact"
						   + "\nLMB (WITH WEP): SHOOT"
						   + "\nLMB (NO WEP): EXAMINE"
						   + "\nPRESS ENTER TO PLAY"
						   + "\n"
						   + "\n\n\n\n\n\n\n\n\nProgrammed by Ahmed Faisal, c2017-18";
	
	public String mainMenu = "Press Number Key related to Action" 
						 + "\n\n1)Start Game"
						 + "\n\n2)Load Game"
						 + "\n\n3)Load Custom Map"
						 + "\n\n4)Help"
						 + "\n\n5)Exit to Windows"
						 + "\n\n\n\n\n\n\n\n\n\n\n\nProgrammed by Ahmed Faisal, c2017-18";
	
	public MenuState(Gade r) {
		this.engine = r;
		//textmanager = new TextManager();
		loadCamera();
		mainmenu = new Window(MathStuff.CalculateWindowMiddle(Window.WIDTH_STANDARD, Window.HEIGHT_LARGE).x,
				              MathStuff.CalculateWindowMiddle(Window.WIDTH_STANDARD, Window.HEIGHT_LARGE).y,
				              Window.WIDTH_STANDARD,Window.HEIGHT_LARGE,"GadeEngine: WIP!", mainMenu);
		
		notice = new Window(MathStuff.CalculateWindowBottom(Window.WIDTH_STANDARD, Window.HEIGHT_SMALL).x,
				MathStuff.CalculateWindowBottom(Window.WIDTH_STANDARD, Window.HEIGHT_SMALL).y,
				Window.WIDTH_STANDARD,Window.HEIGHT_SMALL,"NOTICE", "All content in this engine is subject to change");
		
		leave = new Window(MathStuff.CalculateWindowMiddle(Window.WIDTH_LARGE, Window.HEIGHT_SMALL).x,
				MathStuff.CalculateWindowMiddle(Window.WIDTH_LARGE, Window.HEIGHT_SMALL).y,
				Window.WIDTH_LARGE,Window.HEIGHT_SMALL,"Quit to Windows", "Are you sure you want to exit?\n (y/n)");
		
		//load = new Window((Gdx.graphics.getWidth() / 2) - (500/2),420,500,150,"Load Save (Press ESC to cancel)", buildsavelist());
		help = new Window((Gdx.graphics.getWidth() / 2) - (500/2),450,500,100,"Help (Press ESC to cancel)", helptext);
		loadError = new Window((Gdx.graphics.getWidth() / 2) - (500/2),450,500,100,"ERROR", "Save File Empty!");
		
		//buildsavelist();
		
	}
	
//	public String[] getLoadRoster() {
//		File saves = new File("saves");
//		loadroster = saves.listFiles();
//		
//		String[] savenames = new String[loadroster.length];
//		
//		for (int i = 0; i < loadroster.length; i++) {
//			if (loadroster[i].isFile()) {
//				
//				savenames[i] = loadroster[i].getName();
//			}
//		}
//		
//		return savenames;
//	}
	
//	public String buildsavelist() {
//		String savelist = "";
//		
//		String[] list = getLoadRoster();
//		
//		savelist += "\n(Press a number key to load the corresponding save)";
//		
//		for (int i = 0; i < list.length; i++) {
//			savelist += "\n\n SLOT " + (i) + " (" + list[i] + ") " + loadroster[i].length() + " BYTES";
//		}
//		
//		System.out.println(savelist);
//		
//		return savelist;
//	}
	
	public boolean isFileEmpty(int filenum) {
		
		if (loadroster[filenum].length() <= 10) {
			return true;
		}
		
		return false;
	}
	
	public void loadCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, engine.WINDOW_WIDTH, engine.WINDOW_HEIGHT);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		engine.batch.begin();
		
		engine.batch.draw(Assets.Title, MathStuff.CalculateWindowMiddle(Assets.Title.getRegionWidth(), Assets.Title.getRegionHeight()).x, 
				Gdx.graphics.getHeight() - 100, Assets.Title.getRegionWidth(), Assets.Title.getRegionHeight());
		
		mainmenu.render(engine.batch);
		notice.render(engine.batch);
//		textmanager.setSize(24);
//		textmanager.drawTextWrapped(engine.batch, Color.GREEN, helptext, camera.position.x - (Gdx.graphics.getWidth() / 2) + 16, camera.position.y + scrollY, 800, 15);
//		scrollY += 10 * Gdx.graphics.getDeltaTime();
		
		if (leaveP == true) {
			leave.render(engine.batch);
			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				Gdx.app.exit();
			}else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				leaveP = false;
			}
		}
		
		if (loadP == true) {
			load.render(engine.batch);
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				loadP = false;
			}
		}
		
		if (fileEmpty == true) {
			loadError.render(engine.batch);
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				fileEmpty = false;
			}
		}
		
		engine.batch.end();
		
		//System.out.println("Menu Rendering");
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1) && loadP != true) {
			System.out.println("SWITCHING TO GAME");
			engine.setScreen(new GameState(engine));
		}else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2) && loadP != true) {
			loadP = true;
			
		}else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5) && loadP != true) {
			leaveP = true;
		}
		
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
