package com.Week4;

import java.util.ArrayList;

public interface KnapsackInterface {
	// European Coins are stored into the coins array
	// stored in descending order of value
	final static int[] eucoins = {100, 50, 20, 10, 5, 4, 2, 1};

	// Dollars are stored into the coins array
	// stored in descending order of value	
	final static int[] docoins = {100, 50, 20, 10, 5, 4, 1};
	
	ArrayList<Integer> produceChange();
}
