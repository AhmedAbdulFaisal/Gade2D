package com.gdx.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.utility.Assets;
import com.gdx.utility.DebugHUD;
import com.gdx.utility.Inventory;

/* HOW TO RELOAD IN STEPS
 * 1) Collect a Magazine from the environment
 * 2) Open your inventory and place it in your hands (while you have another gun ready to use)
 * 3) Use the Magazine, your pistol will have it's previous mag disposed and a new one inserted
 */

public class PistolMag extends Item{

	public final int AMMO = 11;
	
	public int getAMMO() {
		return AMMO;
	}

	public PistolMag(float xPos, float yPos, boolean pick) {
		super(xPos, yPos, pick); 
		classify = item_states.AMMO;
		
		id = 006;
		itemName = "10MM magazine 7 rounds";
		itemDesc = "A 10MM magazine with 7 bullets inside.";
		inventoryTexture = Assets.magazine10mm;
		
		bounds = new Rectangle(xPos,yPos,(WIDTH * 3) * 2,(HEIGHT * 2) * 2);
	}

	@Override
	public void use(float x, float y, Inventory i) {
		int pistolslot = i.findItem(005);
		Pistol newP = (Pistol) i.getReadyslots().get(pistolslot);
		newP.load(this);
		i.getReadyslots().set(pistolslot, newP);
		i.getReadyslots().remove(this);
		DebugHUD.setText("You hastily load a clip (" + AMMO + " ROUNDS)");
		
	}

	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}
