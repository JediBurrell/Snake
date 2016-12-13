package com.jediburrell.snake.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import io.qwerty.neo.framework.ObjectID;
import io.qwerty.neo.objects.GameObject;

public class A extends GameObject{
	
	public A(float x, float y, float size) {
		super(x, y, ObjectID.a);
		
		this.width = this.height = size;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public void onCollision(GameObject arg0, ObjectID arg1) {
	}

	@Override
	public void render(Graphics arg0) {
		arg0.setColor(Color.WHITE);
		
		arg0.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	
	
}
