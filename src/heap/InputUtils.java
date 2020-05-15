package heap;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class InputUtils {

	public static char startMenu() {
		System.out.println("Hello! press the following keys:");
		System.out.println("1 For creating a Max-Min heap");
		System.out.println("0 Quit");
		Scanner scan = new Scanner(System.in);
		char choice = scan.next().charAt(0);
		scan.close();
		return choice;
	}

	public static char heapMenu() {
		System.out.println("What would you like to do next?");
		System.out.println("1 Heap-sort");
		System.out.println("2 Heap-Insert");
		System.out.println("3 Heap-Extract-Max");
		System.out.println("4 Heap-Extract-Min");
		System.out.println("5 Heap-Delete");
		System.out.println("6 Display heap as binary tree");
		System.out.println("7 Display heap as array");
		System.out.println("0 Quit");
		Scanner scan = new Scanner(System.in);
		char choice = scan.next().charAt(0);
		scan.close();
		return choice;
	}

	public static ArrayList<Integer> readFile() {
		System.out.println("Please insert the full path to your txt file:");
		Scanner pathScan = new Scanner(System.in);
		String inputPath="";
		if (pathScan.hasNextLine()) {
			inputPath = pathScan.nextLine();
			System.out.println(pathScan);
			pathScan.close();
		}
		else System.out.println("bla");

		Path path = Paths.get(inputPath);
		// System.out.println(path); // FOR DEBUGGING

		ArrayList<Integer> intArray = new ArrayList<Integer>();
		File file;

		try {
			file = new File(path.toString());
			Scanner scannedFile = new Scanner(file);

			while (scannedFile.hasNext()) {
				// System.out.println(scannedFile.nextLine());
				if (scannedFile.hasNextInt()) {
					intArray.add(scannedFile.nextInt());
				} else {
					scannedFile.next();
				}
			}
			scannedFile.close();
			// System.out.println(intArray);// FOR DEBUGGING
		} catch (Exception e) {
			System.out.println("There is a problem with opening the file   " + e);
		} finally {

		}
		return intArray;

	}

	public static ArrayList<Integer> generateArrayFromFile(File file) throws Exception {
		ArrayList<Integer> inputArray = new ArrayList<Integer>();

		return inputArray;
	}

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
