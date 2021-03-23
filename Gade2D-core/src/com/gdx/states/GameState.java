package com.gdx.states;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.gdx.gade.Gade;
import com.gdx.utility.Assets;
import com.gdx.utility.DebugHUD;
import com.gdx.utility.EntityManager;
import com.gdx.utility.GUI;
import com.gdx.utility.Worlds;

public class GameState implements Screen{
	
	/* Objects for camera, the world / main game class, and various others */
	
	public Worlds world;
	final Gade engine;
	public static OrthographicCamera camera;
	public static ShapeRenderer shapeR;
	
	public GUI gui;
	public DebugHUD debugHUD;
	
	
	/* Getter and setter methods */
	
	public static OrthographicCamera getCamera() {
		return camera;
	}

	public static void setCamera(OrthographicCamera camera) {
		GameState.camera = camera;
	}
	
	/* Gamestate constructor for starting a saved game*/
	public GameState(Gade r, String savedGamePath) {
		this.engine = r;
		world.loadworld.LoadSave(savedGamePath);
		world = new Worlds(world.loadworld.getMapPath(), engine.batch);
		loadCamera();
	}
	
	
	/* Gamestate constructor for starting a new game */
	
	public GameState(Gade r) { //use this constructor if you want to start a new game
		this.engine = r;
		world = new Worlds("worlds/CAVE01.tmx", engine.batch);
		loadCamera();
	}

	
	/* load camera and HUD info */
	
	public void loadCamera() {
		shapeR = new ShapeRenderer();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, engine.WINDOW_WIDTH, engine.WINDOW_HEIGHT);
		camera.update();
		
		gui = new GUI((int) camera.position.x,(int) camera.position.y);
		debugHUD = new DebugHUD(0,0); //let's initialize HUD here temporarily
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	
	/* generate world coordinates for localized mouse coords */
	
	public static Vector3 generateWorldMouseCoords() {
		return camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	
	
	/* render game elements */
	
	@Override
	public void render(float delta) {
		//TICK
		
		engine.batch.begin(); //here we intiate the batch just for level loading, nothing else. ALWAYS KEEP RENDER SPECIFIC INSTANCES IN THE RENDER LOOP AND OTHERWISE FOR TICK
		
		gui.tick();
		
		if (!gui.paused) { //if this bool is false, tick the world
			world.tick(camera, engine.batch); //tick
			debugHUD.tick();
		}
		gui.dynamicallyAdjustCoords(camera.position.x, camera.position.y); //tick
		
		
		engine.batch.end(); //here we end the batch just for level loading
		
		//RENDER
		
		Gdx.gl.glClearColor(0, 0, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		world.renderMapBEFORE(); //render
		
		engine.batch.begin(); //render
		engine.batch.setProjectionMatrix(camera.combined);
		shapeR.setProjectionMatrix(camera.combined);
		world.render(engine.batch);
		
		engine.batch.end(); //render
		
		world.renderMapAFTER(); //render
		
		engine.batch.begin(); //render
		gui.render(engine.batch);
		debugHUD.render(engine.batch);//render
		
		if (EntityManager.ifMouseTouches()) {
			engine.batch.draw(Assets.cursor_int, generateWorldMouseCoords().x - 16, generateWorldMouseCoords().y - 16, 32, 32);
		}
		
		engine.batch.end();//render
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
