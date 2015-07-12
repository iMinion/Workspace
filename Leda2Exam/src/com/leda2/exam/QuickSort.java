package com.leda2.exam;

import java.util.Scanner;

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
        String s = "(";
        for(int i = 0; i < this.size(); ++i) {
            if( i == this.size() - 1) {
                s = s + UBArray[i].toString();
            }
            else
                s = s + UBArray[i].toString() + ",";
        }
        s = s + ")";
        return s;
    }
}

class QuickSortD {
	UBArrays<Integer> a;
	
	public QuickSortD() {
		a = new UBArrays<Integer>();
	}
	
	public QuickSortD(UBArrays<Integer> a) {
		this.a = a;
	}
	
	public UBArrays<Integer> quickSorting(UBArrays<Integer> elements) {
		if(elements.size() <= 1) {
			return elements;
		}
		else {
			UBArrays<Integer> small = new UBArrays<Integer>();
			UBArrays<Integer> equal = new UBArrays<Integer>();
			UBArrays<Integer> big = new UBArrays<Integer>();
			int p = elements.get(0);
			int i = 0;
//			int j = 0;
//			int k = 0;
//			int l = 0;
			while(i < elements.size()) {
				int t = elements.get(i);
				if(t < p) {
					small.pushBack(t);
//					System.out.println("small " + small.get(j++));
				}
				else if(t == p) {
					equal.pushBack(t);
//					System.out.println("equal " + equal.get(k++));
				}
				else {
					big.pushBack(t);
//					System.out.println("big " + big.get(l++));
				}
				++i;
			}
			String s = small.toString() + " : " + equal.toString() + " : " + big.toString();
			System.out.println(s);
			return concatenate(quickSorting(small), equal, quickSorting(big));
		}
	}
	
	public UBArrays<Integer> concatenate(UBArrays<Integer> small, UBArrays<Integer> equal, UBArrays<Integer> big) {
		UBArrays<Integer> elements = new UBArrays<Integer>();
		for(int i = 0; i < small.size(); ++i) {
			elements.pushBack(small.get(i));
		}
		for(int i = 0; i < equal.size(); ++i) {
			elements.pushBack(equal.get(i));
		}
		for(int i = 0; i < big.size(); ++i) {
			elements.pushBack(big.get(i));
		}
		this.a = elements;
		return elements;
	}
	
	public String intToString(UBArrays<Integer> elements) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(int i = 0; i < elements.size() - 1; ++i) {
			sb.append(elements.get(i));
			sb.append(",");
		}
		sb.append(a.size() - 1);
		sb.append(")");
		return sb.toString();
	}
}

public class QuickSort {
	public static void main(String[] args) {
		UBArrays<Integer> elements = new UBArrays<Integer>();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		String[] str = s.split(",");
		for(int i = 0; i < str.length; ++i) {
			elements.pushBack(Integer.parseInt(str[i]));
		}
		QuickSortD abc = new QuickSortD(elements);
//		System.out.println(abc.intToString(elements));
		System.out.println(elements);
		elements = abc.quickSorting(elements);
		System.out.println(elements);
//		System.out.println(abc.intToString(elements));
	}
}