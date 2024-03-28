package fighter_X;

import javafx.scene.layout.Pane;

/**
 * @author James Yu Lin
 * 
 * Move class is the handler of each move
 * it will spawn the hitbox and check collision with opponent
 * keeps track of frame counts for the startup, active and recovery frames
 */
public class Move {

	private Pane parent;
	private Character opponent;
	private String type;
	private double width, height;
	private double offSetX, offSetY;
	private double damage;
	
	private boolean active;
	private int startup;
	private int duration;//number of active frames
	private int lag;
	private long timer=0;
	private boolean hasTriggered = false;
	
	private Attack now;

	/**
	 * Constructor 
	 * 
	 * @param parent Parent Javafx to draw attack onto
	 * @param opponent The character to check collision with
	 * @param type The type of the move
	 * @param damage How much damage to do
	 * @param duration How long the hitbox lasts
	 * @param width
	 * @param height	
	 * @param offSetX
	 * @param offSetY
	 */
	public Move(Pane parent, Character opponent, String type, double damage, int duration, double width, double height, double offSetX, double offSetY){
		this(parent, opponent, type, damage, 0, duration, 0, width, height, offSetX, offSetY);
	}

	/**
	 * Constructor
	 * 
	 * @param parent Parent Javafx to draw attack onto
	 * @param opponent The character to check collision with
	 * @param type The type of the move
	 * @param damage How much damage to do
	 * @param startup How much frames it takes for the hitbox to spawn
	 * @param duration How long the hitbox lasts
	 * @param lag How much recovery frames the move has
	 * @param width Width of hitbox
	 * @param height Height of hitbox
	 * @param offSetX X position of hitbox relative to character
	 * @param offSetY Y position of hitbox relative to character
	 */
	public Move(Pane parent, Character opponent, String type, double damage, int startup, int duration, int lag, double width, double height, double offSetX, double offSetY){
		this.parent = parent;
		this.opponent = opponent;
		this.startup = startup;
		this.duration = duration;
		this.lag = lag;
		this.damage = damage;
		this.width = width;
		this.height = height;
		this.offSetX = offSetX;
		this.offSetY = offSetY;
	}
	
	/**
	 * spawn the attack with offSet from Character position x, y
	 * @param x
	 * @param y
	 */
	public void doAttack(double x, double y) {
		active = true;
		now = new Attack(type, damage, width, height, x+offSetX, y+offSetY);
		parent.getChildren().add(now);
	}
	
	/**
	 * update the current status of the move
	 * @param deltaTime
	 * @return
	 */
	public Move update(long deltaTime) {
		if(!active) return null;
		if(!hasTriggered) {
			if(now.connect(opponent))hasTriggered=true;
		}
		timer += deltaTime;
		if(timer>(duration*MainStage.FRAME_RATE)) {
			boolean b = parent.getChildren().remove(now);
			now = null;
//			System.out.printf("%s, %d, %d, %b%n", timer, deltaTime, duration*MainStage.FRAME_RATE, b);
			timer = 0;
			active = false;
			hasTriggered = false;
		}
		return this;
	}


}
