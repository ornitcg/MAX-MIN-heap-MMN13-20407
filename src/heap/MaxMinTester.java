package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Write a description of class MaxMinTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MaxMinTester {
	public static void main(String[] args) throws Exception {
				
		
		// Path path1 = Paths.get("c:\\Users\\Ornit\\Desktopheap.txt");
		// Path path2 = Paths.get("src/testFiles/heap.txt");
		// ArrayList<Integer> inputArray = automateInputArray(50, "dupInd");
		//ArrayList<Integer> intArray = new ArrayList<Integer>(readFile());
		
		ArrayList<Integer> intArray = readFile();
		MaxMinHeap heap1 = new MaxMinHeap(intArray);

		heap1.heapInsert(200);
//		heap1.heapInsert(5);
//		heap1.heapInsert(67);
		int max = heap1.extractMax();
//		int min = heap1.extractMin();
		//heap1.heapDelete(7);

		System.out.println(heap1);
		// System.out.println(max + " " + min);

	}// end main

	private static ArrayList<Integer> readFile() {
		System.out.println("Please insert the full path to your txt file:");
		Scanner scan = new Scanner(System.in);
		
		Path path = Paths.get(scan.nextLine());
		System.out.println(path);
		
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		File file;
		
		try {
			file = new File(path.toString());
			Scanner scannedFile = new Scanner(file);

			while (scannedFile.hasNext()) {
				//System.out.println(scannedFile.nextLine());
				if (scannedFile.hasNextInt()) {
					intArray.add(scannedFile.nextInt());
				} else {
					scannedFile.next();
				}
			}
			System.out.println(intArray);
		} catch (Exception e) {
			System.out.println("There is a problem with opening the file   "+e );
		}
		finally {
			return intArray;

		 }
	}

	private static ArrayList automateInputArray(int size, String mode) {
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

	private static ArrayList generateArrayFromFile(File file) throws Exception {
		ArrayList<Integer> inputArray = new ArrayList<Integer>();

		return inputArray;
	}
}

//C:/Users/Ornit/Desktop/heap.txt
