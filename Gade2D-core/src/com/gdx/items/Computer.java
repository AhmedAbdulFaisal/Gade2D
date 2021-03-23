package com.gdx.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.utility.Assets;
import com.gdx.utility.Inventory;

public class Computer extends Item{

	public Computer(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick);
		classify = item_states.GEAR;
		id = 012;
		itemName = "C-Tech Praxis DataComputer";
		itemDesc = "(SPECS: 16Mhz, 64KB of RAM, Variable Bandwith and Antenna Strength)A Multi-purpose computer specifically built to transmit programs to nearby reviecers in your general vicinity. It has an upgradable signal strength and bandwith. Specifically, with a C-Tech Computer you can send programs to overload nearby computers, and view disks. if you have the skills, you can upgrade bandwith and antennae strength, thus allowing you to transmit higher tier software.";
		inventoryTexture = Assets.computer;
		
		bounds = new Rectangle(xPos, yPos, (WIDTH * 3) * 2, (HEIGHT * 2) * 2);
		price = 2500; //computers can only be brought at electronics stores
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	public void use(Inventory i) {
		
	}


}
