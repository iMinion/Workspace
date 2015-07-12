import java.util.Scanner;

class digitMultiplication {
	public static String reverse(String str) {
		int i = str.length() - 1;
		String res = "";
		while(i >= 0) {
			res = res + str.charAt(i);
			i--;
		}
		return res;
	}
	public static String mul(String str1, String str2) {
		String result = "";
		int c = 0;
		int multip = (int) str2.charAt(0) - 48;
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
	public static String result(String str1, String str2) {
		String str = "-";
		if(str1.charAt(0) == '-' && str2.charAt(0) != '-') {
			return str + mul(str1.substring(1), str2);
		}
		else if(str1.charAt(0) != '-' && str2.charAt(0) == '-') {
			return str + mul(str1, str2.substring(1));
		}
		else if(str1.charAt(0) != '-' && str2.charAt(0) != '-'){
			return mul(str1, str2);
		}
		else {
			return mul(str1.substring(1), str2.substring(1));
		}
	}
	
	public static void main(String[] args) {
		String str1, str2;
		Scanner sc = new Scanner(System.in);
		str1 = sc.nextLine();
		str2 = sc.nextLine();
		sc.close();
		if(str1.length() == 1) {
			if(str1.equals(0)) {
				System.out.println(0);
				System.exit(0);
			}
			else {
				System.out.println(result(str2, str1));
			}
		}
		else if(str2.length() == 1) {
			if(str2.equals(0)) {
				System.out.println(0);
			}
			else {
				System.out.println(result(str1, str2));
			}
		}
		else {
			if(str1.charAt(0) == '-' || str2.charAt(0) == '-') {
				if(str2.length() > str1.length()) {
					System.out.println(result(str2, str1));
				}
				else {
					System.out.println(result(str1, str2));
				}
			}
		}
	}
}