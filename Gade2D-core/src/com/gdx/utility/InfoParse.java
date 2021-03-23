package com.gdx.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* InfoParse.class parses a text/cfg file for values and then returns an array with the list of values allocated.
 * 
 * Basis on how config files work:
 * 
 * All variables in the game which are to be used shall be denoted with an "="
 * for instance, this:
 * WINDOW_WIDTH=1280
 * 
 * this parse object supports booleans,integers, and strings.
 * 
 */

public class InfoParse {
	//initialize variables
	private BufferedReader reader;
	private File file;
	private String path;
	
	public InfoParse(String p) {
		this.path = p;
		file = new File(path);
		
		
		
		
	}
	
	/* Parse methods - feed in a line, return the variable at that line
	 * 
	 */
	
	public int parseInt(int line) {
		
		int linecount=0;
		int token;
		
		String text = null; //the line
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((text = reader.readLine()) != null) {
					if (linecount==line) {
						if (isInteger(text.substring(text.indexOf("=") + 1))) {
							token=Integer.parseInt(text.substring(text.indexOf("=") + 1));
							return token;
						}else {
							System.out.println("Error: cannot parse string to int! (Numberformat) [ line" + linecount + " " + text + "]");
						}
					}
					linecount++;
					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
public boolean parseBool(int line) {
		
		int linecount=0;
		boolean token;
		
		String text = null; //the line
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((text = reader.readLine()) != null) {
					if (linecount==line) {
						token=isBoolean(text.substring(text.indexOf("=") + 1));
						return token;
					}
					linecount++;
					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isBoolean(String s) {
		if (s.equals("true")) {
			return true;
		}else if (s.equals("false")){
			return false;
		}else{
			return false;
			
		}
	}
	
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);

			// s is a valid integer

			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	

}
