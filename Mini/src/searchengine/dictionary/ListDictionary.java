package searchengine.dictionary;

import java.io.Serializable;
import java.util.ArrayList;

public class ListDictionary implements DictionaryInterface,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -630706658073954011L;
	private ListNode start;
	private ListNode end ;
	private int size=0;
	ArrayList<String> keys=new ArrayList<String>();

	private class ListNode {
		/**
		 * 
		 */
		
		private String key;
		private Object value;
		private ListNode next;

		public ListNode(String key, Object value, ListNode next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	public void insert(String key, Object value) {
		// TODO Auto-generated method stub
		ListNode nptr = new ListNode(key, value, null);    
		System.out.println("Insert: "+key);
		//if((!nptr.key.equals("null")) && nptr.value!=null){
		if(start == null) {
			start = nptr;
			end = start;
			size++ ; 
			keys.add(key);
		}
		else if(contains(nptr.key)){
//			remove(key);
			System.out.println("key remove: "+key);
//
//			end.next=nptr;
//			end=nptr;
//			size++ ; 
			ListNode current = start;
			while(current != null) {
				if(current.key.equals(key)){
					current=nptr;
				}
				current=current.next;
			}
			//nptr.value=value;
			
		}
		else{
			System.out.println("key entered: "+key);
			end.next = nptr;
			end = nptr;
			size++ ;
			keys.add(key);
		}
		//}
	}

	public Object getValue(String key) {
		ListNode ptr = start;
		if(key.equals(start.key))
			return start.value;
		
		ptr = start.next;
		int i=size();
		while (i>0){
			if(ptr.key.equals(key)){
				return ptr.value;
			}
			ptr = ptr.next;
			i--;
		}
		return null;
	}

	public void remove(String key) {
		if (start.key.equals(key)) {
		    start = start.next;
		    size--;
		} else {
		    ListNode temp = start;
		    while (temp.next != null) {
		        if (temp.next.key.equals(key)) {
		            temp.next = temp.next.next;
		            size--;
		            break;
		        } else {
		            temp = temp.next;
		        }
		    }
		}
	}

	public String[] getKeys() {
		String[] mkeys = new String[keys.size()];
//		int i=size(), j = 0;
//		ListNode temp = start;
//		System.out.println(i+"   siii");
//		while (i>0 && !temp.key.equals("null") ){
//			keys[j] = temp.key;
//			temp = temp.next;
//			j++;
//			i--;
//			//size--;
//		}
		for(int i=0;i<keys.size();i++){
			mkeys[i]=keys.get(i);
		}
		return mkeys;
	}

	public boolean contains(String key) {
//		ListNode current = start;
//		  while (current != null) {
//		    if (current.key.equals(key)) {
//		      return true;
//		    } else {
//		      current = current.next;
//		    }
//		  }
//		  return false;
		if(keys.contains(key)){
			return true;
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ListNode current = start;
		while(current != null) {
			sb.append("key = " + current.key + "\t");
			sb.append("value = " + current.value + "\n");
			current = current.next;
		}
		return sb.toString();
	}
}