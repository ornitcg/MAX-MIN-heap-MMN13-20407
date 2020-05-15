package heap;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * MaxMinHeap is a data structure of an almost full binary tree 
 * in which, all values in an even levels are larger than values in lower levels
 * and values in odd levels are smaller than all values in lower levels.
 * this class builds such a heap and maintains it.
 * 
 * @author (Ornit Cohen Gindi)
 * @version (20407-2020b)
 */
public class MaxMinHeap {
    protected ArrayList<Integer> _heap; // an array that represents the MAX-MIN heap
    protected int _heapSize; // the size of the heap
    final static char SPACE = ' '; //used mainly for debugging the display of the binary tree
    final static int PAD = 5; //used for beutifyig the display of the binary tree 
    /**
     * Constructor of MaxMinHeap from array of integers given as parameter.
     * Complexity O(nlgn)
     * @param intArray An array of integers
     */
    public MaxMinHeap(ArrayList<Integer> intArray)
    {
        _heap = new ArrayList<>(intArray);
        _heapSize = _heap.size();
        buildHeap();

    }//end constructor

    /**
     * Copy constructor
     * @param other The heap to copy
     */
    public MaxMinHeap(MaxMinHeap other)
    {
        _heap = new ArrayList<>(other._heap);
        _heapSize = other._heapSize;
    }//end constructor

    /**
     * Creates a MaxMinHeap from array of integers
     * Complexity O(nlgn)
     */
    private void buildHeap()
    {
        for(int node = 0; node < _heapSize; node++)
            if (indexDepth(node)%2 == 1)// all the MIN first
            {
                heapify(node);
                //System.out.println("heapify for node :" + node); 
                //System.out.println(this);
            }

        for(int node = _heapSize-1; node >=0 ; node--)
            if (indexDepth(node)%2 == 0) //all the MAX second
            {
                heapify(node);
                //System.out.println("heapify for node :" + node);
                //System.out.println(this);
            }      
    }//end buildHeap

    /**
     * Corrects  the heap from a specific index. assuming the rest of the heap is correct.
     * Complexity O(lgn)
     * @param ind index of array representing the vinary tree heap
     **/
    public void heapify(int ind)
    {
        //System.out.println("heapify : \n"+this);
        int depth = indexDepth(ind);
        int maxChild = maxChild(ind);           //saving index
        int maxGrandChild = maxGrandChild(ind); //saving index

        if (depth % 2 == 0){ //if on MAX level
            if (!hasChild(ind) && _heap.get(parent(ind)) > _heap.get(ind))
            {//ind is at the bottom of the tree
                swap(_heap, ind , parent(ind));
                heapify(parent(ind));
            } 
            else if (hasGrandchild(ind) && maxGrandChild != ind)
            {
                swap(_heap, ind, maxGrandChild );
                heapify(maxGrandChild);
            }//end if
            else if(_heap.get(ind) < _heap.get(maxChild)){// compare to children that may be
                swap(_heap, ind , maxChild);
                heapify(maxChild);
            }
        } //end if MAX
        else // if on MIN level
        {
            int grandparent =  (ind+1)/4 -1;
            if (grandparent < 1) //case of no grandparent
                return;
            if (_heap.get(ind) < _heap.get(grandparent))
            {
                swap(_heap, ind , grandparent);
                heapify(grandparent);
            }
        } //end if MIN
    }

    /**
     * Removes the node of the maximum value from heap
     * @return The maximum value that use to be in heap before its removal
     */
    public int extractMax(){
        int max = _heap.get(0);
        swap(_heap,0, _heapSize-1);
        _heap.remove(_heapSize-1);
        _heapSize -=1;
        heapify(0);
        return max;
    }

    /**
     * Removes the node of the minimum value from heap
     * @return The minimum value that use to be in heap before its removal
     */
    public int extractMin(){
        int min = getMinInd();
        swap(_heap,min, _heapSize-1);
        _heap.remove(_heapSize-1); //remove the min
        _heapSize -=1;
        bubbleUp(min);
        return min;
    }

    /**
     *   Removes the node of the requested index from heap
     */
    public void heapDelete(int ind)
    {
        int max = _heap.get(0);
        _heap.set(ind, max+1); // mark the wanted to be removed index
        bubbleUp(ind);
        extractMax();
    }

    public void heapInsert(int key){
        int max = _heap.get(0); //keep max for later
        _heap.set(0, key);
        heapify(0);  // O(lgn)
        _heap.add(_heapSize, max); 
        _heapSize +=1;
        bubbleUp(_heapSize-1); //O(lgn)
    }

    public int getMaxInd(){
        return 0;
    }

    public int getMinInd(){
        if (_heap.get(1) < _heap.get(2))
            return 1;
        return 2;
    }

