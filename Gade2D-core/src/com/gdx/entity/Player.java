package com.gdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gdx.items.Computer;
import com.gdx.items.DataReader;
import com.gdx.items.Item;
import com.gdx.items.LMG;
import com.gdx.items.LMGAmmo;
import com.gdx.items.Pistol;
import com.gdx.states.GameState;
import com.gdx.utility.Assets;
import com.gdx.utility.DebugHUD;
import com.gdx.utility.EntityManager;
import com.gdx.utility.Graphics;
import com.gdx.utility.Inventory;
import com.gdx.utility.TextManager;
/* To be added
 * SKILLS
 * 
 * 
 * Entrenchment - Using a shovel or a pickaxe, you can dig foxholes in the ground to avoid enemy fire and take cover in the midst of battle.
 * (NOTE: Shovels can be brought at a store)
 * 
 * Rescue - Working as a rescue operator in the cave villages your grew up in helped you overcome your fears about climbing. At a young age, your job consisted of climbing down dark and foreboding caverns to rescue 
 * entrapped personnel and others from certain death. You have additionally learned to force doors when rescuing people from the labyrynthian facilities beneath. With this skill, you can easily climb 
 * walls and force open doors using tools. 
 * 
 * Medical - Working as a medic in the Army prepared you for the worst wounds you have seen. Remembering most wounds by heart, you can help tend to the wounds of your companions, and additionally, tend to your own 
 * wounds yourself. As a result, using medical equipment to restore yourself is faster than if you didin't have these skills.
 * (NOTE: Healing is faster with this perk enabled, and you can heal the wounds of your allies).
 * 
 * Electrical Engineering - As an Electrical Engineer, you are able to fix various broken devices and panels. Building radios at a young age, you have gained an affinity for electronic devices and can repair most broken
 * devices with ease. With this skill, repairing computers has no risk of breaking or destroying the device forever. You also automatically know what you need for repair just by looking at the broken device.
 * (NOTE: If you do not have this skill, then all broken electronics have a chance of being permanently destroyed or requiring even more parts to repair.)
 * 
 * Software Development - Working in a military office 24/7, Software Programming has been your passion. Ever since you got your first computer for your 12th birthday, you remember learning how to create basic
 * programs that would help determine your career. When you were picked up for a job, you knew how to repair and revitalize targeting software and viruses for military use. As a Software Engineer, you can 
 * 
 * Outdoorsmen - You love the wilderness. From the deep forested chasms near the temple to the towering mountains bordering Asia, you know how to survive the wild and live off the land. As a seasoned outdoorsman, you can
 * build special camps using campfire kits (One at a time), build traps which may or may not catch food, and resist diseases by simply resting.
 * 
 * Skills are divided into two arrays - Primary and Secondary
 * both arrays take strings that define the type of trait. On startup, this defines your skill check data and your health
 * 
 * 
 * 
 */
public class Player extends Creature {
	public weapons getCurrentState() {
		return currentState;
	}

	public void setCurrentState(weapons currentState) {
		this.currentState = currentState;
	}

	// public Assets assets;
	public TextManager textmanager;
	public int speed = 50;

	public final String[] primary_skills;
	public final String[] secondary_skills;
	
	// stats

	private DebugHUD debug;
	private Inventory invent;
	private GameState g;
	private EntityManager em;
	private int BulletTimer;
	private int bulletCap = 10;
	
	private int bandwidth = 1024; //bandwith allows you to transmit disk content over an alloted radius 
	//If the disk size is over your bandwith, then you can't transmit disk contents.
	
	private boolean standing = true;
	private weapons currentState;

	private enum weapons {
		UNARMED, WEAPON
	};

	private enum anim_movement {
		LEFT, RIGHT, UP, DOWN, IDLE
	};

	private int direction = 0; // 0 = down, 1 = up, 2 = left, 3 = right;
	private anim_movement mstate;
	private float degrees = 0; // shooting degree of the player

	private float stateTime = 0;

	public static int JUMPHEIGHT = 16;
	public static final int JUMPLIMIT = 16;
	public boolean jumping = false;
	public static float clickDistance = 0; // check clickDistance between player and (n,o) point 

