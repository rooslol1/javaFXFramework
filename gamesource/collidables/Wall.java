package gamesource.collidables;

import gamesource.main.*;
import javafx.scene.image.*;


public class Wall extends GameObject
{
	
	public Wall(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image("WallSprite.png"));
		
		scaleX = 5;
		scaleY = 5;
	}
	
	
}
