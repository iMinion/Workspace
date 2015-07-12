import java.util.Scanner;
import java.io.FileNotFoundException;
class k432 {
	static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		int num = sc.nextInt();
		String num1, num2;
		num1 = "";
		num2 = "";
		
		for(int i = 0; i <= num -1; i++) {
			int k;
			do {
				k = (int) (10 * Math.random());
				if(k != 0)
					num1 = k + num1;
			}while( k == 0);
			do {
				k = (int) (10 * Math.random());
				if(k != 0)
					num2 = k + num2;
			}while( k == 0);
		}
		long start4 = System.currentTimeMillis();
		k4Mul(num1, num2);
		long end4 = System.currentTimeMillis();
		long total4 = end4 - start4;
		long start32 = System.currentTimeMillis();
		k32Mul(num1, num2);
		long end32 = System.currentTimeMillis();
		long total32 = end32 - start32;
		System.out.println(total4 + "\n" + total32);
		System.out.println(Math.round((double)total4/(double)total32));
	}
	
	public static String k4Mul(String a, String b) {
		int k = a.length();
		int j = b.length();
		if(k < j) {
			for(int i = 0; i < j - k; ++i) {
				a = "0" + a;
			}
		}
		else if(j < k) {
			for(int i = 0; i < k - j; ++i) {
				b = "0" + b ;
			}
		}
		if(a.length() < 4 && b.length() < 4) {
			String r = sMul(a, b);
			return r;
		}
		else {
			int k1 = a.length() / 2;
			String a0 = a.substring(a.length() - k1);
			String a1 = a.substring(0, a.length() - k1);
			String b0 = b.substring(b.length() - k1);
			String b1 = b.substring(0, b.length() - k1);
			String B1k = ""; String B2 = "";
			int i = 0;
			for(i = 0; i < k1 ; i++) {
				B1k = B1k + "0";
				B2 = B2 + "0";
			}
			for(; i < k1 + k1; ++i) {
				B2 = B2 + "0";
			}
			String p1, p2, p0;
			p2 = k4Mul(a1, b1);
			p1 = k4Mul(add(a1, a0), add(b1, b0));
			p0 = k4Mul(a0, b0);
			return add(p2 + B2, subtract(p1, add(p2, p0)) + B1k, p0);
		}
	}
	
	public static String k32Mul(String a, String b) {
		int k = a.length();
		int j = b.length();
		if(k < j) {
			for(int i = 0; i < j - k; ++i) {
				a = "0" + a;
			}
		}
		else if(j < k) {
			for(int i = 0; i < k - j; ++i) {
				b = "0" + b ;
			}
		}
		if(a.length() < 32 && b.length() < 32) {
			String r = sMul(a, b);
			return r;
		}
		else {
			int k1 = a.length() / 2;
			String a0 = a.substring(a.length() - k1);
			String a1 = a.substring(0, a.length() - k1);
			String b0 = b.substring(b.length() - k1);
			String b1 = b.substring(0, b.length() - k1);
			String B1k = ""; String B2 = "";
			int i = 0;
			for(; i < k1 ; ++i) {
				B1k = B1k + "0";
				B2 = B2 + "0";
			}
			for(; i < k1 + k1; ++i) {
				B2 = B2 + "0";
			}
			String p1, p2, p0;
			p2 = k32Mul(a1, b1);
			p1 = k32Mul(add(a1, a0), add(b1, b0));
			p0 = k32Mul(a0, b0);
			return add(p2 + B2, subtract(p1, add(p2, p0)) + B1k, p0);
		}
	}
	public static String sMul(String num1, String num2) {
		String result = "";
		for(int i = num2.length() -1; i >= 0; i--) {
			String temp;
			temp = mul(num1, num2.charAt(i));
			int k = num2.length()-1-i;
			for(int j = 0; j < k; j++) {
				temp = temp + "0";
			}
			result = add(result, temp);
		}
		return result;
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
	
	public static String add(String a, String b, String c) {
		return add(add(a, b), c);
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
	public static String subtract(String s1, String s2) {
		String result = "";
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
		return reverse(result);
	}
}