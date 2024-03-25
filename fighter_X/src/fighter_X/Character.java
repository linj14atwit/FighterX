package fighter_X;

import javafx.scene.input.KeyCode;
public class Character extends Hurtbox{
	
	private int hp;
	private int ground;
	
	private int facing_left;
	private int move_direction;
	private double speed;
	
	public Character(double x, double y, double width, double height) {
		this(x, y, width, height, 40);
	}
	
	public Character(double x, double y, double width, double height, int ground) {
		super(x, y, width, height);
		this.ground = ground;
		hp = 100;
		
		facing_left = -1; //1 for right, -1 for left
		move_direction = 0; //1 for right, 0 for idle, -1 for left
		
		this.setOnKeyPressed(e->{
			switch (e.getCode()) {
			case A:
				move_direction = -1;
				
				break;
			case D:
				move_direction = 1;
				
				break;
			default:
				break;
			}
		});
		this.setOnKeyReleased(e->{
			switch(e.getCode()) {
			case A:
				move_direction = move_direction==-1 ? 0 : move_direction; 
				
				break;
			case D:
				move_direction = move_direction==1 ? 0 : move_direction; 
				
				break;
			default:
				break;
			}
		});
	}
	
	
	public void update() {
		this.setX(this.getX()+5*move_direction);
	}

	
	public void takeDamage(double d) {
		hp -= d;
	}
}
