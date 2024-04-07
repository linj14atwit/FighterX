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
	
	private final double SPEED = 10;
	
	private Move current = null;
	private boolean is_crouched=false;
	private boolean is_block = false;
	
	private Move light;
	private Move crouch_light;
	private Move jump_light;
	
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
	public Character(Pane parent,  double x, double y, double width, double height, double ground, boolean input) {
		super(x, y, width, height);
		this.ground = ground;
		standing_height = height;
		crouch_height = this.getHeight()*0.5;
		hp = 1000;
		
		facing_left = -1; //1 for right, -1 for left
		move_direction = 0; //1 for right, 0 for idle, -1 for left
		
		light = new Move(parent, Attack.NORMAL, 30, 6, 3, 12, 60, 50, 30, 10);
		crouch_light = new Move(parent, Attack.LOW, 20, 7, 3, 13, 70, 30, 40, 10);
		jump_light = new Move(parent, Attack.AIR, 40, 6, 3, 0, 40, 60, 50, 80);
		
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
				if(is_jumping) {
					jump_light.doAttack(this.getX(),this.getY());
					current = jump_light;
					break;
				}
				if(is_crouched) {
					crouch_light.doAttack(this.getX(), this.getY());
					current = crouch_light;
					break;
				}
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
		this.setX(this.getX()+SPEED*move_direction);
		
		if(is_jumping) {
			jump_timer += deltaTime;
			this.setY(this.getY()-(-2*(jump_timer/MainStage.FRAME_RATE)+45));
//			System.out.printf("%.0f, x:%d%n", this.getY(), (jump_timer/MainStage.FRAME_RATE));
			
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
		light.addOpponent(c);
		crouch_light.addOpponent(c);
		jump_light.addOpponent(c);
	}
	
	/**
	 * takes damage
	 * @param d
	 */
	public void takeDamage(double d) {
//		System.out.printf("%s -%f%n", this.toString(), d);
		hp -= d;
	}

	
	public double ground() {
		return this.ground;
	}
}
