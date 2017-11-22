/*
 * MIT LICENSE 
 */
package superalarm;
import superalarm.alarmBoss ;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.time.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
/**
 * The main file of SuperAlarm 
 * @author Tu sie logowac
 */
public class SuperAlarm implements Runnable{
	static int setTime = 0;
    // public boolean alarmOn = false ;// dont know if required
	
	static public void main (String[] args){
	//start $waiter instance of SuperAlarm and thread it 
		Shutdown.operatingSystemDetect();
		SuperAlarm waiter = new SuperAlarm();
		Thread waiterThread = new Thread(waiter);
		waiterThread.start() ;
	}
	
	public void run(){
	//continously check if its late enough to trigger the alarm
	//by calling evalTime
	//if it is, start and thread new alarmBoss
		boolean itsTime = false;
		try{
            Thread.sleep(2000) ;
        }catch (InterruptedException e){
        	// do nothing
        }
		while (!(itsTime)) {
			if (evalTime()) {
				alarmBoss aBoss = new alarmBoss() ;
				Thread alBoss = new Thread(aBoss) ;
				alBoss.start() ;
				itsTime = true ; 
			} else { 
				System.out.println("not late");
            	try{
                	Thread.sleep(2000);
            	}catch (InterruptedException ex){
                //do nothing
            	} 
            }
		}
	}
	public static boolean evalTime(){
	//check if its earlier than static $setTime
	// thats not lazy, thats to prevent the user from 
	// changing the value after compilation
		if (java.time.LocalTime.now().getHour() < SuperAlarm.setTime)
            return false;
        else 
            return true;
	}
}

class alarmBoss implements Runnable{	
	public void run(){
	// initializes the XML Parser and GUI 	
		XMLSnooze xmlSnooze = new XMLSnooze() ;
		this.newinstance();
		
	}
	void newinstance(){
	//startGUI
	//as a seperate function to enable the initialization of
	//numerous instances of gui on one base application
		AlarmGraph alarm = new AlarmGraph(XMLSnooze.getText());
	}
}

class waitAfterSnooze implements Runnable {
    public void run(){
    //sleeps for 5 minutes then runs new alarmBoss    
        try{ 
            Thread.sleep(3000);
        } catch (InterruptedException e){
            //do nothing 
        }
        new Thread(new alarmBoss()).start();
    }
}


