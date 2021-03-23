package com.gdx.utility;

import java.util.ArrayList;
import java.util.Comparator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.entity.Entity;
import com.gdx.entity.Player;
import com.gdx.states.GameState;

public class EntityManager {

	public static ArrayList<Entity> entityList;
	public Player player;
	public Entity e;
	
	private Comparator<Entity> renderSort = new Comparator<Entity>() { //sort entities that overlap 
		@Override
		public int compare (Entity a, Entity b) {
			if (a.getY() < b.getY()) {
				return 1;
			}
		return -1;
	}
	};
	
	public EntityManager() {
		entityList = new ArrayList<Entity>();
	}
	
	public EntityManager(Player p) {
		this.player = p;
		entityList = new ArrayList<Entity>();
		entityList.add(p);
	}
	
	public void tick() {
		
		for (int i = 0; i < entityList.size(); i++) {
			Entity entity = entityList.get(i);
			entity.tick();
			if (entity.isDead()) {
				entityList.remove(entity);
			}
		}
		entityList.sort(renderSort); //for some reason this is throwing an IllegalArgumentException... I have no fucking idea why
	}
	
	public Entity find(int id) {
		for (int i = 0; i < entityList.size(); i++) {
			Entity e = entityList.get(i);
			
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public void render(SpriteBatch batch) {
		for (int i = 0; i < entityList.size(); i++) {
			Entity entity = entityList.get(i);
			entity.render(batch);
		}
	}
	
	public void renderDebug(SpriteBatch batch) {
		for (int i = 0; i < entityList.size(); i++) {
			Entity entity = entityList.get(i);
			if (entity.getBounds() != null) {
				batch.draw(Assets.red_outline_square,entity.getBounds().x,entity.calculateGraphicZ((int) entity.getZ()), entity.getBounds().width, entity.getBounds().height);
			}else {
				continue;
			}
			if (entity.isFull3d() == true) { //if object has a fully3d collison mesh, then render debug box
				
				batch.draw(Assets.red_outline_square,entity.getBounds().x,entity.getBounds().y, entity.getBounds().width, entity.getBounds().height); //bottom
				batch.draw(Assets.blue_outline_square,entity.getBounds().x,entity.calculateGraphicZ((int) entity.getDepth()), entity.getBounds().width, entity.getBounds().height); //top
				batch.draw(Assets.green_outline_square,entity.getBounds().x, entity.calculateGraphicZ((int) entity.getDepth()), entity.getBounds().width, -entity.calculateZ(entity.getDepth())); //front
				
			}else {
				continue;
			}
		}
	}
	
	public static ArrayList<Entity> getEntityList() {
		return entityList;
	}

	public static void setEntityList(ArrayList<Entity> entityList) {
		EntityManager.entityList = entityList;
	}

	public static void setPosList(String saveFile) {
		for (int i = 0; i < entityList.size(); i++) {
			Entity entity = entityList.get(i);
			
			if (entity.getId() == 000) { //if ID == ID num on save file
				entity.setX(0); //set x to x on save file
				entity.setY(0); //set y to y on save file
			}
		}
	}
	
	public static boolean ifMouseTouches() {
		for (int i = 0; i < entityList.size(); i++) {
			Entity entity = entityList.get(i);
			
			if (entity.getBounds().contains((float) GameState.generateWorldMouseCoords().x, (float) GameState.generateWorldMouseCoords().y)) {
				return true;
			}
		}
		return false;
	}
	
	public static Player getPlayer() {
		Player player = null;
		for (Entity e: entityList) {
			int id = e.getId();
			if (id == 0) {
				player = (Player) e;
				break;
			}
		}
			
		return player;
	}
	
	public void add(Entity e) {
		entityList.add(e);
	}
	
	public void delete(Entity e) {
		entityList.remove(e);
	}
	
	public void purge() {
		Player p = getPlayer(); //create temporary player;
		entityList.removeAll(entityList);
		entityList.add(0, p);
	}
	
}
