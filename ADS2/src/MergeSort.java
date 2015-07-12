import java.util.Scanner;



class MergeSort {
	int[] a; 
	boolean flag = false;
	
	public MergeSort() {
	}
	
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		a = new int[n];
		int bo = Integer.parseInt(sc.nextLine());
		if(bo == 0) setFlag();
		String s = sc.nextLine();
		sc.close();
		sc = new Scanner(s);
		sc.useDelimiter(",");
		for(int i = 0; i < n; ++i) {
			a[i] = Integer.parseInt(sc.next());
		}
		sc.close();
	}
	
	public void setFlag() {
		flag = !flag;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
	public void merge() {
		a = merge(a);
	}
	
	public int[] merge(int[] a) {
		int []b;
		int []c;
		if(a.length == 1) return a;
		else {
			b = new int[a.length/2];
			c = new int[a.length - a.length/2];
			System.arraycopy(a, 0, b, 0, b.length);
			System.arraycopy(a, b.length, c, 0, c.length);
			if(flag) return joinDescending(merge(b), merge(c));
			else return joinAscending(merge(b), merge(c));
		}
	}
	
	
	public int[] joinAscending(int []a, int []b) {
		int c[] = new int[a.length + b.length];
		int k = 0;
		int i = 0;
		int j = 0;
		while(k < c.length) {
			if(i == a.length && j != b.length) {
				for(;j < b.length; ++j) {
					c[k++] = b[j];
				}
			}
			else if(i != a.length && j == b.length) {
				for(;i < a.length; ++i) {
					c[k++] = a[i];
				}
			}
			else {
				if(a[i] < b[j]) {
					c[k++] = a[i++];
				}
				else {
					c[k++] = b[j++];
				}
			}
		}
		return c;
	}
	
	public int[] joinDescending(int []a, int []b) {
		int c[] = new int[a.length + b.length];
		int k = 0;
		int i = 0;
		int j = 0;
		while(k < c.length) {
			if(i == a.length && j != b.length) {
				for(;j < b.length; ++j) {
					c[k++] = b[j];
				}
			}
			else if(i != a.length && j == b.length) {
				for(;i < a.length; ++i) {
					c[k++] = a[i];
				}
			}
			else {
				if(a[i] < b[j]) {
					c[k++] = b[j++];
				}
				else {
					c[k++] = a[i++];
				}
			}
		}
		return c;
	}
	
	public String toString() {
		String s = "";
		int i = 0;
		for(; i < a.length - 1; ++i) {
			s = s + a[i] + ",";
		}
		s = s + a[i];
		return s;
	}
	
	public static void main(String[] args) {
		MergeSort numArray = new MergeSort();
		numArray.readInput();
		numArray.merge();
		System.out.println(numArray);
	}
}