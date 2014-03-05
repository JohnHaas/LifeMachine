package gui;

public class Window {
	
	protected float widthRatio;
	protected float heightRatio;
	protected float startRatioX;
	protected float startRatioY;
	
	
	
	
	
	
	
	public Window(){
		
		widthRatio = 0;
		heightRatio = 0;
		startRatioX = 0;
		startRatioY = 0;
	}
	
	public Window(float x, float y, float w, float h){
		
		startRatioX = x;
		startRatioY = y;
		widthRatio = w;
		heightRatio = h;
		
		
		
		
	}
	
	public float getWidthRatio(){
		return widthRatio;
		
	}
	
	public float getHeightRatio(){
		return heightRatio;
	}
	
	public float getStartXRatio(){
		
		return startRatioX;
	}
	
	public float getStartYRatio(){
		return startRatioY;
	}
	
	
	public void setWidthRatio(float x){
		widthRatio = x;
	}
	
	public void setHeightRatio(float x){
		heightRatio = x;
	}
	
	public void setStartXRatio(float x){
		startRatioX = x;
	}
	
	public void setStartYRatio(float x){
		startRatioY = x;
	}
	
	public float getWindowWidth(){
		
		return Game.screenRes.x * widthRatio;
	}
	
	public float getWindowHeight(){
		
		return Game.screenRes.y * heightRatio;
	}
	
	public float getWindowStartX(){
		return Game.screenRes.x * startRatioX;
	}
	
	public float getWindowStartY(){
		return Game.screenRes.y * startRatioY;
	}
	
	public float getWindowEndX(){
		return this.getWindowWidth() + this.getWindowStartX();
	}
	
	public float getWindowEndY(){
		return this.getWindowHeight() + this.getWindowStartY();
		
	}
	
	
	
	
	

}
