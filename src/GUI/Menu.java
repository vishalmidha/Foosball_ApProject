package GUI;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.*;
@SuppressWarnings("serial")
public class Menu extends JPanel {
	private static final int MenuHeight = 100;
	private static final int MenuWidth = 700;
	
	private static int level;
	private static  int NumOfCPUMid ;
	private static int NumOfCPUDef;
	private static int NumOfCPUAttack ;
	private static int NumOfDef;
	private static int NumOfMid;
	private static int NumOfAttack;
	private JPanel controlPanel;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Menu() {
		// setLayout(new FlowLayout());
		// add radio buttons to JFrame
		this.setPreferredSize(new Dimension(MenuWidth, MenuHeight));
		
		  controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());
	      
		final JComboBox levels = new JComboBox();
	     
	      levels.addItem("novice");
	      levels.addItem("amateur");
	      levels.addItem("pro");
	      JButton levelButton = new JButton("level");
	      controlPanel.add(levels);
	      controlPanel.add(levelButton);
	     levelButton.addActionListener(new ActionListener(){
	    	 public void actionPerformed(ActionEvent e){
	    		 if(levels.getSelectedIndex()!=-1)
	    		 {
	    			 Menu.setLevel(levels.getSelectedIndex()+1);
	    		 }
	    	 }

	    	 
	     });
	      
	      final JComboBox teamFormation = new JComboBox();

	      teamFormation.addItem("4_4_2");
	      teamFormation.addItem("4_3_3");
	      teamFormation.addItem("4_5_1");
	      teamFormation.addItem("4_2_4");
	      teamFormation.addItem("6_3_1");
	      teamFormation.addItem("3_3_4");
	      teamFormation.addItem("3_4_3");
	      teamFormation.addItem("3_5_2"); 

	      JButton showButton = new JButton("Player Team");
	      controlPanel.add(teamFormation);          
	      controlPanel.add(showButton); 
	      
	      showButton.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) { 
	             @SuppressWarnings("unused")
				String data = "";
	             
	             if (teamFormation.getSelectedIndex() != -1) {                     
	                /*data = "Fruits Selected: " 
	                   + teamFormation.getItemAt
	                     (teamFormation.getSelectedIndex());*/
	                @SuppressWarnings("resource")
					Scanner in = new Scanner((String)teamFormation.getItemAt(teamFormation.getSelectedIndex())).useDelimiter("[^0-9]+");
	                //data = (String) teamFormation.getItemAt(teamFormation.getSelectedIndex());
	                
	                Menu.setNumOfDef(in.nextInt());
	                
	                //Menu.setNumOfDef(4);
	                Menu.setNumOfMid(in.nextInt());
	                Menu.setNumOfAttack(in.nextInt());
	             }              

	          }
	       }); 
	      
	      final JButton startGame = new JButton("Start Game"); 
	      startGame.setActionCommand("gameon");
	      startGame.addActionListener(new ButtonClickListener());
	      controlPanel.add(startGame);
	      
	      
	      this.add(controlPanel);
		/* startGame.action(JButton., Ball.getInstance().start()); */
	}
	public static int getNumOfDef() {
		return NumOfDef;
	}
	public static void setNumOfDef(int numOfDef) {
		NumOfDef = numOfDef;
	}
	public static int getNumOfMid() {
		return NumOfMid;
	}
	public static void setNumOfMid(int numOfMid) {
		NumOfMid = numOfMid;
	}
	public static int getNumOfAttack() {
		return NumOfAttack;
	}
	public static void setNumOfAttack(int numOfAttack) {
		NumOfAttack = numOfAttack;
	}
	
	public static int getNumOfCPUAttack() {
		return NumOfCPUAttack;
	}
	public static int getNumOfCPUMid() {
		// TODO Auto-generated method stub
		return NumOfCPUMid;
	}
	public static int getNumOfCPUDef() {
		// TODO Auto-generated method stub
		return NumOfCPUDef;
	}
	public static void setNumOfCPUMid(int numOfCPUMid) {
		NumOfCPUMid = numOfCPUMid;
	}
	
	public static void setNumOfCPUAttack(int numOfCPUAttack) {
		NumOfCPUAttack = numOfCPUAttack;
	}
	public static void setNumOfCPUDef(int numOfCPUDef) {
		NumOfCPUDef = numOfCPUDef;
	}
	public static int getLevel() {
		return level;
	}
	public static void setLevel(int level) {
		Menu.level = level;
	}

	
}
