package searchengine.dictionary;

import java.io.Serializable;
import java.util.ArrayList;

public class MyHashDictionary implements DictionaryInterface,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 892662214911753053L;
	private final int capacity = 1000;
	int size;
	private HashTableNode[] table;
	ArrayList<String> keys=new ArrayList<String>();
	private class HashTableNode{
		String key;
		Object value;
		HashTableNode next;

		/* Constructor */
		HashTableNode(String key, Object value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}

	public MyHashDictionary() {
		size = 0;
		table = new HashTableNode[capacity];
	}

	public String[] getKeys() {
		String[] mkeys = new String[keys.size()];
		for(int i=0;i<keys.size();i++){
			mkeys[i]=keys.get(i);
		}
		//		int k=0;
		//		for(int i=0; i<capacity; i++){
		//			System.out.println(table[i]+" nnnnnnnnn ");
		//			if(!table[i].key.equals("null")){
		//				keys[k] = table[i].key;
		//				k++;
		////				HashTableNode entry = table[i];
		////				System.out.println("sttttttop");
		////				while (entry.next != null){
		////					System.out.println("sttttttttt");
		////					entry = entry.next;
		////					keys[k] = entry.key;
		////					System.out.println(keys[k]+" array ");
		////					k++;
		////				}
		//			}
		//}
		return mkeys;
	}

	public Object getValue(String key) {
		int hash = (myhash( key ) % capacity);
		if (table[hash] == null)
			return null;
		else {
			HashTableNode entry = table[hash];
			while (entry != null && !entry.key.equals(key))
				entry = entry.next;
			if (entry == null)
				return null;
			else
				return entry.value;
		}
	}

	public void insert(String key, Object value) {
		System.out.println("Insertion Key " + key +"\tHashcode " + myhash(key));
		int hash = (myhash( key ) % capacity);
		if(hash>0){
			if (table[hash] == null){
				table[hash] = new HashTableNode(key, value);
				keys.add(key);
				size++;
			}
			else {
				HashTableNode entry = table[hash];
				while (entry.next != null && !entry.key.equals(key)) {
					entry = entry.next;
					System.out.println("In while key = " + key);
				}
				if (entry.key.equals(key)){
					System.out.println(" In if key.equals: ");
					//					//entry.value = value;
					//					System.out.println(table[hash].value+" bbbbbb");
					table[hash].value=value;
					//					System.out.println(table[hash].value+" aaaaaaaa");


				}
				else{
					entry.next = new HashTableNode(key, value);
					System.out.println("entered else: ");
					size++;
					keys.add(key);
				}

			}
		}

	}

	public void remove(String key) {
		int hash = (myhash( key ) % capacity);
		if (table[hash] != null) {
			HashTableNode prevEntry = null;
			HashTableNode entry = table[hash];
			while (entry.next != null && !entry.key.equals(key)) {
				prevEntry = entry;
				entry = entry.next;
			}
			if (entry.key.equals(key)) {
				if (prevEntry == null)
					table[hash] = entry.next;
				else
					prevEntry.next = entry.next;
				size--;
			}
		}
	}

	private int myhash(String x ){
		int hash=7;
		for (int i=0; i < x.length(); i++)
			hash = hash*31 + x.charAt(i);
		return hash;
	}

	public boolean contains(String key) {
		//		String[] allKeys = getKeys();
		//		for(int i=0;i<allKeys.length;i++){
		//			//if(!allKeys[i].equals("null"))
		//				System.out.println(allKeys[i]);
		//		}
		//		for(String k: allKeys) {
		//			System.out.println(k+" mmmmmmmmmmm ");
		//			if(k.equals(key)){ 
		//				System.out.println("entered");
		//				return true;
		//			}
		//			else if(k.equals("null")){
		//				System.out.println("entered null");
		//			}
		//		}
		//	int hash=(myhash(key)%capacity);
		//	if(hash>0){
		//		//System.out.println(table[hash].key+" "+table[hash].value);
		//		if(table[hash].key.equals(key)){
		//			return true;
		//		}
		//	}
		if(keys.contains(key)){
			return true;
		}

		return false;
	}
}