
import java.util.Random;

class HashTableNode{
    String key;
    String value;
    HashTableNode next;
 
    HashTableNode(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
 
class UniversalHashing1{
    private int capacity;
    private int size; 
    private HashTableNode[] table;
    private int[] randomHashCode;
 
    public UniversalHashing1() {
        size = 0;
        capacity = 257;
        table = new HashTableNode[capacity];
        for (int i = 0; i < capacity; i++)
            table[i] = null;
        Random r = new Random();
        randomHashCode = new int[32];
        for(int i=0; i<randomHashCode.length; i++)
        	randomHashCode[i] = r.nextInt(31)+1;
    } 
    
    public int getSize(){
        return size;
    }

    public void makeEmpty(){
        for (int i = 0; i < capacity; i++)
            table[i] = null;
    }

	public String get(String key) {
        int hash = (myHash( key ) % capacity);
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
	
    public void insert(String key, String value) {
        int hash = (myHash( key ) % capacity);
        if (table[hash] == null)
            table[hash] = new HashTableNode(key, value);
        else {
            HashTableNode entry = table[hash];
            while(entry.next != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry.key.equals(key))
                entry.value = value;
            else
                entry.next = new HashTableNode(key, value);
        }
        size++;
    }
 
    public void remove(String key) {
        int hash = (myHash(key) % capacity);
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

    private int myHash(String k){
        int hashVal = 0;
        for(int i=0; i<k.length(); i++)
        	hashVal+=k.charAt(i)*randomHashCode[i];
        return hashVal;
    }
    
    public void display(){
    	for (int i = 0; i < capacity; i++){
            System.out.print("\nIndex "+i+": ");
            HashTableNode entry = table[i];
            while (entry != null){
                System.out.print(entry.value +" ");
                entry = entry.next;
            }            
        }
    }
}

public class UniversalHashing{
	public static void main(String[] args) {
		UniversalHashing1 test = new UniversalHashing1();
		test.insert("1", "1");
		test.insert("2", "2");
		test.insert("3", "3");
		test.insert("1", "4");
		System.out.println(test.get("1"));
		//test.display();
	}
}