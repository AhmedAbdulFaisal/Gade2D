package com.gdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.items.Computer;
import com.gdx.items.DataReader;
import com.gdx.entity.PBullet;
import com.gdx.entity.Player;
import com.gdx.states.GameState;
import com.gdx.entity.CollisionBox;
import com.gdx.entity.Crate;
import com.gdx.entity.LootableBox;
import com.gdx.entity.PanelMK1;
import com.gdx.entity.Pillar;
import com.gdx.entity.SlidingDoorMechanical;
import com.gdx.utility.EntityManager;
import com.gdx.utility.MathStuff;

public abstract class Entity {

	public static final int WIDTH = 16, HEIGHT = 16;
	public static final int SCALE = 2;
	public static final int FDW = WIDTH * SCALE, FDH = HEIGHT * SCALE;
	public static final int FREEFALL_ACCELERATION = 3; 
	public static int ACTUAL_GROUND = 0; 
	
	public static Entity[] entityindex = new Entity[512]; //512 possible entities in the entire game
	
	public static enum states {WOOD,METAL,CLOTH,FLESH,FIRE,PLASMA,EXPLOSIVE,STONE,QUEST, PFIRE, PPLASMA, PEXPLOSIVE}; 
	/* CLASSIFICATION TYPES (weakened generally indicates 1/6 of health removed, cauterized means 1/2 of health is removed, and vaporized means all health is removed)
	 * WOOD - weakened by standard, vaporized by energy and explosive, 
	 * METAL - immune to standard, weakened by energy and explosive,  
	 * CLOTH - weakened (high) by standard, vaporized by energy and explosive, 
	 * FLESH - weakened by standard, cauterized by energy, vaporized by explosive, 
	 * FIRE, - basis for standard ammo, weakened by none 
	 * PLASMA, - basis for energy ammo and other energy based items, weakened by none
	 * EXPLOSIVE, - basis for explosive ammo, weakened by none
	 * STONE, weakened (low) by standard, immune to energy, and weakened by explosive;
	 * QUEST - immune to all, reserved for things like lootable crates and important entities
	 * PFIRE, PPLASMA, PEXPLOSIVE: bullets only shot by the player
	 */
	protected states classif;
	public states getClassif() {
		return classif;
	}


	public void setClassif(states classif) {
		this.classif = classif;
	}
	//set a full array of entities to be used in level creation 
	//HOW LEVEL LOADING WORKS
	//an entity is designated by a number in the .tmx map file. When we parse this number when first
	//loading up a level, we then search for the number in entityindex[]. when a match is made, we then
	//create an entity based off of that match and then do other secondaries (setting position, width, and
	//height). 
	
	//Previously, the parser looked for a keyword given to every entity possible and then made entities based off of that.
	//the end result was a long list of if statements.
	//Here, we are simplifying the process in the factory class by alot more.
	//we will still have more if statements, for different entity 'categories'...
	
	/* Types of entities:
	 * Basic: (Require only an X and y position)
	 * BasicWithBoolean: (Require an X, Y, and a boolean)
	 * Containers: (Require an X, Y, and an arraylist)
	 * BasicWithInteger: (Contain an X, Y, and an extra data integer)
	 * 
	 */
	
	 
	
	public static void setIndex() {
		Player p = new Player(0,0);
		p.setId(0);
		entityindex[0] = p; 
		
		CollisionBox cb = new CollisionBox(0,0, 0, 0);
		cb.setId(1);
		entityindex[1] = cb; 
		
		Crate c = new Crate(0,0, 0);
		c.setId(2);
		entityindex[2] = c; 
		
		LootableBox lb = new LootableBox(0,0);
		lb.setId(3);
		entityindex[3] = lb; 
		
		PanelMK1 pk = new PanelMK1(0,0,0);
		pk.setId(4);
		entityindex[4] = pk; 
		
		Pillar pill = new Pillar(0,0,0);
		p.setId(5);
		entityindex[5] = pill;
		
		SlidingDoorMechanical sdm = new SlidingDoorMechanical(0,0,0);
		p.setId(6);
		entityindex[6] = sdm;
		
		Computer com = new Computer(0,0,false);
		p.setId(7);
		entityindex[7] = com;
		
		DataReader dr = new DataReader(0,0,false);
		p.setId(6);
		entityindex[8] = dr;
		
		
	}
	
	
	
	protected int health;
	
	/* ID NUMBER LIBRARY INDEX (MASTER, MUST BE UPDATED DAILY)
	 * CLASS             
	 * Player.java       
	 * CollisionBox.java 
	 * PBullet.java      
	 * 
	 */
	
