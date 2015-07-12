import java.util.Scanner;

class SelectionSort {
	int[] a;
	public SelectionSort() {
		
	}
	
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		sc.useDelimiter(",");
		a = new int[n];
		int i = 0;
		while(sc.hasNext()) {
			a[i] = Integer.parseInt(sc.next());
			++i;
		}
		sc.close();
	}
	
	public void sorting() {
		for(int i = 0; i < a.length; ++i) {
			int min = a[i];
			int j = i;
            int k = i;
			while(j < a.length) {
				if(a[j] < min) {
                    min = a[j];
                    k = j;
                }
				++j;
			}
			if(min != a[i]) 
				a[i] = (a[i] + a[k]) - (a[k] = a[i]);
		}
	}
	
	public String toString() {
		String s = "";
		int i = 0;
		for(; i < a.length -1; ++i) {
			s = s + a[i] + ",";
		}
		s = s + a[i];
		return s;
	}
	
	public static void main(String[] args) {
		SelectionSort numArray = new SelectionSort();
		numArray.readInput();
		numArray.sorting();
		System.out.println(numArray);
	}
}