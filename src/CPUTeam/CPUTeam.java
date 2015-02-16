package CPUTeam;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Components.Ball;
import GUI.Menu;
import Interfaces.CPUTeamInterface;

public class CPUTeam implements CPUTeamInterface{

	private CPUGoalKeeper goalie;
	// private Defender[] defenders;
	private static ArrayList<CPUDefender> defenders = new ArrayList<CPUDefender>();
	// private MidFielder[] midfielders;
	private static ArrayList<CPUMidFielder> midfielders = new ArrayList<CPUMidFielder>();
	// private Attacker[] attackers;
	private static ArrayList<CPUAttacker> attackers = new ArrayList<CPUAttacker>();
	private Thread teamThread;
	private boolean player1FlagUp;
	private boolean player1FlagDown;
	private int NumOfGoals;
	int up = 5;
	int down = -5;
	int def, mid, att;

	public CPUTeam() {
		Random rand = new Random();
		int r = rand.nextInt(7);
		int[][] formation = new int[][]{
				{4,4,2},
				{4,3,3},
				{4,5,1},
				{4,2,4},
				{6,3,1},
				{3,3,4},
				{3,4,3},
				{3,5,2},
				
		};
		int d = formation[r][0];
		int m = formation[r][1];
		int a = formation[r][2];
		this.def = d;
		this.mid = m;
		this.att = a;
		this.setNumOfGoals(0);
		setCPUGoalie(new CPUGoalKeeper());
		// set position
		getCPUGoalie().setCurrentY(getGap(1) - 10);
		// set speed
		getCPUGoalie().setSpeed(getGap(1) / 10);
		// set team
		goalie.setTeam("CPU");

		for (int i = 0; i < d; i++) {
			getCPUDefenders().add(i, new CPUDefender());
			float gap = getGap(d);
			// set position
			getCPUDefenders().get(i).setCurrentY(gap * (i + 1) - 10);
			// set speed
			getCPUDefenders().get(i).setSpeed(gap / 10);
			// set team
			getCPUDefenders().get(i).setTeam("CPU");
		}

		for (int i = 0; i < m; i++) {
			getCPUMidfielders().add(i, new CPUMidFielder());
			float gap = getGap(m);
			// set position
			getCPUMidfielders().get(i).setCurrentY(gap * (i + 1) - 10);
			// set speed
			getCPUMidfielders().get(i).setSpeed(gap / 10);
			// set team
			getCPUMidfielders().get(i).setTeam("CPU");
		}

		for (int i = 0; i < a; i++) {
			getCPUAttackers().add(i, new CPUAttacker());
			float gap = getGap(a);
			// set position
			getCPUAttackers().get(i).setCurrentY(gap * (i + 1) - 10);
			// set speed
			getCPUAttackers().get(i).setSpeed(gap / 10);
			// set team
			getCPUAttackers().get(i).setTeam("CPU");
		}

		teamThread = new Thread() {
			public void run() {
				while (true) {
					while (Ball.IsGameOn != true) {
						// System.out.println("w8ing");
					}
					while (Ball.IsGameOn == true) {
						moveTowardBall();
						//System.out.println("CPU is playin");
						if (goalie.getCurrentX() - Ball.getCurrentX() <= 5*Menu.getLevel()
								&& goalie.getCurrentX() - Ball.getCurrentX() >= -5*Menu.getLevel()
								&& goalie.getCurrentY() - Ball.getCurrentY() <= 5*Menu.getLevel()
								&& goalie.getCurrentY() - Ball.getCurrentY() >= -5*Menu.getLevel()) {
							goalie.play();
							break;
						}
						for (int i = 0; i < def; i++) {
							if (getCPUDefenders().get(i).getCurrentX()
									- Ball.getCurrentX() <= 5*Menu.getLevel()
									&& getCPUDefenders().get(i).getCurrentX()
											- Ball.getCurrentX() >= -5*Menu.getLevel()
									&& getCPUDefenders().get(i).getCurrentY()
											- Ball.getCurrentY() <= 5*Menu.getLevel()
									&& getCPUDefenders().get(i).getCurrentY()
											- Ball.getCurrentY() >= -5*Menu.getLevel()) {
								getCPUDefenders().get(i).play();
								break;
							}
						}
						for (int i = 0; i < mid; i++) {
							if (getCPUMidfielders().get(i).getCurrentX()
									- Ball.getCurrentX() <= 5*Menu.getLevel()
									&& getCPUMidfielders().get(i).getCurrentX()
											- Ball.getCurrentX() >= -5*Menu.getLevel()
									&& getCPUMidfielders().get(i).getCurrentY()
											- Ball.getCurrentY() <= 5*Menu.getLevel()
									&& getCPUMidfielders().get(i).getCurrentY()
											- Ball.getCurrentY() >= -5*Menu.getLevel()) {
								getCPUMidfielders().get(i).play();
								break;
							}
						}
						for (int i = 0; i < att; i++) {
							if (getCPUAttackers().get(i).getCurrentX()
									- Ball.getCurrentX() <= 5*Menu.getLevel()
									&& getCPUAttackers().get(i).getCurrentX()
											- Ball.getCurrentX() >= -5*Menu.getLevel()
									&& getCPUAttackers().get(i).getCurrentY()
											- Ball.getCurrentY() <= 5*Menu.getLevel()
									&& getCPUAttackers().get(i).getCurrentY()
											- Ball.getCurrentY() >= -5*Menu.getLevel()) {
								getCPUAttackers().get(i).play();
								break;
							}
							
						}
						if (Ball.CheckforGoals() == 1) {
							setNumOfGoals(getNumOfGoals() + 1);
							Ball.IsGameOn = false;
							Ball.setLastContact("CPU");
							Ball.setCurrentX(getCPUGoalie().getCurrentX());
							Ball.setCurrentY(getCPUGoalie().getCurrentY());
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
						
						try {
							Thread.sleep(40-10*Menu.getLevel()); // milliseconds
						} catch (InterruptedException ex) {
						}
					}
				}
			}
		};
		teamThread.start();
		System.out.println("CPUteam thread runnning");
	}
	
	
	
	public void moveTowardBall()
	{
		//Dividing the whole table into horizontal strips
		CPUAttacker near_attacker;
		CPUDefender near_defender;
		CPUMidFielder near_midfielder;
		int intersectionY_attacker,intersectionY_def,intersectionY_mid,intersectionY_gk;
		int i=0,k=0;
		int attackerX=(int) CPUTeam.attackers.get(0).getCurrentX();
		int defX=(int) CPUTeam.defenders.get(0).getCurrentX();
		int midfX=(int) CPUTeam.midfielders.get(0).getCurrentX();
		
			near_attacker=CPUTeam.attackers.get(0);
			near_defender=CPUTeam.defenders.get(0);
			near_midfielder=CPUTeam.midfielders.get(0);
			intersectionY_attacker= (int) ((((attackerX-Ball.getCurrentX())/Ball.getSpeedX())*Ball.getSpeedY())+Ball.getCurrentY());
			intersectionY_def= (int) ((((defX-Ball.getCurrentX())/Ball.getSpeedX())*Ball.getSpeedY())+Ball.getCurrentY());
			intersectionY_mid= (int) ((((midfX-Ball.getCurrentX())/Ball.getSpeedX())*Ball.getSpeedY())+Ball.getCurrentY());
			/*System.out.println("intersections is for attacker X= "+attackerX+" Y= " +intersectionY_attacker);
			System.out.println("intersections is for defender X= "+defX+" Y= " +intersectionY_def);
			System.out.println("intersections is for midfielder X= "+midfX+" Y= " +intersectionY_mid);*/
			
			if(Ball.getCurrentX()>0 && Ball.getCurrentX()<199)
			{
				if(intersectionY_attacker>0 && intersectionY_attacker<400 )
				{
					for(i=0;i<CPUTeam.attackers.size();i++)
					{
						if(Math.abs((CPUTeam.attackers.get(i).getCurrentY()-intersectionY_attacker))<Math.abs((near_attacker.getCurrentY()-intersectionY_attacker)))
						{
							near_attacker=CPUTeam.attackers.get(i);
							k=i;
						}
						
					}
					System.out.println("near attacker number "+k);
					if((CPUTeam.attackers.get(k).getCurrentY()-intersectionY_attacker)!=0 && CPUTeam.attackers.get(0).getCurrentY()>0 && CPUTeam.attackers.get(CPUTeam.attackers.size()-1).getCurrentY()<400 )
					{	
						 if( (Math.abs(near_attacker.getCurrentY()-intersectionY_attacker)/(near_attacker.getCurrentY()-intersectionY_attacker))==-1)
						 {
							moveCPUPlayerDown();
						 }
						 else
						 {
							 moveCPUPlayerUp();
						 }
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							i++;
						}
				}
			}
			if(Ball.getCurrentX()>404 && Ball.getCurrentX()<566)
			{
				if(intersectionY_def>0 && intersectionY_def<400 )
				{
					for(i=0;i<CPUTeam.defenders.size();i++)
					{
						if(Math.abs((CPUTeam.defenders.get(i).getCurrentY()-intersectionY_def))<Math.abs((near_defender.getCurrentY()-intersectionY_def)))
						{
							near_defender=CPUTeam.defenders.get(i);
							k=i;
						}
						
					}
					System.out.println("near defender number "+k);
					if((CPUTeam.defenders.get(k).getCurrentY()-intersectionY_def)!=0 && CPUTeam.defenders.get(0).getCurrentY()>0 && CPUTeam.defenders.get(CPUTeam.defenders.size()-1).getCurrentY()<400 )
					{	
						 if( (Math.abs(near_defender.getCurrentY()-intersectionY_def)/(near_defender.getCurrentY()-intersectionY_def))==-1)
						 {
							moveCPUPlayerDown();
						 }
						 else
						 {
							 moveCPUPlayerUp();
						 }
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							i++;
					}
				}
			}
			if(Ball.getCurrentX()>201 && Ball.getCurrentX()<402)
			{
				if(intersectionY_mid>0 && intersectionY_mid<400 )
				{
					for(i=0;i<CPUTeam.midfielders.size();i++)
					{
						if(Math.abs((CPUTeam.midfielders.get(i).getCurrentY()-intersectionY_mid))<Math.abs((near_midfielder.getCurrentY()-intersectionY_mid)))
						{
							near_midfielder=CPUTeam.midfielders.get(i);
							k=i;
						}
						
					}
					System.out.println("near midfielder number "+k);
					if((CPUTeam.midfielders.get(k).getCurrentY()-intersectionY_mid)!=0 && CPUTeam.midfielders.get(0).getCurrentY()>0 && CPUTeam.midfielders.get(CPUTeam.midfielders.size()-1).getCurrentY()<400 )
					{	
						 if( (Math.abs(near_midfielder.getCurrentY()-intersectionY_mid)/(near_midfielder.getCurrentY()-intersectionY_mid))==-1)
						 {
							moveCPUPlayerDown();
						 }
						 else
						 {
							 moveCPUPlayerUp();
						 }
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							i++;
					}
				}
			}
				
}
			
	

	public void moveCPUPlayerUp() {
		if (goalie.getCurrentY() > 0) {
			goalie.setCurrentY(goalie.getCurrentY() - 1);

			for (int i = 0; i < def; i++) {
				getCPUDefenders().get(i).setCurrentY(
						getCPUDefenders().get(i).getCurrentY()
								- 2);
			}

			for (int i = 0; i < mid; i++) {
				getCPUMidfielders().get(i).setCurrentY(
						getCPUMidfielders().get(i).getCurrentY()
								- 2);
			}

			for (int i = 0; i < att; i++) {
				getCPUAttackers().get(i).setCurrentY(
						getCPUAttackers().get(i).getCurrentY()
								- 2);
			}
		}

	}

	public void moveCPUPlayerDown() {
		if (goalie.getCurrentY() < Ball.getBoxHeight()) {
			goalie.setCurrentY(goalie.getCurrentY() + 1);

			for (int i = 0; i < def; i++) {
				getCPUDefenders().get(i).setCurrentY(
						getCPUDefenders().get(i).getCurrentY()
								+ 2);
			}

			for (int i = 0; i < mid; i++) {
				getCPUMidfielders().get(i).setCurrentY(
						getCPUMidfielders().get(i).getCurrentY()
								+ 2);
			}

			for (int i = 0; i < att; i++) {
				getCPUAttackers().get(i).setCurrentY(
						getCPUAttackers().get(i).getCurrentY()
								+ 2);
			}
		}
	}

	public int getGap(int a) {
		// find gap between players on a stick
		int gap = Ball.getBoxHeight() / (a + 1);
		return gap;
	}

	public CPUGoalKeeper getCPUGoalie() {
		return goalie;
	}

	public void setCPUGoalie(CPUGoalKeeper goalie) {
		this.goalie = goalie;
	}

	public ArrayList<CPUDefender> getCPUDefenders() {
		return defenders;
	}

	public void setCPUDefenders(ArrayList<CPUDefender> defenders) {
		this.defenders = defenders;
	}

	public ArrayList<CPUAttacker> getCPUAttackers() {
		return attackers;
	}

	public void setCPUAttackers(ArrayList<CPUAttacker> attackers) {
		this.attackers = attackers;
	}

	public ArrayList<CPUMidFielder> getCPUMidfielders() {
		return midfielders;
	}

	public void setCPUMidfielders(ArrayList<CPUMidFielder> midfielders) {
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
