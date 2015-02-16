package GUI;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Components.Ball;

class ButtonClickListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();  
		if( command.equals( "gameon" ))  {
            
			Ball.getInstance().start();
			int i=0;
			if(i==0)
			{
				Robot r;
				try {
					r = new Robot();
					r.keyPress(KeyEvent.VK_TAB);
				      				
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			i++;
	         }
	}
	
}