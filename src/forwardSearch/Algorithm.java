package forwardSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;

import Model.Coordinates;
import Model.RuleNode;

public class Algorithm {
	public ArrayList<RuleNode> rules = new ArrayList<RuleNode>();
	private ArrayList<Coordinates> boxes = new ArrayList<Coordinates>();
	
	public Algorithm() {
		boxes.add(new Coordinates(0,0));
		boxes.add(new Coordinates(0,1));
		boxes.add(new Coordinates(1,0));
		boxes.add(new Coordinates(2,0));
		boxes.add(new Coordinates(2,1));
	}
	
	public void addRule(String str) {
		rules.add(new RuleNode(str));
	}
	
	public RuleNode find(String str) {
		for (int i =0; i < rules.size(); ++i) {
			RuleNode currentRule = rules.get(i);
			if (currentRule.getRHS().equals(str)) {
				return currentRule;
			}
		}
		
		return null;
	}
	
	public void backward(String str) {
		RuleNode mainRule = find(str);
		
		// Checks if the main rule is null. If it is not null, then check if the LHS of the 
		// rule follows all the rules
		if (mainRule != null) {
			ArrayList<Coordinates> lhsRules = new ArrayList<Coordinates>();
			
			lhsRules = mainRule.getLHSCoords();
			
			// Checks if the main rule has characters on the LHS
			// If it does, then get the coordinates of that character
			// MIGHT HAVE TO BE ITERATIVE
			if (mainRule.lhsHasChar()) {
				RuleNode tempNode = find(mainRule.getLHSCharacter());
				
				lhsRules.addAll(tempNode.getLHSCoords());
			}

			
			// Here we go through each and every pixels of the rule. This should be changed for different platforms
			for (int i = 0; i < boxes.size(); ++i) {	
				Coordinates testCoord = boxes.get(i);
				for (int index = 0; index < lhsRules.size(); index++) {
					Coordinates rightCoord = lhsRules.get(index);
					
					if (testCoord.equals(rightCoord))
				}
			}
			
			// At this point, character is recognized
			System.out.println("Character has been recognized");
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Charset charset = Charset.forName("US-ASCII");
		
		Path file = Paths.get("./text.txt");
		
		Algorithm working = new Algorithm();
		
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        //System.out.println(line);
		        working.addRule(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		// Do backward search
		String character = "C";
		working.backward(character);

	}

}
