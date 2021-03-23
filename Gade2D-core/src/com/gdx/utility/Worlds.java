package com.gdx.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.entity.Entity;
import com.gdx.entity.PBullet;
import com.gdx.entity.Player;
import com.gdx.states.GameState;
import com.gdx.entity.Crate;
import com.gdx.entity.Cube;
import com.gdx.entity.Pillar;



public class Worlds {

	public LoadWorld loadworld;
	public GameState g;
	public EntityManager em;
	public static Player p;
	
	//BOX2D STUFF
	
	
	public static TiledMapRenderer tmaprender;
	public static TiledMap tmap;
	public static MapProperties mprop;
	private int width, height;
	
	public ArrayList<String> maplist;
	
	public int currentLevel = 0;
	
	public static int mapCursorX = 0, mapCursorY = 0;
	
	public String[][] mapMatrix;
	
	public int[] bglayers = { 0, 1, 2, 3, 4, 5 }; 
	public int[] fglayers = { 6, 7 };
			
	
	public Worlds(String world0, SpriteBatch batch) {
		mapMatrix = new String[8][5];
		
		p = new com.gdx.entity.Player(0,0);
		em = new EntityManager(p);
		loadmapList("levels.cfg");
		loadworld = new LoadWorld(world0, batch, em);
		displayList();
		mprop = tmap.getProperties();
		width = Entity.FDW * mprop.get("width", Integer.class);
		height = Entity.FDH * mprop.get("height", Integer.class);
	}
	
	public void loadmapList(String path) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
			String text;
			String words[];
			int linecount=0;
			
			while((text = reader.readLine()) != null) {
				words = text.split(" ");
				for (int x = 0; x < 5; x++) {
					mapMatrix[linecount][x]=words[x];
				}
				linecount++;
			}
			
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayList() {
		for (int i = 0; i < mapMatrix.length; i++) {
			for (int j = 0; j < mapMatrix[0].length; j++) {
				System.out.print(mapMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void renderMapBEFORE() {
		tmaprender.render(bglayers);
		
		
	}
	
	public void renderMapAFTER() {
		tmaprender.render(fglayers);
	}
	
	
	public void render(SpriteBatch batch) {
		
		em.render(batch);
		//em.renderDebug(batch);
	}
	
	public void tick(OrthographicCamera camera, SpriteBatch batch) {
		float x = p.getX();
		float y = p.getY();
		
		
		
		
//		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//			em.purge();
//			currentLevel++;
//			loadworld = new LoadWorld("worlds/" + maplist.get(currentLevel) + ".tmx", batch, em);
//			System.out.println(maplist.get(currentLevel) + " loaded!");
//			
//		}
		
		//CODE FOR LEFT/RIGHT LEVEL LOADING
		
		
		
//		if ((p.getX() + 32) == width) {
//			em.purge(); //destroy all entites but the player
//			//System.out.println(mapMatrix[mapCursorX][mapCursorY+1]);
//			mapCursorY = 0; mapCursorX+= 1;
//			System.out.println("worlds/" + mapMatrix[mapCursorY][mapCursorX] + ".tmx");
//			loadworld = new LoadWorld("worlds/" + mapMatrix[mapCursorY][mapCursorX] + ".tmx", batch, em); //load new map
//			p.setPosition(32, y);
//			
//		}else if ((p.getX()) == 0) {
//			em.purge(); //destroy all entites but the player
//			//System.out.println(mapMatrix[mapCursorX][mapCursorY+1]);
//			mapCursorY = 0; mapCursorX-= 1;
//			System.out.println("worlds/" + mapMatrix[mapCursorY][mapCursorX] + ".tmx");
//			loadworld = new LoadWorld("worlds/" + mapMatrix[mapCursorY][mapCursorX] + ".tmx", batch, em); //load new map
//			p.setPosition(width - 64, y);
//		}
		em.tick();
		camera.position.x = p.getX();
		camera.position.y = p.getY();
		
		camera.update();
		tmaprender.setView(camera);
	}
	
	public void dispose() {
		
	}
	
}
