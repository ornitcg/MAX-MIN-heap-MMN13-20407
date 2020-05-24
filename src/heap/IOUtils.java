package heap;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains some helper methods for the MaxMinHeap class 
 * and for the MaxMinTester class
 * 
 * @author (Ornit Cohen Gindi)
 * @version (2020b -20407)
 */
public class IOUtils {
	final public static char SPACE = ' '; // used mainly for debugging the display of the binary tree
	final public static int PAD1 = 5; // used for beautifying the display of the binary tree
	final public static int PAD2 = 3; // used for beautifying the display of the binary tree

	/**
	 * Displays the relevant menu for each string received as parameter.
	 * 
	 * @param String Name of relevant menu. options :"start" for start menu,"file" for input file menu and  "heap"
	 *               for heap menu
	 */
	public static void menu(String type) {
		if (type.equals("start")) {
			System.out.println("\nHello! press the following keys:");
			System.out.println("1 To create a Max-Min heap");
			System.out.println("0 To Quit");
			System.out.println("-----------------------------------");
			System.out.print("Your input: ");
		}
		if (type.equals("file")) {
			System.out.println("\nWhich input do you wish to use? please press the key for the chosen action");
			System.out.println("1 Default file from package: heap.txt");
			System.out.println("2 Your own file");
			System.out.println("0 To Quit");
			System.out.println("-----------------------------------");
			System.out.print("Your input: ");


		}
		if (type.equals("heap")) {
			System.out.println("\n---------------What would you like to do next?-------------------");
			System.out.println("\nplease press the key for the chosen action");
			System.out.println("1 Heap-sort");
			System.out.println("2 Heap-Insert");
			System.out.println("3 Heap-Extract-Max");
			System.out.println("4 Heap-Extract-Min");
			System.out.println("5 Heap-Delete");
			System.out.println("6 Display heap as binary tree");
			System.out.println("7 Display heap as array");
			System.out.println("0 Quit");
			System.out.println("-----------------------------------");
			System.out.print("Your input: ");		}
	}//end menu
	
	
	/**
	 * Prints out a message about wrong user input
	 */
	public static void wrongKeyMessage() {
		System.out.println("You pressed a key that was not on the list\nPlease try again\n");
	}

	
	/**
	 * Creates an array that contains all the integer values read from the file
	 * 
	 * @param path a full path including the filename ,given by the user
	 * @return An ArrayList type of array, containing all the integer values read
	 *         from the file
	 * @throws FileNotFoundException 
	 */
	public static ArrayList<Integer> readFile(Path path) throws FileNotFoundException  
	{
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		File file;
		try {
			file = new File(path.toString());
			Scanner scanFile = new Scanner(file);

			while (scanFile.hasNext()) {
				if (scanFile.hasNextInt()) {
					intArray.add(scanFile.nextInt());
				} else {
					scanFile.next();
				}
			}
			scanFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("There is a problem with opening the file   " + e);
			intArray = null;
			throw e;
		}
		return intArray;
	}//end readFile


	/**
	 * Returns a string of spaced at a length of a requested amount
	 * 
	 * @param amount The amount of space characters to string
	 * @return The String created of the wanted amount of space characters
	 */
	public static String printSpaces(int amount) {
		String spaces = "";
		for (int i = 1; i <= amount; i++)
			spaces += SPACE;
		return spaces;
	}

	/**
	 * Returns a string of a number and spaces so that the number is centered within
	 * the spaces the purpose is to help beautify the binary tree display Complexity
	 * O(1)
	 * 
	 * @param num
	 * @param pad
	 * @return
	 */
	public static String spacePad(int num, int pad)// assuming numbers have max of 3 digits
	{
		String paddedNumber = "";
		String numStr = String.valueOf(num); // turn number into string
		int len = numStr.length();
		int halfSpace = pad - len; // small numbers, considered as constants
		if (halfSpace % 2 == 0) {
			paddedNumber += printSpaces(halfSpace / 2) + num;
			paddedNumber += printSpaces(halfSpace / 2);
		} else {
			paddedNumber += printSpaces(halfSpace / 2) + num;
			paddedNumber += printSpaces(halfSpace / 2 + 1);
		}
		return paddedNumber;
	}//end spacePad
	
}//end IOUtils class