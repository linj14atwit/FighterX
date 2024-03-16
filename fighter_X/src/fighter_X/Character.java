package fighter_X;

public class Character {
	
	private Hurtbox hb;
	private int hp;
	
	public Hurtbox getHurtbox() {
		return this.hb;
	}
	
	public void takeDamage(double d) {
		hp -= d;
	}
}
