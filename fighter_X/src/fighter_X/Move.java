package fighter_X;

import javafx.scene.layout.Pane;

/**
 * @author James Yu Lin
 */
public class Move {

	private Pane parent;
	private String type;
	private double width, height;
	private double offSetX, offSetY;
	private double damage;
	
	private boolean active;
	private int duration;//number of active frames
	private long timer=0;
	
	private Attack now;

	public Move(Pane parent, String type, double damage, int duration, double width, double height, double offSetX, double offSetY){
		this.parent = parent;
		this.duration = duration;
		this.damage = damage;
		this.width = width;
		this.height = height;
		this.offSetX = offSetX;
		this.offSetY = offSetY;
	}
	
	public void doAttack(double x, double y) {
		active = true;
		now = new Attack(type, damage, width, height, x+offSetX, y+offSetY);
		parent.getChildren().add(now);
	}
	
	public void update(long deltaTime) {
		if(!active) return;
		timer += deltaTime;
		if(timer>(duration*MainStage.FRAME_RATE)) {
			boolean b = parent.getChildren().remove(now);
			now = null;
//			System.out.printf("%s, %d, %d, %b%n", timer, deltaTime, duration*MainStage.FRAME_RATE, b);
			timer = 0;
			active = false;
		}
	}


}
