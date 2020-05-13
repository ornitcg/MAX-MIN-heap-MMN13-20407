package heap;
import java.util.ArrayList;
import java.io.*; 

/**
 * Write a description of class MaxMinTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MaxMinTester {
    public static void main(String[] args) {
    	
    	
    	ArrayList<Integer> inputArray = automateInputArray(50,"dupInd");
	       
        MaxMinHeap heap1 = new MaxMinHeap(inputArray);
        // heap1.heapInsert(200);
        heap1.heapInsert(5);
        heap1.heapInsert(67);
        int max = heap1.extractMax();
        int min = heap1.extractMin();
        heap1.heapDelete(7);

        System.out.println(heap1);
        System.out.println(max + "  " + min);

    }// end main
    private static ArrayList automateInputArray(int size, String mode) {
    	ArrayList<Integer> inputArray = new ArrayList<Integer>();
        if (mode.equals("dupInd")) {
        	for (int i = 0; i < size; i++)
                inputArray.add(i, i * 2);
			}
        return inputArray;
    }
}
