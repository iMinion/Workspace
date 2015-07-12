//import java.util.Queue;

class BinaryHeap {
	private int[] heapArray;
	private int w = 100;
	private int n = 1;
	private String s = "";
	private String subTree = "";
	
	BinaryHeap() {
		heapArray = new int[w];
		heapArray[0] = -9999;
	}
	
	BinaryHeap(int w) {
		this.w = w;
		heapArray = new int[w];
		heapArray[0] = -9999;
	}
	
	BinaryHeap(int[] heapArray) {
		this.heapArray = heapArray;
		n += (heapArray.length - 1);
	}
	
	void setN(int n) {
		this.n = n;
	}
	
	int getN() {
		return this.n;
	}
	
	String getStringS() {
		return s;
	}
	
	void binaryHeapRec(int i) {
		if(4 * i < n) {
			binaryHeapRec(2 * i);
			binaryHeapRec((2 * i) + 1);
		}
		shiftDown(i);
	}
	
	int deleteMin() {
		assert n > 0;
		int result = heapArray[1];
		heapArray[1] = heapArray[n-1];
		heapArray[n-1] = 0;
		--n;
		shiftDown(1);
		return result;
	}
	
	private void indexOrigin(int i) {		
		if(2 * i < n) {
			s = s + (2*i);
			s = s + " ";
			subTree = subTree + heapArray[2 * i] + " ";
		}
		if((2*i) + 1 < n){
			s = s + ((2*i) + 1);
			s = s + " ";
			subTree = subTree + heapArray[(2*i) + 1] + " ";
		}
		s = s.substring(s.indexOf(' ', 0) + 1);
		if(s.length() != 0) {
			indexOrigin(Integer.parseInt(s.substring(0, s.indexOf(' ', 0))));
		}
	}
	
	void insert(int e) {
		assert n<w: "Crossed the limits of the Heap Array";
		heapArray[n] = e;
		shiftUp(n++);
	}
	
	
	void modify(int index, int value) {
		heapArray[index] = value;
		modifyShift(index);
	}
	
	private void modifyShift(int i) {
		
		if(i > 1) {
			if(heapArray[i/2] > heapArray[i]) {
				shiftUp(i);
			}
			else shiftDown(i);
		}
		else if(i == 1) {
			shiftDown(i);
		}
		else if(i<0) {
			return;
		}
	}
	
	void shiftDown(int i) {
		int m = 0;
		if( (2 * i) <= n -1) {
			if( (2 * i) + 1 > n-1 || (heapArray[ 2 * i ] < heapArray[ (2 * i) +1])) m = 2 * i;
			else m = (2 * i) + 1;
			
			if(heapArray[i] > heapArray[m]) {
				swap(i, m);
				shiftDown(m);
			}
		}
	}
	
	void shiftUp(int i) {
		if (i == 1 || heapArray[i/2] <= heapArray[i]) {
			return;
		}
		else {
			swap(i, i/2);
			shiftUp(i/2);
		}
	}
	
	void sort() {
		for(int i = 1; i < n; ++i) {
			sortMin(i);
		}
	}
	
	void sortMin(int i) {
		swap(1, n-i+1);
	}
	
	String subTree(int i) {
		subTree = heapArray[i] + " ";
		s = s + i + " ";
		indexOrigin(i);
		return subTree;
	}
	
	void swap(int x, int y) {
		heapArray[x] = (heapArray[x] + heapArray[y]) - (heapArray[y] = heapArray[x]);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(n == 1) {
			return "";
		}
		else {
			for(int i = 1; i < n - 1; ++i) {
				sb.append(heapArray[i]);
				sb.append(" ");
			}
			sb.append(heapArray[n - 1]);
			return sb.toString();
		}
	}
}
