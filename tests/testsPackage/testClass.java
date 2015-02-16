package testsPackage;

import org.testng.annotations.Test;

import Team.Attacker;
import Team.Defender;
import Team.MidFielder;
import Team.Team;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import CPUTeam.CPUAttacker;
import CPUTeam.CPUDefender;
import CPUTeam.CPUMidFielder;
import CPUTeam.CPUTeam;
import Components.Ball;

public class testClass {
	@Test
	public void testLogin(){
		System.out.println("test working");
	}
	@Test
	public void shouldReturnWidthOfTheFrame() {
		int BoxWidth= 700;
		assertThat(Ball.getBoxwidth(), is(equalTo(BoxWidth)));		
	}
	@Test
	public void shouldReturnHeightOfTheFrame() {
		int BoxHeight= 400;
		assertThat(Ball.getBoxHeight(), is(equalTo(BoxHeight)));		
	}
	@Test
	public void shouldCheckTheGoalsForPlayer(){
		
		if(Ball.getCurrentX()>650 && (Ball.getCurrentY()>134 && Ball.getCurrentY()<239)){
		assertThat(Ball.CheckforGoals(),is(equalTo(1)));
		}
		
	} 
	@Test
	public void shouldCheckTheGoalsForCPUPlayer(){
		if(Ball.getCurrentX()<20 && (Ball.getCurrentY()>134 && Ball.getCurrentY()<239)){
		assertThat(Ball.CheckforCPUGoals(),is(equalTo(1)));
		}	
	} 
	@Test
	public void shouldCheckThePositionOfAttackers(){
		
		Attacker att = new Attacker();
		assertThat((int)att.getCurrentX(),is(equalTo(487)));	
	} 
	@Test
	public void shouldCheckThePositionOfDefenders(){
		
		Defender def = new Defender();
		assertThat((int)def.getCurrentX(),is(equalTo(117)));	
	} 
	@Test
	public void shouldCheckThePositionOfMidFielder(){
		
		MidFielder mid = new MidFielder();
		assertThat((int)mid.getCurrentX(),is(equalTo(280)));	
	} 
	@Test
	public void shouldCheckThePositionOfCPUAttackers(){
		
		CPUAttacker att = new CPUAttacker();
		assertThat((int)att.getCurrentX(),is(equalTo(200)));	
	} 
	@Test
	public void shouldCheckThePositionOfCPUDefenders(){
		
		CPUDefender def = new CPUDefender();
		assertThat((int)def.getCurrentX(),is(equalTo(562)));	
	} 
	@Test
	public void shouldCheckThePositionOfCPUMidFielder(){
		
		CPUMidFielder mid = new CPUMidFielder();
		assertThat((int)mid.getCurrentX(),is(equalTo(403)));	
	} 
	@Test
	public void shouldCheckTheLastContact(){
		assertThat(Ball.getLastContact(),is(equalTo("CPU")));
	}
	@Test
	public void shouldCheckTheGap(){
		CPUTeam team = new CPUTeam();
		assertThat(team.getGap(4),is(equalTo(80)));
	}
	
	@Test
	public void shouldGamebeOn(){
		assertThat(Ball.IsGameOn,is(equalTo(false)));
		
	}
	@Test
	public void should(){
		CPUMidFielder mid = new CPUMidFielder();
		assertThat(mid.getSize(),is(equalTo(20)));
	}
}



