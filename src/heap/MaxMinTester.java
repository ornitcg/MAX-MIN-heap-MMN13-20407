package heap;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the user interface for creating and changing a MaxMinHeap.
 * 
 *
 * @author (Ornit Cohen Gindi)
 * @version (2020b -20407)
 */
public class MaxMinTester {
	final static  String DEFAULT_PATH = "testFiles/heap.txt";
	public static void main(String[] args) {
		Scanner scanInput = new Scanner(System.in);
		int reqIndex;
		ArrayList<Integer> intArray = null;
		int choice = 0; // initialization

		IOUtils.menu("start"); // show first menu;
		while (choice != 1) {
			try {
				choice = Integer.parseInt(scanInput.nextLine()); // read user input
				if (choice == 0) { // user quits
					System.out.println("So soon? You did not see anything yet . Bye Bye!");
					scanInput.close();
					return; // quit program
				}
			} catch (NumberFormatException e) {
				IOUtils.wrongKeyMessage(); //
				IOUtils.menu("start"); // try again
			}
		} // end while

		IOUtils.menu("file");
		Path path = Paths.get(DEFAULT_PATH); // set default path
		while (choice != 0) {
			try {
				choice = Integer.parseInt(scanInput.nextLine()); // read user input
				System.out.println(choice);
				switch (choice) {
				case 1: {// sort using heap
					System.out.println("\nOK! Loading heap.txt"); // default path is used here
					path = Paths.get(DEFAULT_PATH); // read user input
					choice = 0; // to stop the while loop
					break;
				}
				case 2: {// file from path
					System.out.println("Please insert full path to your txt file:\n");
					path = Paths.get(scanInput.nextLine()); // read user input
					choice = 0; // to stop the while loop
					break;
				}
				case 0: {// sort using heap
					System.out.println("\nSo soon? You did not see anything yet . Bye Bye!");
					scanInput.close();
					return;
				}
				default: {
					IOUtils.wrongKeyMessage();
					IOUtils.menu("file"); // try again
					break;
				}

				}// end switch
			} catch (NumberFormatException e) {
				IOUtils.wrongKeyMessage(); // output message
				IOUtils.menu("file"); // try again
			} // end try-catch
			
			try {
				intArray = IOUtils.readFile(path); // load array from path
			}
			catch (FileNotFoundException e){
				IOUtils.menu("file"); // try again
				choice = 1; // to make while loop continue
			}
		} // end while
		
		
//		if (intArray == null) // loading failed, exit program
//			return;

		MaxMinHeap heap = new MaxMinHeap(intArray); // congrats! we have a heap!
		if (heap.isEmpty()) {
			System.out.println("No values were found in the input file. sorry , your heap is empty.\n");
		} else
			System.out.println("CONGRATS! YOU HAVE A MAX-MIN HEAP! To see the heap choose 6 or 7\n");

		choice = 1; // to start next while loop

		while (choice != 0) {
			IOUtils.menu("heap"); // show second menu

			try {
				choice = Integer.parseInt(scanInput.nextLine()); // read user input

				// System.out.println(choice); // debug
				switch (choice) {
				case 1: {// sort using heap
					if (heap.isEmpty())
						System.out.println("\nHEAP IS EMPTY! NOTHING TO SORT\n");
					else
						System.out.println("\nThe sorted array of values using heapsort:\n" + heap.heapSort() + "\n");
					break;
				}
				case 2: {// insert new value
					System.out.println("\nWhat Integer would you like to Insert?\n");
					reqIndex = Integer.parseInt(scanInput.nextLine()); // read user input
					heap.heapInsert(reqIndex);
					System.out.println("\nInsertion of " + reqIndex + " was successful!\n");
					break;
				}
				case 3: {// extract max
					System.out.println("\nYou chose ExtractMax");
					Integer max = heap.extractMax(); // returning the extracted value
					if (max == null)
						System.out.println("HEAP IS EMPTY! CANNOT EXTRACT\n");
					else
						System.out.println("Extracted max value from heap. The extracted value was: " + max + "\n");
					break;
				}
				case 4: {// extract min
					System.out.println("\nYou chose ExtractMin");

					Integer min = heap.extractMin(); // returning the extracted value
					if (min == null)
						System.out.println("HEAP IS EMPTY! CANNOT EXTRACT\n");
					else {
						System.out.println("Extracted min value from heap. The extracted value was: " + min + "\n");
					}
					break;
				}
				case 5: {// heap delete
					if (heap.isEmpty())
						System.out.println("\nHEAP IS EMPTY! NOTHING TO DELETE\n");
					else {
						System.out.println("\nPlease insert the index of the value that you wish to remove?\n");
						reqIndex = Integer.parseInt(scanInput.nextLine());
						Integer delVal = heap.heapDelete(reqIndex);
						if (delVal != null)
							System.out.println("\nDeletion of index " + reqIndex
									+ " was successful! the extracted value was " + delVal);
						else
							System.out.println("\nDeletion failed! the requested index is out of bound\n");
					}
					break;
				}
				case 6: {// display as tree
					System.out.println(heap); // show the heap after each procedure;
					break;
				}
				case 7: {// display as array
					heap.showHeapAsArray();
					// System.out.println(heap._heap);

					break;
				}
				case 0: {// quit
					System.out.println("That was fun! Bye Bye!");
					scanInput.close();
					return;
				}
				default: {
					IOUtils.wrongKeyMessage(); // any other number
					break;
				} // end default
				}// end switch
			} catch (NumberFormatException e) { // case input is not an integer
				IOUtils.wrongKeyMessage();
			}
		} // end while
		scanInput.close();

	}// end main

}
