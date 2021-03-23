package com.gdx.utility;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.items.Item;
import com.gdx.items.Item.item_states;
import com.gdx.items.Pistol;
import com.gdx.entity.Player;

public class Inventory {

	public static ArrayList<Item> inventory; // array of generic inventory items
	public static ArrayList<Item> readyslots;

	public static final int INVENTORY_SPACE = 12; // 20 items can be stored in
													// the inventory
	public final int TOP = 0, BOTTOM = 10;
	public int cursory = 0;
	private TextManager tm;
	private String empty = "You have nothing on you";

	public Inventory(Player p) { // inventory requires player

		inventory = new ArrayList<Item>();
		readyslots = new ArrayList<Item>();

		tm = new TextManager(12);
	}

	public String toString() {
		String build = "{";
		for (int i = 0; i < readyslots.size(); i++) {
			Item io = readyslots.get(i);
			build = build + "(" + io.getItemName() + ")";
		}
		return build + "}";
	}

	public void render(SpriteBatch batch, float f, float g) {
		if (!inventory.isEmpty()) {
			tm.drawTextNormal(batch, Color.GREEN, "=>", f - 48, g - (cursory * 12) - 12);

			TextureRegion selectedTexture = inventory.get(cursory).getInventoryTexture(); 

			batch.draw(selectedTexture, (f - 48), (g + 32) - 16, selectedTexture.getRegionWidth(),
					selectedTexture.getRegionHeight());

			for (int i = 0; i < inventory.size(); i++) {
				Item ito = inventory.get(i);
				tm.drawTextNormal(batch, Color.GREEN, ito.getItemName(), f - 24, g - (i * 12) - 12);
			}

			// System.out.println(readyslots.size());

		} else {
			tm.drawTextNormal(batch, Color.GREEN, empty, f - ((empty.length() * 2) / 2), g - 32);
		}
		
		if (!readyslots.isEmpty()) {

			for (int i = 0; i < readyslots.size(); i++) {
				Item ito2 = readyslots.get(i);
				tm.drawTextNormal(batch, Color.ORANGE, ito2.getItemName() + " HAND " + (i + 1), f - 24,
						g - 216 - (i * 12));
			}
		} else {

		}

		tm.drawTextWrapped(batch, Color.GREEN,
				"TIP: To use your Inventory cursor, move the [UP] and [DOWN] arrows on your keyboard. To select an item to use in your hands, press U. To remove items from your (HAND 1, HAND 2), press 1 or 2 respectively.",
				f - (80), g - 250, 450, 250);
	}

	public void itemTick() {
		if (!inventory.isEmpty()) {
			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				item.tick();
			}
		}
		
		if (!readyslots.isEmpty()) {
			for (int i = 0; i < readyslots.size(); i++) {
				Item item2 = readyslots.get(i);
				item2.tick();
			}
		}
		
		
	}
	
	public int findItem(int id) { //code finds weapon in ready slot
		for (int i = 0; i < readyslots.size(); i++) {
			Item item = readyslots.get(i);
			if (item.getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public int findItemInInv(int id) {
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (item.getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public void tick() {

		if (!inventory.isEmpty()) {
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				cursory--;
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
				cursory++;
			}

			if (cursory >= inventory.size()) {
				cursory--;
			} else if (cursory < 0) {
				cursory++;
			}
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.U) && readyslots.size() < 2) {
				Item placed = inventory.get(cursory);

				inventory.remove(cursory);
				readyslots.add(placed);
			}
			
		}else if (inventory.isEmpty()){
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {

				Item removed = readyslots.get(0);
				readyslots.remove(0);
				inventory.add(removed);
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
				Item removed = readyslots.get(readyslots.size() - 1);
				readyslots.remove(readyslots.size() - 1);
				inventory.add(removed);
			}
		}
	}

	public static ArrayList<Item> getInventory() {
		return inventory;
	}

	public static void setInventory(ArrayList<Item> inventory) {
		Inventory.inventory = inventory;
	}

	public static ArrayList<Item> getReadyslots() {
		return readyslots;
	}

	public static void setReadyslots(ArrayList<Item> readyslots) {
		Inventory.readyslots = readyslots;
	}

	public void updateWeapons() {

	}

//	public int getFirstWeaponID() {
//		return weapons.get(0).getId();
//	}
//	
//	public int getSecondWeaponsID() {
//		return weapons.get(1).getId();
//	}

	public int getQuantity(String tag) {
		int count = 0;

		for (Item i : inventory) {
			if (i.getClassify().equals(tag)) {

				count++;
			}
		}

		return count;
	}

	// public static enum item_states {GUN,MELEE,AMMO,COMPONENT,MEDIA,GEAR};

	public void add(Item i) {
		// inventory.add(i);

		inventory.add(i);

		/*
		 * For now, we'll remove this complicated inventory system. All items will
		 * therefore be added into the inventory, regardless of type, to prevent
		 * unneeded complexity.
		 */

//		switch (i.getClassify()) {
//		case GUN:
//			if (weapons.size() < WEP) {
//				weapons.add(i);
//			}
//			break;
//		case MELEE:
//			int total = getQuantity("GUN") + getQuantity("MELEE");
//			if (total <= WEP && inventory.size() < INVENTORY_SPACE) {
//				inventory.add(i);
//			}else {
//				break;
//			}
//			break;
//		case AMMO:
//			if (inventory.size() < INVENTORY_SPACE) {
//				inventory.add(i);
//			}
//			break;
//		case COMPONENT:
//			if (inventory.size() < INVENTORY_SPACE) {
//				inventory.add(i);
//			}
//			break;
//		case MEDIA:
//			if (inventory.size() < INVENTORY_SPACE) {
//				inventory.add(i);
//			}
//			break;
//		case GEAR:
//			if (gear.size() < GEAR_SPACE) {
//				gear.add(i);
//			}
//
//			break;
//		}

	}

}
