package fctrl2;


import java.math.BigInteger;
import java.util.Scanner;

public class FCTRL {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < t; ++i) {
			int n = Integer.parseInt(sc.nextLine());
			System.out.println(factorial(n));
		}
		sc.close();
	}
	
	public static BigInteger factorial(int n) {
		if( n == 0 || n == 1) return BigInteger.valueOf(1);
		else {
			BigInteger res = BigInteger.valueOf(1);
			for(int i = n; i >= 2; i--) {
				res = res.multiply(BigInteger.valueOf(i));
			}
			return res;
		}
	}
}
