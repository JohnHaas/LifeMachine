package gui;

import org.lwjgl.input.*;
import org.lwjgl.opengl.GL11;

import java.awt.Point;
import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

public class Menu extends BasicGameState {

	private String mouseString = "";
	private boolean hoveringStart = false;
	private boolean clickingStart = false;
	private boolean startFading = false;
	private boolean fadeIn = true;
	private float alpha = 1;
	private float titleRot = 0;
	
	private Audio backgroundMusic;
	private Audio startButton;
	private Audio backgroundMusic2;

	private boolean startButtonSound = false;
	private boolean musicStarted = false;

	public Menu(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		try {
			backgroundMusic2 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/IntrigueMan.wav"));

			startButton = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/button-20.wav"));
			backgroundMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/DigitalStream.wav"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		gc.setShowFPS(false);
		Image title = new Image("res/LifeMachine.png");
		Image background = new Image("res/background.png");
		Image startgame50 = new Image("res/startgame50.png");
		Image startgame55 = new Image("res/startgame55.png");

		title.setRotation(titleRot);
		
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		int centerx = Game.screenRes.x / 2;
		int centery = Game.screenRes.y / 2;

		float startgameYpos = (float) (Game.screenRes.y * 0.15) + 150;
		float startgame50Xpos = centerx - startgame50.getWidth() / 2;
		float startgame55Xpos = centerx - startgame55.getWidth() / 2;

		g.drawImage(background, 0, 0);
		g.drawImage(title, centerx - title.getWidth() / 2,
				(float) (Game.screenRes.y * 0.15));

		// if xpos >
		if (hoveringStart)
		//	startgame50.draw(startgame55Xpos, startgameYpos, startgame55.getWidth(), startgame55.getHeight());
			g.drawImage(startgame55, startgame55Xpos, startgameYpos);
		else {
			g.drawImage(startgame50, startgame50Xpos, startgameYpos);
		}

		g.drawString(mouseString, 200, 300);
		
		g.setColor(new Color(0,0,0,alpha));
		
		g.fillRect(0, 0, 2000, 2000);;
		
		
	

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();

		if(!musicStarted){
			backgroundMusic.playAsMusic(.5f, 1f, true);
			musicStarted = true;
		}
		
		
		mouseString = "(" + xpos + ", " + ypos + ")";

		Input input = gc.getInput();
		if (xpos > 650 && xpos < 950 && ypos < 620 && ypos > 560) {
			hoveringStart = true;
			if (input.isMouseButtonDown(0)) {

				hoveringStart = false;
				if(!startButtonSound)
				startButton.playAsSoundEffect(1f, 1f, false);
				startFading = true;
				startButtonSound = true;
				
			}

			

		} else
			hoveringStart = false;
		
		if(fadeIn){
			alpha -= .001;
			
		}
		
		if (alpha <= 0){
			fadeIn = false;
		}
		
		if(startFading){
		alpha += .0005;
		titleRot +=1;
		fadeIn = false;

		}
		if (alpha >= 1){
			startButtonSound = false;
			startFading = false;
			fadeIn = true;
			titleRot = 0;
			backgroundMusic.stop();
			musicStarted = false;
			sbg.enterState(2);
		}
	}

	public int getID() {
		return 0;
	}

}