	protected int healthCAP;
	protected int ground; 
	protected int id; //so we can initiate ids in Entity();
	protected float x, y, z;
	protected int teritiary; //generic third argument that may contain info for certain scripts.
	protected int depth; //height of object in z units. If other objects collides with object we add the height of 
	protected Rectangle bounds;
	protected boolean full3d;
	protected boolean isDead;
	
	public boolean isDead() {
		return isDead;
	}


	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}


	public int getHealth() {
		return health;
	}


	public int getTeritiary() {
		return teritiary;
	}


	public void setTeritiary(int teritiary) {
		this.teritiary = teritiary;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	
	public void increaseHealth(int increment) {
		health += increment;
	}
	
	public void decreaseHealth(int decrement) {
		health -= decrement;
	}
	
	public void vaporizeHealth() { //for certain weapons
		health = 0;
	}
	
	public void halveHealth() {
		health = health / 2;
	}


	public int getHealthCAP() {
		return healthCAP;
	}


	public void setHealthCAP(int healthCAP) {
		this.healthCAP = healthCAP;
	}

	
	public static final double DEVIATION_ANGLE = (Math.PI / 4); //How many degrees does the camera deviate from full top down perspective
	public static final double ANGLE_COSINE = (Math.cos(DEVIATION_ANGLE));

	public Entity() {

	}
	
	public Entity(float xPos, float yPos) {
		this.x = xPos;
		this.y = yPos;
	}


	//get entity that collides with 
	public Entity getEntity() {
		for (Entity e: EntityManager.getEntityList()) {
			if (e.equals(this)) {
				continue;
			}
			if (bounds.overlaps(e.bounds)) {
				return e;
			}
		}
		return null;
	}
	
	public boolean ifMouseTouches() {
		
		Entity e = this;
		
		if (e.getBounds().contains(MathStuff.generateWorldMouseCoords(GameState.camera).x,MathStuff.generateWorldMouseCoords(GameState.camera).y)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public Entity ReturnMouseCollisions() {
		
		for (int i = 0; i < EntityManager.entityList.size(); i++) {
			Entity e = EntityManager.entityList.get(i);
			if (e.getBounds().contains(MathStuff.generateWorldMouseCoords(GameState.camera).x,MathStuff.generateWorldMouseCoords(GameState.camera).y)) {
				return e;
			}else {
				return null;
			}
		}
		return null;
	}
	
	public boolean checkMouseCollisions() {
		for (int i = 0; i < EntityManager.entityList.size(); i++) {
			Entity e = EntityManager.entityList.get(i);
			if (e.getBounds().contains(MathStuff.generateWorldMouseCoords(GameState.camera).x,MathStuff.generateWorldMouseCoords(GameState.camera).y)) {
				return true;
			}
		}
		return false;
	}
	
	//actual collision detection for entities INSIDE entities
	public boolean collisonsInside() {
		for (int i = 0; i < EntityManager.entityList.size(); i++) {
			Entity e = EntityManager.entityList.get(i);
			if (e.equals(this)) {
				continue;
			}
			
			if (e.bounds.contains(bounds)) {
				return true;
			}
		}
		return false;
	}
	
	public void updateBounds() {
		//bounds.set(x,y, bounds.width, bounds.height);
		bounds.x = x;
		bounds.y = y;
		
		//System.out.println(bounds.x + "||" + x);
	}


	public abstract void tick();
	
	public abstract void render(SpriteBatch batch);
	
	//converts any coordinates from z to y. program can then add this to the y coordinate
	public float calculateZ(int z) {
		return (float) z;
		//return (float) (ANGLE_COSINE * z);
	}
	
	/* Method returns value that allows for ***graphic manipulation*** of sprites relative to z position. 
	 * Does not actually move the entity along the y axis, only moves it's graphics position. 
	 */
	
	public float calculateGraphicZ(int zShift) {
		return getY() + calculateZ(zShift);
		
	}
	
	/* method returns a parabola value, then converts it to z coords
	 * Method can be used for jumping
	 */
	
	public float jumpStandard(int xPos) {
		int zjump = (int) (Math.pow(xPos, 2));
		return calculateZ(zjump);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "ID: " + getId();
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}


	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public int getDepth() {
		return depth;
	}


	public void setDepth(int d) {
		this.depth = d;
	}


	public boolean isFull3d() {
		return full3d;
	}


	public void setFull3d(boolean full3d) {
		this.full3d = full3d;
	}
}
