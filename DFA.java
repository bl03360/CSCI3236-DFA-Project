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
		
		for(int i = 0;i<alph.size();i++) {
			System.out.println("This is the alph at " + i + " " + alph.get(i));
		}
		for(int i = 0;i<states.size();i++) {
			System.out.println("This is the state at " + i + " " + states.get(i));
		}
		for(int i = 0;i<finalStates.size();i++) {
			System.out.println("This is the final state at " + i + " " +finalStates.get(i));
		}
		for(int i = 0;i<transitions.size();i++) {
			System.out.print(transitions.get(i));
		}
		System.out.println();
		
		createDfa();
		
		for (int i = 0; i < states.size(); i++) {
			System.out.println("At state " + states.get(i) + " " + outer.get(states.get(i)));
		}

		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter a string of the alphabet, " + alph.toString() + ", to generate a proper string of the dfa.");
		String userInput = scan.next();
		
		traverseDFA(userInput);

	}

	private static void traverseDFA(String userInput) {
		ArrayList<Character> lang = new ArrayList<>();
		
		lang.add(startState);
		
	}

	private static void fileReader() {
		try {
			File file = new File("DFA.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			line = line.replaceAll("[^a-zA-Z0-9]", "");
			
			int count = 0;

			while (line != null) {
				
				while (count <= 3) {
					
					if (count == 0) {
						for (int i = 0; i < line.length(); i++) {
							alph.add(line.charAt(i));
							System.out.println(line + " " + count);
						}
					} // reads alphabet from line 1 from file
					else if (count == 1) {
						for (int i = 0; i < line.length(); i++) {
							states.add(line.charAt(i));
							System.out.println(line + " " + count);
						}
					} // reads states from line 2 from file
					else if (count == 2) {
						startState = line.charAt(0);
						System.out.println(line + " " + count);
					} // reads start state from line 3 from file
					else {
						for (int i = 0; i < line.length(); i++) {
							finalStates.add(line.charAt(i));
							System.out.println(line + " " + count);
						}
					} // reads final states
					line = br.readLine();
					line = line.replaceAll("[^a-zA-Z0-9]", "");
					count++;
				} // reads first 4 lines for the alphabet, states, start state, and final states
				
				line = line.replaceAll("[^a-zA-Z0-9]", "");
				
				for (int i = 0; i < line.length(); i++) {
					transitions.add(line.charAt(i));
					System.out.println(line + " " + count);
				} // reads transitions from file adding from rest of the lines
				
				line = br.readLine();
				
			} // while loop to read all lines of file

		} // try block
		catch (IOException error) {
			error.printStackTrace();
		} // catch block
	}// file reader method

	private static void createDfa() {
		for (int i = 0; i < states.size(); i++) {
			System.out.println(" ok new problem" + " " + states.get(i));
			outer.put(states.get(i), createTransition(states.get(i)));
		}
	}

	private static HashMap<Character, Character> createTransition(char a) {
		inner = new HashMap<Character, Character>();
		
		while (transitions.peek() != null && a == transitions.peekFirst()) {
			transitions.removeFirst();
			inner.put(transitions.poll(), transitions.poll());
		}
		System.out.println(inner.toString());
		return inner;
	}

	private void improperString() {
		System.out.println("The string you entered is not a proper language for this DFA");
	}

}
