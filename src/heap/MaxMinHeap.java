package heap;

import java.util.ArrayList;

/**
 * MaxMinHeap is a data structure of an almost full binary tree in which, all
 * values in an even levels are larger than values in lower levels and values in
 * odd levels are smaller than all values in lower levels. this class builds
 * such a heap and maintains it.
 * 
 * @author (Ornit Cohen Gindi)
 * @version (20407-2020b)
 */
public class MaxMinHeap {
	protected ArrayList<Integer> _heap; // an array that represents the MAX-MIN heap
	protected int _heapSize; // the size of the heap
	/**
	 * For debugging
	 */
	final static int ROOT = 0;
	final static int LEFT = 1;
	final static int RIGHT = 2;

/////////////////////////////////////////////CONSTRUCTORS////////////////////////////////
	/**
	 * Constructor of MaxMinHeap from array of integers given as parameter.
	 * Complexity O(nlgn)
	 * 
	 * @param intArray An array of integers
	 */
	public MaxMinHeap(ArrayList<Integer> intArray) {
		_heap = new ArrayList<>(intArray);
		_heapSize = _heap.size();
		buildHeap();

	}// end constructor

	/**
	 * Copy constructor
	 * 
	 * @param other The heap to copy
	 */
	public MaxMinHeap(MaxMinHeap other) {
		_heap = new ArrayList<>(other._heap);
		_heapSize = other._heapSize;
	}// end constructor

/////////////////////////////////////////PRIME METHODS//////////////////////////////////////////////////////////	

	
	private void buildHeap() {
		for (int node = 0; node < _heapSize; node++)
			if (indexDepth(node) % 2 == 1) {// all the MIN first
				System.out.println("BuildHeap on node#" + node + "\n");// debug
				heapify(node, "build");
			}
		for (int node = _heapSize - 1; node >= 0; node--)
			if (indexDepth(node) % 2 == 0) { // all the MAX second
				System.out.println("BuildHeap on node#" + node + "\n");// debug
				heapify(node, "build");
			}

	}// end buildHeap

	/**
	 * Corrects the heap from a specific index. correct. Complexity O(lgn)
	 * 
	 * @param ind   index of array representing the binary tree heap
	 * @param build is assigned true when calling heapify from buildHeap, and false
	 *              otherwise
	 **/
	private void heapify(int ind, String mode) {
		System.out.println("heapify : node# " + ind + "\n" + this); // debug
		int depth = indexDepth(ind);
		int maxChild = maxChild(ind); // saving index of the maximum of an index's children
		int minChild = minChild(ind); // saving index of the maximum of an index's children
		int maxGrandchild = maxGrandchild(ind); // saving index of maximum of an index's grandchildren
		int minGrandchild = minGrandchild(ind); // saving index of maximum of an index's grandchildren
		int grandparent = grandParent(ind);
		Integer current = get(ind);
		Integer parent = get(parent(ind));

		if (depth % 2 == 0) { // if depth level is even(max)

			if (hasGrandchild(ind) && maxGrandchild != ind) {
				System.out.println("current " + current + " < maxGrandchild " + get(maxGrandchild)); // debug
				swap(_heap, ind, maxGrandchild);
				heapify(maxGrandchild, mode);
			} else if (hasChild(ind) && current < get(maxChild)) {
				System.out.println("current " + current + " < maxChild " + get(maxChild)); // debug
				swap(_heap, ind, maxChild);
				heapify(maxChild, mode);
			} else if (!hasChild(ind) && parent != null && current < parent) {
				// case ind is at the bottom of the tree
				System.out.println("current " + current + " < parent " + parent); // debug
				swap(_heap, ind, parent(ind));
				heapify(parent(ind), mode);
			} else if (!mode.contentEquals("build") && grandparent != -1 && current > get(grandparent)) { // correction towards MAX node
				System.out.println("current " + current + " > grandparent " + get(grandparent)); // debug
				swap(_heap, ind, grandparent);
				heapify(grandparent, mode);
			}

		} // end if MAX
		else // if depth level is odd(min)
		{
			if (grandparent > -1 && current < get(grandparent)) { // case of no grandparent
				System.out.println("current " + current + " < grandparent " + get(grandparent)); // debug
				swap(_heap, ind, grandparent);
				heapify(grandparent, mode);
			} else if (!mode.equals("build")) {
				if (hasGrandchild(ind) && current > get(minGrandchild)) {
					System.out.println("current " + current + " > minGrandchild " + get(minGrandchild)); // debug
					swap(_heap, ind, minGrandchild);
					heapify(minGrandchild, mode);
				} else if (hasChild(ind) && current > get(minChild)) {
					System.out.println("current " + current + " > minChild " + get(minChild)); // debug
					swap(_heap, ind, minChild);
					heapify(minChild, mode);
				} else if (!hasGrandchild(ind) && !hasChild(ind) && parent != null && current > parent) {
					System.out.println("current " + current + " > parent " + parent); // debug
					swap(_heap, ind, parent(ind));
					// System.out.println("swap " +current+ " with "+ parent); //debug
					heapify(parent(ind), mode);
				}
			} // end
		} // end if MIN
	}



