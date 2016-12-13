package com.jediburrell.snake.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.jediburrell.snake.XY;
import com.jediburrell.snake.scene.GameScene;

import io.qwerty.neo.Neo;
import io.qwerty.neo.framework.Handler;
import io.qwerty.neo.framework.ObjectID;
import io.qwerty.neo.objects.GameObject;

public class SnakeObject extends GameObject{

	public LinkedList<XY> segments = new LinkedList<XY>();
	
	private int tick = 0;
	
	private Neo neo;
	private Handler handler;
	
	public SnakeObject(float x, float y, float size, Neo neo, Handler handler) {
		super(x, y, ObjectID.player);
		this.width = this.height = size;
		segments.add(new XY((int) x, (int) y, 1, 1));
		this.neo = neo;
		this.handler = handler;
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public void onCollision(GameObject arg0, ObjectID arg1) {
		if(arg0==this)
			return;
		
		if(arg1==ObjectID.a){
			segments.add(new XY(segments.getLast().x, segments.getLast().y, 1, segments.getLast().id+1));
			handler.addObject(new A(new Random().nextInt(neo.width())*25/25, new Random().nextInt(neo.height())*25/25, 25));
			handler.removeObject(arg0);
		}
	}

	@Override
	public void render(Graphics arg0) {
		arg0.setColor(Color.WHITE);
		
		for(XY pos : segments)
			arg0.fillRect(pos.x, pos.y, (int) width, (int) height);
	}
	
	@Override
	public void tick(LinkedList<GameObject> objects) {
		super.tick(objects);
		
		if(tick==10){
			for(int pos = segments.size()-1; pos > -1; pos--){
				XY xy = segments.get(pos);
				if(pos == 0)
					switch(xy.rot){
					
						case 0:
							xy.y -= height;
							break;
						case 1:
							xy.x += width;
							break;
						case 2:
							xy.y += height;
							break;
						case 4:
							xy.x -= width;
							break;
					
					}
				else{
					xy.x = segments.get(pos-1).x;
					xy.y = segments.get(pos-1).y;
				}
			}
			
			this.x = segments.getFirst().x;
			this.y = segments.getFirst().y;
			
			tick = 0;
			
			Rectangle head = new Rectangle(segments.getFirst().x, segments.getFirst().y, (int) width, (int) height);
			
			int i = 0;
			
			for(XY xy : segments){
				if(i==0){
					i++;
					continue;
				}else{
					i++;
				}
				Rectangle body = new Rectangle(xy.x, xy.y, (int) width, (int) height);
				if(head.intersects(body)){
					neo.scene = new GameScene(neo);
					neo.scene.onLoad();
				}
			}
			
			if(x>neo.width()||y>neo.height()||x<0||y<0){
				neo.scene = new GameScene(neo);
				neo.scene.onLoad();
			}
		}
		
		tick++;
	}
	
}
