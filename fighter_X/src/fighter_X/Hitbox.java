package fighter_X;

import javafx.scene.paint.Color;

/**
 * @author James Yu Lin
 * A hitbox is a collider that will check if it collides with a hurtbox
 * 
 */
public abstract class Hitbox extends Collider{
	
	public Hitbox(double x, double y, double width, double height){
		super(x, y, width, height);
		this.setFill(new Color(0.9, 0.4, 0.2, 0.4));
	}
	@Override
	public boolean canCollide(Collider c) {
		return c instanceof Hurtbox;
	}
	
}
