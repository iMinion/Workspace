import java.util.Scanner;

class Multiplication {
	
	public static String reverse(String str) {
		int i = str.length() - 1;
		String res = "";
		while(i >= 0) {
			res = res + str.charAt(i);
			i--;
		}
		return res;
	}
	
	
	public static String mul(String str1, char ch) {
		String result = "";
		int c = 0;
		int multip = (int) ch - 48;
		for(int i = str1.length() - 1; i >= 0; i--) {
			int a = (int) str1.charAt(i) - 48;
			int temp = ((a * multip) + c) % 10;
			result = result + temp;
			c = ((a * multip) + c) / 10;
		}
		if (c != 0) {
			result = result + c;
		}
		return reverse(result);
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
	
	
	public static void main(String[] args) {
		boolean flag1 = false, flag2 = false;
		Scanner sc = new Scanner(System.in);
		String s1, s2;
		s1 = sc.nextLine();
		s2 = sc.nextLine();
		sc.close();
		
		if(s1.charAt(0) == '-') {
			flag1 = true;
			s1 = s1.substring(1);
		}
		if(s2.charAt(0) == '-') {
			flag2 = true;
			s2 = s2.substring(1);
		}
		
		String result = "";
		for(int i = s2.length() -1; i >= 0; i--) {
			String temp;
			temp = mul(s1, s2.charAt(i));
			int k = s2.length()-1-i;
			for(int j = 0; j < k; j++) {
				temp = temp + "0";
			}
			result = add(result, temp);
		}
		if( flag1 ^ flag2) {
			System.out.println("-" + result);
		}
		else {
			System.out.println(result);
		}
	}
}