	/**
	 * Removes the node of the maximum value from heap
	 * 
	 * @return The maximum value that use to be in heap before its removal
	 */
	public Integer extractMax() {
		if (_heapSize == 0)
			return null;
		Integer max = get(0);
		// System.out.println(max);// debug
		if (_heapSize > 1)
			swap(_heap, 0, _heapSize - 1);
		_heap.remove(_heapSize - 1);
		_heapSize -= 1;
		if (_heapSize > 1)
			heapify(0, "extract");
		return max;
	}

	/**
	 * Removes the node of the minimum value from heap
	 * 
	 * @return The minimum value that use to be in heap before its removal
	 */
	public Integer extractMin() {
		if (_heapSize == 0) // empty heap
			return null;
		Integer minInd = getMinHeapInd();// save index
		Integer minVal = get(minInd);// save value
		if (_heapSize > 2)
			// no need to swap in case of 1 or 2 elements in heap. since then the min will
			// be the last one anyway
			swap(_heap, minInd, _heapSize - 1); // switch between last and the minimum
		_heap.remove(_heapSize - 1); // remove the min (it's now in the last place of the array)
		_heapSize -= 1; // update size of heap
		if (_heapSize > 3)
			heapify(minInd, "extract"); // min is the index of a node with a different value than before swap. needs to
		// be corrected
		return minVal;
	}

	/**
	 * Removes the node of the requested index from heap
	 * 
	 * @param ind Index of value to delete from heap
	 * @return true if deletion was successful and false otherwise
	 */
	public boolean heapDelete(int ind) {
		if (ind < 0 || ind >= _heapSize) // required index is out of bounds
			return false;
		Integer last = get(_heapSize - 1); // for readability
		_heap.set(ind, last); // mark the wanted to be removed index
		_heap.remove(_heapSize - 1);
		_heapSize -= 1;
		heapify(ind, "delete"); // make the new maximum go to the top and then extract it
		return true;
	}


	/**
	 * Adds a new value of Integer type to heap
	 * 
	 * @param key A value to add to Max-Min heap
	 */
	public void heapInsert(Integer key) {
		if (_heapSize == 0) { // case of empty heap
			_heapSize = 1;
			_heap.add(0, key);
			return;
		}
		_heap.add(_heapSize, key); // place the max value kept aside, at the end of the array
		_heapSize += 1; // update size of heap
		heapify(_heapSize - 1, "insert"); // O(lgn)
	}

	/**
	 * Sorts the values of the Max-Min heap. Does not change the original heap
	 * 
	 * @return An ArrayList containing sorted values of heap
	 */
	public ArrayList<Integer> heapSort() {
		MaxMinHeap tempHeap = new MaxMinHeap(this); // copy heap
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		for (int i = 0; i < _heapSize; i++) {
			Integer tempMax = tempHeap.extractMax();
			sorted.add(0, tempMax);
		}
		return sorted;
	}// end of heapSort
	
//////////////////////////////////////////////////////HELPER METHODS/////////////////////////////////

