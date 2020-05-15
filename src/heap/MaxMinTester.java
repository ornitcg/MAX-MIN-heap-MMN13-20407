package heap;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Write a description of class MaxMinTester here.
 *
 * @author (Ornit Cohen Gindi)
 * @version (2020b -20407)
 */
public class MaxMinTester {
	public static void main(String[] args) throws Exception {
		int request;
		char choice = InputUtils.startMenu();	
		if (choice == '0') {
			System.out.println("Bye Bye");
			return;
		}
		else {

			ArrayList<Integer> intArray = InputUtils.readFile();
			MaxMinHeap heap = new MaxMinHeap(intArray); //congrats! we have a heap!
			
			while (choice != '0') {
				choice = InputUtils.heapMenu();
				Scanner userScanner = new Scanner(System.in);


				switch (choice) {
					case '1':{
						//heap.heapSort();
						break;
					}
					case '2':{
						System.out.println("What Ineger would you like to Insert?");
						request = userScanner.nextInt();
						heap.heapInsert(request);
						break;
					}
					case '3':{
						heap.extractMax();
						break;
					}
					case '4':{
						heap.extractMin();
						break;
					}
					case '5':{
						System.out.println("Please insert the index of the value you wish to remove?");
						request = userScanner.nextInt();
						heap.heapDelete(request);
						break;
					}
					case '6':{
						System.out.println("Display Max-Min heap as a binary tree:\n");
						System.out.println(heap); //show the heap after each procedure;
						break;
					}
					case '7':{
						System.out.println("Display Max-Min heap as an array:\n");
//						heap.showHeapAsArray();
						//System.out.println(heap._heap);

						break;
					}
					case '0':{
						System.out.println("Bye Bye");
						userScanner.close();
						return;
					}
					default:{
						System.out.println("You presed a key that was not on the list");
						break;
					}//end default
				}//end switch
				choice = InputUtils.heapMenu();
				userScanner.close();
			}//end while
		}//end else
	}// end main


}


//System.out.println(heap1);
// System.out.println(max + " " + min);

//C:/Users/Ornit/Desktop/heap2.txt
//Path path1 = Paths.get("c:\\Users\\Ornit\\Desktopheap.txt");
// Path path2 = Paths.get("src/testFiles/heap.txt");
// ArrayList<Integer> inputArray = automateInputArray(50, "dupInd");
// ArrayList<Integer> intArray = new ArrayList<Integer>(readFile());
