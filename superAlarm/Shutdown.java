/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superalarm;

import java.io.IOException;

/**
 *
 * @author omnissiah
 */
public class Shutdown {
    public static void destroyEraseImprove() throws RuntimeException, IOException {
        try {
            Thread.sleep(3000);
            System.out.println("destroyEraseImprove");

        }catch (InterruptedException e ){
            e.printStackTrace();
        }
        /** String shutdownCommand;
            String operatingSystem = System.getProperty("os.name");

            if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
                shutdownCommand = "shutdown -h now";
            } else if (operatingSystem.contains("Windows")) {
                shutdownCommand = "shutdown.exe -s -t 0";
            } else {
                throw new RuntimeException("Unsupported operating system.");
            }

            Runtime.getRuntime().exec(shutdownCommand);
            System.exit(0); 
        
        */
        }
}
