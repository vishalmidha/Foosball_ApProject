package Team;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Components.Ball;
import GUI.Menu;
import Interfaces.TeamInterface;

public class Team implements TeamInterface{

	private GoalKeeper goalie;
	// private Defender[] defenders;
	private ArrayList<Defender> defenders = new ArrayList<Defender>();
	// private MidFielder[] midfielders;
	private ArrayList<MidFielder> midfielders = new ArrayList<MidFielder>();
	// private Attacker[] attackers;
	private ArrayList<Attacker> attackers = new ArrayList<Attacker>();
	private Thread teamThread;
	private boolean player1FlagUp;
	private boolean player1FlagDown;
	private int NumOfGoals;
	int up = 5;
	int down = -5;
	int def, mid, att;

	public Team(int d, int m, int a) {
		this.def = Menu.getNumOfDef();
		this.mid = Menu.getNumOfMid();
		this.att = Menu.getNumOfAttack();
		/*
		 * this.def=d; this.mid=m; this.att=a;
		 */
		this.setNumOfGoals(0);
		setGoalie(new GoalKeeper());
		// set position
		getGoalie().setCurrentY(getGap(1) - 10);
		// set speed
		getGoalie().setSpeed(70 / 10);
		// set team
		goalie.setTeam("Player");

		/*
		 * for (int i = 0; i < this.def; i++) { getDefenders().add(i, new
		 * Defender()); float gap = getGap(this.def); // set position
		 * getDefenders().get(i).setCurrentY(gap * (i + 1) - 10); // set speed
		 * getDefenders().get(i).setSpeed(gap / 10); // set team
		 * getDefenders().get(i).setTeam("Player"); }
		 */

		teamThread = new Thread() {
			public void run() {
				while (true) {
					while (Ball.IsGameOn != true) {
						// System.out.println("w8ing");
					}
					while (Ball.IsGameOn == true) {
						// System.out.println("player is playin");
						// check collision of ball with player
						if (getDefenders().size() == 0) {
							for (int i = 0; i < Menu.getNumOfDef(); i++) {
								getDefenders().add(i, new Defender());
								float gap = getGap(Menu.getNumOfDef());
								// set position
								getDefenders().get(i).setCurrentY(
										gap * (i + 1) - 10);
								// set speed
								getDefenders().get(i).setSpeed(gap / 10);
								// set team
								getDefenders().get(i).setTeam("Player");
							}
							for (int i = 0; i < Menu.getNumOfMid(); i++) {
								getMidfielders().add(i, new MidFielder());
								float gap = getGap(Menu.getNumOfMid());
								// set position
								getMidfielders().get(i).setCurrentY(
										gap * (i + 1) - 10);
								// set speed
								getMidfielders().get(i).setSpeed(gap / 10);
								// set team
								getMidfielders().get(i).setTeam("Player");
							}

							for (int i = 0; i < Menu.getNumOfAttack(); i++) {
								getAttackers().add(i, new Attacker());
								float gap = getGap(Menu.getNumOfAttack());
								// set position
								getAttackers().get(i).setCurrentY(
										gap * (i + 1) - 10);
								// set speed
								getAttackers().get(i).setSpeed(gap / 10);
								// set team
								getAttackers().get(i).setTeam("Player");
							}
						}

						if (goalie.getCurrentX() - Ball.getCurrentX() <= 15
								&& goalie.getCurrentX() - Ball.getCurrentX() >= -15
								&& goalie.getCurrentY() - Ball.getCurrentY() <= 15
								&& goalie.getCurrentY() - Ball.getCurrentY() >= -15) {
							goalie.play();
							break;
						}
						for (int i = 0; i < Menu.getNumOfDef(); i++) {
							if (getDefenders().get(i).getCurrentX()
									- Ball.getCurrentX() <= 15
									&& getDefenders().get(i).getCurrentX()
											- Ball.getCurrentX() >= -15
									&& getDefenders().get(i).getCurrentY()
											- Ball.getCurrentY() <= 15
									&& getDefenders().get(i).getCurrentY()
											- Ball.getCurrentY() >= -15) {
								getDefenders().get(i).play();
								break;
							}
						}
						for (int i = 0; i < Menu.getNumOfMid(); i++) {
							if (getMidfielders().get(i).getCurrentX()
									- Ball.getCurrentX() <= 15
									&& getMidfielders().get(i).getCurrentX()
											- Ball.getCurrentX() >= -15
									&& getMidfielders().get(i).getCurrentY()
											- Ball.getCurrentY() <= 15
									&& getMidfielders().get(i).getCurrentY()
											- Ball.getCurrentY() >= -15) {
								getMidfielders().get(i).play();
								break;
							}
						}
						for (int i = 0; i < Menu.getNumOfAttack(); i++) {
							if (getAttackers().get(i).getCurrentX()
									- Ball.getCurrentX() <= 15
									&& getAttackers().get(i).getCurrentX()
											- Ball.getCurrentX() >= -15
									&& getAttackers().get(i).getCurrentY()
											- Ball.getCurrentY() <= 15
									&& getAttackers().get(i).getCurrentY()
											- Ball.getCurrentY() >= -15) {
								getAttackers().get(i).play();
								break;
							}
						}
						if (Ball.CheckforCPUGoals() == 1) {
							setNumOfGoals(getNumOfGoals() + 1);
							Ball.IsGameOn = false;
							Ball.setLastContact("Player");
							Ball.setCurrentX(getGoalie().getCurrentX());
							Ball.setCurrentY(getGoalie().getCurrentY());

							try {
								TimeUnit.MILLISECONDS.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (getNumOfGoals() >= 5) {
								Ball.IsGameOn = false;
							} else {
								Ball.IsGameOn = true;
							}
						}

					}

					try {
						Thread.sleep(10); // milliseconds
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		teamThread.start();
		System.out.println("team thread runnning");
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			setPlayer1FlagDown(false);
			break;
		case KeyEvent.VK_DOWN:
			setPlayer1FlagUp(false);
			break;
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			movePlayerUp();
			break;
		case KeyEvent.VK_DOWN:
			movePlayerDown();
			break;
		}
	}

	public void movePlayerUp() {
		if (goalie.getCurrentY() > Ball.getBoxHeight() / 2 - 70 - 10) {
			goalie.setCurrentY(goalie.getCurrentY() - goalie.getSpeed());

			for (int i = 0; i < Menu.getNumOfDef(); i++) {
				getDefenders().get(i).setCurrentY(
						getDefenders().get(i).getCurrentY()
								- getDefenders().get(i).getSpeed());
			}

			for (int i = 0; i < Menu.getNumOfMid(); i++) {
				getMidfielders().get(i).setCurrentY(
						getMidfielders().get(i).getCurrentY()
								- getMidfielders().get(i).getSpeed());
			}

			for (int i = 0; i < Menu.getNumOfAttack(); i++) {
				getAttackers().get(i).setCurrentY(
						getAttackers().get(i).getCurrentY()
								- getAttackers().get(i).getSpeed());
			}
		}

	}

	public void movePlayerDown() {
		if (goalie.getCurrentY() < Ball.getBoxHeight() / 2 + 70 - 10) {
			goalie.setCurrentY(goalie.getCurrentY() + goalie.getSpeed());

			for (int i = 0; i < Menu.getNumOfDef(); i++) {
				getDefenders().get(i).setCurrentY(
						getDefenders().get(i).getCurrentY()
								+ getDefenders().get(i).getSpeed());
			}

			for (int i = 0; i < Menu.getNumOfMid(); i++) {
				getMidfielders().get(i).setCurrentY(
						getMidfielders().get(i).getCurrentY()
								+ getMidfielders().get(i).getSpeed());
			}

			for (int i = 0; i < Menu.getNumOfAttack(); i++) {
				getAttackers().get(i).setCurrentY(
						getAttackers().get(i).getCurrentY()
								+ getAttackers().get(i).getSpeed());
			}
		}
	}

	public int getGap(int a) {
		// find gap between players on a stick
		int gap = Ball.getBoxHeight() / (a + 1);
		return gap;
	}

	public GoalKeeper getGoalie() {
		return goalie;
	}

	public void setGoalie(GoalKeeper goalie) {
		this.goalie = goalie;
	}

	public ArrayList<Defender> getDefenders() {
		return defenders;
	}

	public void setDefenders(ArrayList<Defender> defenders) {
		this.defenders = defenders;
	}

	public ArrayList<Attacker> getAttackers() {
		return attackers;
	}

	public void setAttackers(ArrayList<Attacker> attackers) {
		this.attackers = attackers;
	}

	public ArrayList<MidFielder> getMidfielders() {
		return midfielders;
	}

	public void setMidfielders(ArrayList<MidFielder> midfielders) {
		this.midfielders = midfielders;
	}

	public boolean isPlayer1FlagUp() {
		return player1FlagUp;
	}

	public void setPlayer1FlagUp(boolean player1FlagUp) {
		this.player1FlagUp = player1FlagUp;
	}

	public boolean isPlayer1FlagDown() {
		return player1FlagDown;
	}

	public void setPlayer1FlagDown(boolean player1FlagDown) {
		this.player1FlagDown = player1FlagDown;
	}

	public int getNumOfGoals() {
		return NumOfGoals;
	}

	public void setNumOfGoals(int numOfGoals) {
		NumOfGoals = numOfGoals;
	}

}
