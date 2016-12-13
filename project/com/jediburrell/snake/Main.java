package com.jediburrell.snake;

import com.jediburrell.snake.scene.GameScene;

import io.qwerty.neo.Neo;
import io.qwerty.neo.Window;
import io.qwerty.neo.exceptions.UnchangableException;

public class Main extends Neo{

	public Main(Window window) {
		super(window);
		
		this.scene = new GameScene(this);
		this.scene.onLoad();
	}
	
	public static void main(String[] args) throws UnchangableException{
		
		Window window = new Window("Snake");
		window.setSize(1000, 600);
		window.setResizable(false);
		
		Main main = new Main(window);
		window.setContent(main);
		window.start();
		main.start();
		
	}

}
