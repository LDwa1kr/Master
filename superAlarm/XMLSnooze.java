package superalarm;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.transform.TransformerConfigurationException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author omnissiah
 */
public class XMLSnooze {
    static Document doc ;
     String day = null;
    File inputFile;
	XMLSnooze() {
		//build XML parser and writer 
            try {
                inputFile = new File("data/Snooze.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(inputFile);
                System.out.println("XML constructor called");
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Something went wrong during construction of the XML reader");
            }
           
                Integer dayint = java.time.LocalDate.now().getDayOfWeek().getValue();
                switch (dayint) {   
                    case 1: day = "pon";
                            break;
                    case 2: day = "wt";
                            break;
                    case 3: day = "sr";
                            break;
                    case 4: day = "czw";
                            break;
                    case 5: day = "pt";
                            break;
                    case 6: day = "sob";
                            break;
                    case 7: day = "nd";
                            break;
                }
	}
     /**   public static void main(String[] args) {
            XMLSnooze s = new XMLSnooze();
            s.newSnooze();
            System.out.println(s.weekSnooze());
            System.out.println(XMLSnooze.getText());
            
} */
        
	void newSnooze(){
		//write new Snooze to XML
                
                Integer temp = Integer.parseInt(  
                        doc.getElementsByTagName(day).item(0).getTextContent());
               System.out.println(temp); //debug
                temp +=1 ;
                doc.getElementsByTagName(day).item(0).setTextContent(temp.toString()) ;
                System.out.println(temp); //debug
                try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult update = new StreamResult(new File("data/Snooze.xml"));
		transformer.transform(source, update);
                
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e){
                e.printStackTrace();
            }
	}
	
	public String todaySnooze(){
		// return numver of today snoozes 
            return(doc.getElementsByTagName(day).item(0).getTextContent());
        }
	public int weekSnooze(){
                //return dumber of weeks snoozes 
            ArrayList<String> Days = new ArrayList<>(Arrays.asList("pon", "wt","sr", "czw", "pt", "sob", "nd" ));
            
            int weekSnooze = 0;
            for (String o:Days) {
                weekSnooze += Integer.parseInt(
                        doc.getElementsByTagName(o).item(0).getTextContent());
            }
            return(weekSnooze);
        }
	public static String getText(){
		//get text for current alarmGraph
            return("FOO") ;
        }
}
