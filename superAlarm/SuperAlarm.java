/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Tu sie logowac
 */
public class SuperAlarm implements Runnable{
	static int setTime = 0;
        public static boolean alarmOn = false ;
	static public void main (String[] args){
		SuperAlarm waiter = new SuperAlarm();
		Thread waiterThread = new Thread(waiter);
		waiterThread.start() ;
	}
	public void run(){
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
			} else{ 
				System.out.println("not late");
                                try{
                                    Thread.sleep(2000);
                                }catch (InterruptedException ex){
                                    System.out.println("not late");//do nothing
                                } }
		}
	}
	public static boolean evalTime(){
		if (java.time.LocalTime.now().getHour() < SuperAlarm.setTime)
                    return false;
                else 
                    return true;
                // check if the time is right to make the change ! :D 
	}
}

class alarmBoss implements Runnable{
	public void run(){
		XMLSnooze xmlSnooze = new XMLSnooze() ;
		this.newinstance();
		
	}
	void newinstance(){
		AlarmGraph alarm = new AlarmGraph(XMLSnooze.getText());
	}
}

class waitAfterSnooze implements Runnable {
    public void run(){
        try{ //sleeps for 5 minutes then runs new alarmBoss
            Thread.sleep(3000);
        } catch (InterruptedException e){
            //do nothing 
        }
        new Thread(new alarmBoss()).start();
    }
}


