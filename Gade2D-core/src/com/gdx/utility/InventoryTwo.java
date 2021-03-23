package com.gdx.utility;

import java.awt.Point;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.items.Item;
import com.gdx.entity.Player;

/* Scrapped inventory that MIGHT be added in the far future... For now the code stays unused.
 * 
 */

//public class InventoryTwo {
//
//	public ArrayList<Item> inventory;
//	public static final int INVENTORY_WIDTH = 224, INVENTORY_HEIGHT = 224;
//	public static final int INVENTORY_AREA = INVENTORY_WIDTH * INVENTORY_HEIGHT;
//	
//	public static Rectangle inventB;
//	
//	public Player playerLink;
//	private int cursor = 0;
//	
//	public InventoryTwo(Player p) {
//		this.playerLink = p;
//		inventB = new Rectangle(0,0,INVENTORY_WIDTH,INVENTORY_HEIGHT);
//	}
//	
//	public void render(SpriteBatch batch, int xPos, int yPos) {
//		
//	}
//	
//	public float getConsumedArea() {
//		float summation = 0;
//		for (InventoryItem i: inventory) {
//			summation += i.getBounds().area();
//		}
//		
//		return summation;
//	}
//	
//	public boolean checkAvailableSpace(InventoryItem e) {
//		if (getConsumedArea() < INVENTORY_AREA) {
//			return true;
//		}else {
//			return false;
//		}
//	}
//	
//	//not too sure if this way is even efficient at all, but it should work...
//	
//	public Point getItemLocation(InventoryItem e) {
//		for (int x = 0; x < 224 - e.getBounds().getHeight(); x +=1) {
//			for (int y = 0; x < 224 - e.getBounds().getWidth(); x+=1) {
//				for (InventoryItem i: inventory) {
//	
//					Rectangle newRect = new Rectangle(x, y, e.getBounds().getWidth(), e.getBounds().getWidth());
//					
//					if (i.getBounds().overlaps(newRect)) {
//						continue;
//					}else {
//						return new Point(x,y);
//					}
//				}
//			}
//		}
//		return null; //no space found!
//	}
//	
//	public Rectangle findNewItemLocation(InventoryItem e) {
//		Point xy = getItemLocation(e);
//		
//		return new Rectangle(xy.x, xy.y, e.getBounds().width, e.getBounds().height);
//	}
//	
//	
//	public void tick() {
//	}
//
//	public void collect(InventoryItem i) {
//		inventory.add(i);
//	}
//	
//	public void remove(Item i) {
//		inventory.remove(i);
//	}
//	
//	public ArrayList<InventoryItem> getInventory() {
//		return inventory;
//	}
//
//	public void setInventory(ArrayList<InventoryItem> inventory) {
//		this.inventory = inventory;
//	}
//
//	public Player getPlayerLink() {
//		return playerLink;
//	}
//
//	public void setPlayerLink(Player playerLink) {
//		this.playerLink = playerLink;
//	}
	
	
	
//}
