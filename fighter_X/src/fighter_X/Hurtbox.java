package fighter_X;

public class Hurtbox extends Collider{
	
	private boolean isGrabable;
	private boolean isAirInvinc;
	private boolean isInvinc;
	
	public Hurtbox(double x, double y, double width, double height){
		super(x, y, width, height);
	}

	@Override
	public boolean canCollide(Collider c) {
		return false;
	}
	
	/**
	 * setGrabable
	 * @param b
	 */
	public void setGrabable(boolean b) {
		isGrabable = b;
	}
	
	/**
	 * setairInvinc
	 * @param b
	 */
	public void setAirInvinc(boolean b) {
		isAirInvinc = b;
	}

	/**
	 * setInvinc
	 * @param b
	 */
	public void setInvinc(boolean b) {
		isInvinc = b;
	}
	
	/**
	 * @return isGrabable
	 */
	public boolean getGrabable() {
		return isGrabable;
	}

	/**
	 * @return isAirInvinc
	 */
	public boolean getAirInvinc() {
		return isAirInvinc;
	}

	/**
	 * @return isInvinc
	 */
	public boolean getInvinc() {
		return isInvinc;
	}
}
