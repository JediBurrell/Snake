package com.jediburrell.snake.scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.jediburrell.snake.XY;
import com.jediburrell.snake.object.A;
import com.jediburrell.snake.object.SnakeObject;

import io.qwerty.neo.Neo;
import io.qwerty.neo.scene.Scene;

public class GameScene extends Scene{

	private Neo neo;
	private SnakeObject snake;
	
	public GameScene(Neo neo) {
		super(neo);
		
		this.neo = neo;
	}
	
	@Override
	public void onLoad() {
		handler.addObject(new SnakeObject(25, 25, 25, neo, handler));
		snake = (SnakeObject) handler.object.getLast();
		handler.addObject(new A(new Random().nextInt(neo.width())*25/25, new Random().nextInt(neo.height())*25/25, 25));
	}

	@Override
	public void render(Graphics arg0) {
		handler.render(arg0);
	}
	
	@Override
	public boolean onKeyPressed(KeyEvent e) {
		int r = 1;
		
		if(e.getKeyCode()==KeyEvent.VK_W)
			r = 0;
		else if(e.getKeyCode()==KeyEvent.VK_S)
			r = 2;
		else if(e.getKeyCode()==KeyEvent.VK_D)
			r = 1;
		else if(e.getKeyCode()==KeyEvent.VK_A)
			r = 4;
		
		snake.segments.getFirst().rot = r;
		
		return super.onKeyPressed(e);
	}

}
