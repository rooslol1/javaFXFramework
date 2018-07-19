package gamesource.collidables;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;


public class Player extends GameObject
{
	
	public double xPrevious = 0;
	public double yPrevious = 0;
	
		
	public Player(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
	}
	
	
	@Override
	public void Update()
	{

		// REMEMBER PREVIOUS POSITION FIRST:
		xPrevious = x;
		yPrevious = y;
		
		
		// THEN APPLY NEW POSITION:
		if (Input.KEYS_PRESSED.contains("A"))
		{
			x -= 5;
		}
		else if (Input.KEYS_PRESSED.contains("D"))
		{
			x += 5;
		}
		
		if (Input.KEYS_PRESSED.contains("W"))
		{
			y -= 5;
		}
		else if (Input.KEYS_PRESSED.contains("S"))
		{
			y += 5;
		}
		
		
		// ADJUST ROTATION:
		if (Input.KEYS_PRESSED.contains("LEFT"))
		{
			// ROTATE IMAGE:
			setRotation(getRotation() - 5);
		}
		else if (Input.KEYS_PRESSED.contains("RIGHT"))
		{
			// ROTATE IMAGE:
			setRotation(getRotation() + 5);
		}
		
		
		
		// ADJUST SCALE:
		if (Input.MOUSE_PRESSED_LB)
		{
			scaleX += 0.01;
			scaleY += 0.01;
		}
		else
		{
			// CONDITIONAL ASSIGNMENT, WORKS AS FOLLOWS.
			// VALUE = CONDITION (if-statement) ? VALUE WHEN TRUE : VALUE WHEN FALSE;
			scaleX -= scaleX > 1 ? 0.01 : 0;
			scaleY -= scaleY > 1 ? 0.01 : 0;
		}
		
		
		
		// CHECK COLLISION WITH WALLS:
		if (Helper.CheckCollisionRectangle(this, Wall.class) != null)
		{
			// SET TO PREVIOUS POSITION WHEN A WALL IS HIT:
			x = xPrevious;
			y = yPrevious;
		}
		
	}
	
	
	@Override
	public void Draw(GraphicsContext gc)
	{
		
		// EXTRA SHADOW EFFECT:
		gc.setFill(Color.rgb(0, 0, 0, 0.5));
		gc.fillOval(x, y + (getHeight() * 0.9), getWidth(), 50 * scaleY);
		
		
		// ORIGINAL DRAWING METHOD FROM THE INHERITED GAMEOBJECT CLASS:
		super.Draw(gc);
		
	}
	
	
}
