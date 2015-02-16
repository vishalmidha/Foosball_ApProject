package Team;

import java.awt.Dimension;
import java.util.Random;

import Components.Ball;

public abstract class Player {
	// attacker or defender or mid fielder or goal keeper
	private String team;
	private float CurrentX;
	private float CurrentY;
	private float speed;
	private final int size = 20;
	private int minKickSpeed = 2;
	private int maxKickSpeed = 5;

	public Dimension getCurrentPos() {
		Dimension d = new Dimension();
		d.setSize(getCurrentX(), getCurrentY());
		return d;
	}

	public float getCurrentX() {
		return CurrentX;
	}

	public void setCurrentX(float currentX) {
		CurrentX = currentX;
	}

	public float getCurrentY() {
		return CurrentY;
	}

	public void setCurrentY(float currentY) {
		CurrentY = currentY;
	}

	public int getSize() {
		return size;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void play() {
		if (Ball.getLastContact() == this.team) {
			Ball.setLastContact(this.team);
			Attack();

		}
		if (Ball.getLastContact() != this.team) {
			Ball.setLastContact(this.team);
			Defend();

		}
	}

	public void Attack() {
		// pass ball to next line or shoot
		if (this.team == "Player") {
			Random r = new Random();
			Random r1 = new Random();

			int x = minKickSpeed + r.nextInt(maxKickSpeed - minKickSpeed);
			int y = 2 * r1.nextInt(maxKickSpeed) - maxKickSpeed;

			Ball.setSpeedX(x);
			Ball.setSpeedY(y);
			Ball.setCurrentX(Ball.getCurrentX() + Ball.getSpeedX());
			Ball.setCurrentY(Ball.getCurrentY() + Ball.getSpeedY());
			//System.out.println("ättacking");
		}
		if (this.team == "CPU") {
			Random r = new Random();
			Random r1 = new Random();

			int x = minKickSpeed + r.nextInt(maxKickSpeed - minKickSpeed);
			int y = minKickSpeed + r1.nextInt(maxKickSpeed - minKickSpeed);

			Ball.setSpeedX(-x);
			Ball.setSpeedY(y);
			
			//System.out.println("ättacking");
		}
	}

	public void Defend() {
		if (this.team == "Player") {
			// simply reflect
			Ball.setSpeedX(Math.abs(Ball.getSpeedX()));
			Ball.setSpeedY(Ball.getSpeedY());
			//System.out.println("defending");
		}
		if (this.team == "CPU") {
			// simply reflect
			Ball.setSpeedX(-Math.abs(Ball.getSpeedX()));
			Ball.setSpeedY(Ball.getSpeedY());
			//System.out.println("defending");
		}
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
