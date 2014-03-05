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

public class TestGameWindow extends BasicGameState {

	private String mouseString = "";

	private float screenX = Game.screenRes.x;
	private float screenY = Game.screenRes.y;
	private float rotation = 0;
	private int daysPassed;
	private float sky = 255;
	private float timebar = 0;
	private boolean daytime = true;
	private float alphaStars = 0;
	private boolean paused = false;
	
	//Things from pause.
	
	private boolean startFading = false;
	private boolean fadeIn = true;
	private float alpha = 1;
	
	// pause menu coords and dimensions

	private float pauseStartX = Game.screenRes.x / 3;
	private float pauseStartY = Game.screenRes.y / 5;
	private float pauseWidth = Game.screenRes.x / 3;
	private float pauseHeight = Game.screenRes.y / 1.5f;

	// pause text coords
	private float pauseInitialTextHeight = Game.screenRes.y / 5 + 20;
	private float pauseTextSpacing = 130;

	// box for selecting pause item
	private float optionHeight = pauseTextSpacing;

	private Audio pauseButtons;

	private boolean played1 = false;
	private boolean played2 = false;
	private boolean played3 = false;
	private boolean played4 = false;
	private boolean played5 = false;

	private Audio buttonClickSound;
	private Audio backgroundMusic;
	private boolean backgroundMusicStarted = false;
	private boolean pauseButtonClicked = false;
	
	
	
	
	
