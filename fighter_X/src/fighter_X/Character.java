package fighter_X;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * @author James Yu Lin
 * 
 * 
 */
public class Character extends Hurtbox{
	
	private Pane parent; 
	private Character opponent;
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
	
	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Character(Pane parent, double x, double y, double width, double height) {
		this(parent, x, y, width, height, y+height);
	}
	
	
	public Character(Pane parent, double x, double y, double width, double height, double ground) {
		this(parent, x, y, width, height, ground, true);
	}
	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param ground
	 * @param input
	 */
	public Character(Pane parent, double x, double y, double width, double height, double ground, boolean input) {
		super(x, y, width, height);
		this.ground = ground;
		standing_height = height;
		crouch_height = this.getHeight()*0.5;
		hp = 1000;
		
		facing_left = -1; //1 for right, -1 for left
		move_direction = 0; //1 for right, 0 for idle, -1 for left
		
		light = new Move(parent, opponent, Attack.NORMAL, 30, 12, 60, 50, 30, 10);
		
		if(input)this.enableInput();
	}
	
	/**
	 * enables input to be registered
	 */
	public void enableInput() {
		//Input Handler
		this.setOnKeyPressed(e->{
			if(current==null) { //If the character is performing an action it is locked out of grounded movement
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
				
				default:
					break;
				}
			}
			switch(e.getCode()) {
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
	
	/**
	 * updates the current status of Character
	 * @param deltaTime
	 */
	public void update(long deltaTime) {
		this.move();
		if(current != null) {
			current = current.update(deltaTime);
			move_direction = move_direction!=0 ? 0 : move_direction; 
		}
		
		if(is_crouched) {
			this.setY(ground-crouch_height);
			this.setHeight(crouch_height);
		}
		else if(current==null){
			this.setY(ground-standing_height);
			this.setHeight(standing_height);
		}
	}
	
	/**
	 * handles movment of character
	 */
	public void move() {
		this.setX(this.getX()+5*move_direction);
	}
	
	/**
	 * specifies the Opponents character object
	 * @param c
	 */
	public void addOpponent(Character c) {
		this.opponent = c;
	}
	
	/**
	 * takes damage
	 * @param d
	 */
	public void takeDamage(double d) {
		hp -= d;
	}
}
