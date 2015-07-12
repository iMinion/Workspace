import java.util.Scanner;

public class InsertionSort {
	int count = 0;
	int a[];
	public InsertionSort(int []a) {
		this.a = new int[a.length];
		for(int i = 0; i < a.length; ++i) {
			this.a[i] = a[i];
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(" ");
		int[] a = new int[str.length];
		for(int i = 0; i < a.length; ++i) {
			a[i] = Integer.parseInt(str[i]);
		}
		sc.close();
		InsertionSort numArray = new InsertionSort(a);
		numArray.insertion();
		numArray.printCount();
	}
	public void insertion() {
		for(int i = 1; i < a.length; ++i) {
			int min = a[i];
			if(min < a[0]) {
				for(int j = i; j > 0; --j) {
					a[j] = a[j-1];
					++count;
				}
				a[0] = min;
			}
			else {
//				for(int j = i; j > 0; --j) {
					int j = i;
					while(a[j-1] > min) {
						a[j] = a[j-1];
						--j;
						++count;
					}
					a[j] = min;
//					if(j != i)
//				}
			}
			System.out.println(this);
		}
	}
	public String toString() {
		String s = "";
		int i = 0;
		for(i = 0; i < a.length -1; ++i) {
			s = s + a[i] + " ";
		}
		s = s + a[i];
		return s;
	}
	public void printCount() {
		System.out.println(count);
	}
}