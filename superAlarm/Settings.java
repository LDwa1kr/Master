
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document ;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class Settings extends JFrame implements ActionListener {
    static File inputFile ;
    Document doc ;
    JTextField timeI;
    JTextField maxI;
    Settings(){
	super("SuperAlarm Settings");
	setuupXML() ;
	setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setSize(500, 125);
        
        javax.swing.JPanel panel = new JPanel();
	javax.swing.JButton timeB = new JButton("change sleepy time");
        timeB.addActionListener(this);
        timeB.setActionCommand("setNewTime");
        javax.swing.JLabel timeL = new JLabel(
                "current sleepy time: " + this.getTime());
        javax.swing.JButton maxB = new JButton("change max snoozes");
        timeI = new JTextField(2);
        maxB.addActionListener(this);
        maxB.setActionCommand("setMaxSnooze");
        javax.swing.JLabel maxL = new JLabel(
                "snoozes before shutdown: " +this.getMaxSnooze());
        maxI = new JTextField(2);
        
        
        panel.add(timeL);
        panel.add(timeB);
        panel.add(timeI);
        panel.add(maxL);
        panel.add(maxB);
        panel.add(maxI);
        add(panel);
        
        setVisible(true);
	}
        public void actionPerformed(ActionEvent ae){
            String action = ae.getActionCommand();
            if (action == "setNewTime"){
                System.out.println("setNewTime executed");
                setTime(timeI.getText());
            } else if (action == "setMaxSnooze"){
                System.out.println("setMaxSnoozes executed");
                setMaxSnooze(maxI.getText());
            }
                
        }
	public static void main(String[] args){
            Settings settings = new Settings();
	}
        
    void setuupXML(){
        try {
            inputFile = new File("/home/omnissiah/NetBeansProjects/JavaApplication2/src/javaapplication2/data/Snooze.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            System.out.println("XML Parser built"); //debug
        } catch (Exception e){
                e.printStackTrace();
                System.out.println("Something went wrong during construction of the XML reader");
        }
        
	}
	
    String getTime(){    
        return doc.getElementsByTagName("time").item(0).getTextContent();
    }
    void setTime(String timeVar){
        doc.getElementsByTagName("time").item(0).setTextContent(timeVar);
        SettingWrite();
    }

    String getMaxSnooze(){
        return doc.getElementsByTagName("maxSnooze").item(0).getTextContent();
    }

    void setMaxSnooze(String max){
        doc.getElementsByTagName("maxSnooze").item(0).setTextContent(max);
        SettingWrite();
    }
    
    void SettingWrite(){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source;
            source = new DOMSource(doc);
            StreamResult update = new StreamResult(new File("/home/omnissiah/NetBeansProjects/JavaApplication2/src/javaapplication2/data/Snooze.xml"));
            transformer.transform(source, update);
        } catch (TransformerException e) {
                e.printStackTrace();
        }
    }
}
