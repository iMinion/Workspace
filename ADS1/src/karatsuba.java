
class karatsuba {
	
	public String reverse(String str) {
		int i = str.length() - 1;
		String res = "";
		while(i >= 0) {
			res = res + str.charAt(i);
			i--;
		}
		return res;
	}
	public String add(String str1, String str2) {
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
	
	public String recMul(String a, String b) {
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
		if(a.length() == 1 && b.length() == 1) {
			return Integer.toString(Integer.parseInt(a) * Integer.parseInt(b));
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
			p2 = recMul(a1, b1);
			p1 = recMul(add(a1, a0), add(b1, b0));
			p0 = recMul(a0, b0);
			return add(p2 + B2, subtract(p1, add(p2, p0)) + B1k, p0);
		}
	}
	
	public String add(String a, String b, String c) {
		return add(add(a, b), c);
	}
	public String subtract(String s1, String s2) {
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