	/**
	 * Gets the value at a specific index
	 * 
	 * @param index The place of the wanted value
	 * @return The value at the given index
	 */
	public Integer get(int index) {
		if (index < 0 || index >= _heapSize)
			return null;
		return _heap.get(index);
	}

	
	/**
	 * Checks if current heap is empty
	 * 
	 * @return True if heap is empty and false otherwise
	 */
	public boolean isEmpty() {
		return _heapSize == 0;
	}

	
	/**
	 * Builds a String that displays the MaxMinHeap as a binary tree
	 * 
	 * @return String That displays the MaxMinHeap as a binary tree
	 */
	public String toString() {
		int pad1 = IOUtils.PAD1; // 5
		int pad2 = IOUtils.PAD2; // 3

		if (_heapSize == 0)
			return "\nHEAP IS EMPTY! NOTHING TO DISPLAY\n";

		System.out.println("\n********** HEAP DISPLAY AS TREE: ************\n");
		String heapStr = "";
		int finalDepth = heapDepth();
		int height;
		int index = 0;
		for (int depth = 0; depth <= finalDepth; depth++) // loop for each row of the tree
		{
			height = finalDepth - depth; // starts with 0;
			int unitNumber = (int) Math.pow(2, height); // spaces that come before the numbers in each row
			int subTreeWidth = unitNumber * pad1 + unitNumber - 1;
			int spacesBefore = subTreeWidth / 2 - pad1 / 2; // spaces to print before each row of numbers
			int spacesBetween = 2 * spacesBefore + 1; // spaces to print between the tree numbers
			String title = "(Level" + IOUtils.spacePad(depth, pad2) + ")";
			if (depth % 2 == 0)
				title += " MAX ";
			else
				title += " MIN ";
			heapStr += title + IOUtils.printSpaces(spacesBefore);
			int nodes = maxNodesInLevel(depth);
			for (int node = 1; node <= nodes; node++) // loop on the number of nodes in a row
			{
				if (index < _heapSize)
					heapStr += IOUtils.spacePad(get(index), pad1);
				if (node < nodes)
					heapStr += IOUtils.printSpaces(spacesBetween);
				index++;
			}
			heapStr += "\n\n"; // a row of space between levels
		}
		return new String(heapStr);
	}

	
	/**
	 * Displays the MaxMinHeap as an array Complexity O(n) (n is the length of the
	 * array representing the heap)
	 */
	public void showHeapAsArray() {
		if (_heapSize == 0) {
			System.out.println("\nHEAP IS EMPTY! NOTHING TO DISPLAY\n");
			return;
		}
		System.out.println("\nDisplay Max-Min heap as an array:\n");

		String heapStr = "";
		for (int i = 0; i < _heapSize; i++)
			if (i == _heapSize - 1)
				heapStr += get(i);
			else
				heapStr += get(i) + ", ";
		System.out.println(heapStr);
	}

	
	/**
	 * Calculates the level number counting from top to bottom of the binary tree
	 * heap. Count starts from 0 . Complexity O(1)
	 * 
	 * @param ind index of array representing the tree
	 * @return level at which the index is
	 */
	private static int indexDepth(int ind) {
		return (int) (Math.log(ind + 1) / Math.log(2));
	}

	/**
	 * Calculates the number of levels of the binary tree, counted from root
	 * (starting from 0)
	 * 
	 * @return number of levels of the binary tree
	 */
	private int heapDepth() {
		return indexDepth(_heapSize);
	}

	/**
	 * Calculates the index of the parent given index ind Complexity O(1)
	 * 
	 * @param ind The index to calculate for
	 * @return The index of ind's parent
	 */
	private static int parent(int ind) {
		return (ind + 1) / 2 - 1; // returns the floor of the result which is the right answer
	}

	/**
	 * Calculates the index of the left son of the given index ind. Complexity O(1)
	 * 
	 * @param ind The index to calculate for
	 * @return The index of ind's left son
	 */
	private static int leftSon(int ind) {
		return (ind + 1) * 2 - 1;
	}

	/**
	 * Calculates the index of the right son of the given index ind. Complexity O(1)
	 * 
	 * @param ind The index to calculate for
	 * @return The index of ind's right son
	 */
	private static int rightSon(int ind) {
		return (ind + 1) * 2;
	}

	/**
	 * Replaces values in array, between index ind1 and index ind2 Complexity O(1)
	 * 
	 * @param array array if integers
	 * @param ind1  index number 1 in array
	 * @param ind2  index number 2 in array
	 */
	private static void swap(ArrayList<Integer> array, int ind1, int ind2) {
		if (ind1 == ind2) // no swap happens if indices are identical
			return;
		int temp = array.get(ind1);
		array.set(ind1, array.get(ind2));
		array.set(ind2, temp);
	}

	/**
	 * Calculates the number of possible nodes in a specific level of the binary tree
	 * heap Complexity O(1)
	 * 
	 * @param level The level to calculate for
	 * @return The number of max nodes that can be at a specific level (count start
	 *         from 0)
	 */
	private static int maxNodesInLevel(int level) {
		return (int) Math.pow(2, level);
	}

	/**
	 * Calculates if a value in the heap binary tree, has any children. (Complexity
	 * O(1))
	 * 
	 * @param ind an index at the array that represents the binary tree
	 * @return true If the value has at lease one child
	 */
	private boolean hasChild(int ind) {
		return (ind + 1) * 2 <= _heapSize;
	}

