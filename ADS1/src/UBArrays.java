public class UBArrays<T> {
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