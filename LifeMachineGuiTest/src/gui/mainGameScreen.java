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
	
	private boolean timeRunning = true;
	
	private String mouseLocation = "";
	private boolean leftClick = false;
	
	private int screenWidth = Game.screenRes.x;
	private int screenHeight = Game.screenRes.y;
	
	//Proportions for various windows in ratios of screen!
	private Window graphics = new Window(0, 0, .75f, .75f );
	private Window newsBanner = new Window(0, 0, .75f, .04f);
	private Window actions = new Window(graphics.getWidthRatio(), 0, .25f, 1);
	private Window households = new Window( 0, graphics.getHeightRatio(), .50f, .25f);
	private Window time = new Window(households.getWidthRatio(), graphics.getHeightRatio(), .25f, .25f);
	private Window actionsList = new Window( .76f, .1f, .23f, .8f);
	private Window charactersSubwindow = new Window(.02f, .03f, .96f, .94f);

	//these represent the hitboxes for the buttons, will be invisible later
	private Button shop = new Button(.755f, .91f, .115f, .08f);
	private Button menu = new Button(.880f, .91f, .115f, .08f);
	private Button characters = new Button(.755f, .01f, .115f, .08f);
	private Button inventory = new Button(.880f, .01f, .115f, .08f);

	//color change upon hover for debug purposes
	private Color shopButtonColor = Color.black;
	private Color menuButtonColor = Color.black;
	private Color charactersButtonColor = Color.black;
	private Color inventoryButtonColor = Color.black;
	
	private String testNews = "Breaking News: This is a test news string!!";
	private float newsScrollSpeed = .01f;
	private float textpos = 0;
	
	
	//Various subscreens due to button presses
	private boolean charactersScreenOpen;
	
	
	
	
	
	
	
	

	public mainGameScreen(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {



	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		
		
		drawScaledRect(g,graphics, Color.red);
		
		drawScaledRect(g,newsBanner, Color.cyan);
		
		
		//News Banner
		g.setColor(Color.black);
		g.drawString(testNews, .75f * screenWidth + screenWidth - textpos , 10);
		g.drawString(testNews, .75f * screenWidth - textpos, screenHeight * .01f);

		drawScaledRect(g,actions, Color.green);
		
		drawScaledRect(g,time, Color.blue);
		
		drawScaledRect(g,households, Color.orange);
		
		drawScaledRect(g,actionsList, Color.black);
		
		drawScaledRect(g,shop, shopButtonColor);
		
		drawScaledRect(g,menu, menuButtonColor);
		
		drawScaledRect(g,characters, charactersButtonColor);
		
		drawScaledRect(g,inventory, inventoryButtonColor);
		
		if(charactersScreenOpen)
			drawCharacterScreen(g);
		
		
		

		
		g.setColor(Color.white);
		g.drawString(mouseLocation, 100, 100);
		g.drawString(shop.getActiveAreaStartX() + ", " + shop.getActiveAreaEndX(), 100, 200);

		

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
		Input input = gc.getInput();
		
		
		//This is what moves the text across the screen in the news area 
		if(timeRunning)
		updateNewsBanner();
		
		//changes color of hitboxes when mouse is in button region
		updateMouseHoverAreas(input.getMouseX(), input.getMouseY());
		
		if(input.isMouseButtonDown(0))
			leftClick = true;
		
		if (leftClick && !input.isMouseButtonDown(0)){
		
			executeButtonAction(input.getMouseX(), input.getMouseY());
			leftClick = false;
		}
		
		if(!timeRunning)
			if(input.isKeyPressed(input.KEY_ESCAPE))
			{
				charactersScreenOpen = false;
				timeRunning = true;
				
			}
		

	

		
		mouseLocation = "(" + input.getMouseX() + ", " + input.getMouseY() + ")";
		
	}

	public int getID() {
		return 3;
	}
	
	private static void drawScaledRect(Graphics g, Window w, Color c){
		g.setColor(c);
		g.fillRect( w.getWindowStartX(), w.getWindowStartY(), w.getWindowWidth(), w.getWindowHeight());
		
	}
	
	private static void drawScaledRect(Graphics g, Window w){
		
		g.fillRect( w.getWindowStartX(), w.getWindowStartY(), w.getWindowWidth(), w.getWindowHeight());
		
		
	}
	
	private static void drawScaledRect(Graphics g, float x, float y, float w, float h){
		
		g.fillRect(x * Game.screenRes.x, y * Game.screenRes.y, Game.screenRes.x * w, Game.screenRes.y * h);

		
	}
	
	private static void drawScaledRect(Graphics g, float x, float y, float w, float h, Color c){
		
		g.setColor(c);
		g.fillRect(x * Game.screenRes.x, y * Game.screenRes.y, Game.screenRes.x * w, Game.screenRes.y * h);

	}
	
	private static void darkenBackground(Graphics g){
		g.setColor(new Color(0,0,0, .75f));
		g.fillRect(0,0,Game.screenRes.x, Game.screenRes.y);
		
	}
	
	private void updateMouseHoverAreas(float x, float y){
		
		
		if(shop.getActiveAreaStartX() < x  && x < shop.getActiveAreaEndX()
				&& shop.getActiveAreaStartY() < y && y < shop.getActiveAreaEndY() )
			shopButtonColor = Color.blue;
		else
			shopButtonColor = Color.black;
		
		if(menu.getActiveAreaStartX() < x  && x < menu.getActiveAreaEndX()
				&& shop.getActiveAreaStartY() < y && y < menu.getActiveAreaEndY() )
			menuButtonColor = Color.blue;
		else
			menuButtonColor = Color.black;
		
		if(characters.getActiveAreaStartX() < x  && x < characters.getActiveAreaEndX()
				&& characters.getActiveAreaStartY() < y && y < characters.getActiveAreaEndY() )
			charactersButtonColor = Color.blue;
		else
			charactersButtonColor = Color.black;
		
		if(inventory.getActiveAreaStartX() < x  && x < inventory.getActiveAreaEndX()
				&& inventory.getActiveAreaStartY() < y && y < inventory.getActiveAreaEndY() )
			inventoryButtonColor = Color.blue;
		else
			inventoryButtonColor = Color.black;
	}
	
	private void executeButtonAction(float x, float y){
		
		if(characters.getActiveAreaStartX() < x  && x < characters.getActiveAreaEndX()
				&& characters.getActiveAreaStartY() < y && y < characters.getActiveAreaEndY()){
			timeRunning = false;
			charactersScreenOpen = true;
			
		}
		
		
		
		
	}
	
	private void updateNewsBanner(){
		
		textpos += .25;
		if(textpos == screenWidth * 2 + 10*testNews.length() )
			textpos = 0;
	}
	
	
	private void drawCharacterScreen(Graphics g){
		
		
		darkenBackground(g);
		drawScaledRect(g, charactersSubwindow, Color.orange);
		
		
	}
	
	
	

}