    private void bubbleUp(int ind) //to  play on last element of heap (always last)
    {
        int depth = indexDepth(ind);
        int minGrandChild = minGrandChild(ind);
        int minChild = minChild(ind);
        if (depth % 2 == 0){ //if on MAX level
            int grandparent =  (ind+1)/4 -1;
            if (grandparent < 0) //case of no grandparent
                return;
            if (_heap.get(ind) > _heap.get(grandparent))
            {
                swap(_heap, ind , grandparent);
                bubbleUp(grandparent);
            }
        }
        else if (!hasChild(ind) && _heap.get(parent(ind)) < _heap.get(ind))
        {//ind is at the bottom of the tree
            swap(_heap, ind , parent(ind));
            bubbleUp(parent(ind));
        } 
        else if(hasGrandchild(ind) && _heap.get(ind) > minGrandChild(ind))
        {
            swap(_heap, ind, minGrandChild);
            bubbleUp(minGrandChild);
        }
        else if(hasChild(ind) && _heap.get(ind) > minChild)
        {
            swap(_heap, ind, minChild);
            bubbleUp(minChild);
        }

    }

    /**
     * Gets the value of the heap , that is at a given index
     * @param index An integer
     * @return The value at index
     */
    public int getValue(int index)
    {
        return _heap.get(index);
    }

    /**
     * Buildes a String that displays the MaxMinHeap as a binary tree
     * @return String That displays the MaxMinHeap as a binary tree
     */
    public String toString(){
        String heapStr = "";
        int finalDepth = heapDepth();
        int height;
        int index = 0;        
        for(int depth = 0 ; depth <= finalDepth ; depth++) // loop for each row of the tree
        {
            height = finalDepth - depth; //starts with 0;
            int unitNumber = (int)Math.pow(2,height); // spaces that come before thr numbers in each row
            int subTreeWidth = unitNumber*PAD + unitNumber-1;
            int spacesBefore = subTreeWidth/2 - PAD/2; // spaces to print befor each row of numbers
            int spacesBetween = 2 * spacesBefore +1; //spaces to print between the tree numbers
            if (depth%2 == 0)
                heapStr += depth+ " MAX ";
            else heapStr += depth+ " MIN ";
            heapStr += printSpaces(spacesBefore);
            int nodes = maxNodesInLevel(depth);
            for(int node = 1; node <= nodes; node++) // loop on the number of nodes in a row
            {
                if (index < _heapSize)
                    heapStr += spacePad(_heap.get(index),PAD);
                if (node < nodes)
                    heapStr += printSpaces(spacesBetween);
                index++;
            }
            heapStr += "\n\n"; // a row of space between levels
        }
        return new String(heapStr);
    }
    // COMMENT FOR LATER leaves number is ceiling of n/2
    /**
     * Displays the MaxMinHeap as an array
     * Complexity O(n) (n is the length of the array representing the heap)
     */
    public void showHeapAsArray(){
        String heapStr = "";
        for (int i=0; i<_heapSize; i++)
            if (i == _heapSize-1 )
                heapStr +=  _heap.get(i);
            else
                heapStr += _heap.get(i) + ", ";
        System.out.println(heapStr);
    }

    /**
     * Calculates the maximum potential amount of nodes in tree from root to a specific depth
     * @param depth a level deep in the binary tree
     * @return number of maximum nodes counted from root to depth
     */
    private static int maxNodesInDepth(int depth)
    {
        return (int)Math.pow(2,depth+1)-1;
    }

    /**
     * Calculates the number of levels of the binary tree, counted from root (starting from 0)
     * @return number of levels of the binary tree*/
    private int heapDepth()
    {
        return indexDepth(_heapSize);
    }

    /**
     * Calculates if a value in the heap binary tree, has any grandchilden. 
     * (Complexity O(1))
     * @param ind an index at the array that represents the binary tree
     * @return true If the value has at lease one grandchild
     */
    private boolean hasGrandchild(int ind)
    {
        return (ind+1)*4 <= _heapSize;
    }

    /**
     * Calculates if a value in the heap binary tree, has any children. 
     * (Complexity O(1))
     * @param ind an index at the array that represents the binary tree
     * @return true If the value has at lease one child
     */
    private boolean hasChild(int ind)
    {
        return (ind+1)*2 <= _heapSize;
    }

    /**
     * Calculates the max value between a given index and its two sons (or son). 
     * (Complexity O(1))
     * @param ind index to calculate for
     * @return index of the maximum value between ind and its sons
     */
    private int maxChild(int ind)
    {
        int left = leftSon(ind); //left already considers the 0
        int right = rightSon(ind); //right already considers the 0
        int max = ind; 
        if(right < _heapSize && _heap.get(right) > _heap.get(max))
            max=right;
        if(left < _heapSize && _heap.get(left) > _heap.get(max))
            max=left;   
        return max;
    }

