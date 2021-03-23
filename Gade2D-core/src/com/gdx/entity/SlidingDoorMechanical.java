package com.gdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.entity.Entity.states;
import com.gdx.entity.Player;
import com.gdx.utility.Assets;
import com.gdx.utility.EntityManager;

/* Player needs to press [OPEN] (Or click on them) to open the door
 * 
 */

public class SlidingDoorMechanical extends StaticEntity{ //Press [E] to use

	private boolean shut;
	
	private float stateTimeOpen, stateTimeClosed;
	
	
	public SlidingDoorMechanical(float xPos, float yPos, int t) {
		super(xPos, yPos);
		classif = states.METAL;
		stateTimeOpen = 0;
		stateTimeClosed = 0;
		
		id = 3;
		teritiary = t;
		bounds = new Rectangle();
		bounds.set(x, y, FDW, FDW);
		
		desc = "You see a mechanical sliding door. You can simply interact with the door to open it.";
	}

	@Override
	public void tick() {
		getClickMessage(); 
		if ((Gdx.input.isKeyPressed(Buttons.LEFT) && (EntityManager.getPlayer().getClickDistance() <= Player.INTERACTDISTANCE)) && !shut) {
			shut = true;
		}else if ((Gdx.input.isKeyPressed(Buttons.LEFT) && checkMouseCollisions() && (EntityManager.getPlayer().getClickDistance() <= Player.INTERACTDISTANCE)) && shut) {
			shut = false;
		}
		//System.out.println(shut);
	}

	public void open(SpriteBatch batch) {
		batch.draw(Assets.doormopen, getX(), getY(), FDW, FDW);
	}
	
	public void close(SpriteBatch batch) {
		batch.draw(Assets.doormclosed, getX(), getY(), FDW, FDW);
	}
	
	@Override
	public void render(SpriteBatch batch) {
	
		if (shut == true && (stateTimeOpen == 0 && stateTimeClosed == 0)) { //some really complex and confusing code that I have NO fucking clue on how it works, but I'll annotate it
			
			batch.draw(Assets.doormclosed, getX(), getY(), FDW, FDW); //draw closed door if shut == true and all times are equal to zero
			
		}else if (shut == false) { //if shut it false, then open the door?
			
			stateTimeOpen += Gdx.graphics.getDeltaTime();
			TextureRegion DoorO = Assets.openDoor.getKeyFrame(stateTimeOpen, false); //for every frame, convert it into a textureRegion (kinda brilliant when you think about it)
			batch.draw(DoorO, getX(), getY(), FDW, FDW);
			
			if (Assets.closedDoor.isAnimationFinished(stateTimeClosed)) { //if animation is finished, then set it to zero for future use.
				stateTimeClosed = 0;
			}
			
		}else if (shut == true) {
			
			stateTimeClosed += Gdx.graphics.getDeltaTime();
			TextureRegion DoorC = Assets.openDoor.getKeyFrame(stateTimeClosed, false);
			
			batch.draw(DoorC, getX(), getY(), FDW, FDW);
			
			if (Assets.closedDoor.isAnimationFinished(stateTimeOpen)) {
				stateTimeOpen = 0;
			}
			
		}
	}

}
