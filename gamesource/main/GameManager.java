package gamesource.main;


import java.util.*;

import gamesource.collidables.*;

import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;


public class GameManager
{
	
	private Pane _mainPane;
	private Canvas _mainCanvas;
	private GraphicsContext _context;
	
	/* 
	 * CHANGE THE LOCATION INTO WHERE YOU WILL STORE YOUR GAME IMAGES:
	 * 
	 * 		"file:src/../../Art/EnemyBlob.png"
	 * 
	 * This location goes 2 steps up from the "src" [ROOT_FOLDER] folder, then goes into the Art folder where the image is located.
	 * Change the location as instructed below:
	 * 
	 * 		[ROOT]		= Name of the root folder (where the "MainApplication.java" is located).
	 * 		[LOCATION]	= The location RELATIVE to where the root folder is.
	 * 		[FILENAME]	= The name of the image file INCLUDING the file extension.
	 * 
	 * 		"file:[ROOT]/[LOCATION]/[FILENAME]"
	 * 
	 * Example:
	 * 
	 * 		"file:MyGame/../ArtFiles/MySprite.png"
	 * 
	 */
	private String _imageLocation = "EnemyBlob.png";
	
	
	// PROPERTY GAME OBJECT LIST:
	private ArrayList<GameObject> _gameObjList = new ArrayList<GameObject>();
	public ArrayList<GameObject> getGameObjectsList()
	{
		return _gameObjList;
	}
	
	
	public GameManager(Pane paneReference)
	{
		_mainPane = paneReference;
		_mainCanvas = (Canvas)_mainPane.getChildren().get(0);
		_context = _mainCanvas.getGraphicsContext2D();
		
		
		Helper.SetGameManagerReference(this);
		
		
		InitializeGame();
	}
	
	
	public void Update()
	{
		// UPDATE ALL THE GAME OBJECTS:
		if (_gameObjList != null)
		{
			for (int i = 0; i < _gameObjList.size(); i++)
			{
				_gameObjList.get(i).Update();
			}
		}
	}
	
	
	public void Draw()
	{
		// DRAW ALL THE GAME OBJECTS:
		if (_gameObjList != null)
		{
			for (int i = 0; i < _gameObjList.size(); i++)
			{
				_gameObjList.get(i).Draw(_context);
			}
		}
	}
	
	
	public void DrawGUI()
	{
		// DRAW INTERFACE HERE:
	}
	
	
	
	
	// PRIVATE FUNCTIONS, INTERNAL GAME STRUCTURE:
	private void InitializeGame()
	{
		Player myPlayer = new Player(300, 200);
		myPlayer.setSprite(new Image(_imageLocation));
		
		// ADD PLAYER TO LIST OF OBJECTS TO UPDATE AND DRAW:
		_gameObjList.add(myPlayer);
		
		
		
		GameObject test = new GameObject(300, 300);
		test.setSprite(new Image(_imageLocation));
		
		_gameObjList.add(test);
		
		
		Wall wallTest = new Wall(50, 50);
		_gameObjList.add(wallTest);
		
		
	}
	
	
	
	
	// PUBLIC FUNCTIONS:
	public GameObject CheckCollisionRectangle(GameObject objA, Class type)
	{
		for (GameObject objB : _gameObjList)
		{
			// FIRST CHECK IF IT'S THE TYPE OF OBJECT WE'RE LOOKING FOR:
			if (type.isInstance(objB))
			{
				// CHECK FOR COLLISION:
				if (
						objA.getBBoxLeft() <= objB.getBBoxRight() &&
						objA.getBBoxRight() >= objB.getBBoxLeft() &&
						objA.getBBoxTop() <= objB.getBBoxBottom() &&
						objA.getBBoxBottom() >= objB.getBBoxTop()
					)
				{
					// THERE IS COLLISION! RETURN THE OBJECT THAT HAS COLLISION WITH THE GIVEN OBJECT:
					// (The method stops executing after that.)
					return objB;
				}
			}
		}
		
		return null;
	}
	
	
}
