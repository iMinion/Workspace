package com.Week4;

import java.util.ArrayList;
import java.util.Scanner;

public class CoinKnapsack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ch;
		while(true) {
			System.out.println("Press 1 to enter a value.\n Default will be exit");
			ch = Integer.parseInt(sc.nextLine());
			CGreedyKnapsack cha;
			switch(ch) {
			case 1: System.out.println("Please enter currency d = dollars e = european"); 
				char c = sc.nextLine().charAt(0);
				int n = Integer.parseInt(sc.nextLine());
				cha = new CGreedyKnapsack(n, c == 'e');
				System.out.println(cha.produceChange());
				break;
			default: 
				System.exit(0);
				sc.close();
			}
		}
	}
}


class CGreedyKnapsack implements KnapsackInterface {
	
	// True, if it is European
	// False, if it is American
	boolean currency;
	
	// The value for which the change has to be produced
	int capacity;
	ArrayList<Integer> change;
	
	
	CGreedyKnapsack(int capacity, boolean currency) {
		this.capacity = capacity;
		this.currency = currency;
	}
	
	public ArrayList<Integer> produceChange() {
		change = new ArrayList<Integer>();
		if(currency) {
			while(capacity != 0) {
				for(int i = 0; i < eucoins.length; i++) {
					if(eucoins[i] <= capacity) {
						change.add(eucoins[i]);
						capacity -= eucoins[i];
					}
				}
			}
		}
		else {
			while(capacity != 0) {
				for(int i = 0; i < docoins.length; i++) {
					if(docoins[i] <= capacity) {
						change.add(docoins[i]);
						capacity -= docoins[i];
					}
				}
			}
		}
		return change;
	}
	
}