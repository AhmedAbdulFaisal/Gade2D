package com.gdx.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class GameInput {

	public static Vector2 KeyForce = new Vector2();
	
	public static int ppf = 2;
	
	public static void Update() {
		
		KeyForce.x = 0;
		KeyForce.y = 0;
		
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			KeyForce.x -= ppf * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			KeyForce.x += ppf * Gdx.graphics.getDeltaTime();
		}
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			KeyForce.y -= ppf * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			KeyForce.y += ppf * Gdx.graphics.getDeltaTime();
		}
		
		
	}
	
}
