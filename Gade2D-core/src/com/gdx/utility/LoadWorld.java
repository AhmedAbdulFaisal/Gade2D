package com.gdx.utility;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Player;
import com.gdx.entity.CollisionBox;
import com.gdx.entity.Crate;
import com.gdx.entity.LootableBox;
import com.gdx.entity.SlidingDoorMechanical;

public class LoadWorld{
	
	public String mapPath;
	public Worlds world;
	public EntityManager entityMan;

	public LoadWorld(String mapname, SpriteBatch batch, EntityManager em) {
		this.mapPath = mapname;
		this.entityMan = em;
		world.tmap = new TmxMapLoader().load(mapPath);
		world.tmaprender = new OrthogonalTiledMapRenderer(world.tmap,2f, batch);
		buildEntities();
		
	}
	
	public void buildEntities() {
		//first, find the Player and other triggers in the "triggers" layer and build them first
		triggers();
		//secondly, build collision maps
		collision();
		//third, build entities
		entities();
	}
	
	public void triggers() {
		MapLayer trigger = world.tmap.getLayers().get("triggers");
		MapObjects objects_trigger = trigger.getObjects();
		
		for (MapObject obj: objects_trigger) {
			if (obj instanceof RectangleMapObject && objects_trigger.get("SPAWN") != null) {
				Rectangle r = ((RectangleMapObject) obj).getRectangle();
				
				if (world.mapCursorX == 0 && world.mapCursorY == 0) {
					//world.p.setPosition((int) r.x * 2,(int) r.y * 2);
				}
			}
		}
	}
	
	public void collision() {
		MapLayer collisions = world.tmap.getLayers().get("collisions");
		MapObjects objects_trigger2 = collisions.getObjects();
		
		for (MapObject obj: objects_trigger2) {
			if (obj instanceof RectangleMapObject && objects_trigger2.get("COLLISION") != null) {
				Rectangle r = ((RectangleMapObject) obj).getRectangle();
				entityMan.add(new CollisionBox(r.x * 2, r.y * 2, r.width * 2, r.height * 2));
			}
		}
	}
	
	public void entities() {
		MapLayer entities = world.tmap.getLayers().get("entities");
		MapObjects objects_trigger3 = entities.getObjects();
		
		for (MapObject obj: objects_trigger3) {
			if (obj instanceof RectangleMapObject) {
				if (obj.getName().equals("CRATE")) {
					Rectangle r = ((RectangleMapObject) obj).getRectangle();
					entityMan.add(new Crate( r.x * 2, r.y * 2,(int) r.width * 2));
				}
				if (obj.getName().equals("DOORM")) {
					Rectangle r = ((RectangleMapObject) obj).getRectangle();
					entityMan.add(new SlidingDoorMechanical(r.getX() * 2, r.getY() * 2, 0));
				}
				if (obj.getName().equals("LOOTBOX")) {
					Rectangle r = ((RectangleMapObject) obj).getRectangle();
					entityMan.add(new LootableBox(r.x * 2, r.y * 2));
				}
			}
		}
	}

	public void LoadSave(String save) {
		
	}
	
	public void saveFile(String save) {
		
	}
	
	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}
}


