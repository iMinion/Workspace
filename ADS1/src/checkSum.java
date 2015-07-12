import java.util.Scanner;

class checkSum {
	
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
	public static int checksum(String s) {
		int temp = 0;
		if(s.length() == 1) {
			return (int)((int)s.charAt(0) - 48);
		}
		else {
			for(int i = 0; i < s.length(); i++) {
				int a = (int)s.charAt(i) - 48;
				temp += a;
			}
			return checksum("" + temp);
		}
	}
	public static void main(String[] args) {
		String s1, s2;
		int check1, check2, checkResult;
		Scanner sc = new Scanner(System.in);
		s1 = sc.next();
		s2 = sc.next();
		sc.close();
		if(s1.charAt(0) == '-') {
			s1 = s1.substring(1);
		}
		if(s2.charAt(0) == '-') {
			s2 = s2.substring(1);
		}
		check1 = checksum(s1);
		check2 = checksum(s2);
		int check = checksum("" + (check1 * check2));
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
		checkResult = checksum(result);
		System.out.println(check1);
		System.out.println(check2);
		System.out.println(check);
		System.out.println(checkResult);
	}
}