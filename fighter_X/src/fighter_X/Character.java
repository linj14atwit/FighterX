package fighter_X;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * @author James Yu Lin
 * 
 * Character class
 */
public class Character extends Hurtbox{
	
	private Pane parent; 
	private Character opponent;
	private int hp;
	
	private double standing_height;
	private double crouch_height;
	private double ground;
	
	private int facing_left;
	private int move_direction=0;
	
	private boolean is_jumping;
	private long jump_timer;
	
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
				case W:
					startJump();
					break;
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
		this.move(deltaTime);
		if(current != null) {
			current = current.update(deltaTime);
//			move_direction = move_direction!=0 ? 0 : move_direction; 
		}
		
		if(is_crouched) {
//			System.out.printf("y:%f -> ", this.getY());
			this.setY(ground-crouch_height);
//			System.out.println(this.getY());
			this.setHeight(crouch_height);
		}
		else if(current==null && !is_jumping){
			this.setY(ground-standing_height);
			this.setHeight(standing_height);
		}
	}
	
	/**
	 * handles movement of character
	 */
	public void move(long deltaTime) {
		this.setX(this.getX()+5*move_direction);
		
		if(is_jumping) {
			jump_timer += deltaTime;
			System.out.printf("y:%.0f ->", this.getY());
			this.setY(this.getY()-(-(jump_timer/MainStage.FRAME_RATE)+30));
			System.out.printf("%.0f, x:%d%n", this.getY(), (jump_timer/MainStage.FRAME_RATE));
//			System.out.println(jump_timer/MainStage.FRAME_RATE);
			System.out.println(move_direction);
			
			if(this.getHeight()+this.getY()>ground+4 && jump_timer>4*MainStage.FRAME_RATE) {
				is_jumping = false;
				jump_timer = 0;
			}
		}
	}
	
	public void startJump() {
		is_jumping = true;
		jump_timer = 0;
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

	
	public double ground() {
		return this.ground;
	}
}
