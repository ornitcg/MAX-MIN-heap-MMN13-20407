package heap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
//import heap.InputUtils.*;

/**
 * Write a description of class MaxMinTester here.
 *
 * @author (Ornit Cohen Gindi)
 * @version (2020b -20407)
 */
public class MaxMinTester {
	public static void main(String[] args) throws Exception {
		Scanner scanInput = new Scanner(System.in);
		int request;
		int choice = 2; //
		while (choice != 0 && choice != 1) {
			InputUtils.menu("start"); // show first menu;
			try {
				choice = Integer.parseInt(scanInput.nextLine());
				if (choice != 0 && choice != 1)
					InputUtils.wrongKey();
			} catch (NumberFormatException e) {
				InputUtils.wrongKey();
			}
		}

		if (choice == 0) {
			System.out.println("Bye Bye");
			scanInput.close();
			return;
		} else {
			System.out.println("Please insert the full path to your txt file:\n");
			// Path path = Paths.get(scanInput.nextLine());
			Path path = Paths.get("testFiles/heap.txt");
			ArrayList<Integer> intArray = InputUtils.readFile(path);
			MaxMinHeap heap = new MaxMinHeap(intArray); // congrats! we have a heap!
			System.out.println("CONGRATS! YOU HAVE A MAX-MIN HEAP\n");
			while (choice != 0) {
				InputUtils.menu("heap"); // show second menu
				try {
					choice = Integer.parseInt(scanInput.nextLine());
				} catch (NumberFormatException e) {
					InputUtils.wrongKey();
				}

				// System.out.println(choice); // debug
				switch (choice) {
				case 1: {// sort using heap
					System.out.println("\nThe sorted array of values using heapsort:\n" + heap.heapSort() + "\n");
					break;
				}
				case 2: {// insert new value
					System.out.println("\nWhat Integer would you like to Insert?\n");
					request = Integer.parseInt(scanInput.nextLine());
					heap.heapInsert(request);
					break;
				}
				case 3: {// extract max
					Integer max = heap.extractMax();
					if (max == null)
						System.out.println("\nHEAP IS EMPTY! CANNOT EXTRACT\n");
					else
						System.out.println("\nExtracted max value from heap. The max value was: " + max + "\n");
					break;
				}
				case 4: {// extract min
					Integer min = heap.extractMin();
					if (min == null)
						System.out.println("\nHEAP IS EMPTY! CANNOT EXTRACT\n");
					else {
						System.out.println("\nExtracted min value from heap. The min value was: " + min + "\n");
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
							System.out.println("\nDeletion was not successful! the requested index is out of bound\n");

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
					System.out.println("Bye Bye");
					scanInput.close();

					return;
				}
				default: {
					InputUtils.wrongKey();
					break;
				} // end default
				}// end switch
			} // end while
		} // end else
		scanInput.close();

	}// end main

}

//C:/Users/Ornit/Desktop/heap2.txt
//heap/testFiles/heap.txt
