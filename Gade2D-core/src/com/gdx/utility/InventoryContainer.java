package com.gdx.utility;

import com.gdx.items.Item;

public class InventoryContainer {

	private boolean stackable;
	private Item item;
	
	public InventoryContainer(Item i, boolean stack) {
		this.item = i;
		this.stackable = stack;
	}
	
}
