package heap;
import java.util.ArrayList;

/**
 * Write a description of class MaxMinTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MaxMinTester {
    public static void main(String[] args) {
        int size = 50;
        ArrayList<Integer> inputArray = new ArrayList();
        for (int i = 0; i < size; i++)
            inputArray.add(i, i * 2);
        MaxMinHeap heap1 = new MaxMinHeap(inputArray);
        for (int i = 0; i < size; i++)
            inputArray.set(i, (i + 1) * 2);
        // MaxMinHeap heap2 = new MaxMinHeap(inputArray);
        // heap1.heapInsert(200);
        heap1.heapInsert(5);
        heap1.heapInsert(67);
        int max = heap1.extractMax();
        int min = heap1.extractMin();
        heap1.heapDelete(7);

        System.out.println(heap1);
        System.out.println(max + "  " + min);

    }// end main
}
