package Interfaces;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Team.Attacker;
import Team.Defender;
import Team.GoalKeeper;
import Team.MidFielder;

public interface TeamInterface {
	public void movePlayerUp();
	public void movePlayerDown() ;
	public int getGap(int a) ;
	public GoalKeeper getGoalie();
	public void setGoalie(GoalKeeper goalie); 
	public ArrayList<Defender> getDefenders(); 
	public void setDefenders(ArrayList<Defender> defenders); 
	public ArrayList<Attacker> getAttackers();
	public void setAttackers(ArrayList<Attacker> attackers);
	public ArrayList<MidFielder> getMidfielders();
	public void setMidfielders(ArrayList<MidFielder> midfielders);
	public boolean isPlayer1FlagUp();
	public void setPlayer1FlagUp(boolean player1FlagUp); 
	public boolean isPlayer1FlagDown();
	public void setPlayer1FlagDown(boolean player1FlagDown); 
	public int getNumOfGoals();
	public void setNumOfGoals(int numOfGoals);
}
