package gui;

public class Button extends Window {

	private boolean isActive;

	private float activeAreaStartX;
	private float activeAreaStartY;
	private float activeAreaEndX;
	private float activeAreaEndY;

	public Button(float x, float y, float w, float h) {

		isActive = true;
		
		startRatioX = x;
		startRatioY = y;
		widthRatio = w;
		heightRatio = h;

		activeAreaStartX = this.getWindowStartX();
		activeAreaStartY = this.getWindowStartY();
		activeAreaEndX = this.getWindowEndX();
		activeAreaEndY = this.getWindowEndY();

	}

	public Button() {

		isActive = false;
		
		startRatioX = 0;
		startRatioY = 0;
		widthRatio = 0;
		heightRatio = 0;
		
		activeAreaStartX = 0;
		activeAreaStartY = 0;
		activeAreaEndX = 0;
		activeAreaEndY = 0;
		

	}
	
	
	public float getActiveAreaStartX(){
		return activeAreaStartX;
	}
	
	public float getActiveAreaStartY(){
		return activeAreaStartY;
	}
	
	public float getActiveAreaEndX(){
		return activeAreaEndX;
	}
	
	public float getActiveAreaEndY(){
		return activeAreaEndY;
	}
	
	
		
		
		
	
	

}
