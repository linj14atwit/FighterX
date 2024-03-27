package fighter_X;

import javafx.scene.paint.Color;

/**
 * @author James Yu Lin
 * 
 * Class Hitbox extends Collider
 * A hitbox is a collider that will check if it collides with a hurtbox
 */
public abstract class Hitbox extends Collider{
	
	/**
	 * Constructor 
	 * hitbox is set to color pink
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Hitbox(double x, double y, double width, double height){
		super(x, y, width, height);
		this.setFill(new Color(1.0, 0.0, 0.4, 0.4));
	}
	
	/**
	 * only collides with Hurtboxes
	 */
	@Override
	public boolean canCollide(Collider c) {
		return c instanceof Hurtbox;
	}
	
}
