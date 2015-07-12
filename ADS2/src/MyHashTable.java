
import java.util.Arrays;
import java.util.Scanner;

class HashTableLinkedList<K, V>{
	
	private K key;
	private V value;
	private HashTableLinkedList<K, V> next;
	
	public HashTableLinkedList() {
		key = null;
		value = null;
		next = null;
	}
	
	public HashTableLinkedList(K key, V value, HashTableLinkedList<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public HashTableLinkedList<K, V> getNext() {
		return next;
	}

	public void setNext(HashTableLinkedList<K, V> next) {
		this.next = next;
	}
}






public class MyHashTable<K, V> {
	
	private HashTableLinkedList<K, V>[] table;
	private int size;
	private int capacity;
	
	@SuppressWarnings("unchecked")
	public MyHashTable(int capcacity) {
		this.capacity = capcacity;
		this.size = 0;
		table = new HashTableLinkedList[this.capacity];
		Arrays.fill(table, new HashTableLinkedList<K, V>());
	}
	
	public int size(){
		return this.size;
	}
	
	public int hashCode(K key){
		int hash = key.hashCode( );
        hash %= capacity;
        if (hash < 0)
            hash += capacity;
        return hash;
	}
	
	public void insert(K key, V value){
		int hash = (hashCode( key ) % capacity);
		//        if (table[hash] == null)
		//            table[hash] = new HashTableLinkedList<K, V>(key, value, null);
		//        else{
		HashTableLinkedList<K, V> entry = table[hash];
		while (entry.getNext() != null && !entry.getKey().equals(key)) {
			entry.setNext(entry.getNext());
		}
		if (entry.getKey().equals(key))
			entry.setValue(value);
		
		//            else
		//                entry.next = new LinkedHashEntry(key, value);
		//        }
		size++;
	}
	
	public V getValue(K key){
		int hash = (hashCode( key ) % capacity);
        assert table[hash] != null : "Not found";
//        else 
//        {
            HashTableLinkedList<K, V> entry = table[hash];
            while (entry != null && !entry.getKey().equals(key))
                entry = entry.getNext();
            assert entry != null : "Not found";
//            else
                return entry.getValue();
//        }
	}
	
	public void remove(K key){
		int hash = (hashCode( key ) % capacity);
        if (table[hash] != null) 
        {
            HashTableLinkedList<K, V> prevEntry = null;
            HashTableLinkedList<K, V> entry = table[hash];
            while (entry.getNext() != null && !entry.getKey().equals(key)) 
            {
                prevEntry = entry;
                entry = entry.getNext();
            }
            if (entry.getKey().equals(key)) 
            {
                if (prevEntry == null)
                    table[hash] = entry.getNext();
                else
                    prevEntry.setNext(entry.getNext());
                size--;
            }
        }
	}
	
	public void display() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < table.length; ++i) {
			sb.append(i);
			sb.append("\t");
			if(table[i] != null) {
				HashTableLinkedList<K, V> entry = table[i];
				while(entry.getNext() != null) {
					sb.append(entry.getValue());
					entry = entry.getNext();
				}
			}
			sb.append("\n");
		}
	}
	
	public static void main(String[] args) {
		MyHashTable<Integer, Integer> hash = new MyHashTable<Integer, Integer>(10);
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String str[] = s.split(",");
		for(int i = 0; i < str.length; i++) {
			int val = Integer.parseInt(str[i]);
			hash.insert(val%10, val);
		}
		hash.display();
		sc.close();
	}
}