	public static final int LOOKDISTANCE = 600;
	public static final int INTERACTDISTANCE = 100;
	public static final float PRONE_SPEED = 0.6f;
	public static final float WALK_SPEED = 1.2f;
	public static final float RUN_SPEED = 1.8f;
	
	private Item current_item;

	public Player(float xPos, float yPos) {
		super(xPos, yPos);
		
		/* LIST OF ALL PLAYER ATTRIBUTES / SKILLS
		 * 
		 * --PRIMARY--
		 * MACHINEGUNNER
		 * ASSAULT
		 * MARKSMEN
		 * SABOTAGE
		 * 
		 * --SECONDARY--
		 * ENTRENCHMENT
		 * RESCUE
		 * MEDICAL
		 * ELECTRICAL
		 * SOFTWARE
		 * OUTDOORSMEN
		 * 
		 */
		
		
		primary_skills = new String[2];
		secondary_skills = new String[3];
		
		primary_skills[0] = "ASSAULT";
		primary_skills[1] = "SABOTAGE";
		
		secondary_skills[0] = "SOFTWARE";
		secondary_skills[1] = "OUTDOORSMEN";
		secondary_skills[2] = "RESCUE";
		
		id = 0;
		classif = states.FLESH;
		mstate = anim_movement.IDLE;
		currentState = weapons.UNARMED;
		health = 100;
		healthCAP = 100;
		// debug = new DebugHUD(yPos, yPos);
		bounds = new Rectangle();
		bounds.set(x + (FDW / 4), y, FDW / 2, (FDH / 2));
		invent = new Inventory(this);
		//invent.add(new Pistol(0,0,true));
		//invent.add(new PistolMag(0,0,true));
		invent.add(new LMG(0,0,true));
		invent.add(new LMGAmmo(0,0,true));
		invent.add(new Computer(0,0,true));
		invent.add(new DataReader(0,0,true));
		em = new EntityManager();
		//addToInventory(new Pistol(0, 0, true));
	}
	
	private int calculateSlopeX() {
		Vector2 v = new Vector2();

		// v.

		// (y2 - y1) / (x2 - x1)
		return (int) (((Gdx.graphics.getWidth() / 2)) - (GameState.generateWorldMouseCoords().x));
	}

	private int calculateSlopeY() {
		// (y2 - y1) / (x2 - x1)
		return (int) (((Gdx.graphics.getHeight() / 2)) - (GameState.generateWorldMouseCoords().y));
	}

	
	
	public void addToInventory(Item i) {
		if (checkCollisons() && getEntity().getId() == 005) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
				addToInventory(new Pistol(0,0,true));
			}
		}
	}
	


	public void setDesAsc(int prevA, int currentA) {
		
	}
	
	
	
	
	private boolean addCrouching, addRunning;
	
	public void updateGameplayVar() { //accuracy is dependent on health (for now)
		
		
	}
	
	public void generateDirection() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			mstate = anim_movement.UP;
			direction = 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			mstate = anim_movement.DOWN;
			direction = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			mstate = anim_movement.LEFT;
			direction = 2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			mstate = anim_movement.RIGHT;
			direction = 3;
		}
		
		//System.out.println(direction + " " + mstate);
	}
	
	public void input() {
		xVel = 0;
		yVel = 0;
		// 0 = down, 1 = up, 2 = left, 3 = right;
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (!standing) {
				yVel = PRONE_SPEED;
			} else if (standing) {
				yVel = WALK_SPEED;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && standing) {
				yVel = RUN_SPEED;
			}
			//y += yVel;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (!standing) {
				yVel = -PRONE_SPEED;
			} else if (standing) {
				yVel = -WALK_SPEED;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && standing) {
				yVel = -RUN_SPEED;
			}

			//y += yVel;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (!standing) {
				xVel = -PRONE_SPEED;
			} else if (standing) {
				xVel = -WALK_SPEED;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && standing) {
				xVel = -RUN_SPEED;
			}
			//x += xVel;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (!standing) {
				xVel = PRONE_SPEED;
			} else if (standing) {
				xVel = WALK_SPEED;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && standing) {
				xVel = RUN_SPEED;
			}
			//x += xVel;
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.C) && standing) {
			standing = false;
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.C) && !standing) {
			standing = true;
		}

		if (xVel == 0 && yVel == 0) {
			mstate = anim_movement.IDLE;
		}

