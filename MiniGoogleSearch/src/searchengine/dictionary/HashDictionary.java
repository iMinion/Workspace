package searchengine.dictionary;

import java.util.*;
public class HashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>{
	int size;
	K key;
	V value;
	public HashDictionary()
	{
		key=null;
		value=null;
		size=0;
	}

	Hashtable<Object,Object> list=new Hashtable<Object,Object>();
	public K[] getKeys() {
		// TODO Auto-generated method stub
		K[] keys;
		keys=(K[])(new Object[size]);
		//keys=list.keySet();
		return keys;
	}

	public V getValue(K str) {
		// TODO Auto-generated method stub
		V value=(V) list.get(str);
		return value; 
	}

	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		if(list.contains(key))
		{
			list.put(key, value);
		}
		else
		{
			list.put(key,value);
		}
		size++;
		
	}

	public void remove(K key) {
		// TODO Auto-generated method stub
		list.remove(key);
		size--;
		
	}

	public boolean contains(K key) {
		// TODO Auto-generated method stub
		if(list.contains(key)){
			return true;
		}
		return false;
	}

}