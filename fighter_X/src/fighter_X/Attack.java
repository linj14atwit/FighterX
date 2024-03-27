package fighter_X;

public class Attack extends Hitbox{

	public static final String GRAB = "GRAB";
	public static final String AIR = "AIR";
	public static final String LOW = "LOW";
	public static final String HIGH = "HIGH";
	public static final String NORMAL = "N";
	private String type = NORMAL;
	
	private double damage;

	public Attack(String type, double damage, double width, double height, double x, double y){
		super(x, y, width, height);
		this.type = type;
		this.damage = damage;
	}
	
	public void connect(Character c){
		if(this.collidesWith(c)){
			c.takeDamage(damage);
		}
	}
	
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

	public boolean isGrab(){
		return type.equals(GRAB);
	}

	public boolean isAir(){
		return type.equals(AIR);
	}
	
	public boolean isLow(){
		return type.equals(LOW);
	}

	public boolean isHigh(){
		return type.equals(HIGH);
	}


}
