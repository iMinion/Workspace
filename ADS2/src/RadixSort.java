
import java.util.LinkedList;
import java.util.Scanner;

public class RadixSort {

	public final int base = 10;
	@SuppressWarnings("unchecked")
	public void radixSort(int[] array, int maxDigits) {
		LinkedList<Integer>[] counter = new LinkedList[base];
		for(int i=0; i<counter.length; i++)
			counter[i] = new LinkedList<Integer>();
		
		int mod = base;
		int div = 1;
		
		for (int i = 0; i < maxDigits; i++, div *= 10, mod *= 10) {
			System.out.println(i+1+" Iteration:");
			for(int j = 0; j < array.length; j++) {
				int bucket = (array[j] % mod) / div;
				counter[bucket].add(array[j]);
			}
			int pos = 0;
			for(int j = 0; j < counter.length; j++) {
				System.out.print(j+":");
				printList(counter[j]);
				Integer value = null;
                while ((value = counter[j].poll()) != null) {
                    array[pos++] = value;
                }
			}
		}
	}

	private void printList(LinkedList<Integer> ll) {
		// TODO Auto-generated method stub
		if(ll.size()>0){
			int i;
			for(i = 0; i < ll.size()-1; i++) 
				System.out.print(ll.get(i)+",");
			System.out.println(ll.get(i));
		}
		else
			System.out.println();
	}
	
	public void printElements(int[] elements) {
		int i;
		for(i=0; i<elements.length-1; i++)
			System.out.print(elements[i]+",");
		System.out.println(elements[i]);
	}
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String[] s = scan.nextLine().split(",");

		int[] test = new int[s.length];
		int maxDigits = s[0].length();
		for(int i=0; i<test.length; i++){
			test[i] = Integer.parseInt(s[i]);
			if(s[i].length() > maxDigits)
				maxDigits = s[i].length();
		}
		
		RadixSort rs = new RadixSort();
		rs.radixSort(test, maxDigits);
		System.out.println("Sorted Order:");
		rs.printElements(test);
		scan.close();
	}
}