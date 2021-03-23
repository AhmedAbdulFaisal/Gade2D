package com.gdx.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static final int WIDTH = 16, HEIGHT = 16;
	
	public static Texture player, collision_debug, fortTiles, caveprops1, caveprops2, gui1, gui2, inventory, invItems, itemsWorld, FX;
	public static TextureRegion player_standing, player_shadow, red_outline_square, blue_outline_square, green_outline_square, grey_outline_square, crate_top, crate_side, stonebrick, stonetile;
	public static TextureRegion Title, WindowS, WindowL, black;
	
	public static TextureRegion bulletShot, rocketShot;
	
	public static TextureRegion doorm, doormclosed, doormopen;
	public static Animation<TextureRegion> openDoor, closedDoor;
	
	public static TextureRegion inv, pistol, pistol_world, boxC, boxO, bag, magazine10mm, lmgammo, rocketlauncher, plasmarifle, lmg, computer, datareader;
	
	public static Pixmap CURSOR_PASSIVE;
	public static Texture CURSOR_INTERACTIVE;
	public static TextureRegion cursor_int;
	
	//defined player texture animations
	
	public static Animation<TextureRegion> pwalkLR, pwalkU, pwalkD, plproneLR, plproneU, plproneD, pslide;
	public static TextureRegion up, leftright, pdown, pup, pleftright;
	public static TextureRegion[] gundirections, pgundirections; 
	//defined window edges for custom windows
	
	public static TextureRegion WindowULC, WindowURC, WindowLLC, WindowLRC, WindowLe, WindowR, WindowU, WindowD;
	//0.075f
	public static Animation<TextureRegion> createAnimation(int x, int y, Texture tex, int flength, float time) {
		TextureRegion[] anim = new TextureRegion[flength];
		
		for (int i = 0; i < anim.length; i++) {
			anim[i] = new TextureRegion(tex, x + (i * WIDTH),y, WIDTH, HEIGHT);
		}
		
		return new Animation<TextureRegion>(time, anim);
	}
	
	public static Animation<TextureRegion> createAnimationVert(int x, int y, Texture tex, int flength, float time) {
		TextureRegion[] anim = new TextureRegion[flength];
		
		for (int i = 0; i < anim.length; i++) {
			anim[i] = new TextureRegion(tex, x,y + (i * HEIGHT), WIDTH, HEIGHT);
		}
		
		return new Animation<TextureRegion>(time, anim);
	}
	
	public static void create() {
		//MOUSE CURSORS
		
		CURSOR_PASSIVE = new Pixmap(Gdx.files.internal("textures/cursor_01.png"));
		CURSOR_INTERACTIVE = new Texture(Gdx.files.internal("textures/cursor_02.png"));
		cursor_int = new TextureRegion(CURSOR_INTERACTIVE, 0, 0, 32, 32);
		//initialize TEXTURES
		player = new Texture("textures/TEXTURESplayer_01.png");
		collision_debug = new Texture("textures/entity_collision.png");
		fortTiles = new Texture("worlds/texturesFORTRESS_01.png");
		caveprops2 = new Texture("textures/texturecaveprop_02.png");
		gui1 = new Texture("textures/gui_01.png");
		inventory = new Texture("textures/utilitybrief_02.png");
		invItems = new Texture("textures/itemsinventory_01.png");
		itemsWorld = new Texture("textures/texturesitems_01.png");
		FX = new Texture("textures/texturesFX_04.png");
		
		//effects 
		
		bulletShot = new TextureRegion(FX,0,0,WIDTH,HEIGHT);
		
		//initialize TEXTURE REGIONS
		
		Title = new TextureRegion(gui1, 0,0,WIDTH * 8,HEIGHT * 2);
		black = new TextureRegion(gui1, WIDTH * 8,0,WIDTH,HEIGHT); 
		
		//gun directions
		
		gundirections = new TextureRegion[8];
		
		gundirections[0] = new TextureRegion(player, 2 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
		gundirections[1] = new TextureRegion(player, 2 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
		gundirections[2] = new TextureRegion(player, 2 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		gundirections[3] = new TextureRegion(player, 2 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		gundirections[4] = new TextureRegion(player, 3 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
		gundirections[5] = new TextureRegion(player, 3 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
		gundirections[6] = new TextureRegion(player, 3 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		gundirections[7] = new TextureRegion(player, 3 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		
		pgundirections = new TextureRegion[3];
		
		pgundirections[0] = new TextureRegion(player, 4 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
		pgundirections[1] = new TextureRegion(player, 5 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
		pgundirections[2] = new TextureRegion(player, 6 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
		
		//initialize playerRUNSIDE animations
		
		pwalkLR = createAnimation(0, 4 * HEIGHT, player, 7, 0.075f);
		pwalkU = createAnimationVert(0, 0, player, 3, 0.075f);
		pwalkD = createAnimationVert(7 * WIDTH, 0 , player, 3, 0.075f);
		plproneLR = createAnimation(WIDTH * 4, HEIGHT, player, 2, 0.5f);
		plproneU = createAnimation(WIDTH * 4, HEIGHT * 3, player, 2, 0.5f);
		plproneD = createAnimation(WIDTH * 4, HEIGHT * 2, player, 2, 0.5f); 
		
		up = new TextureRegion(player, WIDTH * 7, 0, WIDTH, HEIGHT);
		leftright = new TextureRegion(player, 0, HEIGHT * 5, WIDTH, HEIGHT);
		pdown = new TextureRegion(player, WIDTH * 4, HEIGHT * 2, WIDTH, HEIGHT);
		pup = new TextureRegion(player, WIDTH * 4, HEIGHT * 3, WIDTH, HEIGHT);
		pleftright = new TextureRegion(player, WIDTH * 4, HEIGHT, WIDTH, HEIGHT);
		
		//define window border textures
		
		WindowULC = new TextureRegion(gui1, 0, HEIGHT * 2,WIDTH,HEIGHT);
		WindowURC = new TextureRegion(gui1, WIDTH * 5, HEIGHT * 2,WIDTH,HEIGHT);
		WindowLLC = new TextureRegion(gui1, WIDTH * 0, HEIGHT * 4,WIDTH,HEIGHT);
		WindowLRC = new TextureRegion(gui1, WIDTH * 5, HEIGHT * 4,WIDTH,HEIGHT);
		
		WindowLe = new TextureRegion(gui1, WIDTH * 0, HEIGHT * 3,WIDTH,HEIGHT);
		WindowR = new TextureRegion(gui1, WIDTH * 5, HEIGHT * 3,WIDTH,HEIGHT);
		WindowU = new TextureRegion(gui1, WIDTH * 1, HEIGHT * 2,WIDTH,HEIGHT);
		WindowD = new TextureRegion(gui1, WIDTH * 1, HEIGHT * 4,WIDTH,HEIGHT);
		
		inv = new TextureRegion(inventory,0,0,inventory.getWidth(), inventory.getHeight());
		
		//ANIMATION SLIDING DOOR 001
		doormclosed = new TextureRegion(caveprops2, 0 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		doormopen = new TextureRegion(caveprops2, 5 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		
		TextureRegion[][] tmp = TextureRegion.split(caveprops2, 16, 16); //split into 2d array
		
		TextureRegion[] openD = new TextureRegion[6];
		
		for (int i = 0; i < 6; i++) {
			openD[i] = tmp[1][i];
		}
		
		TextureRegion[] closeD = new TextureRegion[6];
		
		for (int i = 0; i < 6; i++) {
			closeD[i] = tmp[1][5 - i];
		}
		
		openDoor = new Animation<TextureRegion>(0.075f, openD);
		closedDoor = new Animation<TextureRegion>(0.075f, closeD);
		
		//END
		
		
		//INVENTORY ITEMS
		
		computer = new TextureRegion(invItems, 0, HEIGHT * 4, WIDTH * 5, HEIGHT * 3);
		datareader = new TextureRegion(invItems, 0, HEIGHT * 7, WIDTH * 3, HEIGHT * 3);
		pistol = new TextureRegion(invItems,0,0,WIDTH * 3, HEIGHT * 2);
		magazine10mm = new TextureRegion(invItems, 0 * WIDTH, HEIGHT * 2, WIDTH, HEIGHT * 2);
		lmg = new TextureRegion(invItems, 19 * WIDTH, HEIGHT * 0, WIDTH * 8, HEIGHT * 2);
		lmgammo = new TextureRegion(invItems, 1 * WIDTH, 2 * HEIGHT, WIDTH * 2, HEIGHT);
		pistol_world = new TextureRegion(itemsWorld, 0, 0, WIDTH, HEIGHT);
		
		boxO = new TextureRegion(itemsWorld, WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
		boxC = new TextureRegion(itemsWorld, 0, HEIGHT * 2, WIDTH, HEIGHT);
		bag = new TextureRegion(itemsWorld, WIDTH * 2, HEIGHT * 2, WIDTH, HEIGHT);
		
		player_standing = new TextureRegion(player, 0, 0, WIDTH, HEIGHT);
		
		player_shadow = new TextureRegion(player,5 * WIDTH,5 * HEIGHT,WIDTH, HEIGHT);
		
		red_outline_square = new TextureRegion(collision_debug,0 * WIDTH,0 * HEIGHT,WIDTH, HEIGHT);
		blue_outline_square = new TextureRegion(collision_debug,1 * WIDTH,0 * HEIGHT,WIDTH, HEIGHT);
		green_outline_square = new TextureRegion(collision_debug,2 * WIDTH,0 * HEIGHT,WIDTH, HEIGHT);
		grey_outline_square = new TextureRegion(collision_debug,3 * WIDTH,0 * HEIGHT,WIDTH, HEIGHT);
		
		crate_side = new TextureRegion(caveprops2, 0,HEIGHT * 2,WIDTH, HEIGHT);
		crate_top = new TextureRegion(caveprops2, WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
		
		stonebrick = new TextureRegion(fortTiles, 0,0,WIDTH, HEIGHT);
		stonetile = new TextureRegion(fortTiles, WIDTH, 0, WIDTH, HEIGHT);
	}
}
