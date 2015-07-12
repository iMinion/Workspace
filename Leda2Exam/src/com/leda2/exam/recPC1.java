package com.leda2.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

class recPC1 {
	public static void main(String[] args) throws FileNotFoundException {
		Hashtable<String, String> recordEntry = new Hashtable<String, String>();
		Scanner sc = new Scanner(new File("/Users/iMinion/Downloads/test_cases_5357acea8bc01/input000.txt"));
		String pro = sc.nextLine();
		String comm[] = pro.split(",");
		String s = "";
		while(sc.hasNext()) {
			String r = sc.nextLine();
			if(r.length() == 3)
				break;
			s = s + r + "\n";
		}
		s = s.substring(0, s.length() - 1);
		String data[] = s.split("\n");
		s = "";
		while(sc.hasNext()) {
			String r = sc.nextLine();
			if(r.length() == 3) 
				break;
			s = s + r + "\n";
		}
		sc.close();
		int k = 0;
		s = s.substring(0, s.length() - 1);
		String[] queries = s.split("\n");
		for(int i = 0; i < queries.length; ++i) {
			String[] query = queries[i].split("; ");
			for(int j = 0; j < comm.length; ++j) {
				if(comm[j].equalsIgnoreCase(query[0])) {
					k = j;
					break;
				}
			}
			for(int d = 0; d < data.length; ++d) {
				String[] values = data[d].split(",");
				String key1 = query[1] + "\t" + "<=50K";
				String key2 = query[1] + "\t" + ">50K";
				if(values[k].equalsIgnoreCase(query[1])) {
					String lable = values[data.length - 1];
					if(lable.equalsIgnoreCase("<=50K")) {
						if(recordEntry.containsKey(key1)) {
							int val = Integer.parseInt(recordEntry.get(key1)) + 1;
							recordEntry.put(key1, Integer.toString(val));
						}
						else
							recordEntry.put(key1, "1");
					}
					else {
						if(recordEntry.containsKey(key2)) {
							int val = Integer.parseInt(recordEntry.get(key1)) + 1;
							recordEntry.put(key1, Integer.toString(val));
						}
						else
							recordEntry.put(key1, "1");
					}
				}
			}
			Map<String, String> abc = new TreeMap<String, String>(recordEntry);
			

			Set<String> keys = abc.keySet();
			String[] fKeys = new String[keys.size()];
			fKeys = keys.toArray(fKeys);
			String op = "";
			for(int m = 0; m < fKeys.length; ++m) {
				String ke = fKeys[m];
				op = op + ke.substring(ke.indexOf('\t')+1) + ":" + abc.get(ke) + ", ";
			}
			op = op.substring(0, op.length() - 2);
		}
	}
}