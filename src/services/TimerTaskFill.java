package services;
import java.util.TimerTask;

public class TimerTaskFill extends TimerTask {
	protected Cell c;
	public void setC(Cell c) {
		this.c= c;
	}
	@Override
	public void run() {
		c.setContent("PLT");
		
	}
	
}
