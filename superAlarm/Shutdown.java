/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superalarm;

import java.io.IOException;

/**
 * Shutdown command "excutor"
 * @author omnissiah
 */
public class Shutdown {
    //shuts down the machine, after 5 minutes of final Snooze 
    static String shutdownCommand;
    public static void destroyEraseImprove() throws RuntimeException, IOException {
        try {
            Thread.sleep(300000);
            System.out.println("destroyEraseImprove"); //kinda debug
        }catch (InterruptedException e ){
            e.printStackTrace();
        }
        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0); 
        
        
    }
    public static void operatingSystemDetect(){
    //determine what type of operating system is the user running
        String operatingSystem = System.getProperty("os.name");
        if ((operatingSystem.contains("Linux")) || (operatingSystem.contains("Mac OS X"))) {
            shutdownCommand = "shutdown -h now";
        } else if (operatingSystem.contains("Windows")) {
            shutdownCommand = "shutdown.exe -s -t 0";
        } else {
            throw new RuntimeException("Unsupported operating system.");
        }

    }
}
