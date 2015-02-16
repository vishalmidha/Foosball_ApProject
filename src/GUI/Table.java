package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Team.Team;
import CPUTeam.CPUTeam;
import Components.Ball;

public class Table extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tableHeight;
	private int tableWidth;
	private Image table;
	private Image ball;
	@SuppressWarnings("unused")
	private Image player;
	private Team team;
	private CPUTeam cputeam;

	public Table(Team t, CPUTeam ct) {
		this.team = t;
		this.cputeam = ct;
		this.tableHeight = Ball.getBoxHeight();
		this.tableWidth = Ball.getBoxwidth();
		this.setPreferredSize(new Dimension(tableWidth, tableHeight + 100));
		final JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(this);

		Thread tableThread = new Thread() {
			public void run() {
				table = Toolkit.getDefaultToolkit()
						.getImage("Images/Table.jpg");
				ball = Toolkit.getDefaultToolkit().getImage("Images/Ball.jpg");
				player = Toolkit.getDefaultToolkit().getImage(
						"Images/Player.jpg");
				while (true) {
					while (Ball.IsGameOn != true) {
						//repaint();
					}
					while (Ball.IsGameOn == true) {
						// painting
						repaint();
						try {
							Thread.sleep(10); // milliseconds
						} catch (InterruptedException ex) {
						}
					}
					if(cputeam.getNumOfGoals()>=5 || team.getNumOfGoals()>=5){
						Ball.IsGameOn = false;
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						team.setNumOfGoals(0);
						cputeam.setNumOfGoals(0);
						//jframe.dispose();
						
					}
				}
			}
		};
		tableThread.start();
		System.out.println("TableThread running");
	}

	@SuppressWarnings("resource")
	public void paintComponent(Graphics g) {
		setOpaque(false);
		// super.paintComponent(g); // Paint background

		// Draw table
		g.drawImage(table, 0, 0, tableWidth, tableHeight, null);

		// Draw the Ball
		// g.setColor(Color.red);
		// g.fillOval(Ball.getCurrentX(), Ball.getCurrentY(), 8, 8);

		g.drawImage(ball, (int) Ball.getCurrentX(), (int) Ball.getCurrentY(),
				20, 20, null);
		// g.setColor(Color.black);

		// display imformation
		g.setColor(Color.CYAN);
		g.setFont(new Font("Courier New", Font.PLAIN, 12));
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("Ball(%3.0f,%3.0f) Speed(%2.0f,%2.0f) Goals(%2.0f,%2.0f) Formation(%2.0f,%2.0f,%2.0f) Level: (%2.0f)",
				Ball.getCurrentX(), Ball.getCurrentY(), Ball.getSpeedX(),
				Ball.getSpeedY(), (float)cputeam.getNumOfGoals(), (float)team.getNumOfGoals(),(float)Menu.getNumOfDef(),(float)Menu.getNumOfMid(),(float)Menu.getNumOfAttack(),(float)Menu.getLevel());
		g.drawString(sb.toString(), 20, 20);
		// print player
		// print goalkeeper
		g.setColor(Color.red);
		g.fillRect((int) team.getGoalie().getCurrentX(), (int) team.getGoalie()
				.getCurrentY(), team.getGoalie().getSize(), team.getGoalie()
				.getSize());
		// print defenders
		for (int i = 0; i < team.getDefenders().size(); i++) {
			g.fillRect((int) team.getDefenders().get(i).getCurrentX(),
					(int) team.getDefenders().get(i).getCurrentY(), team
							.getDefenders().get(i).getSize(), team
							.getDefenders().get(i).getSize());
		}

		// print mid fielders
		for (int i = 0; i < team.getMidfielders().size(); i++) {
			g.fillRect((int) team.getMidfielders().get(i).getCurrentX(),
					(int) team.getMidfielders().get(i).getCurrentY(), team
							.getMidfielders().get(i).getSize(), team
							.getMidfielders().get(i).getSize());
		}
		// print attackers
		for (int i = 0; i < team.getAttackers().size(); i++) {
			g.fillRect((int) team.getAttackers().get(i).getCurrentX(),
					(int) team.getAttackers().get(i).getCurrentY(), team
							.getAttackers().get(i).getSize(), team
							.getAttackers().get(i).getSize());
		}
		g.setColor(Color.BLUE);
		g.fillRect((int) cputeam.getCPUGoalie().getCurrentX(), (int) cputeam
				.getCPUGoalie().getCurrentY(),
				cputeam.getCPUGoalie().getSize(), cputeam.getCPUGoalie()
						.getSize());

		// print defenders
		for (int i = 0; i < cputeam.getCPUDefenders().size(); i++) {
			g.fillRect((int) cputeam.getCPUDefenders().get(i).getCurrentX(),
					(int) cputeam.getCPUDefenders().get(i).getCurrentY(),
					cputeam.getCPUDefenders().get(i).getSize(), cputeam
							.getCPUDefenders().get(i).getSize());
		}

		// print mid fielders
		for (int i = 0; i < cputeam.getCPUMidfielders().size(); i++) {
			g.fillRect((int) cputeam.getCPUMidfielders().get(i).getCurrentX(),
					(int) cputeam.getCPUMidfielders().get(i).getCurrentY(),
					cputeam.getCPUMidfielders().get(i).getSize(), cputeam
							.getCPUMidfielders().get(i).getSize());
		}
		// print attackers
		for (int i = 0; i < cputeam.getCPUAttackers().size(); i++) {
			g.fillRect((int) cputeam.getCPUAttackers().get(i).getCurrentX(),
					(int) cputeam.getCPUAttackers().get(i).getCurrentY(),
					cputeam.getCPUAttackers().get(i).getSize(), cputeam
							.getCPUAttackers().get(i).getSize());
		}
		if(team.getNumOfGoals()>=5){
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("Courier New", Font.BOLD, 48));
			StringBuilder sb1 = new StringBuilder();
			Formatter formatter1 = new Formatter(sb1);
			formatter1.format("You Lose !");
			g.drawString(sb1.toString(), 200, 200);
		}
		if(cputeam.getNumOfGoals()>=5){
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("Courier New", Font.BOLD, 48));
			StringBuilder sb1 = new StringBuilder();
			Formatter formatter1 = new Formatter(sb1);
			formatter1.format("You Win...!!");
			g.drawString(sb1.toString(), 200, 200);
		}
	}

}
