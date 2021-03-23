package com.gdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity;
import com.gdx.states.GameState;
import com.gdx.utility.EntityManager;
import com.gdx.utility.GameInput;

public abstract class Creature extends Entity {

	protected float xVel, yVel;
	
	protected int stamina;
	
	public Creature(float xPos, float yPos) {
		super(xPos, yPos);   
		
		xVel = 0;
		yVel = 0;
	}

	@Override
	public abstract void tick();

	@Override
	public abstract void render(SpriteBatch batch);

	// COLLISIONS
	
	public void deleteDead() {
		if (checkCollisons()) {
			this.isDead = true;
		}
		
		if (checkCollisons() && getEntity().getId() == 0) {
			this.isDead = false;
		}
	}
	
	public boolean checkCollisons() {
		for (int i = 0; i < EntityManager.entityList.size(); i++) {
			Entity e = EntityManager.entityList.get(i);
			if (e.equals(this)) {
				continue;
			}
			if (bounds.overlaps(e.getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	public void moveX() {
		x += xVel;
	}
	
	public void moveY() {
		y += yVel;
	}
	
	public void move() { //perform actions for entities that move around //&& (xVel > 0 || xVel < 0)
		
		x += xVel;
		updateBounds();
		if (xVel != 0 && checkCollisons()) {
			x -= xVel;
			updateBounds();
		}
		
		y += yVel;
		updateBounds();
		if (yVel != 0 && checkCollisons()) {
			y -= yVel;
			updateBounds();
		}
		
		//xVel = yVel = 0;
		
	}

	// make it so that whenever the player jumps on
	// an entity with a designated height, they
	// appear on top of it.
	
	public void zCollision() { 
		//z = 0;
		if (checkCollisons()) {
			// System.out.println("true");
			z = getEntity().getDepth();
		} else if (!checkCollisons()) {
			// System.out.println("false");
			z = 0;
			// System.out.println(ground);
		}
	}
	
	/* CLASSIFICATION TYPES
	 * WOOD - weakened by standard, vaporized by energy and explosive, 
	 * METAL - immune to standard, weakened by energy and explosive,  
	 * CLOTH - weakened (high) by standard, vaporized by energy and explosive, 
	 * FLESH - weakened by standard, cauterized by energy, vaporized by explosive, 
	 * FIRE, - basis for standard ammo (and some beasts), weakened by none 
	 * PLASMA, - basis for energy ammo and other energy based items, weakened by none
	 * EXPLOSIVE, - basis for explosive ammo, weakened by none
	 * STONE, weakened (low) by standard, immune to energy, and weakened by explosive;
	 * QUEST - immune to all, reserved for things like lootable crates and important entities, and collisions
	 * PFIRE, PPLASMA, PEXPLOSIVE: bullets only shot by the player
	 */
	
	
	/* REFER TO DAMAGE TABLE AS SHOWN HERE
	 * STATE|VALUE_STANDARD|VALUE_ENERGY|VALUE_EXPLOSIVE
	 * wood | -10          | vaporize   | vaporize
	 * metal| 0            | -healthC/7 | -healthC/9
 	 * cloth| -healthC/2   | -healthC   | -vaporize
 	 * flesh| -healthC/6   | -healthC/4 | -vaporize
 	 * fire | 0            | 0          | 0
 	 * plasm| 0            | 0          | 0
 	 * explo| 0            | 0          | 0
 	 * stone| -1           | 0          | -healthC / 2
 	 * quest| 0            | 0          | 0   
	 */
	
	public void BulletDamageTable() { //now with the new and improved enum system
		
		if (checkCollisons()) {
			switch(getEntity().getClassif()) {
				case WOOD: getEntity().decreaseHealth(10); break;
				case METAL: break; //do nothing
				case CLOTH: getEntity().decreaseHealth(getEntity().getHealthCAP() / 2); break;
				case FLESH: 
					if (getEntity().getId() == 0 && this.getClassif().equals(states.PFIRE)) { //pBullets dont hurt player
						
					}else {
						getEntity().decreaseHealth(getEntity().getHealthCAP() / 6);
					}
					break;
				case FIRE: break; //do nothing
				case PLASMA: break; //do nothing
				case EXPLOSIVE: break; //do nothing
				case STONE: getEntity().decreaseHealth(1); break;
				case QUEST: break; //do nothing
				case PFIRE:
			}
		}
	}

	/*
	 * Method simulates real time gravity. To add functionality to an entity,
	 * connect this method to a tick() method
	 */

	public void gravity() {
		if (z <= ground) {
			z = ground;
		}

		if (checkCollisons() && z > ground) {
			z = ground;
		}
	}
	
	public float getxVel() {
		return xVel;
	}


	public void setxVel(float xVel) {
		this.xVel = xVel;
	}


	public float getyVel() {
		return yVel;
	}


	public void setyVel(float yVel) {
		this.yVel = yVel;
	}
}