	public TestGameWindow(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		try {

			backgroundMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/IntrigueMan.wav"));
			buttonClickSound = AudioLoader.getAudio("WAV",
					ResourceLoader.getResourceAsStream("res/button-20.wav"));

			pauseButtons = AudioLoader.getAudio("WAV",
					ResourceLoader.getResourceAsStream("res/button-19.wav"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		int centerx = Game.screenRes.x / 2;
		int centery = Game.screenRes.y / 2;
		
		Image cave = new Image("res/cave.png");
		Image title = new Image("res/pressEscape.png");
		Image pause = new Image("res/pause.png");
		Image resume = new Image("res/pauseResume.png");
		Image options = new Image("res/pauseOptions.png");
		Image save = new Image("res/pauseSave.png");
		Image load = new Image("res/pauseLoad.png");
		Image quit = new Image("res/pauseQuit.png");
		Image daynight = new Image("res/daynight.png");
		Image moon = new Image("res/moon.png");
		Image sun = new Image("res/sun.png");
		Image village = new Image("res/cartoon_village.png");
		Image star = new Image("res/star.png");
		
		
		cave = cave.getFlippedCopy(true, false);

		g.setBackground(Color.black);
		g.setColor(new Color(0, 0,(int) sky));
		

		// Viewoing Area
		g.fillRoundRect(2, 2, screenX * .75f, screenY * .65f, 10);
		
		
		
		star.setAlpha(alphaStars);
		star.draw(100,100,25,25);
		star.draw(342,130,25,25);
		star.draw(207,250,25,25);
		star.draw(488,50,25,25);
		star.draw(640,308,25,25);
		star.draw(800,200,25,25);
		star.draw(938,26,25,25);
		star.draw(1080,130,25,25);
		
		sun.setCenterOfRotation(sun.getCenterOfRotationX() - 100, sun.getCenterOfRotationY()+320);
		sun.setRotation(rotation);
		sun.draw(540, 30, 100, 100);
		
		moon.setCenterOfRotation(moon.getCenterOfRotationX() - 100, moon.getCenterOfRotationY()+300);
		moon.setRotation(rotation + 180);
		moon.draw(540, 30, 100, 100);

		
		

		g.setColor(Color.gray);
		// Buttons
		g.fillRoundRect((float) (screenX * .75 + 5f), 2f, .25f * screenX - 7,
				screenY - 4, 10);

		// time etc
		g.fillRoundRect(2, .65f * screenY + 5, .75f * screenX,
				.35f * screenY , 10);

		g.setColor(Color.green);

		g.fillRect(0, 375, 1206, 222);
		cave.draw(-150,280,500,300);
		//village.draw(10, 115, 1192, 472);

		g.setColor(Color.white);

//		 daynight.setCenterOfRotation(daynight.getCenterOfRotationX() - 200,
//		 daynight.getCenterOfRotationY() - 200);
//		 daynight.setRotation(rotation);
//		 daynight.draw(980, 365,200,200);
//
//		 g.setColor(Color.black);
//		 g.fillArc(976, 360, 210, 210, 315, 225);
//		g.setColor(Color.black);
//		 g.drawOval(1030,415,100,100);
//		 g.setColor(Color.black);
//		 g.fillOval(1030, 415, 100, 100);

		g.fillRect(75, 50, 750, 3);
		g.fillRect(75 + timebar, 40, 10, 23);
		g.fillRect(75, 30, 3, 43);
		g.fillRect(75 + 750, 30, 3, 43);

		g.drawString("Days Passed: " + daysPassed, 100, 100);
		
		
		// THIS IS WHERE TEH BUTTONS ON TEH GUI START!
		
		g.setColor(Color.black);
		//g.drawRoundRect(x, y, width, height, cornerRadius);
		
		
		
		
		
		
		
		if (paused) {

			g.setColor(new Color(0, 0, 0, 0.8f));
			g.fillRoundRect(pauseStartX, pauseStartY, pauseWidth, pauseHeight,
					10);

			g.setColor(new Color(0, 0, 255, .4f));
			g.fillRect(pauseStartX, pauseInitialTextHeight - 7 + optionHeight,
					pauseWidth, resume.getHeight() + 14);

			g.drawImage(pause, centerx - pause.getWidth() / 2,
					pauseInitialTextHeight);
			g.drawImage(resume, centerx - resume.getWidth() / 2,
					pauseInitialTextHeight + pauseTextSpacing * 1);
			g.drawImage(options, centerx - options.getWidth() / 2,
					pauseInitialTextHeight + pauseTextSpacing + 70);
			g.drawImage(save, centerx - save.getWidth() / 2,
					pauseInitialTextHeight + pauseTextSpacing + 140);
			g.drawImage(load, centerx - load.getWidth() / 2,
					pauseInitialTextHeight + pauseTextSpacing + 210);
			g.drawImage(quit, centerx - quit.getWidth() / 2,
					pauseInitialTextHeight + pauseTextSpacing + 280);

		}
		
		g.setColor(new Color(0, 0, 0, alpha));

		g.fillRect(0, 0, 2000, 2000);
		;

		g.setColor(Color.black);
		
		
		

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if (!backgroundMusicStarted){
			backgroundMusic.playAsMusic(1f, 1f, true);
			backgroundMusicStarted = true;
		}
		
		if(!paused){
		if (daytime) {
			sky -= .1f;
			
			if (sky <= 0){
				daytime = false;
				

			}
		}
		
		alphaStars =  1 -sky/255 *2;

		if(!daytime){
			sky +=.1;
			if (sky >= 255){
				daytime = true;
			
				rotation = 0;
			}
			}
		
		rotation += .0705f;
		timebar += .001;
		if (rotation >= 359) {
			daysPassed++;
			rotation = 0;
		}
		
		}
		
		
		Input input = gc.getInput();

		int xpos = Mouse.getX();
		int ypos = Mouse.getY();

		// mouseString = "(" + xpos + ", " + ypos + ")";

		if (!paused && input.isKeyPressed(input.KEY_ESCAPE)) {

			paused = true;
		}

		if (paused && input.isKeyPressed(input.KEY_ESCAPE)) {
			paused = false;
		}

		if (paused) {

			if (xpos > pauseStartX && xpos < pauseStartX + pauseWidth
					&& ypos < 574 && ypos > 524) {
				optionHeight = pauseTextSpacing;

				played2 = false;
				played3 = false;
				played4 = false;
				played5 = false;

				if (!played1)

					pauseButtons.playAsSoundEffect(1f, 1f, false);

				played1 = true;

				if (input.isMouseButtonDown(0)) {
					paused = false;
					if (!pauseButtonClicked)
						buttonClickSound.playAsSoundEffect(1f, 1f, false);

				}
			}

			if (xpos > pauseStartX && xpos < pauseStartX + pauseWidth
					&& ypos < 574 - 70 && ypos > 524 - 70) {

				optionHeight = pauseTextSpacing + 70;

				played1 = false;
				played3 = false;
				played4 = false;
				played5 = false;

				if (!played2)
					pauseButtons.playAsSoundEffect(1f, 1f, false);
				played2 = true;

				if (input.isMouseButtonDown(0)) {

					if (!pauseButtonClicked)
						buttonClickSound.playAsSoundEffect(1f, 1f, false);

					pauseButtonClicked = true;

				}

			}

			if (xpos > pauseStartX && xpos < pauseStartX + pauseWidth
					&& ypos < 574 - 140 && ypos > 524 - 140) {
				optionHeight = pauseTextSpacing + 140;
				played2 = false;
				played1 = false;
				played4 = false;
				played5 = false;

				if (!played3)
					pauseButtons.playAsSoundEffect(1f, 1f, false);
				played3 = true;
				
				if (input.isMouseButtonDown(0)) {

					if (!pauseButtonClicked)
						buttonClickSound.playAsSoundEffect(1f, 1f, false);

					pauseButtonClicked = true;

				}

			}

			if (xpos > pauseStartX && xpos < pauseStartX + pauseWidth
					&& ypos < 574 - 210 && ypos > 524 - 210) {
				optionHeight = pauseTextSpacing + 210;

				played2 = false;
				played1 = false;
				played3 = false;
				played5 = false;

				if (!played4)
					pauseButtons.playAsSoundEffect(1f, 1f, false);
				played4 = true;

				if (input.isMouseButtonDown(0)) {

					if (!pauseButtonClicked)
						buttonClickSound.playAsSoundEffect(1f, 1f, false);

					pauseButtonClicked = true;

				}
				
			}

			if (xpos > pauseStartX && xpos < pauseStartX + pauseWidth
					&& ypos < 574 - 280 && ypos > 524 - 280) {
				optionHeight = pauseTextSpacing + 280;

				played2 = false;
				played1 = false;
				played4 = false;
				played3 = false;

				if (!played5)
					pauseButtons.playAsSoundEffect(1f, 1f, false);
				played5 = true;

				if (input.isMouseButtonDown(0)) {
					paused = false;
					startFading = true;
				}
				
				
				if (input.isMouseButtonDown(0)) {

					if (!pauseButtonClicked)
						buttonClickSound.playAsSoundEffect(1f, 1f, false);

					pauseButtonClicked = true;

				}

			}
			if (!input.isMouseButtonDown(0))
				pauseButtonClicked = false;

		}

		// else{pauseButtonClicked = false;}

		if (fadeIn) {
			alpha -= .001;

		}

		if (alpha <= 0)
			fadeIn = false;

		if (startFading) {

			fadeIn = false;
			alpha += .001;
		}
		if (alpha >= 1) {
			startFading = false;
			fadeIn = true;
			backgroundMusicStarted = false;
			sbg.enterState(0);
		}
		
		
		
		

	}

	public int getID() {
		return 2;
	}

}
