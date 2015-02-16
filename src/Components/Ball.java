package Components;

import java.util.Random;

public class Ball {
	// Container's Dimensions
	private static final int BoxWidth = 700;
	private static final int BoxHeight = 400;
	private static String lastContact;

	// Balls Properties
	private final int Radius = 20;
	private static float CurrentX;
	private static float CurrentY;
	private static float SpeedX;
	private static float SpeedY;

	public static boolean IsGameOn = false;

	private Thread ballThread;
	private static Ball ball = new Ball();

	// Thread ballThread;

	// singleton builder method
	private Ball() {
		setLastContact("CPU");
		Random rand = new Random();
		int  n = rand.nextInt(2);
		if(n==1)
		{
			setCurrentX(640);
		}
		else
		{
			setCurrentX(49);
		}
		setCurrentY(BoxHeight / 2 -10);
		//setSpeed(5,5);
		ballThread = new Thread() {
			public void run() {
				while(true)
				{
					
					while (IsGameOn != true) {
					}
					while (IsGameOn == true) {
						// calculate ball's new position
						setCurrentX(getCurrentX() + getSpeedX());
						setCurrentY(getCurrentY() + getSpeedY());
						//System.out.println("ball thread");
						// checks for all types of collisions
						CheckCollision();
						try {
							Thread.sleep(10); // milliseconds
						} catch (InterruptedException ex) {
						}
					}
				}
			}
		};
		ballThread.start();
		System.out.println("Ball thread running");
	}

	public static Ball getInstance() {
		return ball;
	}

	public void CheckCollision() {
		// Check if Ball hits sides
		CheckBoundCollision();
		// check if playerKicks
		CheckPlayerCollision();
		// check if Goal is scored
	}

	private void CheckPlayerCollision() {
		
	}

	public void CheckBoundCollision() {
		if (getCurrentX()+ Radius >= getBoxwidth() || getCurrentX() <= 0) {
			setSpeedX(-getSpeedX());
		}
		if (getCurrentY() >= BoxHeight) {
			setCurrentY(BoxHeight-1);
			setSpeedY(-getSpeedY());
			
		}
		if(getCurrentY() <= 0){
			setCurrentY(1);
			setSpeedY(-getSpeedY());
		}
	}
	public void setSpeed(float x, float y) {
		setSpeedX(x);
		setSpeedY(y);
	}

	public void start() {
		IsGameOn = true;
	}

	public static int getBoxHeight() {
		return BoxHeight;
	}

	public static int getBoxwidth() {
		return BoxWidth;
	}

	public static float getCurrentX() {
		return CurrentX;
	}

	public static void setCurrentX(float currentX) {
		CurrentX = currentX;
	}

	public static float getCurrentY() {
		return CurrentY;
	}

	public static void setCurrentY(float currentY) {
		CurrentY = currentY;
	}

	public static float getSpeedX() {
		return SpeedX;
	}

	public static void setSpeedX(float speedX) {
		SpeedX = speedX;
	}

	public static float getSpeedY() {
		return SpeedY;
	}

	public static void setSpeedY(float speedY) {
		SpeedY = speedY;
	}

	public static String getLastContact() {
		return lastContact;
	}

	public static void setLastContact(String lc) {
		lastContact = lc;
	}

	public static int CheckforGoals() {
		// TODO Auto-generated method stub
		if(CurrentX>640)
		{
			if((CurrentY>134)&&(CurrentY<239))
				return 1;
			else 
				return 0;
		}
		else
			return 0;
	}
	public static int CheckforCPUGoals() {
		// TODO Auto-generated method stub
		if(CurrentX<9)
		{
			if((CurrentY>134)&&(CurrentY<239))
				return 1;
			else 
				return 0;
		}
		else
			return 0;
	}
}
