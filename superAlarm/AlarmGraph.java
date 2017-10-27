/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superalarm;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author omnissiah
 */

class AlarmGraph extends JFrame implements ActionListener {
	String currentText = null ;
        JButton goingToSleepB ;
        JButton SnoozeB ;
        NewWindowAdapter windowadap = new NewWindowAdapter();
        @SuppressWarnings("OverridableMethodCallInConstructor")
	AlarmGraph(String arg){
		super("GO TO SLEEP !") ;
                    SuperAlarm.alarmOn = true;
                    currentText = arg ;
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setuupButtons();
                    JPanel panel = new JPanel();
                    JLabel alarmTextL = new JLabel(new ImageIcon("data/chiki.jpg")) ;
                this.setAlwaysOnTop(rootPaneCheckingEnabled);
                this.setSize(500, 500);
                    
                panel.add(goingToSleepB);
                panel.add(alarmTextL) ;
                panel.add(SnoozeB);
                add(panel);
                
                setVisible(true);
                
                addWindowListener(windowadap);
	}
        
        public void setuupButtons(){
                    SnoozeB = new JButton("Snooze") ;
                    SnoozeB.addActionListener(this);
                    SnoozeB.setActionCommand("addSnooze");
                    goingToSleepB = new JButton("Ok, give me 5 minutes") ;
                    goingToSleepB.addActionListener(this);
                    goingToSleepB.setActionCommand("waitAndExit");
        }
        public void actionPerformed(ActionEvent ae){
            String action = ae.getActionCommand();
            if (action == "addSnooze"){
                System.out.println("Button pushed");
                new XMLSnooze().newSnooze();
                new Thread(new waitAfterSnooze()).start();
                AlarmGraph.this.dispose();
            } else if (action == "waitAndExit"){
               try{
                    AlarmGraph.this.dispose();
                   Shutdown.destroyEraseImprove();
               }catch (Exception e ){
                   System.out.println("Something went wrong whilr thrying to shut down");
               }
                   
            }
        }
}

class NewWindowAdapter extends WindowAdapter{
        @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        }