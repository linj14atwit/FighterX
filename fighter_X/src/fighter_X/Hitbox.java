package fighter_X;

/**
 * @author James Yu Lin
 * A hitbox is a collider that will check if it collides with a hurtbox
 * 
 */
public abstract class Hitbox extends Collider{
	
	public Hitbox(double x, double y, double width, double height){
		super(x, y, width, height);
	}
	@Override
	public boolean canCollide(Collider c) {
		return c instanceof Hurtbox;
	}
	
}
