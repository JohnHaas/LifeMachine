package gui;

import java.awt.Point;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

public class Testscreen1 extends BasicGameState {

	private String mouseString = "";
	private boolean hoveringStart = false;
	private boolean clickingStart = false;
	private boolean startFading = false;
	private boolean fadeIn = true;
	private float alpha = 1;
	private boolean paused = false;

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
	private boolean pauseButtonClicked = false;

	public Testscreen1(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		try {
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

		gc.setShowFPS(false);

		g.setBackground(Color.white);

		Image title = new Image("res/pressEscape.png");
		Image pause = new Image("res/pause.png");
		Image resume = new Image("res/pauseResume.png");
		Image options = new Image("res/pauseOptions.png");
		Image save = new Image("res/pauseSave.png");
		Image load = new Image("res/pauseLoad.png");
		Image quit = new Image("res/pauseQuit.png");

		
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		int centerx = Game.screenRes.x / 2;
		int centery = Game.screenRes.y / 2;

		g.drawImage(title, centerx - title.getWidth() / 2,
				(float) (Game.screenRes.y / 2 - title.getHeight()));

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

		g.drawString(mouseString, 100, 100);

		// if xpos >

		g.setColor(new Color(0, 0, 0, alpha));

		g.fillRect(0, 0, 2000, 2000);
		;

		g.setColor(Color.black);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

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

			sbg.enterState(0);
		}

	}

	public int getID() {
		return 1;
	}

}
