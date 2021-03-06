package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;
	Game game;
	
	
	public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		
		//movement
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown())velY = 0;
		
		if(handler.isDown()) velY = 5;
		else if(!handler.isUp())velY = 0; 
		
		if(handler.isRight()) velX = 5;
		else if(!handler.isLeft())velX = 0;
		
		if(handler.isLeft()) velX = -5;
		else if(!handler.isRight())velX = 0;
		
	}
	
	private void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			
			if(tempObject.getId() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
			
			if(tempObject.getId() == ID.AmmoCrate) {
				if(getBounds().intersects(tempObject.getBounds())) {
					game.ammo += 10;
					handler.removeObject(tempObject);
				}
			}
			
			if(tempObject.getId() == ID.Enemy_1) {
				if(getBounds().intersects(tempObject.getBounds())) {
					game.hp--;
				}
			}
			
			if(tempObject.getId() == ID.HealthBox) {
				if(getBounds().intersects(tempObject.getBounds())) {
					
					if(game.hp < 100) {
						
						for(int z = 0; z < 5; z++) {
							
							game.hp += z;
						}
						handler.removeObject(tempObject);
							
					}
					
				}
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 48);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 48);
	}

}
