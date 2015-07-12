package leda4.exam;

import java.util.Arrays;
import java.util.Scanner;

public class N_Queens {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		NQ nq = new NQ(n);
		nq.placeQueens(-1, 0);
		System.out.println(nq);
		sc.close();
	}
}

class NQ {

	int n;
	int[][] box;

	public NQ(int n, int[][] box) {
		this.n = n;
		this.box = box;
	}

	public NQ(int n) {
		this.n = n;
		box = new int[n][n];
		for(int i = 0; i < n; i++)
			Arrays.fill(box[i], 0);
	}

	public int[][] getBox() {
		return box;
	}
	public int getN() {
		return n;
	}
	public void setBox(int[][] box) {
		this.box = box;
	}
	public void setN(int n) {
		this.n = n;
	}

	public void placeQueens(int row, int col) {
		for(int j = 0; j < n; j++) {
			int i = canBePlaced(j, col);
			if(i != -1) disableAttackPlacesFor(i, col);
			else {
				i = backtrack(col -1);
				if( i != -1) {
					placeQueens(i, col - 1);
				}
			}
			col++;
		}
	}

	public int backtrack(int col) {
		if(col >= 0) {
			int i = 0;
			for(i = 0; i < n; i++) {
				if(box[i][col] == 1) break;
			}
			if(i == n -1) return backtrack(col - 1);
			i += 1;
			for(; i < n; i++) {
				if(box[i][col] == 0) {
					return i;
				}
			}
			return backtrack(col -1);
		}
		else return -1;
	}

	public void disableAttackPlacesFor(int row, int col) {
		int i = row;
		int j = col;
		while(i >= 0 && j>= 0) {
			box[i][j] = 2;
			i--;
			j--;
		}
		i = 0;
		j = col;
		while(i < n) {
			box[i][j] = 2;
			i++;
		}
		i = row;
		j = 0;
		while(j < n) {
			box[i][j] = 2;
			j++;
		}
		i = row + 1;
		j = col + 1;
		while(i < n && j < n) {
			box[i][j] = 2;
			i++;
			j++;
		}
		i = row + 1;
		j = col - 1;
		while( i < n && j >= 0) {
			box[i][j] = 2;
			i++;
			j--;
		}
		i = row - 1;
		j = col + 1;
		while( i >= 0 && j < n) {
			box[i][j] = 2;
			i--;
			j++;
		}
		box[row][col] = 1;
	}

	public int canBePlaced(int row, int col) {
		for(int i = 0; i < n; i++) 
			if(box[i][col] == 0) {
				if( i != row) return i;
			}
		return -1;
	}



	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(box[i][j] == 1) {
					sb.append('Q');
					sb.append('\t');
				}
				else {
					sb.append('*');
					sb.append('\t');
				}
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}