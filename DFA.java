package DefiniteFiniteAutomata;

import java.io.*;
import java.util.*;


public class DFA {

	HashMap<Character, HashMap> dfa = new HashMap<>();
	
	public static void main(String[] args) {

		ArrayList<Character> alph = new ArrayList<>();
		ArrayList<Character> states = new ArrayList<>();
		ArrayList<Character> finalState = new ArrayList<>();
		char startState = 0;	
		char marker = 0;
		
		fileReader(alph, states, finalState, startState);
		

	}

	private static void fileReader(ArrayList<Character> alph, ArrayList<Character> states, ArrayList<Character> finalState, char startState) {
		
		try {
			File file = new File("");
			FileReader fr = new FileReader(file);
			BufferedReader br = new  BufferedReader(fr);
			String line = br.readLine();
			
			int count = 0;
			
			while (line != null) {
				
				StringBuilder sb = new StringBuilder();
				sb.append(line);
				
				while(count != 3) {
					if(count == 0) {
						for(int i = 0;i<sb.length();i++) {
							if(sb.charAt(i) != '{' || sb.charAt(i) != ',' || sb.charAt(i) != '}' ) {
								alph.add(sb.charAt(i));
							}
						}
					}//reads alphabet from line 1 from file
					else if(count == 1) {
						for(int i = 0;i<sb.length();i++) {
							if(sb.charAt(i) != '{' || sb.charAt(i) != ',' || sb.charAt(i) != '}' ) {
								states.add(sb.charAt(i));
							}
						}
					}//reads states from line 2 from file
					else if(count ==  2){
						startState = sb.charAt(0);
					}//reads start state from line 3 from file
					else {
						
					}//reads final states
					sb.append("");
					line = br.readLine();
					count++;
				}//reads first 4 lines for the alphabet, states, start state, and final states
				
				
			}
		
		}
		catch(IOException error) {
			error.printStackTrace();
		}
		
	}
	
	private HashMap<E, HashMap> createDfa(HashMap dfa, ArrayList alph, ArrayList states){
		return dfa;
	}
}
