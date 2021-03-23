package com.gdx.items;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/* This class manages inventory items, that are to not be confused with normal items.
 * 
 * Inventory items are items collected from storage units in the game world and are then collected in the players inventory.
 * They have their own method for rendering to the inventory.
 */

//public abstract class InventoryItem extends Item{
//
//	protected Rectangle bounds;
//	
//	private boolean pickedUp; //toggle on when player touches it
//	
//	public InventoryItem(float xPos, float yPos) {
//		super(xPos, yPos);
//		bounds = new Rectangle(0,0,16,16);
//	}
//	
//	public abstract void tick();
//
//}
