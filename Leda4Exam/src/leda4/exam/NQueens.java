package leda4.exam;

import java.util.Scanner;

public class NQueens 
{
	static int N = 4;
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter N value:");
		N=sc.nextInt();
		sc.close();
		int[][] board=new int[N][N];
		if(!theBoardSolver(board,0))
			System.out.println("No Solution");
		printBoard(board);
	}

	public static void printBoard(int[][] board)
	{
		int i;
		for(i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)			
				if(board[i][j]==1)
				{
					System.out.print("Q\t");
				}
				else
				{
					System.out.print("_\t");
				}
				System.out.println("\n");
		}
	}
	
	public static boolean theBoardSolver(int[][] board,int col)
	{
		if(col>=N)
		{
			return true;
		}
		for(int i=0;i<N;i++)
		{
			if(toPlaceOrNotToPlace(board,i,col))
			{
				board[i][col]=1;
				if(theBoardSolver(board,col+1))
					return true;
				board[i][col]=0;
			}
		}
		return false;
	}
	
	public static boolean toPlaceOrNotToPlace(int[][] board,int row,int col)
	{
		int i,j;
		for(i=0;i<col;i++)
		{
			if(board[row][i]==1)
				return false;
		}
		for(i=row,j=col;i>=0 && j>=0;i--,j--)
		{
			if(board[i][j]==1)
				return false;
		}
		for(i=row,j=col;i<N && j>=0;i++,j--)
		{
			if(board[i][j]==1)
				return false;
		}
		return true;
	}
	
}
