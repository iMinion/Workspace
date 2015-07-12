 import java.util.Scanner;

class recursiveMultiplication {
	static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		String num1, num2;
		num1 = sc.nextLine();
		num2 = sc.nextLine();
		boolean flag = false;
		if(num1.charAt(0) == '-') {
			flag = true;
			num1 = num1.substring(1);
		}
		if(num2.charAt(0) == '-') {
			flag = !flag;
			num2 = num2.substring(1);
		}
		String result = recMul(num1, num2);
		for(int i = 0; i < result.length(); ++i) {
			if(result.charAt(i) != '0') {
				result = result.substring(i);
				break;
			}
		}
		if(flag) {
			result = "-" + result;
		}
		System.out.println(result);
	}
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
		if(str1.length() < str2.length()) {
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
	
	public static String recMul(String a, String b) {
		if(a.length() < b.length()) {
			int k = b.length();
			int j = a.length();
			for(int i = 0; i < k - j; ++i) {
				a = "0" + a;
			}
		}
		else {
			int k = a.length();
			int j = b.length();
			for(int i = 0; i < k - j; ++i) {
				b = "0" + b;
			}
		}
		
		if(a.length() == 1 && b.length() == 1) {
			return Integer.toString(Integer.parseInt(a) * Integer.parseInt(b));
		}
		else {
			int k = a.length() / 2;
			String a0 = a.substring(a.length() - k);
			String a1 = a.substring(0, a.length() - k);
			String b0 = b.substring(b.length() - k);
			String b1 = b.substring(0, b.length() - k);
			String B1k = ""; String B2 = "";
			int i = 0;
			for(i = 0; i < k ; i++) {
				B1k = B1k + "0";
				B2 = B2 + "0";
			}
			for(; i < k + k; ++i) {
				B2 = B2 + "0";
			}
			return add(recMul(a1, b1) + B2, recMul(a1, b0) + B1k, recMul(a0, b1) + B1k, recMul(a0, b0));
		}
	}
	
	public static String add(String a, String b, String c, String d) {
		return add(add(a, b), add(c, d));
	}
}