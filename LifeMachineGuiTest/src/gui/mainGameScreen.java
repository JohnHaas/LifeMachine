package gui;

import java.awt.Point;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

public class mainGameScreen extends BasicGameState {
	
	private int screenWidth = Game.screenRes.x;
	private int screenHeight = Game.screenRes.y;
	
	//Proportions for various windows in ratios of screen!
	private float graphicsWindowWidthRatio = .75f;
	private float graphicsWindowHeightRatio = .75f;
	private float graphicsWindowStartX = 0;
	private float graphicsWindowStartY = 0;
	
	private float newsBannerWindowWidthRatio = .75f;
	private float newsBannerWindowHeightRatio = .04f;
	private float newsBannerWindowStartX = 0;
	private float newsBannerWindowStartY = 0;
	
	private float actionWindowWidthRatio = .25f;
	private float actionWindowHeightRatio = 1;
	private float actionWindowStartX = graphicsWindowWidthRatio;
	private float actionWindowStartY = .0f;
	
	private float householdWindowWidthRatio = .50f;
	private float householdWindowHeightRatio = .25f;
	private float householdWindowStartX = 0;
	private float householdWindowStartY = graphicsWindowHeightRatio;
	
	private float timeWindowWidthRatio = .25f;
	private float timeWindowHeightRatio = .25f;
	private float timeWindowStartX = householdWindowWidthRatio;
	private float timeWindowStartY = graphicsWindowHeightRatio;
	
	private float actionsListWidthRatio = .23f;
	private float actionsListHeightRatio = .8f; 
	private float actionsListStartX = .76f;
	private float actionsListStartY = .1f;
	
	private float shopButtonWidthRatio = .115f;
	private float shopButtonHeightRatio = .08f;
	private float shopButtonStartX = .755f;
	private float shopButtonStartY = .91f;
	
	private float menuButtonWidthRatio = .115f;
	private float menuButtonHeightRatio = .08f;
	private float menuButtonStartX = .880f;
	private float menuButtonStartY = .91f;
	
	private float characterButtonWidthRatio = .115f;
	private float characterButtonHeightRatio = .08f;
	private float characterButtonStartX = .755f;
	private float characterButtonStartY = .01f;
	
	private float inventoryButtonWidthRatio = .115f;
	private float inventoryButtonHeightRatio = .08f;
	private float inventoryButtonStartX = .880f;
	private float inventoryButtonStartY = .01f;
	
	private String testNews = "Breaking News: This is a test news string!!";
	private float newsScrollSpeed = .01f;
	private float textpos = 0;
	
	
	
	
	
	
	
	

	public mainGameScreen(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {



	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		
		
		drawScaledRect(g,graphicsWindowStartX, graphicsWindowStartY, graphicsWindowWidthRatio, graphicsWindowHeightRatio, Color.red);
		
		drawScaledRect(g,newsBannerWindowStartX, newsBannerWindowStartY, newsBannerWindowWidthRatio, newsBannerWindowHeightRatio, Color.cyan);
		
		g.setColor(Color.black);
		g.drawString(testNews, .75f * screenWidth + screenWidth - textpos , 10);
		
		g.drawString(testNews, .75f * screenWidth - textpos, screenHeight * .01f);

		
		drawScaledRect(g,actionWindowStartX, actionWindowStartY, actionWindowWidthRatio, actionWindowHeightRatio, Color.green);
		
		drawScaledRect(g,timeWindowStartX, timeWindowStartY, timeWindowWidthRatio, timeWindowHeightRatio, Color.blue);
		
		drawScaledRect(g,householdWindowStartX, householdWindowStartY, householdWindowWidthRatio, householdWindowHeightRatio, Color.orange);
		
		drawScaledRect(g,actionsListStartX, actionsListStartY, actionsListWidthRatio, actionsListHeightRatio, Color.black);

		drawScaledRect(g,shopButtonStartX, shopButtonStartY, shopButtonWidthRatio, shopButtonHeightRatio, Color.black);

		drawScaledRect(g,menuButtonStartX, menuButtonStartY, menuButtonWidthRatio, menuButtonHeightRatio, Color.black);

		drawScaledRect(g,characterButtonStartX, characterButtonStartY, characterButtonWidthRatio, characterButtonHeightRatio, Color.black);

		drawScaledRect(g,inventoryButtonStartX, inventoryButtonStartY, inventoryButtonWidthRatio, inventoryButtonHeightRatio, Color.black);
		
	
		
		

		

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		textpos += .25;
		if(textpos == screenWidth * 2 + 10*testNews.length() )
			textpos = 0;

	

	}

	public int getID() {
		return 3;
	}
	
	private static void drawScaledRect(Graphics g, float x, float y, float w, float h){
		
		g.fillRect(x * Game.screenRes.x, y * Game.screenRes.y, Game.screenRes.x * w, Game.screenRes.y * h);

		
	}
	
	private static void drawScaledRect(Graphics g, float x, float y, float w, float h, Color c){
		
		g.setColor(c);
		g.fillRect(x * Game.screenRes.x, y * Game.screenRes.y, Game.screenRes.x * w, Game.screenRes.y * h);

		
		
	}
	
	

}
