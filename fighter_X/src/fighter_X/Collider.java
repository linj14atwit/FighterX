package fighter_X;

import javafx.scene.shape.Rectangle;

/**
 * @author James Yu Lin
 * Collider class
 * 
 */
public abstract class Collider extends Rectangle{
	static Collider[] colliders = {};
	
	public boolean collidesWith(Collider c) {
		if(!canCollide(c)) return false;
		if(
			((
				(this.getX())<(c.getX() + c.getWidth()) &&
				(this.getX())>(c.getX())
			) ||
			(
				(this.getX()+this.getWidth())<(c.getX() + c.getWidth()) &&
				(this.getX()+c.getWidth())>(c.getX())
			)) &&
			((
				(this.getY())<(c.getY() + c.getHeight()) &&
				(this.getY())>(c.getY())
			) ||
			(
				(this.getY()+this.getHeight())<(c.getY() + c.getHeight()) &&
				(this.getY()+c.getHeight())>(c.getY())
			))
		)
		return true;
		return false;
	}
	public abstract boolean canCollide(Collider c);
	
}
