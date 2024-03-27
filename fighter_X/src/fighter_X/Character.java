package fighter_X;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Character extends Hurtbox{
	
	private Pane parent; 
	private int hp;
	
	private double standing_height;
	private double crouch_height;
	private double ground;
	
	private int facing_left;
	private int move_direction;
	
	private double speed;
	
	private Move current = null;
	private boolean is_crouched=false;
	private Move light;
	
	public Character(Pane parent, double x, double y, double width, double height) {
		this(parent, x, y, width, height, y+height);
		standing_height = height;
		crouch_height = this.getHeight()*0.5;
	}
	
	public Character(Pane parent, double x, double y, double width, double height, double ground) {
		super(x, y, width, height);
		this.ground = ground;
		hp = 1000;
		
		facing_left = -1; //1 for right, -1 for left
		move_direction = 0; //1 for right, 0 for idle, -1 for left
		
		light = new Move(parent, Attack.NORMAL, 30, 12, 60, 50, 30, 10);
		
		//inputs
		this.setOnKeyPressed(e->{
			switch (e.getCode()) {
			case A:
				move_direction = -1;
				break;
			
			case S:
				is_crouched = true;
				break;
				
			case D:
				move_direction = 1;
				break;
			
			case J:
				light.doAttack(this.getX(), this.getY());
				current = light;
				System.out.println("light");
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
			
			case S:
				is_crouched = false;
				break;
				
			case D:
				move_direction = move_direction==1 ? 0 : move_direction; 
				break;
			
			default:
				break;
			}
		});
	}
	
	
	public void update(long deltaTime) {
		this.setX(this.getX()+5*move_direction);
		if(current != null) current.update(deltaTime);
		
		if(is_crouched) {
			this.setY(ground-crouch_height);
			this.setHeight(crouch_height);
		}
		else {
			this.setY(ground-standing_height);
			this.setHeight(standing_height);
		}
	}

	
	public void takeDamage(double d) {
		hp -= d;
	}
}
