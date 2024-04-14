package fighter_X;

import javafx.scene.shape.Rectangle;

/**
 * @author James Yu Lin
 * Collider class extends Javafx Rectangle
 * 
 * a collider is a rectangle that can check if it overlaps(collides)
 * with another collider
 */
public abstract class Collider extends Rectangle{
	
	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Collider(double x, double y, double width, double height){
		super(x, y, width, height);
	}
	
	/**
	 * If this Collider collides with collider c
	 * 
	 * @param Collider c
	 * @return if this collider and c overlaps
	 */
	public boolean collidesWith(Collider c) {

		return this.intersects(c.getBoundsInLocal());
//		if(!this.canCollide(c)) return false;
//		if(
//			((
//				(this.getX())<(c.getX() + c.getWidth()) &&
//				(this.getX())>(c.getX())
//			) ||
//			(
//				(this.getX()+this.getWidth())<(c.getX() + c.getWidth()) &&
//				(this.getX()+c.getWidth())>(c.getX())
//			)) &&
//			((
//				(this.getY())<(c.getY() + c.getHeight()) &&
//				(this.getY())>(c.getY())
//			) ||
//			(
//				(this.getY()+this.getHeight())<(c.getY() + c.getHeight()) &&
//				(this.getY()+c.getHeight())>(c.getY())
//			))
//		)
//		return true;
//		return false;

		if(!this.canCollide(c)) return false;
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
	
	/**
	 * returns if this and Collider c can interact
	 * @param c
	 * @return if two this and Collider c can interact
	 */
	public abstract boolean canCollide(Collider c);
	
}
