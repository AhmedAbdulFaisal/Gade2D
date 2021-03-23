package com.gdx.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity;
import com.gdx.entity.Player;
import com.gdx.utility.EntityManager;
import com.gdx.utility.Inventory;

/* Stores:
 * Armories - sell guns, ammunition, and certain armors
 * Electronics Stores - sell disks, software, components, and computers
 * Tool Stores - sell utilities, civilian gear and 
 * Merchant Stops - sell low value items less than 100
 * Groceries - sell food and exclusive utilities (small medical bottles, medicines)
 * 
 * 
 * Money can only be received through doing certain jobs.
 */

public abstract class Item extends Entity{
	
	protected Rectangle bounds;
	protected String itemName, itemDesc;
	protected item_states classify;
	protected TextureRegion inventoryTexture;
	protected int price; 
	
	public static enum item_states {GUN,MELEE,AMMO,COMPONENT,MEDIA,GEAR};   
	/* GUN - relating to all shooty - shooty weapons
	 * MELEE - relating to all melee weapons
	 * AMMO - relating to all ammo types the player can use
	 * COMPONENT - relating to electronic components the player can pick up and use
	 * MEDIA - relating to programs on portable media (tapes, disks)
	 * GEAR - relating to gear that can be worn (armor, masks, pendants).
	 * 
	 */
	
	public boolean ifObjectClicked() {
		if (ifMouseTouches()) {
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public String getItemName() {
		return itemName;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public item_states getClassify() {
		return classify;
	}

	public void setClassify(item_states classify) {
		this.classify = classify;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public boolean pickedUp;
	
	public Item(float xPos, float yPos, boolean pick) {
		super(xPos, yPos);
		classif = states.QUEST;
		bounds = new Rectangle(0,0,16,16);
		this.pickedUp = pick;
		
	}
	
	public void use(float x, float y) {
		
	}
	
	public void use(float x, float y, float dirLength, float playerX, float playerY) {
		//FOR BULLET USAGE ONLY
	}
	
	public void use(float x, float y, Inventory inv) {
		
	}

	@Override
	public abstract void tick();

	
	@Override
	public abstract void render(SpriteBatch batch);
	
	
	public TextureRegion getInventoryTexture() {
		return inventoryTexture;
	}

	public void setInventoryTexture(TextureRegion inventoryTexture) {
		this.inventoryTexture = inventoryTexture;
	}

	public void addInventoryCollisions() {
		for (Entity e: EntityManager.getEntityList()) {
			if (bounds.overlaps(e.getBounds())) {
				pickedUp = true;
//				if (e.getId() == 001) {
//					//Player p = (Player) e;
//					p.addToInventory(this);
//					
//				}
			}
		}
		
	}
	
	public void followMouse() {
		
		if (Gdx.input.isKeyPressed(Input.Buttons.LEFT) && bounds.contains(Gdx.input.getX(), Gdx.input.getY())) {
			
			float widthMouse = Gdx.input.getX() - bounds.width, heightMouse = Gdx.input.getY() - bounds.height; //calculate distance between mouse and bounding rectangle position;
			
			x = Gdx.input.getX() - widthMouse;
			y = Gdx.input.getY() - heightMouse;
		}
	}
	
}