    /**
     * Calculates the min value between a given index and its two sons (or son). 
     * (Complexity O(1))
     * @param ind index to calculate for
     * @return index of the minimum value between ind and its sons
     */
    private int minChild(int ind)
    {
        int left = leftSon(ind);
        int right = rightSon(ind);
        int min = ind; 
        if(right < _heapSize && _heap.get(right) < _heap.get(min))
            min=right;
        if(left < _heapSize && _heap.get(left) < _heap.get(min))
            min=left;   
        return min;
    }

    /**
     * Calculates the max value between a given index and its four possible grandchildren (or less).
     * Complexity O(1)
     * @param ind index to calculate for
     * @return index of thee maximum value between ind and its grandchildren
     */
    private int maxGrandChild(int ind)
    {
        ind = ind +1; // to avoid the 0 case
        int[] grandChildren = {ind*4, ind*4+1, ind*4+2, ind*4+3}; //indices of grandchildren of ind
        int max = ind-1; 
        int grandchild;
        for(int i=0 ; i < grandChildren.length ;i++)
        {
            grandchild = grandChildren[i]-1;
            if (grandchild < _heapSize && _heap.get(grandchild) > _heap.get(max))
                max = grandchild;
        }
        return max;
    }

    /**
     * Calculates the min value between a given index and its four possible grandchildren (or less).
     * Complexity O(1)
     * @param ind index to calculate for
     * @return index of thee minimum value between ind and its grandchildren
     */
    private int minGrandChild(int ind)
    {
        ind = ind +1; // to avoid the 0 case
        int[] grandChildren = {ind*4, ind*4+1, ind*4+2, ind*4+3}; //indices of grandchildren of ind
        int min = ind; 
        int grandchild;
        for(int i=0 ; i < grandChildren.length ;i++)
        {
            grandchild = grandChildren[i]-1;
            if (grandchild < _heapSize && _heap.get(grandchild) < _heap.get(min))
                min = grandchild;
        }
        return min;
    }

    /**
     * Calculates the index of the left son of the given index ind. 
     * Complexity O(1) 
     * @param ind The index to calculate for
     * @reutrn The index of ind's left son
     */
    private static int leftSon(int ind)
    {
        return (ind+1)*2-1;
    }

    /**
     * Calculates the index of the right son of the given index ind. 
     * Complexity O(1) 
     * @param ind The index to calculate for
     * @reutrn The index of ind's right son
     */
    private static int rightSon(int ind)
    {
        return (ind+1)*2;
    }

    /**
     * Calculates the level number counting from top to bottom of the binary tree heap. count starts from 0
     * Complexity O(1) 
     * @param ind index of array representing thee tree
     * @return level at which the index is 
     */
    public static int indexDepth(int ind)
    {
        return (int)(Math.log(ind+1) / Math.log(2));
        // TEST:      System.out.println("The level of index is :" + nodeDepth(number));
    }

    /**
     * Replaces values in array, between inex ind1 and index ind2
     * Complexity O(1)
     * @param array array if integers
     * @param ind1 index number 1 in array
     * @param ind2 index number 2 in array
     */
    private static void swap(ArrayList<Integer> array, int ind1, int ind2)
    {
        if (ind1 == ind2) // no swap happens if indices ar identical
            return;
        int temp = array.get(ind1);
        array.set(ind1, array.get(ind2));
        array.set(ind2,temp);
    }

    /**
     * Returns a string of spaced at a lngth of a requested amount
     */
    private static String printSpaces(int amount)
    {
        String spaces="";
        for(int i=1; i<=amount; i++)
            spaces+= SPACE;
        return spaces;
    }

    /**
     * Returns a string of a number centered in spaces padding of a requested size 
     * Complexity O(1)
     */
    private static String spacePad(int num, int pad)// assuming numbers have max of 3 digits
    {
        String paddedNumber ="";
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int halfSpace = pad - len ; //small numbers, considered as constants
        if (halfSpace % 2 == 0)
        {
            paddedNumber += printSpaces(halfSpace/2) + num;
            paddedNumber += printSpaces(halfSpace/2);
        }
        else
        {
            paddedNumber += printSpaces(halfSpace/2) + num;
            paddedNumber += printSpaces(halfSpace/2 +1);
        }
        return paddedNumber;
    }

    /**
     * Calculates the index of the parent given index ind
     * Complexity O(1)
     * @param ind The index to calculate for
     * @reutrn The index of ind's parent
     */
    private static int parent(int ind)
    {
        return (ind+1)/2-1; // returns the floor of the result which is the right answer
    }

    private boolean hasParent(int ind)
    {
        return (ind+1)/2-1 >=0; // returns the floor of the result which is the right answer
    }

    /**
     * Calculates the number of possible nodesin a specific level of the binary tree heap
     * Complexity O(1)
     * @pararm level The level to calculate for
     * @return The number of max nodes that can be at a specific level (count start from 0)
     */
    private static int maxNodesInLevel(int level)
    {
        return (int)Math.pow(2,level);
    }

}