//		if (Gdx.input.isButtonPressed(Input.Keys.LEFT) && BulletTimer == bulletCap && mstate == anim_movement.IDLE && currentState == weapons.WEAPON) {
//			em.add(new PBullet(x, y,14, (posX / dirLength), (posY / dirLength)));
//			BulletTimer = 0;
//		}else if (Gdx.input.isButtonPressed(Input.Keys.LEFT) && mstate != anim_movement.IDLE) {
//			DebugHUD.setText("You can't shoot while moving!");
//		}
		if (!invent.getReadyslots().isEmpty()) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
				current_item = invent.getReadyslots().get(0);
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
				current_item = invent.getReadyslots().get(invent.getReadyslots().size() - 1);
			}
		}
		

		
		if (current_item != null) {
			if (Gdx.input.isButtonPressed(Input.Keys.LEFT) && (current_item.getId() == 005)) { //for pistol
				current_item.use(posX, posY, dirLength, x, y);
			}else if (Gdx.input.isButtonPressed(Input.Keys.LEFT) && (!standing) && (current_item.getId() == 007)) {
				current_item.use(posX, posY, dirLength, x, y);
			}else {
				//current_item.use(posX, posY, invent);
			}
		}
		
	}

	private int posX, posY;
	private float dirLength;

	public void calculateAngles() {
		posX = (int) (GameState.generateWorldMouseCoords().x - x);
		posY = (int) (GameState.generateWorldMouseCoords().y - y);
		degrees = (float) Math.toDegrees(Math.atan2(posY, posX));
		dirLength = (float) Math.sqrt(posX * (posX / 10) + posY * (posY / 10));
	}

	public void updateBounds() {
		bounds.set(x + (FDW / 4), y, FDW / 2, (FDH / 2));
	}

	@SuppressWarnings("static-access")
	public void calculateMouseDistance() {
		clickDistance = (float) Math
				.sqrt((x - GameState.generateWorldMouseCoords().x) * (x - GameState.generateWorldMouseCoords().x)
						+ (y - GameState.generateWorldMouseCoords().y) * (y - GameState.generateWorldMouseCoords().y));
	}


	public void wepClass() {
	}

	@Override
	public void tick() {
		BulletTimer++;
		calculateAngles();
		updateGameplayVar();
		invent.itemTick(); //tick all player items in inventory.
		
		if (BulletTimer > bulletCap) { // cap
			BulletTimer = bulletCap;
		}

		calculateMouseDistance();
		
		gravity();
		input();
		
		move();
		wepClass();
		generateDirection();

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && standing) {
			jumping = true;
		} else if (z <= 0) {
			jumping = false;
		}

		if (jumping) {
			z += JUMPHEIGHT;
			z -= FREEFALL_ACCELERATION;
			if (z >= JUMPLIMIT) {
				JUMPHEIGHT = 0;
			}

		}

		if (!jumping) {
			JUMPHEIGHT = 5;
		}
		// GameInput.Update();
		// velocity();
	}

	public float getClickDistance() {
		return clickDistance;
	}

	public void setClickDistance(float clickDistance) {
		this.clickDistance = clickDistance;
	}

	@Override
	public void render(SpriteBatch batch) {
		if (jumping || z != 0) {
			Graphics.transparency(batch);
			batch.draw(Assets.player_shadow, getX(), getY() - (FDW / 2), FDW, FDH);
			Graphics.opaque(batch);
		}
		// batch.draw(Assets.blue_outline_square, x + 4, y + 8, FDW, FDH);
		switch (mstate) {
		case DOWN:
			if (!standing) {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion PD = Assets.plproneD.getKeyFrame(stateTime, true);
				batch.draw(PD, getX(), calculateGraphicZ((int) z), FDW, FDH);
			} else {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion UP = Assets.pwalkU.getKeyFrame(stateTime, true);
				batch.draw(UP, getX(), calculateGraphicZ((int) z), FDW, FDH);
			}

			break;
		case UP:
			if (!standing) {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion PU = Assets.plproneU.getKeyFrame(stateTime, true);
				batch.draw(PU, getX(), calculateGraphicZ((int) z), FDW, FDH);
			} else {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion DOWN = Assets.pwalkD.getKeyFrame(stateTime, true);
				batch.draw(DOWN, getX(), calculateGraphicZ((int) z), FDW, FDH);
			}
			break;
		case RIGHT:
			if (!standing) {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion PL = Assets.plproneLR.getKeyFrame(stateTime, true);
				batch.draw(PL, getX(), calculateGraphicZ((int) z), FDW, FDH);
			} else {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion LEFT = Assets.pwalkLR.getKeyFrame(stateTime, true);
				batch.draw(LEFT, getX(), calculateGraphicZ((int) z), FDW, FDH);
			}
			break;
		case LEFT:
			if (!standing) {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion RP = Assets.plproneLR.getKeyFrame(stateTime, true);
				batch.draw(RP, getX() + FDW, calculateGraphicZ((int) z), -FDW, FDH);
			} else {
				stateTime += Gdx.graphics.getDeltaTime();
				TextureRegion RIGHT = Assets.pwalkLR.getKeyFrame(stateTime, true);
				batch.draw(RIGHT, getX() + FDW, calculateGraphicZ((int) z), -FDW, FDH);
			}
			break;
		// 0 = down, 1 = up, 2 = left, 3 = right;
		case IDLE:
			if (currentState == weapons.UNARMED) {
				unarmedPose(batch);
			}else if (currentState == weapons.WEAPON) {
				weaponPose(batch);
			}
		}


	}
	
	public void unarmedPose(SpriteBatch batch) {
		stateTime = 0;
		switch (direction) {
		case 0:
			if (!standing) {
				batch.draw(Assets.pdown, getX(), calculateGraphicZ((int) z), FDW, FDH);
			} else {
				batch.draw(Assets.player_standing, getX(), calculateGraphicZ((int) z), FDW, FDH);
			}
			break;
		case 1:
			if (!standing) {
				batch.draw(Assets.pup, getX(), calculateGraphicZ((int) z), FDW, FDH);
			} else {
				batch.draw(Assets.up, getX(), calculateGraphicZ((int) z), FDW, FDH);
			}
			break;
		case 2:
			if (!standing) {
				batch.draw(Assets.pleftright, getX(), calculateGraphicZ((int) z), FDW, FDH);
			} else {
				batch.draw(Assets.leftright, getX(), calculateGraphicZ((int) z), FDW, FDH);
			}
			break;
		case 3:
			if (!standing) {
				batch.draw(Assets.pleftright, getX() + FDW, calculateGraphicZ((int) z), -FDW, FDH);
			} else {
				batch.draw(Assets.leftright, getX() + FDW, calculateGraphicZ((int) z), -FDW, FDH);
			}
			break;
		}
	}

	
	//goes clockwise, starting at 0, -45, -90, -135, 180, 135, 90, 45
	public void weaponPose(SpriteBatch batch) { 
		if (degrees < 30 && degrees >= -30) {
			batch.draw(Assets.gundirections[0], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}else if (degrees < -30 && degrees >= -60) {
			batch.draw(Assets.gundirections[1], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}else if (degrees < -60 && degrees >= -120){
			batch.draw(Assets.gundirections[2], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}else if (degrees < -120 && degrees >= -150){
			batch.draw(Assets.gundirections[3], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}else if ((degrees < -150 && degrees >= -180) || (degrees < 180 && degrees >= 150)){
			batch.draw(Assets.gundirections[4], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}else if (degrees < 150 && degrees >= 120){
			batch.draw(Assets.gundirections[5], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}else if (degrees < 120 && degrees >= 60) {
			batch.draw(Assets.gundirections[6], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}else if (degrees < 60 && degrees >= 30) {
			batch.draw(Assets.gundirections[7], getX(), calculateGraphicZ((int) z), FDW, FDH);
		}
	}
		
	public Player getPlayer() {
		return this;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public void setPosition(float xPos, float yPos) {
		this.x = xPos;
		this.y = yPos;
	}

	public Inventory getInvent() {
		return invent;
	}

	public void setInvent(Inventory invent) {
		this.invent = invent;
	}

}

