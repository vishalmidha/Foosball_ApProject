package Interfaces;

import java.util.ArrayList;

import CPUTeam.CPUAttacker;
import CPUTeam.CPUDefender;
import CPUTeam.CPUGoalKeeper;
import CPUTeam.CPUMidFielder;

public interface CPUTeamInterface {
	public void moveTowardBall();
	public void moveCPUPlayerUp();
	public void moveCPUPlayerDown();
	public int getGap(int a);
	
	
	public CPUGoalKeeper getCPUGoalie() ;
	public void setCPUGoalie(CPUGoalKeeper goalie); 
	public ArrayList<CPUDefender> getCPUDefenders(); 
	public void setCPUDefenders(ArrayList<CPUDefender> defenders); 
	public ArrayList<CPUAttacker> getCPUAttackers() ;
	public void setCPUAttackers(ArrayList<CPUAttacker> attackers); 
	public ArrayList<CPUMidFielder> getCPUMidfielders() ;
	public void setCPUMidfielders(ArrayList<CPUMidFielder> midfielders); 
	public boolean isPlayer1FlagUp() ;
	public void setPlayer1FlagUp(boolean player1FlagUp); 
	public boolean isPlayer1FlagDown() ;
	public void setPlayer1FlagDown(boolean player1FlagDown);
	public int getNumOfGoals();
	public void setNumOfGoals(int numOfGoals);

}
