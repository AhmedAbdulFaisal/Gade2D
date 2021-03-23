package com.gdx.utility;

import java.io.FileWriter;
import java.io.IOException;

import com.gdx.entity.Entity;

public class SaveWorld {

	private FileWriter scanner;
	
	public SaveWorld(String inputFile) throws IOException {
		scanner = new FileWriter(inputFile);
		
	}
	
	/* FORMAT OF A SAVE FILE
	 * <ID,XPOS,YPOS>
	 * <ID,XPOS,YPOS,NUM>
	 * <ID,XPOS,YPOS,BOOLEAN>
	 * <ID,XPOS,YPOS,ARRAYLIST>
	 * 
	 */
	
	//we write positions and id values of each entity.
	//certain entities have special cases, and will be noted
	
	@SuppressWarnings("static-access")
	public void writeToFile(EntityManager entitymanager) throws IOException {
		for (int i = 0; i < entitymanager.getEntityList().size(); i++) {
			Entity e = entitymanager.getEntityList().get(i);
			if (e.getId() != 1) {
				scanner.write("<" + e.getId() + "," + e.getX() + "," + e.getY() + "> ");
			}else if (e.getId() == 1) { //special for bounding boxes
				scanner.write("<" + e.getId() + "," + e.getX() + "," + e.getY() + "," + e.getBounds().getWidth() + "," + e.getBounds().getHeight() + "> ");
			}
		}
	}

}
