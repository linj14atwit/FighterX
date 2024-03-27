package fighter_X;

/**
 * @author James Yu Lin
 * 
 * Class Attack extends Hitbox
 * 
 * An Attack is a Hitbox with attribute type
 * that determine the properties of the attack
 * 
 * Attack also will be dealing damage to the hurtbox it collides with
 */
public class Attack extends Hitbox{

	public static final String GRAB = "GRAB";
	public static final String AIR = "AIR";
	public static final String LOW = "LOW";
	public static final String HIGH = "HIGH";
	public static final String NORMAL = "N";
	private String type = NORMAL;
	
	private double damage;
	
	/**
	 * Constructor 
	 * 
	 * @param type
	 * @param damage
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public Attack(String type, double damage, double width, double height, double x, double y){
		super(x, y, width, height);
		this.type = type;
		this.damage = damage;
	}
	
	/**
	 * deal damage if this collides with character c
	 * @param c
	 * @return
	 */
	public boolean connect(Character c){
		if(this.collidesWith(c)){
			c.takeDamage(damage);
			return true;
		}
		return false;
	}
	
	/**
	 * collides depending on type property
	 */
	@Override
	public boolean canCollide(Collider c) {
		if(super.canCollide(c)) {
			Hurtbox hb = (Hurtbox) c;
			return !hb.getInvinc() &&
				(this.isGrab() && hb.getGrabable()) ||
				(this.isAir() && !hb.getAirInvinc());
		}
		return false;
	}
	
	/**
	 * returns if attack is a grab
	 * @return
	 */
	public boolean isGrab(){
		return type.equals(GRAB);
	}
	
	/**
	 * returns if attack is an aerial
	 * @return
	 */
	public boolean isAir(){
		return type.equals(AIR);
	}
	
	/**
	 * returns if attack is a low
	 * @return
	 */
	public boolean isLow(){
		return type.equals(LOW);
	}
	
	/**
	 * returns if attack is an overhead
	 * @return
	 */
	public boolean isHigh(){
		return type.equals(HIGH);
	}


}
