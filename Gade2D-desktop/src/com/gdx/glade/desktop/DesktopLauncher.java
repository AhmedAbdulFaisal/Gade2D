package com.gdx.glade.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.gade.Gade;
import com.gdx.utility.InfoParse;

public class DesktopLauncher {
	
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		
		/* Start PARAMETER LOADING from CFG
		 * 
		 * 
		 */
		
		
		
		
		InfoParse ip = new InfoParse("config.cfg");
		
		
		config.title = "Gade engine ver2.0";
		
		config.width = ip.parseInt(0);
		config.height = ip.parseInt(1);
		config.fullscreen = ip.parseBool(2);
		config.resizable = ip.parseBool(3);
		
		
		/* End LOADING here
		 * 
		 */
		
		new LwjglApplication(new Gade(), config);
	}
}
