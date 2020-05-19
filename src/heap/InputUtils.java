package heap;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class InputUtils {

	/**
	 * Displays the relevant menu
	 * 
	 * @param String Name of relevant menu
	 */
	public static void menu(String type) {
		if (type.equals("start")) {
			System.out.println("\nHello! press the following keys:");
			System.out.println("1 to create a Max-Min heap");
			System.out.println("0 to Quit");
		}
		if (type.equals("heap")) {
			System.out.println("\n------------------------------------------------------");
			System.out.println("\nWhat would you like to do next?");
			System.out.println("1 Heap-sort");
			System.out.println("2 Heap-Insert");
			System.out.println("3 Heap-Extract-Max");
			System.out.println("4 Heap-Extract-Min");
			System.out.println("5 Heap-Delete");
			System.out.println("6 Display heap as binary tree");
			System.out.println("7 Display heap as array");
			System.out.println("0 Quit");
			System.out.println("\n------------------------------------------------------");

		}
	}

	/**
	 * Prints out a message about wrong input
	 */
	public static void wrongKey() {
		System.out.println("You pressed a key that was not on the list\nPlease try again\n");
	}

	/**
	 * Creates an array that contains all the integer values read from the file
	 * 
	 * @param path A full path including the filename ,given by the user
	 * @return An ArrayList type of array, containing all the integer values read
	 *         from the file
	 */
	public static ArrayList<Integer> readFile(Path path) {
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		File file;
		try {
			file = new File(path.toString());
			Scanner scannedFile = new Scanner(file);

			while (scannedFile.hasNext()) {
				if (scannedFile.hasNextInt()) {
					intArray.add(scannedFile.nextInt());
				} else {
					scannedFile.next();
				}
			}
			scannedFile.close();
		} catch (Exception e) {
			System.out.println("There is a problem with opening the file   " + e);
		} finally {

		}
		return intArray;

	}

	// in use for author
	public static ArrayList<Integer> generateArrayFromFile(File file) throws Exception {
		ArrayList<Integer> inputArray = new ArrayList<Integer>();

		return inputArray;
	}

	// in use for author
	public static ArrayList<Integer> automateInputArray(int size, String mode) {
		ArrayList<Integer> inputArray = new ArrayList<Integer>();
		switch (mode) {
		case "dupInd":
			for (int i = 0; i < size; i++)
				inputArray.add(i, i * 2);
			break;
		case "ind":
			for (int i = 0; i < size; i++)
				inputArray.add(i, i);
		default:
			break;
		}

		return inputArray;
	}

}
