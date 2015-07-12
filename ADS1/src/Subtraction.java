import java.util.Scanner;

class Subtraction {
	static final Scanner sc = new Scanner(System.in);
	public static String reverse(String str) {
		int i = str.length() - 1;
		String res = "";
		while(i >= 0) {
			res = res + str.charAt(i);
			i--;
		}
		return res;
	}
	public static String add(String str1, String str2) {
		String result = "";
		int c = 0;
		int j = 0;
		if(str1.length() <= str2.length()) {
			String temp = str1;
			str1 = str2;
			str2 = temp;
		}
		j = str2.length() - 1;
		for(int i = str1.length() - 1; i >= 0;i--) {
			int b = 0;
			int a = (int) str1.charAt(i) - 48;
			if (j >= 0) {
				b = (int) str2.charAt(j--) - 48;
			}
			result = result + (a + b + c)%10;
			c = (a + b + c)/10;
		}
		if(c != 0) {
			result = result + c;
		}
		return reverse(result);
	}
	public static String subtract(String s1, String s2) {
		String result = "";
		//boolean flag = false;
		//boolean equal = false;
		boolean swapped = false;
		if(s1.length() < s2.length()) {
			String temp = s1;
			s1 = s2;
			s2 = temp;
			swapped = true;
		}
		else if(s1.length() == s2.length() && s1.compareTo(s2) < 0) {
			String temp = s1;
			s1 = s2;
			s2 = temp;
			swapped = true;
		}
		int j = s2.length() - 1;
		int borrow = 0;
		for(int i = s1.length() - 1; i >= 0; i--) {
			int b = 0;
			if(j >= 0) {
				b = (int)s2.charAt(j) - 48;
				j--;
			}
			int a = (int)s1.charAt(i) - 48;
			
			if (borrow != 0) {
				a -= borrow;
				borrow = 0;
			}
			if( a < b ) {
				a += 10;
				borrow = 1;
			}
			int temp = a - b;
			result = result + temp;
		}
		if(swapped) {
			result = result + "-";
		}
		return reverse(result);
	}
	public static void main(String[] args) {
		String s1, s2, result = null;
		boolean flag1 = false, flag2 = false;
		s1 = sc.nextLine();
		s2 = sc.nextLine();
		if(s1.charAt(0) == '-') {
			s1 = s1.substring(1);
			flag1 = true;
		}
		if(s2.charAt(0) == '-') {
			s2 = s2.substring(1);
			flag2 = true;
		}
		if(flag1 ^ flag2) {
			if(flag1) {
				result = "-" + add(s1, s2);
			}
			else {
				result = add(s1, s2);
			}
		}
		else {
			if(flag1) {
				result = subtract(s2, s1);
			}
			else {
				result = subtract(s1, s2);
			}
		}
		System.out.println(result);
	}
}