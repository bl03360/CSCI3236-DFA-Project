package DefiniteFiniteAutomata;

import java.io.*;
import java.util.*;


public class DFA {

	private static HashMap<Character, Character> inner;
	private static HashMap<Character, HashMap<Character, Character>> outer = new HashMap<>();
	private static ArrayList<Character> alph = new ArrayList<>();
	private static ArrayList<Character> states = new ArrayList<>();
	private static ArrayList<Character> finalStates = new ArrayList<>();
	private static LinkedList<Character> transitions = new LinkedList<>();
	private static char startState;
	
	
	public static void main(String[] args) {
	
		char marker = 0;
		
		fileReader();
		createDfa();
		
		Scanner scan = new Scanner(System.in);
		

	}

	private static void fileReader() {
		try {
			File file = new File("");
			FileReader fr = new FileReader(file);
			BufferedReader br = new  BufferedReader(fr);
			String line = br.readLine();
			
			int count = 0;
			
			while (line != null) {
				
				line.replaceAll("[^a-zA-Z0-9]+", "");
				StringBuilder sb = new StringBuilder();
				sb.append(line);
				
				while(count != 3) {
					if(count == 0) {
						for(int i = 0;i<sb.length();i++) {
							alph.add(sb.charAt(i));
						}
					}//reads alphabet from line 1 from file
					else if(count == 1) {
						for(int i = 0;i<sb.length();i++) {
							states.add(sb.charAt(i));
						}
					}//reads states from line 2 from file
					else if(count ==  2){
						startState = sb.charAt(0);
					}//reads start state from line 3 from file
					else {
						for(int i = 0;i<sb.length();i++) {
							finalStates.add(sb.charAt(i));
						}
					}//reads final states
					sb.append("");
					line = br.readLine();
					count++;
				}//reads first 4 lines for the alphabet, states, start state, and final states
				
				for(int i = 0;i<sb.length();i++) {
						transitions.add(sb.charAt(i));
				}//reads transitions from file adding from rest of the lines
				
			}//while loop to read all lines of file
		
		}//try block
		catch(IOException error) {
			error.printStackTrace();
		}//catch block	
	}//file reader method
	
	private static void createDfa() {
		for(int i = 0;i<alph.size();i++) {
			outer.put(alph.get(i), createTransition(alph.get(i)));
		}
		
	}

	private static HashMap<Character, Character> createTransition(char a) {
		inner = new HashMap<Character, Character>();
		
		while(a == transitions.peekFirst()) {
			transitions.removeFirst();
			inner.put(transitions.poll(), transitions.poll());
		}
		return inner;
	}
	
	private void improperString() {
		System.out.println("The string you entered is not a proper language for this DFA");
	}

	
}
