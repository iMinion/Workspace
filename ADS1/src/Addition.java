import java.util.Scanner;

class Addition {
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
		if(str1.length() <= str2.length()) {
			String temp = str1;
			str1 = str2;
			str2 = temp;
		}
		int j = str2.length() - 1;
		for(int i = str1.length() - 1; i >= 0; i--) {
			int a = (int) str1.charAt(i) - 48;
			int b = 0;
			if(j >= 0) {
				b = (int) str2.charAt(i) - 48;
			}
			result = result + (a + b + c)%10;
			j--;
			c = (a + b + c)/10;
		}
		if(c != 0) {
			result = result + c;
		}
		return reverse(result);
	}
	
	public static void main(String[] args) {
		String str1, str2;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number 1");
		str1 = sc.nextLine();
		System.out.println("Enter number 2");
		str2 = sc.nextLine();
		sc.close();
		System.out.println("The addition of two number is " + add(str1, str2));
	}
}