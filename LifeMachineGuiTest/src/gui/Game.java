package gui;

import java.awt.Point;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	
	public static final String gamename = "LifeMachine";
	public static final int menu = 0;
	public static final int testscreen1 = 1;
	public static final int testgamewindow = 2;
	public static final int mainGameScreen = 3;
	public static Point screenRes = new Point(1600,900);
	
	public Game(String gamename){
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Testscreen1(testscreen1));
		this.addState(new TestGameWindow(testgamewindow));
		this.addState(new mainGameScreen(mainGameScreen));
		
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(testscreen1).init(gc, this);
		this.getState(testgamewindow).init(gc,this);
		this.getState(mainGameScreen).init(gc,this);
		this.enterState(mainGameScreen);
		
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(screenRes.x, screenRes.y, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}

	}

}
