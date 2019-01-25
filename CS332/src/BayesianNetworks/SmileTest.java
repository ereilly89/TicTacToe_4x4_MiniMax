package BayesianNetworks;
import java.text.DecimalFormat;
import java.util.Scanner;

import smile.*;
import smile.learning.*;
public class SmileTest {
private static DataSet loadDataSet(String fname)
    {
        DataSet ds = new DataSet();
        System.out.println("Reading data file: " + fname);
        ds.readFile(fname);
        return ds;
    }
private static void showNetInfo(Network net)
    {
        System.out.println("Node count: " + net.getNodeCount());
        int count = 0;
        for (int h = net.getFirstNode(); h >= 0; h = net.getNextNode(h))
        {
            if (net.getParents(h).length == 0) count ++;
        }
        System.out.println("Toplevel nodes: " + count);
    }
private static void testNetworkCreation() {
 
Network net = new Network();
   
   //Create nodes for Bayesian Network****************************************//

//Create the Burglary Node and set the two outcomes True and False
   net.addNode(Network.NodeType.Cpt, "Burglary");
   net.addOutcome("Burglary", "True"); 
   net.addOutcome("Burglary", "False");
   net.deleteOutcome("Burglary", 0);
   net.deleteOutcome("Burglary", 0);
 //Create the Earthquake Node and set the two outcomes True and False   
   net.addNode(Network.NodeType.Cpt, "Earthquake");
   net.addOutcome("Earthquake", "True"); 
   net.addOutcome("Earthquake", "False");
   net.deleteOutcome("Earthquake", 0);
   net.deleteOutcome("Earthquake", 0);
 //Create the Alarm Node and set the two outcomes True and False 
   net.addNode(Network.NodeType.Cpt, "Alarm");
   net.addOutcome("Alarm", "True"); 
   net.addOutcome("Alarm", "False");
   net.deleteOutcome("Alarm", 0);
   net.deleteOutcome("Alarm", 0);
 //Create the node for when John calls and set the two outcomes True and False  
   net.addNode(Network.NodeType.Cpt, "JohnCall");
   net.addOutcome("JohnCall", "True"); 
   net.addOutcome("JohnCall", "False");
   net.deleteOutcome("JohnCall", 0);
   net.deleteOutcome("JohnCall", 0);
 //Create the node for when Mary calls and set the two outcomes True and False    
   net.addNode(Network.NodeType.Cpt, "MaryCall");
   net.addOutcome("MaryCall", "True"); 
   net.addOutcome("MaryCall", "False");
   net.deleteOutcome("MaryCall", 0);
   net.deleteOutcome("MaryCall", 0);
   
   //set connections to nodes
   net.addArc("Burglary", "Alarm");
   net.addArc("Earthquake", "Alarm");
   net.addArc("Alarm", "JohnCall");
   net.addArc("Alarm", "MaryCall");
   
   //Set probabilities for each node
   double[] aBurglaryDef = {0.001, 0.999};
   net.setNodeDefinition("Burglary", aBurglaryDef);
   
   double[] aEarthquakeDef = {0.002, 0.998};
   net.setNodeDefinition("Earthquake", aEarthquakeDef);
   
   double[] aAlarmDef = {0.95, 0.05, 0.94, 0.06, 0.29, 0.71, 0.001, 0.999};
   net.setNodeDefinition("Alarm", aAlarmDef);
   
   double[] aJohnCallDef = {0.9, 0.1, 0.05, 0.95};
   net.setNodeDefinition("JohnCall", aJohnCallDef);
   
   double[] aMaryCallDef = {0.7, 0.3, 0.01, 0.99};
   net.setNodeDefinition("MaryCall", aMaryCallDef);
   
   // Writing the network to a file:
   net.writeFile("Assignment5.dsl");
 
   net.updateBeliefs();
   
   //Create a scanner and ask user for the evidence
   Scanner input = new Scanner(System.in);
   System.out.println("Type '1' if: John Calls");
   System.out.println("Type '2' if: Mary Calls");
   System.out.println("Type '3' if: Both");
   System.out.print("Evidence: ");
   int evidence = input.nextInt();
	
   switch(evidence) {
   case 1:
	   net.setEvidence("JohnCall", "True");
	   System.out.println("Evidence set for John.");
	   break;
   case 2:
	   net.setEvidence("MaryCall", "True");
	   System.out.println("Evidence set for Mary.");
	   break;
   case 3:
	   net.setEvidence("JohnCall", "True");
	   net.setEvidence("MaryCall", "True");
	   System.out.println("Evidence set for both John and Mary.");
	   break;
   }
   
   //Update beliefs
   net.updateBeliefs();
   
   //Create new decimal format for calculated probabilities
   DecimalFormat df = new DecimalFormat("#.###");
   
   //Create arrays of values within nodes after the evidence is set
   double[] burglaryValues = net.getNodeValue("Burglary");
   double[] earthquakeValues = net.getNodeValue("Earthquake");
   double[] alarmValues = net.getNodeValue("Alarm");
  
   //Locate and save the index of the 'True' value for Burglary
   String[] aBurglaryOutcomeIDs = net.getOutcomeIds("Burglary");
   int burglaryTrueIndex;
   for(burglaryTrueIndex=0;burglaryTrueIndex<aBurglaryOutcomeIDs.length;burglaryTrueIndex++) {
	   if(aBurglaryOutcomeIDs[burglaryTrueIndex].equals("True")) 
		   break;
   }
   //Locate and save the index of the 'True' value for Earthquake
   String[] aEarthquakeOutcomeIDs = net.getOutcomeIds("Earthquake");
   int earthquakeTrueIndex;
   for(earthquakeTrueIndex=0;earthquakeTrueIndex<aEarthquakeOutcomeIDs.length;earthquakeTrueIndex++) {
	   if(aEarthquakeOutcomeIDs[earthquakeTrueIndex].equals("True")) 
		   break;
   }
   //Locate and save the index of the 'True' value for Alarm
   String[] aAlarmOutcomeIDs = net.getOutcomeIds("Alarm");
   int alarmTrueIndex;
   for(alarmTrueIndex=0;alarmTrueIndex<aAlarmOutcomeIDs.length;alarmTrueIndex++) {
	   if(aAlarmOutcomeIDs[alarmTrueIndex].equals("True")) 
		   break;
   }
   
   //Print relevant probabilities to the user
   try {
	   System.out.println("Probability of a burglary: "+df.format(burglaryValues[burglaryTrueIndex]));
	   System.out.println("Probability of an earthquake: "+df.format(earthquakeValues[earthquakeTrueIndex]));
	   System.out.println("Probability of the alarm being sounded: "+df.format(alarmValues[alarmTrueIndex]));
	   net.setEvidence("Alarm", "True");
	   net.updateBeliefs();
   }catch(Exception e) {
	   //Notify user that there is an error finding 'True' values for at least one of the three nodes
	   System.out.println("ERROR: couldn't find index of 'True' for at least one of three nodes");
   }
   burglaryValues = net.getNodeValue("Burglary");
   earthquakeValues = net.getNodeValue("Earthquake");
   System.out.println("If the alarm IS sounded, the chance of a burglary is: "+df.format(burglaryValues[burglaryTrueIndex]));
   System.out.println("If the alarm IS sounded, the chance of an earthquake is: "+df.format(earthquakeValues[earthquakeTrueIndex]));
   
   net.clearAllEvidence();
   net.updateBeliefs();
}
public static void main(String args[]) {
System.out.println(System.getProperty("java.library.path"));
System.load("C:\\Users\\Evan\\Downloads\\jsmile-1.2.1-win64-academic\\jsmile.dll");
//License issued by BayesFusion Licensing Server
//This code must be executed before any other jSMILE object is created
new smile.License(
	"SMILE LICENSE 43a8876c 18a7146b 98069d80 " +
	"THIS IS AN ACADEMIC LICENSE AND CAN BE USED " +
	"SOLELY FOR ACADEMIC RESEARCH AND TEACHING, " +
	"AS DEFINED IN THE BAYESFUSION ACADEMIC " +
	"SOFTWARE LICENSING AGREEMENT. " +
	"Serial #: 8mnktdzrfxxdew9rgqv3kck6w " +
	"Issued for: Evan Reilly (reillyem11@uww.edu) " +
	"Academic institution: University of Wisconsin-Whitewater " +
	"Valid until: 2018-09-16 " +
	"Issued by BayesFusion activation server",
	new byte[] {
	38,38,-64,-52,-42,82,74,-82,55,-119,18,-11,-15,43,66,-77,
	-59,-31,-55,-74,95,-112,84,-33,65,-110,93,10,17,-37,42,-47,
	1,73,84,43,76,-114,101,124,83,101,-57,-100,-94,23,-106,99,
	47,-60,-24,-73,60,63,-59,107,-94,-86,-124,85,58,-22,-121,99
	}
);

testNetworkCreation();
}
}