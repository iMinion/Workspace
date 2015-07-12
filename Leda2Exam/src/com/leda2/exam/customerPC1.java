package com.leda2.exam;



import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

class customerPC1 {
	public static void main(String[] args) {
		Hashtable<String, String> a = new Hashtable<String, String>();
		Scanner sc = new Scanner(System.in);
		String s = "";
		sc.nextLine();
		int k = 0;
		while(sc.hasNext()) {
			s = s + sc.nextLine() + "\n";
			++k;
		}
		sc.close();
		s = s.substring(0, s.length() - 1);
		String [] t = new String[k-1];
		sc = new Scanner(s);
		sc.useDelimiter("\n");
		k = 0;
		while(sc.hasNext()) {
			String r = sc.next();
			String[] temp = r.split(",");
			if(temp.length == 3) {
				t[k] = r;
			}
			++k;
		}
		sc.close();
		for(int i = 0; i < t.length; ++i) {
			String alpha[] = t[i].split(",");
			if(a.containsKey(alpha[1])) {
				int val = Integer.parseInt(a.get(alpha[1])) + 1;
				a.put(alpha[1], Integer.toString(val));
			}
			else
				a.put(alpha[1], "1");
		}
		Map<String, String> abc = new TreeMap<String, String>(a);
		
		
		
		Set<String> keys = abc.keySet();
		String[] fKeys = new String[keys.size()];
		fKeys = keys.toArray(fKeys);
		int i = 0;
		while(i < fKeys.length) {
			String ke = fKeys[i];
			System.out.println(ke + "-" + abc.get(ke));
			++i;
		}
		System.out.println();
		a = new Hashtable<String, String>();
		for(i = 0; i < t.length; ++i) {
			String alpha[] = t[i].split(",");
			if(a.containsKey(alpha[1])) {
				String customers = a.get(alpha[1]);
				if(!customers.contains(alpha[0])) {
					customers = customers + ", " + alpha[0];
					a.put(alpha[1], customers);
				}
			}
			else
				a.put(alpha[1], alpha[0]);
		}
		abc = new TreeMap<String, String>(a);		
		keys = abc.keySet();
		fKeys = new String[keys.size()];
		fKeys = keys.toArray(fKeys);
		i = 0;
		while(i < fKeys.length) {
			System.out.println(fKeys[i] + "-" + 
			abc.get(fKeys[i]).split(", ").length);
			++i;
		}
		
		System.out.println();
		a = new Hashtable<String, String>();
		for(i = 0; i < t.length; ++i) {
			String alpha[] = t[i].split(",");
			if(a.containsKey(alpha[0])) {
				int val = Integer.parseInt(a.get(alpha[0])) + Integer.parseInt(alpha[2]);
				a.put(alpha[0], Integer.toString(val));
			}
			else {
				a.put(alpha[0], alpha[2]);
			}
		}
		abc = new TreeMap<String, String>(a);		
		keys = abc.keySet();
		fKeys = new String[keys.size()];
		fKeys = keys.toArray(fKeys);
		i = 0;
		while(i < fKeys.length) {
			System.out.println(fKeys[i] + "-" + abc.get(fKeys[i]));
			++i;
		}
		
		System.out.println();
		a = new Hashtable<String, String>();
		for(i = 0; i < t.length; ++i) {
			String alpha[] = t[i].split(",");
			if(a.containsKey(alpha[0])) {
				String customers = a.get(alpha[0]);
				if(!customers.contains(alpha[1])) {
					customers = customers + ", " + alpha[1];
					a.put(alpha[0], customers);
				}
			}
			else {
				a.put(alpha[0], alpha[1]);
			}
		}
		abc = new TreeMap<String, String>(a);		
		keys = abc.keySet();
		fKeys = new String[keys.size()];
		fKeys = keys.toArray(fKeys);
		i = 0;
		while(i < fKeys.length) {
			System.out.println(fKeys[i] + "-" + abc.get(fKeys[i]).split(", ").length);
			++i;
		}
		
	}
}