	/**
	 * Find the index of the minimum child of a given index
	 * 
	 * @param ind The index to calculate min child for
	 * @return index of the minimum child. If index is smaller than the minimum, ind
	 *         will be returned
	 */
	private int minChild(int ind) {
		int left = leftSon(ind); // left already considers the 0
		int right = rightSon(ind); // right already considers the 0
		int min = ind;
		if (right < _heapSize && get(right) < get(min))
			min = right;
		if (left < _heapSize && get(left) < get(min))
			min = left;
		return min;
	}

	/**
	 * Calculates the max value between a given index and its two sons (or son).
	 * (Complexity O(1))
	 * 
	 * @param ind index to calculate for
	 * @return index of the maximum value between ind and its sons
	 */
	private int maxChild(int ind) {
		int left = leftSon(ind); // left already considers the 0
		int right = rightSon(ind); // right already considers the 0
		int max = ind;
		if (right < _heapSize && get(right) > get(max))
			max = right;
		if (left < _heapSize && get(left) > get(max))
			max = left;
		return max;
	}

	/**
	 * Calculates the minimum between nodes in level 1 (MIN level) - which contains the minimal values of
	 * whole MAX-MIN heap
	 * 
	 * @return Index of the minimal value in heap
	 */
	

	
	private int getMinHeapInd() {
		int left = Integer.MAX_VALUE;
		int right = Integer.MAX_VALUE;

		if (_heapSize > 1)
			left = get(LEFT);
		else // case of heap of 1 node
			return ROOT;
		if (_heapSize > 2)
			right = get(RIGHT);
		if (left < right)
			return 1;
		return 2;
	}// end of getMinHeapInd

	/**
	 * Calculates if a value in the heap binary tree, has any grandchildren.
	 * (Complexity O(1))
	 * 
	 * @param ind an index at the array that represents the binary tree
	 * @return true If the value has at least one grand child
	 */
	private boolean hasGrandchild(int ind) {
		return (ind + 1) * 4 <= _heapSize;
	}

	/**
	 * Calculates the max value between a given index and its four possible
	 * grandchildren (or less). Complexity O(1)
	 * 
	 * @param ind index to calculate for
	 * @return index of the maximum value between ind and its grandchildren
	 */
	private int maxGrandchild(int ind) {
		ind = ind + 1; // to avoid the 0 case
		int[] grandchildren = { ind * 4, ind * 4 + 1, ind * 4 + 2, ind * 4 + 3 }; // indices of grandchildren of ind
		int max = ind - 1;
		int grandchild;
		for (int i = 0; i < grandchildren.length; i++) {
			grandchild = grandchildren[i] - 1;
			if (grandchild < _heapSize && rightSon(ind - 1) < _heapSize && get(grandchild) > get(max))
				max = grandchild;
		}
		if (grandchildren[2] - 1 >= _heapSize && rightSon(ind - 1) < _heapSize && get(rightSon(ind - 1)) > get(max))
			// case of less than 3 grandchildren. comparison to right son is required
			max = rightSon(ind - 1);
		return max;
	}

	/**
	 * Calculates the min value between a given index and its four possible
	 * grandchildren (or less). Complexity O(1)
	 * 
	 * @param ind index to calculate for
	 * @return index of the minimum value between ind and its grandchildren
	 */
	private int minGrandchild(int ind) {
		ind = ind + 1; // to avoid the 0 case
		int[] grandchildren = { ind * 4, ind * 4 + 1, ind * 4 + 2, ind * 4 + 3 }; // indices of grandchildren of ind
		int min = ind - 1;
		int grandchild;
		for (int i = 0; i < grandchildren.length; i++) {
			grandchild = grandchildren[i] - 1;
			if (grandchild < _heapSize && get(grandchild) < get(min))
				min = grandchild;
		}
		if (grandchildren[2] - 1 >= _heapSize && rightSon(ind - 1) < _heapSize && get(rightSon(ind - 1)) < get(min))
			// case of less than 3 grandchildren. comparison to right son is required
			min = rightSon(ind - 1);
		return min;
	}

	/**
	 * Calculates the index of a grandparent for a given index ind
	 * 
	 * @param ind the index for which the grandparent is calculated
	 * @return index of the grandparent or -1 if a grandparent does not exist
	 */
	private int grandParent(int ind) {
//		int gp = (ind + 1) / 4 - 1;
		int grandparent = parent(parent(ind));
		if (grandparent < 0)
			return -1;
		return grandparent;
	}

}
