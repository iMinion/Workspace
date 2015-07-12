import java.util.Scanner;

class QuickSelect {
	UBArrays<Integer> elements;
	int k;
	
	public QuickSelect() {
		elements = new UBArrays<Integer>();
	}
	
	public UBArrays<Integer> get() {
		return elements;
	}
	
	public int select(UBArrays<Integer> elements) {
		assert elements.size() > k;
		UBArrays<Integer> a = new UBArrays<Integer>();
		UBArrays<Integer> b = new UBArrays<Integer>();
		UBArrays<Integer> c = new UBArrays<Integer>();
		int i = 0;
		int p = elements.get(0);
		while(i < elements.size()) {
			int t = elements.get(i);
			if(t < p) {
				a.pushBack(t);
			}
			else if(t == p) {
				b.pushBack(t);
			}
			else {
				c.pushBack(t);
			}
			++i;
		}
		if(a.size() >= k) {
			return select(a);
		}
		else if((a.size() + b.size()) >= k) {
			return p;
		}
		else {
			this.k = this.k - a.size() -b.size();
			return select(c);
		}
	}
	
	public int select(UBArrays<Integer> elements, int i) {
		this.k = i;
		return this.select(elements);
	}
	
	public int getK() {
		return this.k;
	}
	
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		this.k = Integer.parseInt(sc.nextLine());
		sc.close();
		String[] str = s.split(",");
		for(int i = 0; i < str.length; ++i) {
			elements.pushBack(Integer.parseInt(str[i]));
		}
	}
}

public class QuickSelectMain {
	public static void main(String args[]) {
		QuickSelect a = new QuickSelect();
		a.readInput();
		int c = a.getK();
		StringBuilder sb = new StringBuilder();
		int j = 1;
		for(int i = 0; i < c - 1; i++) {
			sb.append(a.select(a.get(), j));
			++j;
			sb.append(",");
		}
		sb.append(a.select(a.get(), c));
		String s = sb.toString();
		System.out.println(s);
	}
}

class UBArrays<T> {
	int w = 1;
	int n = 0;
	int alpha = 4;
	int beta = 2;
	T[] UBArray;
	
	@SuppressWarnings("unchecked")
	public UBArrays() {
		UBArray = (T[]) new Object[w];
	}
	@SuppressWarnings("unchecked")
	public UBArrays(int n) {
		UBArray = (T[]) new Object[n];
		this.w = n;
		this.n = 0;
	}
	@SuppressWarnings("unchecked")
	public UBArrays(int n, int alpha, int beta) {
		UBArray = (T[]) new Object[n];
		this.w = n;
		this.n = 0;
		this.alpha = alpha;
		this.beta = beta;
		if(!((this.alpha * n >= w || n ==0) && w < this.beta)) {
			System.exit(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public UBArrays(int alpha, int beta) {
		UBArray = (T[]) new Object[w];
		this.alpha = alpha;
		this.beta = beta;
		if(!((this.alpha * n >= w || n ==0) && w < this.beta)) {
			System.exit(0);
		}
	}
	
	public void pushBack(T t) {
		if(n == w) {
			reallocate(beta * n);
		}
		UBArray[n] = t;
		++n;
	}
	public void popBack() {
		if(n > 0) {
			--n;
		}
		if(alpha * n <= w && n > 0) {
			reallocate(beta * n);
		}
	}
	@SuppressWarnings("unchecked")
	public void reallocate(int w) {
		this.w = w;
		T[] UBArrayNew = (T[]) new Object[w];
		System.arraycopy(UBArray, 0, UBArrayNew, 0, n);
		UBArray = UBArrayNew;
	}

	public T get(int i) throws ArrayIndexOutOfBoundsException{
		return UBArray[i];
	}

	public int size() {
		return n;
	}
	public int bsize() {
		return UBArray.length;
	}
    
    public String toString() {
        String s = "[";
        for(int i = 0; i < this.size(); ++i) {
            if( i == this.size() - 1) {
                s = s + UBArray[i].toString();
            }
            else
                s = s + UBArray[i].toString() + ", ";
        }
        s = s + "]";
        return s;
    }
}