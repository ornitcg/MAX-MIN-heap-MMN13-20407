package heap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
//import heap.InputUtils.*;

/**
 * Here a user can give a text file , full of integers as input and play with
 * the MaxMinHeap methods on that input
 *
 * @author (Ornit Cohen Gindi)
 * @version (2020b -20407)
 */
public class MaxMinTester {
	public static void main(String[] args) throws Exception {
		Scanner scanInput = new Scanner(System.in);
		int request;
		ArrayList<Integer> intArray = null;
		int choice = 0; // initialization

		IOUtils.menu("start"); // show first menu;
		while (choice != 1) {
			try {
				choice = Integer.parseInt(scanInput.nextLine()); //read user input
				if (choice == 0) {
					System.out.println("So soon? You did not see anything yet . Bye Bye!");
					scanInput.close();
					return; // quit program
				}
			} catch (NumberFormatException e) {
				IOUtils.wrongKey();
				IOUtils.menu("start"); // try again
			}
		} // end while

		IOUtils.menu("file");
		Path path = Paths.get("testFiles/heap.txt"); // set default path
		while (choice != 0) {
			try {
				choice = Integer.parseInt(scanInput.nextLine()); //read user input
				System.out.println(choice);
				switch (choice) {
				case 1: {// sort using heap
					System.out.println("OK! Loading heap.txt"); // default path is used here
					choice = 0; // to stop the while loop
					break;
				}
				case 2: {// file from path
					System.out.println("Please insert full path to your txt file:\n");
					path = Paths.get(scanInput.nextLine()); //read user input
					choice = 0; // to stop the while loop
					break;
				}
				case 0: {// sort using heap
					System.out.println("\nSo soon? You did not see anything yet . Bye Bye!");
					scanInput.close();
					return;
				}
				default: {
					IOUtils.wrongKey();
					IOUtils.menu("file"); // try again
					break;
				}

				}// end switch
			} catch (NumberFormatException e) {
				IOUtils.wrongKey(); // output message
				IOUtils.menu("file"); // try again
			} // end try-catch
		} // end while

		intArray = IOUtils.readFile(path); // load array from path
		if (intArray == null) // loading failed, exit program
			return;

		MaxMinHeap heap = new MaxMinHeap(intArray); // congrats! we have a heap!
		System.out.println("CONGRATS! YOU HAVE A MAX-MIN HEAP\n");

		choice = 1; // to start next while loop

		while (choice != 0) {
			IOUtils.menu("heap"); // show second menu

			try {
				choice = Integer.parseInt(scanInput.nextLine()); //read user input

				// System.out.println(choice); // debug
				switch (choice) {
				case 1: {// sort using heap
					System.out.println("\nThe sorted array of values using heapsort:\n" + heap.heapSort() + "\n");
					break;
				}
				case 2: {// insert new value
					System.out.println("\nWhat Integer would you like to Insert?\n");
					request = Integer.parseInt(scanInput.nextLine()); //read user input
					heap.heapInsert(request);
					break;
				}
				case 3: {// extract max
					System.out.println("\nYou chose ExtractMax\n");
					Integer max = heap.extractMax(); //returning the extracted value
					if (max == null)
						System.out.println("\nHEAP IS EMPTY! CANNOT EXTRACT\n");
					else
						System.out.println("\nExtracted max value from heap. The extracted value was: " + max + "\n");
					break;
				}
				case 4: {// extract min
					System.out.println("\nYou chose ExtractMin\n");

					Integer min = heap.extractMin(); //returning the extracted value
					if (min == null)
						System.out.println("\nHEAP IS EMPTY! CANNOT EXTRACT\n");
					else {
						System.out.println("\nExtracted min value from heap. The extracted value was: " + min + "\n");
					}
					break;
				}
				case 5: {// heap delete
					if (heap.isEmpty())
						System.out.println("\nHEAP IS EMPTY! NOTHING TO DELETE\n");
					else {
						System.out.println("\nPlease insert the index of the value you wish to remove?\n");
						request = Integer.parseInt(scanInput.nextLine());
						if (heap.heapDelete(request))
							System.out.println("\nDeletion was successful!\n");
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
					IOUtils.wrongKey(); //any other number
					break;
				} // end default
				}// end switch
			} catch (NumberFormatException e) { // case input is not an integer
				IOUtils.wrongKey();
			}
		} // end while
		scanInput.close();

	}// end main

}

//C:/Users/Ornit/Desktop/heap2.txt
//heap/testFiles/heap.txt
