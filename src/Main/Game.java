package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import CPUTeam.CPUTeam;
import Components.Ball;
import GUI.Menu;
import GUI.Table;
import Team.Team;

public class Game extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Team team;

	private Table table;

	public Game() {
		super();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(700, 500));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// System.out.println("key listener working");
				formKeyPressed(e);
				// System.out.println("key");
			}

			public void keyReleased(KeyEvent e) {
				// System.out.println("key listener working");
				formKeyReleased(e);
			}

			public void keyTyped(KeyEvent e) {
				// System.out.println("key listener working");
			}
		});
		this.setFocusable(true);
		Menu menu = new Menu();
		@SuppressWarnings("unused")
		Ball ball = Ball.getInstance();
		team = new Team(Menu.getNumOfDef(), Menu.getNumOfMid(), Menu.getNumOfAttack());
		CPUTeam cputeam = new CPUTeam();

		table = new Table(team, cputeam);

		this.setContentPane(menu);
		this.getContentPane().add(table);
		// frame.getContentPane().add(ball,BorderLayout.CENTER);
		// frame.getContentPane().add(team, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
		
	}

	private void formKeyPressed(KeyEvent e) {
		team.keyPressed(e);
		// System.out.println("pressed");
	}

	private void formKeyReleased(KeyEvent e) {
		team.keyReleased(e);
	}

}
